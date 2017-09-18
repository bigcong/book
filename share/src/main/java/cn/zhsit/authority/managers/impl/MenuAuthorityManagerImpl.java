package cn.zhsit.authority.managers.impl;

import cn.zhsit.authority.daos.AuthorityAuthorityMapper;
import cn.zhsit.authority.daos.MenuAuthorityMapper;
import cn.zhsit.authority.managers.AuthorityAuthorityManager;
import cn.zhsit.authority.managers.MenuAuthorityManager;
import cn.zhsit.authority.models.po.AuthorityAuthority;
import cn.zhsit.authority.models.po.MenuAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class MenuAuthorityManagerImpl implements MenuAuthorityManager {
    @Autowired
    private MenuAuthorityMapper menuAuthorityMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMenuAuthority(List<MenuAuthority> list) {
        for (MenuAuthority menuAuthority : list) {
            menuAuthorityMapper.insert(menuAuthority);
        }
        return true;
    }
}
