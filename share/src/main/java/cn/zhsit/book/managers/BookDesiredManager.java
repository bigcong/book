package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.BookDesired;
import cn.zhsit.book.models.po.BookDesiredExample;
import cn.zhsit.generator.models.po.ZhsFileGeneral;

import java.util.List;


public interface BookDesiredManager {
    boolean insert(BookDesired bu, List<ZhsFileGeneral> fileGeneralList);

    List<BookDesired> select(BookDesiredExample querySql);

    BookDesired selectByKey(String id);

    boolean del(BookDesiredExample delSql);
    boolean del(String personId,String  bookDesiredId);

    Long count(BookDesiredExample sql);
}
