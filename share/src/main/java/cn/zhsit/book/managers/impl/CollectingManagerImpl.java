package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.CollectingDao;
import cn.zhsit.book.daos.CollectingMapper;
import cn.zhsit.book.managers.CollectingManager;
import cn.zhsit.book.models.po.Collecting;
import cn.zhsit.book.models.po.CollectingExample;
import cn.zhsit.common.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CollectingManagerImpl implements CollectingManager {
    @Autowired
    private CollectingMapper collectingMapper;
    @Autowired
    private CollectingDao collectingDao;

    @Override
    public long count(CollectingExample querySql) {
        return collectingMapper.countByExample(querySql);
    }

    @Override
    public int insert(Collecting collecting) {
        return collectingMapper.insert(collecting);
    }

    @Override
    public List<Collecting> select(CollectingExample querySql) {
        return collectingMapper.selectByExample(querySql);
    }

    @Override
    public List<Collecting> findHotList(Page page) {
        return collectingDao.selectByPage(page);
    }

    @Override
    public int del(CollectingExample sql) {
        return collectingMapper.deleteByExample(sql);
    }
}
