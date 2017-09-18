package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


public class PersonAuthReq {


    /**
     * 姓名
     */
    @NotBlank(message = "姓名不可为空")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不可为空")
    private String mobile;

    /**
     * 地址
     */
    @NotBlank(message = "地址不可为空")
    @Length(max=200,message = "地址不可超过200字")
    private String address;

    public String getName() {
        return name;
    }

    public PersonAuthReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public PersonAuthReq setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PersonAuthReq setAddress(String address) {
        this.address = address;
        return this;
    }
}
