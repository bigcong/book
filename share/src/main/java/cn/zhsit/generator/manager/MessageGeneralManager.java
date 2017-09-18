package cn.zhsit.generator.manager;

import cn.zhsit.generator.models.po.MessageGeneral;
import cn.zhsit.generator.models.po.MessageGeneralExample;

import java.util.List;

/**
 * Created by Darren on 2017/8/12.
 */
public interface MessageGeneralManager {
    List<MessageGeneral> selectByPerson(String personId);

    List<MessageGeneral> select(MessageGeneralExample sql);

    int updateByKey(MessageGeneral p);

    int del(MessageGeneralExample del);
}
