package cn.zhsit.generator.daos;

import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZhsFileAuthorityGeneralMapper {
    long countByExample(ZhsFileAuthorityGeneralExample example);

    int deleteByExample(ZhsFileAuthorityGeneralExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZhsFileAuthorityGeneral record);

    int insertSelective(ZhsFileAuthorityGeneral record);

    List<ZhsFileAuthorityGeneral> selectByExample(ZhsFileAuthorityGeneralExample example);

    ZhsFileAuthorityGeneral selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZhsFileAuthorityGeneral record, @Param("example") ZhsFileAuthorityGeneralExample example);

    int updateByExample(@Param("record") ZhsFileAuthorityGeneral record, @Param("example") ZhsFileAuthorityGeneralExample example);

    int updateByPrimaryKeySelective(ZhsFileAuthorityGeneral record);

    int updateByPrimaryKey(ZhsFileAuthorityGeneral record);
}