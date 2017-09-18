package cn.zhsit.authority.models.vo;

import java.util.Date;


public class UserResp {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 头像图片路径
     */
    private String headPic;


    /**
     * 电话
     */
    private String tel;

    /**
     * 手机
     */
    private String mobile;
    /**
     * 手机号中间加*号
     */
    private String mobileStar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 认证状态:1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；
     */
    private Byte authStatus;
    /**
     * 认证状态文字描述
     */
    private String authStatusStr;

    public String getMobileStar() {
        return mobileStar;
    }

    public UserResp setMobileStar(String mobileStar) {
        this.mobileStar = mobileStar;
        return this;
    }

    public String getAuthStatusStr() {
        return authStatusStr;
    }

    public UserResp setAuthStatusStr(String authStatusStr) {
        this.authStatusStr = authStatusStr;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public UserResp setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserResp setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getHeadPic() {
        return headPic;
    }

    public UserResp setHeadPic(String headPic) {
        this.headPic = headPic;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public UserResp setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserResp setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public UserResp setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResp setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserResp setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserResp setId(String id) {
        this.id = id;
        return this;
    }
}
