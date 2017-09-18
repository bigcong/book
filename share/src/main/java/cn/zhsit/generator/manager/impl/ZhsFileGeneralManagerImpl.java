package cn.zhsit.generator.manager.impl;

import cn.zhsit.common.utils.page.Page;
import cn.zhsit.generator.daos.ZhsFileGeneralMapper;
import cn.zhsit.generator.manager.ZhsFileGeneralManager;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneralExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Darren on 2017/8/5.
 */
@Component
public class ZhsFileGeneralManagerImpl implements ZhsFileGeneralManager {
    @Autowired
    private ZhsFileGeneralMapper zhsFileGeneralMapper;

    @Override
    public ZhsFileGeneral selectLastByServiceId(String serviceId) {
        ZhsFileGeneralExample query = new ZhsFileGeneralExample();
        query.createCriteria().andServiceIdEqualTo(serviceId);
        query.setOrderByClause(" create_time desc ");
        query.setPage(new Page().setPage(0).setRows(1));
        List<ZhsFileGeneral> list = zhsFileGeneralMapper.selectByExample(query);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ZhsFileGeneral> selectByServiceId(String serviceId) {
        ZhsFileGeneralExample query = new ZhsFileGeneralExample();
        query.createCriteria().andServiceIdEqualTo(serviceId);
        query.setOrderByClause(" create_time desc ");
        return zhsFileGeneralMapper.selectByExample(query);
    }

    @Override
    public ZhsFileGeneral selectByKey(String imgId) {
       return zhsFileGeneralMapper.selectByPrimaryKey(imgId);
    }

    @Override
    public int delByKey(String imgId) {
        return zhsFileGeneralMapper.deleteByPrimaryKey(imgId);
    }

    @Override
    public int delByServiceId(String serviceId) {
        ZhsFileGeneralExample del=new ZhsFileGeneralExample();
        del.createCriteria().andServiceIdEqualTo(serviceId);
        return zhsFileGeneralMapper.deleteByExample(del);
    }
}
