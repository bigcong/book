package cn.zhsit.common.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gjc on 2017/7/8.
 */
public enum ZhsContextHandler {
    instance;

    private static Logger log = LoggerFactory.getLogger(ZhsContextHandler.class);
    private ThreadLocal<String> session = null;


    public void setSessionKey(String sessionKey) {
        if (null == session) {
            session = new ThreadLocal<>();
        }
        session.set(sessionKey);
    }

    public String getSessionKey() {
        if (null == session) {
            return null;
        }
        return session.get();
    }

    public void clear() {
        if (null != session) {
            session.remove();
        }
    }

}
