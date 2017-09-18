package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.Collecting;
import cn.zhsit.book.models.po.CollectingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectingMapper {
    long countByExample(CollectingExample example);

    int deleteByExample(CollectingExample example);

    int deleteByPrimaryKey(String id);

    int insert(Collecting record);

    int insertSelective(Collecting record);

    List<Collecting> selectByExample(CollectingExample example);

    Collecting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Collecting record, @Param("example") CollectingExample example);

    int updateByExample(@Param("record") Collecting record, @Param("example") CollectingExample example);

    int updateByPrimaryKeySelective(Collecting record);

    int updateByPrimaryKey(Collecting record);
}