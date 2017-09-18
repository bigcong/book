package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.Feedback;
import cn.zhsit.book.models.po.FeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper {
    long countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    Feedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}