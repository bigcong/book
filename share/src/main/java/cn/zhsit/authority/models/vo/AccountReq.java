package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class AccountReq {
    @NotBlank(message = "原密码不能为空！")
    private String oldPwd;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "须在6至20位之间")
    private String newPwd;
    @NotBlank(message = "再次输入新密码不能为空")
    private String repeatNewPwd;

    public String getNewPwd() {
        return newPwd;
    }

    public AccountReq setNewPwd(String newPwd) {
        this.newPwd = newPwd;
        return this;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public AccountReq setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
        return this;
    }

    public String getRepeatNewPwd() {
        return repeatNewPwd;
    }

    public AccountReq setRepeatNewPwd(String repeatNewPwd) {
        this.repeatNewPwd = repeatNewPwd;
        return this;
    }
}
