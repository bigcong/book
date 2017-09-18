package cn.zhsit.generator.services;

import cn.zhsit.generator.models.vo.MessageGeneralResp;

import java.util.List;

/**
 * Created by Darren on 2017/8/12.
 */
public interface MessageGeneralService {
    List<MessageGeneralResp> findByPerson(String personId);

    MessageGeneralResp findByPerson(String personId, String id);

    boolean delByPerson(String personId, String msgId);
}
