package cn.zhsit.book.models.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


public class ShippingAddressEditPcReq {
    /**
     * ID
     */
    private String id;

    /**
     * 拥有者
     */
    private String personId;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名不能为空")
    private String name;

    /**
     * 收货人手机
     */
    @NotBlank(message = "收货人收货人手机不能为空")
    private String mobile;

    /**
     * 所在地区
     */
    @NotBlank(message = "所在地区不能为空")
    private String region;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 邮政编码
     */
    @Length(max = 6,message = "邮政编码不能超过6位")
    private String postCode;

    /**
     * 是默认:1 不是；2是；
     */
    private Byte isDefault;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public String getId() {
        return id;
    }

    public ShippingAddressEditPcReq setId(String id) {
        this.id = id;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public ShippingAddressEditPcReq setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShippingAddressEditPcReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public ShippingAddressEditPcReq setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public ShippingAddressEditPcReq setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public ShippingAddressEditPcReq setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddressEditPcReq setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public ShippingAddressEditPcReq setCounty(String county) {
        this.county = county;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddressEditPcReq setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public ShippingAddressEditPcReq setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ShippingAddressEditPcReq setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public ShippingAddressEditPcReq setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
