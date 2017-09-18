package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.NotBlank;


public class ModifyEmailReq {
    @NotBlank(message = "邮箱不可为空")
    private String email;

    public String getEmail() {
        return email;
    }

    public ModifyEmailReq setEmail(String email) {
        this.email = email;
        return this;
    }
}
