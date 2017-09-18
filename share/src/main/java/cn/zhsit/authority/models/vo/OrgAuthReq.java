package cn.zhsit.authority.models.vo;

import cn.zhsit.book.models.po.Authentication;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class OrgAuthReq {
    private Authentication tu;

    /**
     * 姓名
     */
    @NotBlank(message = "负责人姓名不可为空")
    private String name;


    @NotBlank(message = "单位名称不可为空")
    private String orgName;

    /**
     * 地址
     */
    @NotBlank(message = "收到书籍地址不可为空")
    @Length(max = 200, message = "收到书籍地址不可超过200字")
    private String address;

    /**
     * 办公地址
     */
    @NotBlank(message = "单位的办公地址不可为空")
    @Length(max = 200, message = "单位的办公地址不可超过200字")
    private String officeAddress;
    ;


    public Authentication getTu() {
        return tu;
    }

    public void setTu(Authentication tu) {
        this.tu = tu;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }

    public OrgAuthReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrgAuthReq setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public OrgAuthReq setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
        return this;
    }
}
