package cn.zhsit.authority.daos;

import cn.zhsit.authority.models.po.MenuAuthority;
import cn.zhsit.authority.models.po.MenuAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuAuthorityMapper {
    long countByExample(MenuAuthorityExample example);

    int deleteByExample(MenuAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(MenuAuthority record);

    int insertSelective(MenuAuthority record);

    List<MenuAuthority> selectByExample(MenuAuthorityExample example);

    MenuAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MenuAuthority record, @Param("example") MenuAuthorityExample example);

    int updateByExample(@Param("record") MenuAuthority record, @Param("example") MenuAuthorityExample example);

    int updateByPrimaryKeySelective(MenuAuthority record);

    int updateByPrimaryKey(MenuAuthority record);
}