package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.BookDesiredReq;
import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.common.utils.page.Page;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BookDesiredService {
    boolean createDesire(BookDesiredReq req, Errors errors, MultipartFile[] imgFiles)throws Exception;

    List<BookDesiredReq> findByPersonId(String personId);
    List<BookDesiredReq> findByPersonId(String personId,Page page);

    boolean del(String id);

    List<BookResp> findByName(String searchKey,Page page);
    List<BookResp> findByNamePerson(String personId,String searchKey,Page page);
}
