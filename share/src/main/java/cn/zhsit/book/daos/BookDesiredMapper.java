package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.BookDesired;
import cn.zhsit.book.models.po.BookDesiredExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookDesiredMapper {
    long countByExample(BookDesiredExample example);

    int deleteByExample(BookDesiredExample example);

    int deleteByPrimaryKey(String id);

    int insert(BookDesired record);

    int insertSelective(BookDesired record);

    List<BookDesired> selectByExample(BookDesiredExample example);

    BookDesired selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BookDesired record, @Param("example") BookDesiredExample example);

    int updateByExample(@Param("record") BookDesired record, @Param("example") BookDesiredExample example);

    int updateByPrimaryKeySelective(BookDesired record);

    int updateByPrimaryKey(BookDesired record);
}