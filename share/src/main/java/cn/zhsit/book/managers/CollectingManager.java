package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.Collecting;
import cn.zhsit.book.models.po.CollectingExample;
import cn.zhsit.common.utils.page.Page;

import java.util.List;


public interface CollectingManager {
    long count(CollectingExample querySql);

    int insert(Collecting collecting);

    List<Collecting> select(CollectingExample querySql);

    List<Collecting> findHotList(Page page);

    int del(CollectingExample sql);
}
