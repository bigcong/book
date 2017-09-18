package cn.zhsit.book.models.po;

import java.io.Serializable;
import java.util.Date;

/**
* 冯先生 
* 61947666@qq.com 
* 15652963646 
*/
public class Authentication implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 拥有者
     */
    private String personId;

    /**
     * 认证类别:1,个人；2，单位；
     */
    private Byte authType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 办公地址
     */
    private String officeAddress;

    /**
     * 认证状态：1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；
     */
    private Byte authStatus;

    /**
     * 前图片ID
     */
    private String imgFrontId;

    /**
     * 后图片ID
     */
    private String imgBackId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public Byte getAuthType() {
        return authType;
    }

    public void setAuthType(Byte authType) {
        this.authType = authType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
    }

    public String getImgFrontId() {
        return imgFrontId;
    }

    public void setImgFrontId(String imgFrontId) {
        this.imgFrontId = imgFrontId == null ? null : imgFrontId.trim();
    }

    public String getImgBackId() {
        return imgBackId;
    }

    public void setImgBackId(String imgBackId) {
        this.imgBackId = imgBackId == null ? null : imgBackId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", personId=").append(personId);
        sb.append(", authType=").append(authType);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", address=").append(address);
        sb.append(", officeAddress=").append(officeAddress);
        sb.append(", authStatus=").append(authStatus);
        sb.append(", imgFrontId=").append(imgFrontId);
        sb.append(", imgBackId=").append(imgBackId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}