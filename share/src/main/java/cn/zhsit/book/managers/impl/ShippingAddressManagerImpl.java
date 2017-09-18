package cn.zhsit.book.managers.impl;

import cn.zhsit.book.daos.ShippingAddressMapper;
import cn.zhsit.book.managers.ShippingAddressManager;
import cn.zhsit.book.models.po.ShippingAddress;
import cn.zhsit.book.models.po.ShippingAddressExample;
import cn.zhsit.common.enums.DefaultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class ShippingAddressManagerImpl implements ShippingAddressManager {

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Override
    public int inert(ShippingAddress shippingAddress) {
        return shippingAddressMapper.insert(shippingAddress);
    }

    @Override
    public long countByPersonId(String personId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andPersonIdEqualTo(personId);
        return shippingAddressMapper.countByExample(example);
    }

    @Override
    public List<ShippingAddress> selectByPersonId(String personId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andPersonIdEqualTo(personId);
        example.setOrderByClause(" create_time desc ");
        return shippingAddressMapper.selectByExample(example);
    }


    @Override
    @Transactional
    public boolean setDefault(String personId, String addressId) {
        Date current = Calendar.getInstance().getTime();
        ShippingAddressExample setAllNotDefaultExample = new ShippingAddressExample();
        setAllNotDefaultExample.createCriteria().andPersonIdEqualTo(personId).andIsDefaultEqualTo(DefaultEnum.isDefault.getCode());
        ShippingAddress noDefault = new ShippingAddress();
        noDefault.setModifyTime(current);
        noDefault.setIsDefault(DefaultEnum.notDefault.getCode());

        shippingAddressMapper.updateByExampleSelective(noDefault, setAllNotDefaultExample);

        ShippingAddressExample setDefaultExample = new ShippingAddressExample();
        setDefaultExample.createCriteria().andPersonIdEqualTo(personId).andIdEqualTo(addressId);
        ShippingAddress defaultPo = new ShippingAddress();
        defaultPo.setIsDefault(DefaultEnum.isDefault.getCode());
        defaultPo.setModifyTime(current);
        return shippingAddressMapper.updateByExampleSelective(defaultPo, setDefaultExample) == 1;
    }


    @Override
    public boolean del(String personId, String addressId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andPersonIdEqualTo(personId).andIdEqualTo(addressId);
        return shippingAddressMapper.deleteByExample(example) == 1;
    }

    @Override
    public ShippingAddress selectByPersonIdAddressId(String personId, String addressId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andPersonIdEqualTo(personId).andIdEqualTo(addressId);
        List<ShippingAddress> list=shippingAddressMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int update(ShippingAddress address, String personId) {
        ShippingAddressExample example=new ShippingAddressExample();
        example.createCriteria().andIdEqualTo(address.getId()).andPersonIdEqualTo(personId);
        return shippingAddressMapper.updateByExampleSelective(address,example);
    }
}
