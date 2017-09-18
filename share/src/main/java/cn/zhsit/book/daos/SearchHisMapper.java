package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.SearchHis;
import cn.zhsit.book.models.po.SearchHisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchHisMapper {
    long countByExample(SearchHisExample example);

    int deleteByExample(SearchHisExample example);

    int deleteByPrimaryKey(String id);

    int insert(SearchHis record);

    int insertSelective(SearchHis record);

    List<SearchHis> selectByExample(SearchHisExample example);

    SearchHis selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SearchHis record, @Param("example") SearchHisExample example);

    int updateByExample(@Param("record") SearchHis record, @Param("example") SearchHisExample example);

    int updateByPrimaryKeySelective(SearchHis record);

    int updateByPrimaryKey(SearchHis record);
}