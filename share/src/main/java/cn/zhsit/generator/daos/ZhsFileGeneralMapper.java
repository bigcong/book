package cn.zhsit.generator.daos;

import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZhsFileGeneralMapper {
    long countByExample(ZhsFileGeneralExample example);

    int deleteByExample(ZhsFileGeneralExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZhsFileGeneral record);

    int insertSelective(ZhsFileGeneral record);

    List<ZhsFileGeneral> selectByExample(ZhsFileGeneralExample example);

    ZhsFileGeneral selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZhsFileGeneral record, @Param("example") ZhsFileGeneralExample example);

    int updateByExample(@Param("record") ZhsFileGeneral record, @Param("example") ZhsFileGeneralExample example);

    int updateByPrimaryKeySelective(ZhsFileGeneral record);

    int updateByPrimaryKey(ZhsFileGeneral record);
}