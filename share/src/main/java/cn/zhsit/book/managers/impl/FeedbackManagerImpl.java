package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.FeedbackMapper;
import cn.zhsit.book.managers.FeedbackManager;
import cn.zhsit.book.models.po.Feedback;
import cn.zhsit.generator.daos.ZhsFileGeneralMapper;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class FeedbackManagerImpl implements FeedbackManager {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private ZhsFileGeneralMapper zhsFileGeneralMapper;

    @Override
    @Transactional
    public int insert(Feedback fb, List<ZhsFileGeneral> files) {
        if (files != null) {
            files.forEach(f -> {
                zhsFileGeneralMapper.insert(f);
            });
        }
        return feedbackMapper.insert(fb);
    }

    @Override
    public Feedback selectByKey(String id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }


}
