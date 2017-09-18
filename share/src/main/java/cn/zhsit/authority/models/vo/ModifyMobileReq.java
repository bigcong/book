package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.NotBlank;


public class ModifyMobileReq {
    @NotBlank(message = "手机号不可为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public ModifyMobileReq setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
