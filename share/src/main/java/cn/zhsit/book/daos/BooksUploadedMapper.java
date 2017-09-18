package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.book.models.po.BooksUploadedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BooksUploadedMapper {
    Long countByExample(BooksUploadedExample example);

    int deleteByExample(BooksUploadedExample example);

    int deleteByPrimaryKey(String id);

    int insert(BooksUploaded record);

    int insertSelective(BooksUploaded record);

    List<BooksUploaded> selectByExample(BooksUploadedExample example);

    BooksUploaded selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BooksUploaded record, @Param("example") BooksUploadedExample example);

    int updateByExample(@Param("record") BooksUploaded record, @Param("example") BooksUploadedExample example);

    int updateByPrimaryKeySelective(BooksUploaded record);

    int updateByPrimaryKey(BooksUploaded record);
}