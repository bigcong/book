package cn.zhsit.authority.helpers;

import cn.zhsit.authority.api.ApiServiceAuthority;
import cn.zhsit.authority.api.models.ConstantsAuthority;
import cn.zhsit.authority.api.models.Person;
import cn.zhsit.authority.api.models.Wrapper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Component
public class CacheHelper {
    private static Logger log = LoggerFactory.getLogger(CacheHelper.class);


    @Cacheable(value = ConstantsAuthority.CacheKey.SessionCacheName, key = "#sessionKey", unless = "#result == null")
    public ZhsSession getSession(String sessionKey) {
        return new ZhsSession(sessionKey);
    }


}
