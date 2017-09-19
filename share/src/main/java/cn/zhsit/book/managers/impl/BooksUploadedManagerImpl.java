package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.BooksUploadedMapper;
import cn.zhsit.book.managers.BooksUploadedManager;
import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.book.models.po.BooksUploadedExample;
import cn.zhsit.generator.daos.ZhsFileGeneralMapper;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneralExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class BooksUploadedManagerImpl implements BooksUploadedManager {
    @Autowired
    private BooksUploadedMapper booksUploadedMapper;
    @Autowired
    private ZhsFileGeneralMapper zhsFileGeneralMapper;


    @Override
    @Transactional
    public boolean insert(BooksUploaded bu, List<ZhsFileGeneral> files) {
        if (files != null) {
            files.forEach(f -> {
                zhsFileGeneralMapper.insert(f);
            });
        }
        booksUploadedMapper.insert(bu);
        return true;
    }

    @Override
    public List<BooksUploaded> selectByPersonId(String personId) {
        BooksUploadedExample query = new BooksUploadedExample();
        query.createCriteria().andPersonIdEqualTo(personId);
        query.setOrderByClause(" create_time desc ");
        return booksUploadedMapper.selectByExample(query);
    }

    @Override
    @Transactional
    public boolean del(String personId, String bookId) {
        BooksUploadedExample delBook = new BooksUploadedExample();
        delBook.createCriteria().andIdEqualTo(bookId).andPersonIdEqualTo(personId);
        int num = booksUploadedMapper.deleteByExample(delBook);

        if (num == 1) {
            ZhsFileGeneralExample delFile = new ZhsFileGeneralExample();
            delFile.createCriteria().andServiceIdEqualTo(bookId);
            zhsFileGeneralMapper.deleteByExample(delFile);
        }
        return num == 1;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public BooksUploaded selectByKey(String bookId) {
        return booksUploadedMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public List<BooksUploaded> select(BooksUploadedExample query) {
        return booksUploadedMapper.selectByExample(query);
    }

    @Override
    public Long count(BooksUploadedExample sql) {
        return booksUploadedMapper.countByExample(sql);
    }

    @Override
    public boolean modifyById(BooksUploaded book, List<ZhsFileGeneral> addFileGeneralList) {
        if (addFileGeneralList != null) {
            addFileGeneralList.forEach(f -> {
                zhsFileGeneralMapper.insert(f);
            });
        }
        return booksUploadedMapper.updateByPrimaryKeySelective(book) == 1;
    }


}
