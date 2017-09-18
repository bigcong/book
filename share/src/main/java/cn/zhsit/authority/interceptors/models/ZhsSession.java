package cn.zhsit.authority.interceptors.models;

import cn.zhsit.common.enums.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ZhsSession {
    private static Logger log = LoggerFactory.getLogger(ZhsSession.class);

    private String sessionKey;


    private DeviceType deviceType;

    private LoginStatus loginStatus;

    private long checkLoggedOKLastTime = 0L;


    private String personId;


    public ZhsSession() {
    }

    public ZhsSession(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public ZhsSession setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public ZhsSession setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }


    public ZhsSession setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
        if (LoginStatus.Logged == loginStatus) {
            this.checkLoggedOKLastTime = System.currentTimeMillis();
        } else {
            this.checkLoggedOKLastTime = 0L;
            this.personId=null;
        }
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public ZhsSession setPersonId(String personId) {
        this.personId = personId;
        return this;
    }


    public LoginStatus checkLoginStatus(int expirySecond) {
        long cha = System.currentTimeMillis() - checkLoggedOKLastTime;
        if (LoginStatus.Logged == loginStatus && cha < expirySecond*1000) {
            setLoginStatus(LoginStatus.Logged);
            checkLoggedOKLastTime=System.currentTimeMillis();
            return loginStatus;
        } else {
            setLoginStatus(LoginStatus.NoLogin);
            checkLoggedOKLastTime=0L;
            return loginStatus;
        }
    }

    public enum LoginStatus {
        NoLogin, Logged;
    }
}
