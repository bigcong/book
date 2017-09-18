package cn.zhsit.generator.services.impl;

import cn.zhsit.authority.enums.ReadStatusEnum;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.generator.manager.MessageGeneralManager;
import cn.zhsit.generator.manager.ZhsFileGeneralManager;
import cn.zhsit.generator.models.po.MessageGeneral;
import cn.zhsit.generator.models.po.MessageGeneralExample;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.vo.MessageGeneralResp;
import cn.zhsit.generator.services.MessageGeneralService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darren on 2017/8/12.
 */
@Service
public class MessageGeneralServiceImpl implements MessageGeneralService {
    @Autowired
    private MessageGeneralManager messageGeneralManager;
    @Autowired
    private PersonAuthorityManager personAuthorityManager;
    @Autowired
    private ZhsFileGeneralManager zhsFileGeneralManager;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public List<MessageGeneralResp> findByPerson(String personId) {
        List<MessageGeneralResp> respList = new ArrayList<>();
        List<MessageGeneral> poList = messageGeneralManager.selectByPerson(personId);
        for (MessageGeneral po : poList) {
            MessageGeneralResp resp = new MessageGeneralResp();
            BeanUtils.copyProperties(po, resp);
            respList.add(resp);
            PersonAuthority personAuthority = personAuthorityManager.selectByKey(po.getSenderId());
            resp.setSenderNick(personAuthority.getNickname());
//            ZhsFileGeneral file = zhsFileGeneralManager.selectLastByServiceId(po.getId());
//            if (null != file) {
//                resp.setPath(file.getLocation() + "/" + file.getThumbnail());
//            }
        }
        return respList;
    }

    @Override
    public MessageGeneralResp findByPerson(String personId, String id) {
        MessageGeneralExample sql = new MessageGeneralExample();
        List<MessageGeneral> poList = messageGeneralManager.select(sql);
        if (poList.size() < 1) {
            return null;
        }
        MessageGeneral po = poList.get(0);
        MessageGeneralResp resp = new MessageGeneralResp();
        BeanUtils.copyProperties(po, resp);
        PersonAuthority personAuthority = personAuthorityManager.selectByKey(po.getSenderId());
        resp.setSenderNick(personAuthority.getNickname());
        ZhsFileGeneral file = zhsFileGeneralManager.selectLastByServiceId(po.getId());
        if (null != file) {
            resp.setPath(file.getLocation() + "/" + file.getThumbnail());
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if(session.getPersonId().equals(personId)){
            MessageGeneral p=new MessageGeneral();
            p.setId(po.getId());
            p.setStatus(ReadStatusEnum.Read.getCode());
            int num = messageGeneralManager.updateByKey(p);
        }
        return resp;
    }

    @Override
    public boolean delByPerson(String personId, String msgId) {
        MessageGeneralExample del=new MessageGeneralExample();
        del.createCriteria().andIdEqualTo(msgId).andReceiverIdEqualTo(personId);
        return messageGeneralManager.del(del)>0;
    }
}
