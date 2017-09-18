package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.BooksHot;
import cn.zhsit.book.models.po.BooksHotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BooksHotMapper {
    long countByExample(BooksHotExample example);

    int deleteByExample(BooksHotExample example);

    int deleteByPrimaryKey(String id);

    int insert(BooksHot record);

    int insertSelective(BooksHot record);

    List<BooksHot> selectByExample(BooksHotExample example);

    int updateByExampleSelective(@Param("record") BooksHot record, @Param("example") BooksHotExample example);

    int updateByExample(@Param("record") BooksHot record, @Param("example") BooksHotExample example);
}