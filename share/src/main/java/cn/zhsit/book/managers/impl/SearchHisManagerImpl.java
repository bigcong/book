package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.SearchHisMapper;
import cn.zhsit.book.managers.SearchHisManager;
import cn.zhsit.book.models.po.SearchHis;
import cn.zhsit.book.models.po.SearchHisExample;
import cn.zhsit.common.utils.ZhsUnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class SearchHisManagerImpl implements SearchHisManager {
    @Autowired
    private SearchHisMapper searchHisMapper;

    @Override
    public List<SearchHis> selectByPerson(String personId) {
        SearchHisExample sql=new SearchHisExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        sql.setOrderByClause("create_time desc");
        return searchHisMapper.selectByExample(sql);
    }

    @Override
    public int delByPerson(String personId) {
        SearchHisExample sql=new SearchHisExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        return searchHisMapper.deleteByExample(sql);
    }

    @Override
    @Transactional
    public int insert(String personId, String searchKey, List<SearchHis> delPoList) {
        if (delPoList != null) {
            for (SearchHis po : delPoList) {
                searchHisMapper.deleteByPrimaryKey(po.getId());
            }
        }
        SearchHis s=new SearchHis();
        s.setId(ZhsUnique.unique25());
        s.setPersonId(personId);
        s.setSearchKey(searchKey);
        Date current = Calendar.getInstance().getTime();
        s.setCreateTime(current);
        s.setModifyTime(current);
        return searchHisMapper.insert(s);
    }

    @Override
    public List<SearchHis> findByPersonKey(String personId, String searchKey) {
        SearchHisExample sql=new SearchHisExample();
        sql.createCriteria().andPersonIdEqualTo(personId).andSearchKeyEqualTo(searchKey);
        return searchHisMapper.selectByExample(sql);
    }
}
