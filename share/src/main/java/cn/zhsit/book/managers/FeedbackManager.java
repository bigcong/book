package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.Feedback;
import cn.zhsit.generator.models.po.ZhsFileGeneral;

import java.util.List;


public interface FeedbackManager {
    int insert(Feedback fb, List<ZhsFileGeneral> files);

    Feedback selectByKey(String id);
}
