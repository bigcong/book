package cn.zhsit.book.models.vo;

import org.hibernate.validator.constraints.NotBlank;


public class ShippingAddressReq {
    private String id;
    /**
     * 收货人姓名
     */
    @NotBlank(message = "收件人姓名不能为空")
    private String name;

    @NotBlank(message = "收件人电话不能为空")
    private String mobile;

    @NotBlank(message = "详细地址不能为空")
    private String region;
    /**
     * 邮政编码
     */
    private String postCode;

    public String getId() {
        return id;
    }

    public ShippingAddressReq setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShippingAddressReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public ShippingAddressReq setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public ShippingAddressReq setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddressReq setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

}
