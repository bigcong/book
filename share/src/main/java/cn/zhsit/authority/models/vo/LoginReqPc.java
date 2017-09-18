package cn.zhsit.authority.models.vo;

import cn.zhsit.common.utils.ZhsStringUtil;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class LoginReqPc {

    /**
     * 手机
     */
    @NotBlank(message = "手机号/用户名/邮箱 不能为空")
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空，须在6至20位之间")
    private String pwd;

    public String getName() {
        return name;
    }

    public LoginReqPc setName(String name) {
        this.name = name;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public LoginReqPc setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }
}
