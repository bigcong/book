package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.ShippingAddressEditPcReq;
import cn.zhsit.book.models.vo.ShippingAddressReq;
import cn.zhsit.book.models.vo.ShippingAddressResp;
import cn.zhsit.common.exceptions.ZhsException;
import org.springframework.validation.Errors;

import java.util.List;


public interface ShippingAddressService {
    boolean createShippingAddress(ShippingAddressReq addressReq, Errors errors) throws ZhsException;

    /**
     * 查询当前用户的收货地址列表
     * @param personId
     * @return
     */
    List<ShippingAddressResp> findShippingAddressListByPersonId(String personId);


    boolean setShippingAddressDefaultByPersonId(String personId, String addressId);

    boolean delShippingAddressWithPersonId(String personId, String addressId);

    ShippingAddressReq findShippingAddressByPersonIdAddressId(String personId, String addressId);

    boolean modifyShippingAddress(ShippingAddressReq addressReq, Errors errors);

    ShippingAddressResp findShippingAddressByPersonId(String personId);

    ShippingAddressEditPcReq findShippingAddressEditByPersonIdAddressId(String personId, String addressId);

    boolean modifyShippingAddressPc(ShippingAddressEditPcReq address, Errors errors);

    boolean createShippingAddressPc(ShippingAddressEditPcReq address, Errors errors);
}
