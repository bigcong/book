package cn.zhsit.authority.managers;

import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;

import java.util.List;



public interface PersonAuthorityManager {


    PersonAuthority selectByKey(String personId);

    boolean removeCache(String personId);
    /**
     * 根据登录名查询
     * @param loginName
     * @return
     */
    PersonAuthority selectByLoginName(String loginName);

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    PersonAuthority selectByMobile(String mobile);

    /**
     * 根据email查询
     * @param email
     * @return
     */
    PersonAuthority selectByEmail(String email);

    /**
     * 插入信息
     * @param person
     * @return
     */
    public int insert(PersonAuthority person);

    int updateByKey( PersonAuthority personAuthority);

    int updateByKey(PersonAuthority po, List<ZhsFileAuthorityGeneral> addFileList);
}
