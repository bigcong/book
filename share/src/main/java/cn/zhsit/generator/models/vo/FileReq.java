package cn.zhsit.generator.models.vo;

import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.springframework.beans.BeanUtils;

/**
 * Created by Darren on 2017/8/1.
 */
public class FileReq extends ZhsFileGeneral {
    /**
     * 文件存储路径
     */
    private String path;

    /**
     * 文件
     */
    private byte[] file;

    public FileReq of(ZhsFileGeneral file) {
        BeanUtils.copyProperties(file,this);
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileReq setPath(String path) {
        this.path = path;
        return this;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
