package cn.zhsit.book.services.impl;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.managers.ShippingAddressManager;
import cn.zhsit.book.models.po.ShippingAddress;
import cn.zhsit.book.models.vo.ShippingAddressEditPcReq;
import cn.zhsit.book.models.vo.ShippingAddressReq;
import cn.zhsit.book.models.vo.ShippingAddressResp;
import cn.zhsit.book.services.ShippingAddressService;
import cn.zhsit.common.enums.DefaultEnum;
import cn.zhsit.common.exceptions.ZhsException;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.ZhsUnique;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    private ShippingAddressManager shippingAddressManager;
    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public boolean createShippingAddress(ShippingAddressReq addressReq, Errors errors) throws ZhsException {
//判断是否超过5个
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());

        long num = shippingAddressManager.countByPersonId(session.getPersonId());
        if (num >= 5) {
            errors.rejectValue("region", "region", "收货地址个数已经到达最大值。");
            return false;
        }
        ShippingAddress ad = new ShippingAddress();
        ad.setIsDefault(DefaultEnum.notDefault.getCode());
        ad.setId(ZhsUnique.unique25());
        ad.setPersonId(session.getPersonId());
        ad.setName(addressReq.getName());
        ad.setMobile(addressReq.getMobile());
        ad.setPostCode(addressReq.getPostCode());
        ad.setRegion(addressReq.getRegion());
        Date current = Calendar.getInstance().getTime();
        ad.setCreateTime(current);
        ad.setModifyTime(current);
        return shippingAddressManager.inert(ad) == 1;
    }

    @Override
    public List<ShippingAddressResp> findShippingAddressListByPersonId(String personId) {
        List<ShippingAddress> list = shippingAddressManager.selectByPersonId(personId);
        List<ShippingAddressResp> respList = new ArrayList<>();
        for (ShippingAddress po : list) {
            ShippingAddressResp resp = new ShippingAddressResp();
            respList.add(resp);
            resp.setId(po.getId());
            resp.setMobile(po.getMobile());
            resp.setName(po.getName());

            resp.setDefaultAddress(DefaultEnum.of(po.getIsDefault()).isBool());
            resp.setRegion(po.getRegion());
            resp.setProvince(po.getProvince());
            resp.setCity(po.getCity());
            resp.setPostCode(po.getPostCode());
            resp.setCounty(po.getCounty());
        }
        return respList;
    }

    @Override
    public boolean setShippingAddressDefaultByPersonId(String personId, String addressId) {
        return shippingAddressManager.setDefault(personId, addressId);
    }

    @Override
    public boolean delShippingAddressWithPersonId(String personId, String addressId) {
        return shippingAddressManager.del(personId, addressId);
    }

    @Override
    public ShippingAddressReq findShippingAddressByPersonIdAddressId(String personId, String addressId) {
        ShippingAddress po = shippingAddressManager.selectByPersonIdAddressId(personId, addressId);
        if (po != null) {
            ShippingAddressReq req = new ShippingAddressReq();
            req.setId(po.getId());
            req.setMobile(po.getMobile());
            req.setName(po.getName());
            req.setPostCode(po.getPostCode());
            req.setRegion(po.getRegion());
            return req;
        }
        return null;
    }

    @Override
    public boolean modifyShippingAddress(ShippingAddressReq addressReq, Errors errors) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ShippingAddress address = new ShippingAddress();
        address.setId(addressReq.getId());
        address.setModifyTime(Calendar.getInstance().getTime());
        address.setPostCode(addressReq.getPostCode());
        address.setName(addressReq.getName());
        address.setMobile(addressReq.getMobile());
        address.setRegion(addressReq.getRegion());
        return shippingAddressManager.update(address, session.getPersonId()) == 1;
    }

    @Override
    public ShippingAddressResp findShippingAddressByPersonId(String personId) {
        List<ShippingAddress> list = shippingAddressManager.selectByPersonId(personId);
        ShippingAddressResp resp = new ShippingAddressResp();
        ShippingAddress pp = null;
        if (list != null) {
            pp = list.get(0);
        }
        for (ShippingAddress po : list) {
            if (DefaultEnum.isDefault.getCode() == po.getIsDefault().byteValue()) {
                pp = po;
                break;
            }
        }
        BeanUtils.copyProperties(pp, resp);
        return resp;
    }

    @Override
    public ShippingAddressEditPcReq findShippingAddressEditByPersonIdAddressId(String personId, String addressId) {
        ShippingAddress po = shippingAddressManager.selectByPersonIdAddressId(personId, addressId);
        if (po != null) {
            ShippingAddressEditPcReq req = new ShippingAddressEditPcReq();
            BeanUtils.copyProperties(po, req);
            return req;
        }
        return null;
    }


    @Override
    public boolean modifyShippingAddressPc(ShippingAddressEditPcReq req, Errors errors) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ShippingAddress address = new ShippingAddress();
        address.setId(req.getId());
        address.setModifyTime(Calendar.getInstance().getTime());
        address.setPostCode(req.getPostCode());
        address.setName(req.getName());
        address.setMobile(req.getMobile());
        address.setRegion(req.getRegion());
        address.setProvince(req.getProvince());
        address.setCity(req.getCity());
        address.setCounty(req.getCounty());
        return shippingAddressManager.update(address, session.getPersonId()) == 1;
    }

    @Override
    public boolean createShippingAddressPc(ShippingAddressEditPcReq req, Errors errors) {
        //判断是否超过5个
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        long num = shippingAddressManager.countByPersonId(session.getPersonId());
        if (num >= 5) {
            errors.rejectValue("postCode", "postCode", "收货地址个数已经到达最大值。");
            return false;
        }
        ShippingAddress ad = new ShippingAddress();
        ad.setIsDefault(DefaultEnum.notDefault.getCode());
        ad.setId(ZhsUnique.unique25());
        ad.setPersonId(session.getPersonId());
        ad.setName(req.getName());
        ad.setMobile(req.getMobile());
        ad.setPostCode(req.getPostCode());
        ad.setRegion(req.getRegion());
        ad.setCity(req.getCity());
        ad.setProvince(req.getProvince());
        ad.setCounty(req.getCounty());
        Date current = Calendar.getInstance().getTime();
        ad.setCreateTime(current);
        ad.setModifyTime(current);
        return shippingAddressManager.inert(ad) == 1;
    }
}
