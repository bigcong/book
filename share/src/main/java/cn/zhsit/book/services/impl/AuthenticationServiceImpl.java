package cn.zhsit.book.services.impl;

import cn.zhsit.authority.enums.AuthStatusEnum;
import cn.zhsit.authority.enums.AuthTypeEnum;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.authority.models.vo.OrgAuthReq;
import cn.zhsit.authority.models.vo.OrgAuthReqPc;
import cn.zhsit.authority.models.vo.PersonAuthReq;
import cn.zhsit.book.managers.AuthenticationManager;
import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.po.AuthenticationExample;
import cn.zhsit.book.services.AuthenticationService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.FileType;
import cn.zhsit.common.enums.ServiceNameEnum;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.helpers.ZhsFileHelper;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.common.utils.page.Page;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PersonAuthorityManager personAuthorityManager;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ZhsConfig zhsConfig;

    @Override
    public Authentication find(AuthTypeEnum type, String personId) {
        AuthenticationExample query = new AuthenticationExample();
        query.createCriteria().andPersonIdEqualTo(personId).andAuthTypeEqualTo(type.getCode());
        query.setOrderByClause(" create_time desc ");
        List<Authentication> list = authenticationManager.select(query);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Authentication find(String personId) {
        AuthenticationExample query = new AuthenticationExample();
        query.createCriteria().andPersonIdEqualTo(personId);
        query.setPage(new Page().setRows(1));
        query.setOrderByClause(" create_time desc ");
        List<Authentication> list = authenticationManager.select(query);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean createPersonAuth(PersonAuthReq authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
        String auId = ZhsUnique.unique25();

        ZhsFileAuthorityGeneral fileFront = null;
        if (imgFront.getBytes() == null || imgFront.getBytes().length < 1) {
        } else {
            fileFront = new ZhsFileAuthorityGeneral();
            fileFront.setServiceId(auId);
            fileFront.setServiceName(ServiceNameEnum.Authentication.getService());
            fileFront.setCreateTime(current);
            fileFront.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileFront, FileType.Authentication, imgFront, 1);
        }

        ZhsFileAuthorityGeneral fileBack = null;
        if (imgBack.getBytes() == null || imgBack.getBytes().length < 1) {
        } else {
            fileBack = new ZhsFileAuthorityGeneral();
            fileBack.setServiceId(auId);
            fileBack.setServiceName(ServiceNameEnum.Authentication.getService());
            fileBack.setCreateTime(current);
            fileBack.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileBack, FileType.Authentication, imgBack, 2);
        }

        Authentication au = new Authentication();
        au.setId(auId);
        au.setPersonId(session.getPersonId());
        au.setAuthType(AuthTypeEnum.Person.getCode());
        au.setName(authReq.getName());
        au.setMobile(authReq.getMobile());
        au.setAddress(authReq.getAddress());
        au.setAuthStatus(AuthStatusEnum.Authing.getCode());
//        au.setContainFile(new Byte("2"));
        au.setCreateTime(current);
        au.setModifyTime(current);
        if (fileFront != null) {
            au.setImgFrontId(fileFront.getId());
        }
        if (fileBack != null) {
            au.setImgBackId(fileBack.getId());
        }

        PersonAuthority personAuthority = new PersonAuthority();
        personAuthority.setId(session.getPersonId());
        personAuthority.setAuthStatus(AuthStatusEnum.Authing.getCode());
        personAuthority.setModifyTime(current);
        return authenticationManager.insert(au, personAuthority, fileFront, fileBack);
    }

    @Override
    public boolean createOrgAuth(OrgAuthReq authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
        String auId = ZhsUnique.unique25();

        ZhsFileAuthorityGeneral fileFront = null;
        if (imgFront.getBytes() == null || imgFront.getBytes().length < 1) {
        } else {
            fileFront = new ZhsFileAuthorityGeneral();
            fileFront.setServiceId(auId);
            fileFront.setServiceName(ServiceNameEnum.Authentication.getService());
            fileFront.setCreateTime(current);
            fileFront.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileFront, FileType.Authentication, imgFront, 1);
        }

        ZhsFileAuthorityGeneral fileBack = null;
        if (imgBack.getBytes() == null || imgBack.getBytes().length < 1) {
        } else {
            fileBack = new ZhsFileAuthorityGeneral();
            fileBack.setServiceId(auId);
            fileBack.setServiceName(ServiceNameEnum.Authentication.getService());
            fileBack.setCreateTime(current);
            fileBack.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileBack, FileType.Authentication, imgBack, 2);
        }

        Authentication au = new Authentication();
        au.setId(auId);
        au.setPersonId(session.getPersonId());
        au.setAuthType(AuthTypeEnum.Org.getCode());

        if (authReq.getOrgName() != null) {
            au.setName(authReq.getOrgName() + "-" + authReq.getName());
        } else {
            au.setName(authReq.getName());
        }
        au.setAddress(authReq.getAddress());
        au.setOfficeAddress(authReq.getOfficeAddress());
        au.setAuthStatus(AuthStatusEnum.Authing.getCode());
        au.setCreateTime(current);
        au.setModifyTime(current);

        if (fileFront != null) {
            au.setImgFrontId(fileFront.getId());
        }
        if (fileBack != null) {
            au.setImgBackId(fileBack.getId());
        }

        PersonAuthority personAuthority = new PersonAuthority();
        personAuthority.setId(session.getPersonId());
        personAuthority.setAuthStatus(AuthStatusEnum.Authing.getCode());
        personAuthority.setModifyTime(current);

        return authenticationManager.insert(au, personAuthority, fileFront, fileBack);
    }

    @Override
    public boolean createOrgAuthPc(OrgAuthReqPc authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
        String auId = ZhsUnique.unique25();
        ZhsFileAuthorityGeneral fileFront = null;
        if (imgFront.getBytes() == null || imgFront.getBytes().length < 1) {
        } else {
            fileFront = new ZhsFileAuthorityGeneral();
            fileFront.setServiceId(auId);
            fileFront.setServiceName(ServiceNameEnum.Authentication.getService());
            fileFront.setCreateTime(current);
            fileFront.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileFront, FileType.Authentication, imgFront, 1);
        }
        ZhsFileAuthorityGeneral fileBack = null;
        if (imgBack.getBytes() == null || imgBack.getBytes().length < 1) {
        } else {
            fileBack = new ZhsFileAuthorityGeneral();
            fileBack.setServiceId(auId);
            fileBack.setServiceName(ServiceNameEnum.Authentication.getService());
            fileBack.setCreateTime(current);
            fileBack.setModifyTime(current);
            ZhsFileHelper.addFile(zhsConfig.getStore(), fileBack, FileType.Authentication, imgBack, 2);
        }

        Authentication au = new Authentication();
        au.setId(auId);
        au.setPersonId(session.getPersonId());
        au.setAuthType(AuthTypeEnum.Org.getCode());
        au.setName(authReq.getName());
        au.setMobile(authReq.getMobile());
        au.setOfficeAddress(authReq.getOfficeAddress());
        au.setAuthStatus(AuthStatusEnum.Authing.getCode());
        au.setCreateTime(current);
        au.setModifyTime(current);
        if (null != fileFront) {
            au.setImgFrontId(fileFront.getId());
        }

        if (null != fileBack) {
            au.setImgBackId(fileBack.getId());
        }

        PersonAuthority personAuthority = new PersonAuthority();
        personAuthority.setId(session.getPersonId());
        personAuthority.setAuthStatus(AuthStatusEnum.Authing.getCode());
        personAuthority.setModifyTime(current);

        return authenticationManager.insert(au, personAuthority, fileFront, fileBack);
    }
}
