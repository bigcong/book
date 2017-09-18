package cn.zhsit.generator.services;

import cn.zhsit.generator.models.vo.FileAuthorityReq;
import cn.zhsit.generator.models.vo.FileReq;

/**
 * Created by Darren on 2017/8/5.
 */
public interface ZhsFileGeneralService {
    FileReq getFile(FileReq f);

    FileAuthorityReq getAuthFile(FileAuthorityReq f);
}
