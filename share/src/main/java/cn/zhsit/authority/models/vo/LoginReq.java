package cn.zhsit.authority.models.vo;

import cn.zhsit.common.utils.ZhsStringUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class LoginReq {

    /**
     * 手机
     */
    @NotBlank(message = "账号/手机号不能为空")
    private String mobile;

    /**
     * 密码
     */
    @Length(min = 6, max = 20, message = "密码不能为空，须在6至20位之间")
    @NotBlank(message = "密码不能为空，须在6至20位之间")
    private String pwd;


    public String getMobile() {
        return mobile;
    }

    public LoginReq setMobile(String mobile) {
        if (ZhsStringUtil.isBlank(mobile)) {
            mobile = null;
        }
        this.mobile = mobile;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public LoginReq setPwd(String pwd) {
        if(ZhsStringUtil.isBlank(pwd)){
            pwd=null;
        }
        this.pwd = pwd;
        return this;
    }

}
