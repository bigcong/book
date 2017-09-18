package cn.zhsit.generator.manager;

import cn.zhsit.generator.models.po.ZhsFileGeneral;

import java.util.List;

/**
 * Created by Darren on 2017/8/5.
 */
public interface ZhsFileGeneralManager {
    ZhsFileGeneral selectLastByServiceId(String serviceId);
    List<ZhsFileGeneral> selectByServiceId(String serviceId);

    ZhsFileGeneral selectByKey(String imgId);

    int delByKey(String imgId);

    int delByServiceId(String serviceId);
}
