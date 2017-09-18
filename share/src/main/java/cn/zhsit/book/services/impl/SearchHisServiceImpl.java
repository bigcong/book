package cn.zhsit.book.services.impl;

import cn.zhsit.book.managers.SearchHisManager;
import cn.zhsit.book.models.po.SearchHis;
import cn.zhsit.book.models.vo.SearchHisResp;
import cn.zhsit.book.services.SearchHisService;
import cn.zhsit.common.utils.ZhsStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchHisServiceImpl implements SearchHisService {
    @Autowired
    private SearchHisManager searchHisManager;

    @Override
    public List<SearchHisResp> findHisByPerson(String personId) {
        List<SearchHisResp> respList = new ArrayList<>();
        List<SearchHis> poList = searchHisManager.selectByPerson(personId);
        for (SearchHis po : poList) {
            SearchHisResp resp=new SearchHisResp();
            respList.add(resp);
            BeanUtils.copyProperties(po,resp);
        }
        return respList;
    }

    @Override
    public boolean delSearchHis(String personId) {
        int num= searchHisManager.delByPerson(personId);
        return true;
    }

    @Override
    public void createSearchHis(String personId, String searchKey) {
        if (ZhsStringUtil.isBlank(searchKey)) {
            return;
        }
        searchKey= StringUtils.deleteWhitespace(searchKey).trim();
        int endIndexExclude=5;
        if (searchKey.length() <= 5) {
            endIndexExclude=searchKey.length();
        }
        searchKey =searchKey.substring(0, endIndexExclude);
        List<SearchHis> poList=searchHisManager.findByPersonKey(personId, searchKey);

        int num=searchHisManager.insert(personId,searchKey,poList);

    }
}
