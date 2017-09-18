package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.po.AuthenticationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthenticationMapper {
    long countByExample(AuthenticationExample example);

    int deleteByExample(AuthenticationExample example);

    int deleteByPrimaryKey(String id);

    int insert(Authentication record);

    int insertSelective(Authentication record);

    List<Authentication> selectByExample(AuthenticationExample example);

    Authentication selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Authentication record, @Param("example") AuthenticationExample example);

    int updateByExample(@Param("record") Authentication record, @Param("example") AuthenticationExample example);

    int updateByPrimaryKeySelective(Authentication record);

    int updateByPrimaryKey(Authentication record);
}