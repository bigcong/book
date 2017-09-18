package cn.zhsit.authority.models.vo;

import cn.zhsit.book.models.po.Authentication;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class OrgAuthReqPc {
    private Authentication tu;

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
//    /**
//     * 地址
//     */
//    @NotBlank(message = "收到书籍地址不可为空")
//    @Length(max=200,message = "收到书籍地址不可超过200字")
//    private String address;

    /**
     * 办公地址
     */
    @NotBlank(message = "单位的办公地址不可为空")
    @Length(max=200,message = "单位的办公地址不可超过200字")
    private String officeAddress;;

    public String getName() {
        return name;
    }

    public OrgAuthReqPc setName(String name) {
        this.name = name;
        return this;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public OrgAuthReqPc setAddress(String address) {
//        this.address = address;
//        return this;
//    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public OrgAuthReqPc setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public OrgAuthReqPc setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
