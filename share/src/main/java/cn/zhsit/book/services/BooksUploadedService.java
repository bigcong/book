package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.book.models.vo.MyBooksAddReq;
import cn.zhsit.book.models.vo.MyBooksListReq;
import cn.zhsit.common.utils.page.Page;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BooksUploadedService {
    boolean createBook(MyBooksAddReq myBooksAddReq, Errors errors,MultipartFile[] imgFiles)throws Exception ;

    List<MyBooksListReq> findByPersonId(String personId);
    List<MyBooksListReq> findByPersonId(String personId,Page page);

    boolean delBookWithPersonId(String personId, String bookId);

    boolean modifyBook(MyBooksAddReq booksAddReq, Errors errors, MultipartFile[] imgFiles)throws Exception ;

    MyBooksAddReq findById(String bookId);

    List<MyBooksListReq> findSuggestList(int pageSize);

    boolean delImg(String imgId);

    List<BookResp> findByName(String searchKey,Page page);
}
