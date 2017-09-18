package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.BookDesiredMapper;
import cn.zhsit.book.managers.BookDesiredManager;
import cn.zhsit.book.models.po.BookDesired;
import cn.zhsit.book.models.po.BookDesiredExample;
import cn.zhsit.generator.daos.ZhsFileGeneralMapper;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneralExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class BookDesiredManagerImpl implements BookDesiredManager {

    @Autowired
    private BookDesiredMapper bookDesiredMapper;
    @Autowired
    private ZhsFileGeneralMapper zhsFileGeneralMapper;

    @Override
    public boolean insert(BookDesired bu, List<ZhsFileGeneral> files) {
        if (files != null) {
            files.forEach(f -> {
                zhsFileGeneralMapper.insert(f);
            });
        }
        bookDesiredMapper.insert(bu);
        return true;
    }

    @Override
    public List<BookDesired> select(BookDesiredExample querySql) {
        return bookDesiredMapper.selectByExample(querySql);
    }

    @Override
    public BookDesired selectByKey(String id) {
        return bookDesiredMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean del(BookDesiredExample delSql) {
         bookDesiredMapper.deleteByExample(delSql);
        return true;
    }

    @Override
    @Transactional
    public boolean del(String personId,String bookDesiredId) {
        BookDesiredExample delSql = new BookDesiredExample();
        delSql.createCriteria().andIdEqualTo(bookDesiredId).andPersonIdEqualTo(personId);
       int num= bookDesiredMapper.deleteByExample(delSql);
        if(num==1) {
            ZhsFileGeneralExample delFileSql = new ZhsFileGeneralExample();
            delFileSql.createCriteria().andServiceIdEqualTo(bookDesiredId);
            zhsFileGeneralMapper.deleteByExample(delFileSql);
        }
        return 1==num;
    }

    @Override
    public Long count(BookDesiredExample sql) {
       return bookDesiredMapper.countByExample(sql);
    }
}
