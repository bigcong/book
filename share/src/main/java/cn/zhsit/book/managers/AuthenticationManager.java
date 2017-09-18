package cn.zhsit.book.managers;

import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.po.AuthenticationExample;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;

import java.util.List;


public interface AuthenticationManager {

    Authentication selectByKey(String id);

    List<Authentication> select(AuthenticationExample query);

    /**
     * 添加认证信息
     * @param au
     * @param personAuthority
     * @param fileFront
     *@param fileBack @return
     */
    boolean insert(Authentication au, PersonAuthority personAuthority, ZhsFileAuthorityGeneral fileFront, ZhsFileAuthorityGeneral fileBack);
}
