package cn.zhsit.common.helpers;

import cn.zhsit.authority.api.models.ConstantsAuthority;
import cn.zhsit.common.enums.FileType;
import cn.zhsit.common.utils.ZhsFileUtils;
import cn.zhsit.common.utils.ZhsImgUtil;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

/**
 * Created by gjc on 2017/8/3.
 */
public class ZhsFileHelper {

    /**
     * 添加有安全要求的文件
     *
     * @param f
     * @param type
     * @param mf
     * @param orderNum
     * @return
     * @throws IOException
     */
    public static boolean addFile(String store, ZhsFileAuthorityGeneral f, FileType type, MultipartFile mf, long orderNum) throws IOException {
        String postfix = ZhsFileUtils.filePostfix(mf.getOriginalFilename());
        f.setId(ZhsUnique.unique25());
        f.setPostfix(postfix);
        f.setFileSize(mf.getSize());
        f.setOrderNum(orderNum);
        String location = ZhsFileUtils.location(type);
        String name=ZhsUnique.unique25();
        String fileName =name + postfix;
        String thumbnail=name+ ConstantsAuthority.thumbnail_postfix+postfix;
        f.setName(fileName);
        f.setThumbnail(thumbnail);
        f.setLocation(location);
        ZhsFileUtils.saveFile(store, location, fileName, mf.getBytes());
        ZhsImgUtil.compress(mf.getSize(), store + "/" + location + "/" + fileName);
        return true;
    }


    public static boolean addFile(String store, ZhsFileGeneral f, FileType type, MultipartFile mf, long orderNum) throws IOException {
        String postfix = ZhsFileUtils.filePostfix(mf.getOriginalFilename());
        f.setId(ZhsUnique.unique25());
        f.setPostfix(postfix);
        f.setFileSize(mf.getSize());
        f.setOrderNum(orderNum);
        String location = ZhsFileUtils.location(type);
        String name=ZhsUnique.unique25();
        String fileName =name + postfix;
        String thumbnail=name+ConstantsAuthority.thumbnail_postfix+postfix;
        f.setName(fileName);
        f.setThumbnail(thumbnail);
        f.setLocation(location);
        ZhsFileUtils.saveFile(store, location, fileName, mf.getBytes());
        ZhsImgUtil.compress(mf.getSize(), store + "/" + location + "/" + fileName);
        return true;
    }

}
