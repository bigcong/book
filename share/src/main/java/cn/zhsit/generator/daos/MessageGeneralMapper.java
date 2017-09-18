package cn.zhsit.generator.daos;

import cn.zhsit.generator.models.po.MessageGeneral;
import cn.zhsit.generator.models.po.MessageGeneralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageGeneralMapper {
    long countByExample(MessageGeneralExample example);

    int deleteByExample(MessageGeneralExample example);

    int deleteByPrimaryKey(String id);

    int insert(MessageGeneral record);

    int insertSelective(MessageGeneral record);

    List<MessageGeneral> selectByExample(MessageGeneralExample example);

    MessageGeneral selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MessageGeneral record, @Param("example") MessageGeneralExample example);

    int updateByExample(@Param("record") MessageGeneral record, @Param("example") MessageGeneralExample example);

    int updateByPrimaryKeySelective(MessageGeneral record);

    int updateByPrimaryKey(MessageGeneral record);
}