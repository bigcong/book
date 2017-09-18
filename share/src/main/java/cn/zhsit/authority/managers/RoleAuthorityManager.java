package cn.zhsit.authority.managers;

import cn.zhsit.authority.models.po.RoleAuthority;

import java.util.List;


public interface RoleAuthorityManager {
    List<RoleAuthority> selectRoleListByPersonId(String personId);
}
