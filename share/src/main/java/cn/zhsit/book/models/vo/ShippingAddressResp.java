package cn.zhsit.book.models.vo;

import org.hibernate.validator.constraints.NotBlank;


public class ShippingAddressResp {
    private String id;
    /**
     * 收件人姓名
     */
    private String name;
    /**
     * 收件人电话
     */
    private String mobile;
    /**
     * 详细地址
     */
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
    private String postCode;
    /**
     * 是默认
     */
    private boolean defaultAddress;

    public String getId() {
        return id;
    }

    public ShippingAddressResp setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShippingAddressResp setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public ShippingAddressResp setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public ShippingAddressResp setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddressResp setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public ShippingAddressResp setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public ShippingAddressResp setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddressResp setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public ShippingAddressResp setCounty(String county) {
        this.county = county;
        return this;
    }
}
