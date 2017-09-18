package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.book.models.po.BooksUploadedExample;
import cn.zhsit.generator.models.po.ZhsFileGeneral;

import java.util.List;


public interface BooksUploadedManager {
    boolean insert(BooksUploaded bu, List<ZhsFileGeneral> fileGeneralList);

    List<BooksUploaded> selectByPersonId(String personId);

    boolean del(String personId, String bookId);

    boolean update();

    BooksUploaded selectByKey(String bookId);

    List<BooksUploaded> select(BooksUploadedExample query);

    Long count(BooksUploadedExample sql);

    boolean modifyById(BooksUploaded book, List<ZhsFileGeneral> addFileGeneralList);
}
