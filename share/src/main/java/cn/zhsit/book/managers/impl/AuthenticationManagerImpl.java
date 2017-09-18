package cn.zhsit.book.managers.impl;

import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.book.daos.AuthenticationMapper;
import cn.zhsit.book.managers.AuthenticationManager;
import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.po.AuthenticationExample;
import cn.zhsit.generator.daos.ZhsFileAuthorityGeneralMapper;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 认证
 */
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
    @Autowired
    private AuthenticationMapper authenticationMapper ;
    @Autowired
    private ZhsFileAuthorityGeneralMapper zhsFileAuthorityGeneralMapper;
    @Autowired
    private PersonAuthorityManager personAuthorityManager;

    @Override
    public Authentication selectByKey(String id) {
        return authenticationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Authentication> select(AuthenticationExample query) {
        return authenticationMapper.selectByExample(query);
    }

    @Override
    @Transactional

    public boolean insert(Authentication au, PersonAuthority personAuthority, ZhsFileAuthorityGeneral fileFront, ZhsFileAuthorityGeneral fileBack) {
        authenticationMapper.insert(au);
        personAuthorityManager.updateByKey(personAuthority);
        if(null!=fileFront) {
            zhsFileAuthorityGeneralMapper.insert(fileFront);
        }
        if(null!=fileBack) {
            zhsFileAuthorityGeneralMapper.insert(fileBack);
        }
        return true;
    }
}
