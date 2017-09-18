package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.SearchHisResp;

import java.util.List;


public interface SearchHisService {
    List<SearchHisResp> findHisByPerson(String personId);

    boolean delSearchHis(String personId);

    void createSearchHis(String personId, String searchKey);
}
