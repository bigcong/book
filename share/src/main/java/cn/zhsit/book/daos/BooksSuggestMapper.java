package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.BooksSuggest;
import cn.zhsit.book.models.po.BooksSuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BooksSuggestMapper {
    long countByExample(BooksSuggestExample example);

    int deleteByExample(BooksSuggestExample example);

    int deleteByPrimaryKey(String id);

    int insert(BooksSuggest record);

    int insertSelective(BooksSuggest record);

    List<BooksSuggest> selectByExample(BooksSuggestExample example);

    int updateByExampleSelective(@Param("record") BooksSuggest record, @Param("example") BooksSuggestExample example);

    int updateByExample(@Param("record") BooksSuggest record, @Param("example") BooksSuggestExample example);
}