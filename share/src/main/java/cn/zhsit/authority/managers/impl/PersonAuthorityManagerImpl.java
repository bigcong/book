package cn.zhsit.authority.managers.impl;

import cn.zhsit.authority.api.models.ConstantsAuthority;
import cn.zhsit.authority.daos.PersonAuthorityMapper;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.authority.models.po.PersonAuthorityExample;
import cn.zhsit.generator.daos.ZhsFileAuthorityGeneralMapper;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneralExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PersonAuthorityManagerImpl implements PersonAuthorityManager {
    @Autowired
    private PersonAuthorityMapper personAuthorityMapper;
    @Autowired
    private ZhsFileAuthorityGeneralMapper zhsFileAuthorityGeneralMapper;


    @Override
    @Cacheable(value = ConstantsAuthority.CacheKey.ZhsPersonRoleAuthorityOrgCacheName, key = "#personId+'_" + ConstantsAuthority.CacheKey.PersonAuthority + "'", unless = "#result == null")
    public PersonAuthority selectByKey(String personId) {
        return personAuthorityMapper.selectByPrimaryKey(personId);
    }

    @Override
    @CacheEvict(value = ConstantsAuthority.CacheKey.ZhsPersonRoleAuthorityOrgCacheName
            , key = "#personId+'_" + ConstantsAuthority.CacheKey.PersonAuthority + "'"
            , beforeInvocation = true
    )
    public boolean removeCache(String personId) {
        return true;
    }

    @Override
    public PersonAuthority selectByLoginName(String loginName) {
        PersonAuthorityExample q = new PersonAuthorityExample();
        q.createCriteria().andLoginNameEqualTo(loginName);
        List<PersonAuthority> list = personAuthorityMapper.selectByExample(q);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public PersonAuthority selectByMobile(String mobile) {
        PersonAuthorityExample q = new PersonAuthorityExample();
        q.createCriteria().andMobileEqualTo(mobile);
        List<PersonAuthority> list = personAuthorityMapper.selectByExample(q);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public PersonAuthority selectByEmail(String email) {
        PersonAuthorityExample q = new PersonAuthorityExample();
        q.createCriteria().andEmailEqualTo(email);
        List<PersonAuthority> list = personAuthorityMapper.selectByExample(q);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int insert(PersonAuthority person) {
        return personAuthorityMapper.insert(person);
    }

    @Override
    @CacheEvict(value = ConstantsAuthority.CacheKey.ZhsPersonRoleAuthorityOrgCacheName
            , key = "#personAuthority.id+'_" + ConstantsAuthority.CacheKey.PersonAuthority + "'"
    )
//            ,allEntries=true
//            ,beforeInvocation=true
    public int updateByKey(PersonAuthority personAuthority) {
        return personAuthorityMapper.updateByPrimaryKeySelective(personAuthority);
    }

    @Override
    @CacheEvict(value = ConstantsAuthority.CacheKey.ZhsPersonRoleAuthorityOrgCacheName
            , key = "#personAuthority.id+'_" + ConstantsAuthority.CacheKey.PersonAuthority + "'"
    )
    @Transactional
    public int updateByKey(PersonAuthority personAuthority, List<ZhsFileAuthorityGeneral> addFileList) {
        if (addFileList != null && addFileList.size() > 0) {
            ZhsFileAuthorityGeneralExample delFile = new ZhsFileAuthorityGeneralExample();
            delFile.createCriteria().andServiceIdEqualTo(personAuthority.getId());
            zhsFileAuthorityGeneralMapper.deleteByExample(delFile);
        }
        for (ZhsFileAuthorityGeneral f : addFileList) {
            zhsFileAuthorityGeneralMapper.insert(f);
        }
        return personAuthorityMapper.updateByPrimaryKeySelective(personAuthority);
    }
}
