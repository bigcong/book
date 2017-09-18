package cn.zhsit.book.managers;

import cn.zhsit.book.models.po.ShippingAddress;

import java.util.List;


public interface ShippingAddressManager {

    public int inert(ShippingAddress shippingAddress);

    public long countByPersonId(String personId);

    List<ShippingAddress> selectByPersonId(String personId);

    boolean setDefault(String personId, String addressId);

    boolean del(String personId, String addressId);

    ShippingAddress selectByPersonIdAddressId(String personId, String addressId);

    int update(ShippingAddress address, String personId);
}
