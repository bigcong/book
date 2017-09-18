package cn.zhsit.authority.api.models;

/**
 * Created by Darren on 2017/7/7.
 */
public class ConstantsAuthority {

    public static final class CacheKey{
        //session所在的缓存块名称
        public static final String SessionCacheName = "ZhsSession";
        //人员，角色缓存模块名称，通过key区分：key= #id + 'person'+'list'
        public static final String ZhsPersonRoleAuthorityOrgCacheName = "ZhsPersonRoleAuthorityOrg";

        //图书列表
        public static final String ZhsBookListCacheName = "BookList";
        //普通文件缓存， key = #serviceid
        public static final String FileGeneralCacheName = "FileGeneral";
        //推荐图书热门图书
        public static final String SuggestHotBookCacheName = "SuggestHotBook";

        ///////////////////////////////
        public static final String PersonAuthority = "PersonAuthority";
        public static final String RoleAuthorityList = "RoleAuthorityList";
        //推荐图书
        public static final String SuggestList = "SuggestList";
    }

//    //人员所在缓存块名称
//    public static final String ZhsPersonCacheName = "ZhsPerson";
//    //角色权限缓存块名称
//    public static final String ZhsRoleCacheName = "ZhsRole";
//    //权限缓存块名称
//    public static final String ZhsAuthorityCacheName = "ZhsAuthority";
    //角色组织权限缓存块名称
//    public static final String ZhsRoleAuthorityOrgCacheName = "ZhsRoleAuthorityOrg";
    //
//    public static final String ZhsRoleListCacheName="ZhsRoleListKeyPersonId";
//    public static final String personId="personId";
    public static final String ZhsLoginCookieName = "zhslogincookie";

    // 收藏表唯一索引
    public static final String index_union_collecting_person_book = "index_union_collecting_person_book";


    //缩略图后缀
    public static final String thumbnail_postfix="_suo";
}
