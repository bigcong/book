package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.SearchHis;

import java.util.List;


public interface SearchHisManager {
    List<SearchHis> selectByPerson(String personId);

    int delByPerson(String personId);

    int insert(String personId, String searchKey, List<SearchHis> delPoList);

    List<SearchHis> findByPersonKey(String personId,String searchKey);

}
