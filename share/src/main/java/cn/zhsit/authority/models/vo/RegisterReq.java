package cn.zhsit.authority.models.vo;

import cn.zhsit.common.utils.ZhsStringUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class RegisterReq {

    /**
     * 手机
     */
    @Length(min = 11,max = 11,message = "手机号格式不正确")
    private String mobile;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 登录名
     */
    @Length(min = 3,max = 20,message="名称须在3至20位之间")
    private String loginName;
    /**
     * 密码
     */
    @Length(min = 6, max = 20, message = "密码须在6至20位之间")
    private String pwd;
    @Length(min = 6, max = 20, message = "再次输入密码须和密码相同")
    private String repeatPwd;

    public String getMobile() {
        return mobile;
    }

    public RegisterReq setMobile(String mobile) {
        if (ZhsStringUtil.isBlank(mobile)) {
            mobile = null;
        }
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterReq setEmail(String email) {
        if (ZhsStringUtil.isBlank(email)) {
            email = null;
        }
        this.email = email;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public RegisterReq setLoginName(String loginName) {
        if (ZhsStringUtil.isBlank(loginName)) {
            loginName = null;
        }
        this.loginName = loginName;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public RegisterReq setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public String getRepeatPwd() {
        return repeatPwd;
    }

    public RegisterReq setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
        return this;
    }
}
