package cn.zhsit.generator.manager.impl;

import cn.zhsit.generator.daos.MessageGeneralMapper;
import cn.zhsit.generator.manager.MessageGeneralManager;
import cn.zhsit.generator.models.po.MessageGeneral;
import cn.zhsit.generator.models.po.MessageGeneralExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Darren on 2017/8/12.
 */
@Component
public class MessageGeneralManagerImpl implements MessageGeneralManager {
    @Autowired
    private MessageGeneralMapper messageGeneralMapper;

    @Override
    public List<MessageGeneral> selectByPerson(String personId) {
        MessageGeneralExample sql = new MessageGeneralExample();
        sql.createCriteria().andReceiverIdEqualTo(personId);
        sql.setOrderByClause(" create_time desc ");
        return messageGeneralMapper.selectByExample(sql);
    }

    @Override
    public List<MessageGeneral> select(MessageGeneralExample sql) {
        return messageGeneralMapper.selectByExample(sql);
    }

    @Override
    public int updateByKey(MessageGeneral p) {
        return messageGeneralMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public int del(MessageGeneralExample del) {
        return messageGeneralMapper.deleteByExample(del);
    }
}
