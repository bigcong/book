package cn.zhsit.common.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix="zhsit")
public class ZhsConfig {
    //文件存储路径
    private String store;
    //超时时间（秒）
    private int expirySecond=1800;
    //pc登录地址
    private String pcLoginUrl;
    //移动端登录地址
    private String mobileLoginUrl;
    //pc设备404时跳转页面（原模版页面）
    private String pc404Html;
    //移动设备404时跳转页面（原模版页面）
    private String mobile404Html;
    //pc设备405时跳转页面（原模版页面）
    private String pc405Html;
    //移动设备404时跳转页面（原模版页面）
    private String mobile405Html;
    //pc设备500时跳转页面（原模版页面）
    private String pc500Html;
    //移动设备500时跳转页面（原模版页面）
    private String mobile500Html;
    //无权限pc端跳转地址
    private String noAuthorityPcUrl;
    //无权限移动端跳转地址
    private String noAuthorityMobileUrl;
    //不受登录拦截的url列表
    private List<String> loginExcludeUrl;


    public String getStore() {
        return store;
    }

    public ZhsConfig setStore(String store) {
        this.store = store;
        return this;
    }

    public int getExpirySecond() {
        return expirySecond;
    }

    public ZhsConfig setExpirySecond(int expirySecond) {
        this.expirySecond = expirySecond;
        return this;
    }

    public String getPcLoginUrl() {
        return pcLoginUrl;
    }

    public ZhsConfig setPcLoginUrl(String pcLoginUrl) {
        this.pcLoginUrl = pcLoginUrl;
        return this;
    }

    public String getMobileLoginUrl() {
        return mobileLoginUrl;
    }

    public ZhsConfig setMobileLoginUrl(String mobileLoginUrl) {
        this.mobileLoginUrl = mobileLoginUrl;
        return this;
    }

    public String getNoAuthorityPcUrl() {
        return noAuthorityPcUrl;
    }

    public ZhsConfig setNoAuthorityPcUrl(String noAuthorityPcUrl) {
        this.noAuthorityPcUrl = noAuthorityPcUrl;
        return this;
    }

    public String getNoAuthorityMobileUrl() {
        return noAuthorityMobileUrl;
    }

    public ZhsConfig setNoAuthorityMobileUrl(String noAuthorityMobileUrl) {
        this.noAuthorityMobileUrl = noAuthorityMobileUrl;
        return this;
    }

    public List<String> getLoginExcludeUrl() {
        return loginExcludeUrl;
    }

    public ZhsConfig setLoginExcludeUrl(List<String> loginExcludeUrl) {
        this.loginExcludeUrl = loginExcludeUrl;
        return this;
    }

    public String getPc404Html() {
        return pc404Html;
    }

    public ZhsConfig setPc404Html(String pc404Html) {
        this.pc404Html = pc404Html;
        return this;
    }

    public String getMobile404Html() {
        return mobile404Html;
    }

    public ZhsConfig setMobile404Html(String mobile404Html) {
        this.mobile404Html = mobile404Html;
        return this;
    }

    public String getPc500Html() {
        return pc500Html;
    }

    public ZhsConfig setPc500Html(String pc500Html) {
        this.pc500Html = pc500Html;
        return this;
    }

    public String getMobile500Html() {
        return mobile500Html;
    }

    public ZhsConfig setMobile500Html(String mobile500Html) {
        this.mobile500Html = mobile500Html;
        return this;
    }

    public String getPc405Html() {
        return pc405Html;
    }

    public ZhsConfig setPc405Html(String pc405Html) {
        this.pc405Html = pc405Html;
        return this;
    }

    public String getMobile405Html() {
        return mobile405Html;
    }

    public ZhsConfig setMobile405Html(String mobile405Html) {
        this.mobile405Html = mobile405Html;
        return this;
    }
}
