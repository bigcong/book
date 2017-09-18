package cn.zhsit.generator.services.impl;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.book.managers.AuthenticationManager;
import cn.zhsit.book.managers.FeedbackManager;
import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.po.Feedback;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.ServiceNameEnum;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.ZhsFileUtils;
import cn.zhsit.common.utils.ZhsStringUtil;
import cn.zhsit.generator.daos.ZhsFileAuthorityGeneralMapper;
import cn.zhsit.generator.daos.ZhsFileGeneralMapper;
import cn.zhsit.generator.manager.ZhsFileGeneralManager;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.vo.FileAuthorityReq;
import cn.zhsit.generator.models.vo.FileReq;
import cn.zhsit.generator.services.ZhsFileGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

/**
 * Created by Darren on 2017/8/5.
 */
@Service
public class ZhsFileGeneralServiceImpl implements ZhsFileGeneralService {
    @Autowired
    private ZhsFileAuthorityGeneralMapper zhsFileAuthorityGeneralMapper;
    @Autowired
    private ZhsConfig zhsConfig;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private FeedbackManager feedbackManager;
    @Autowired
    private PersonAuthorityManager personAuthorityManager;
    @Autowired
    private ZhsFileGeneralManager zhsFileGeneralManager;
    @Autowired
    private ZhsFileGeneralMapper zhsFileGeneralMapper;
    @Override
    public FileReq getFile(FileReq f) {
        if(f.getPath()==null){
            return null;
        }
        String storeFile=zhsConfig.getStore()+"/"+f.getPath();
        f.setFile(ZhsFileUtils.readFile(storeFile));
        return f;
    }

    @Override
    public FileAuthorityReq getAuthFile(FileAuthorityReq f) {
        boolean haveAuth = false;
        ZhsFileAuthorityGeneral file = zhsFileAuthorityGeneralMapper.selectByPrimaryKey(f.getId());
        ServiceNameEnum sn = ServiceNameEnum.of(file.getServiceName());
        switch (sn) {
            case Authentication:
                haveAuth = checkAuhenticationAuth(file);
                break;
            case  Feedback:
                haveAuth = checkFeedbackAuth(file);
                break;
            case PersonAuthority:
                haveAuth = checkHeadPicAuth(file);
                break;
            default:
                haveAuth=false;
        }
        if (!haveAuth) {
            f.setFile("noAuth".getBytes());
            return f;
        }

        f.of(file);
        StringJoiner joiner = new StringJoiner("/");
        String storeFile =null;
        if(sn==ServiceNameEnum.PersonAuthority){
            storeFile = joiner.add(zhsConfig.getStore()).add(file.getLocation()).add(file.getThumbnail()).toString();
        }else {
              storeFile = joiner.add(zhsConfig.getStore()).add(file.getLocation()).add(file.getName()).toString();
        }
        f.setFile(ZhsFileUtils.readFile(storeFile));
        return f;
    }

    private boolean checkAuhenticationAuth(ZhsFileAuthorityGeneral file) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication a = authenticationManager.selectByKey(file.getServiceId());
        return session.getPersonId().equals(a.getPersonId());
    }

    private boolean checkFeedbackAuth(ZhsFileAuthorityGeneral file) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Feedback a =feedbackManager.selectByKey(file.getServiceId());
        return session.getPersonId().equals(a.getPersonId());
    }

    private boolean checkHeadPicAuth(ZhsFileAuthorityGeneral file) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority a =personAuthorityManager.selectByKey(file.getServiceId());
        return session.getPersonId().equals(a.getId());
    }
}
