package cn.zhsit.common.utils;

import cn.zhsit.authority.api.models.ConstantsAuthority;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


public class ZhsImgUtil {
    public static final int topSize = 150 * 1024;

    public static void compress(long size, String inFilePathName) throws IOException {
        String postfix = FilenameUtils.getExtension(inFilePathName);
        String inFilePathNameExcludePostfix = StringUtils.substringBeforeLast(inFilePathName, "." + postfix);
        String outFilePathName = inFilePathNameExcludePostfix + ConstantsAuthority.thumbnail_postfix + "." + postfix;
        if (size < topSize) {
            Thumbnails.of(inFilePathName).scale(1f).outputFormat(postfix).toFile(outFilePathName);
        } else {
            Thumbnails.of(inFilePathName).scale(1f).outputQuality(topSize / size).outputFormat(postfix).toFile(outFilePathName);
        }
    }


}
