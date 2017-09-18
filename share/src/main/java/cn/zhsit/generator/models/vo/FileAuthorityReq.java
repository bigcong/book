package cn.zhsit.generator.models.vo;

import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import org.springframework.beans.BeanUtils;

/**
 * Created by Darren on 2017/8/1.
 */
public class FileAuthorityReq extends ZhsFileAuthorityGeneral {

    /**
     * 文件
     */
    private byte[] file;

    public FileAuthorityReq of(ZhsFileAuthorityGeneral file) {
        BeanUtils.copyProperties(file,this);
        return this;
    }


    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
