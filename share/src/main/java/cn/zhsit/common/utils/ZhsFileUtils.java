package cn.zhsit.common.utils;

import cn.zhsit.common.enums.FileType;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.ClosedChannelException;
import java.util.Calendar;
import java.util.StringJoiner;


public class ZhsFileUtils {
    private static Logger log = LoggerFactory.getLogger(ZhsFileUtils.class);

    public static void writeFile(HttpServletRequest request, HttpServletResponse response, byte[] bytes, String fileName) throws Exception {
        OutputStream outputStream = null;
        try {
            response.reset();
            response.setContentType("application/x-download");
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            String fileDisplay = transCharacter(userAgent, fileName);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileDisplay);
            // 输出文件流
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            if (e instanceof IOException) {
                return;
            }
            log.error("writeFile时异常,{}", e);
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                response.getOutputStream().close();
            } catch (Exception e) {}
        }
    }


    private static String transCharacter(String userAgent, String fileName) throws Exception {
        if (userAgent != null && userAgent.indexOf("firefox") > 0) {
            return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        } else {

            return URLEncoder.encode(fileName, "UTF-8");
        }
    }

    public static boolean saveFile(String base, String path, String name, byte[] data) {
        boolean success = false;
        FileOutputStream fos = null;
        try {
            if (base == null) {
                base = "zhsstore";
            }
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            String allPath = base + "/" + path;
            String fileIncludeName=allPath+"/"+name;
            File file = new File(fileIncludeName);
            if (!file.exists()) {
                File f = new File(allPath);
                if(!f.exists()){
                    f.mkdirs();
                }
                f.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            success = true;
        } catch (Exception e) {
            log.error("写文件时异常,{}", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("文件流关闭失败");
                }
            }
        }
        return success;
    }

    /**
     * 船检文件夹
     *
     * @param base
     * @param path
     */
    private static void createFolder(String base, String path) {
        base = "zhsfolder";
        String allPath = base + path;
        File f = new File(allPath);
        f.mkdirs();
    }

    /**
     * 根据文件全名获取我文件后缀
     *
     * @param fileName
     * @return 含有".",例 ".png"
     */
    public static String filePostfix(String fileName) {
        if (!fileName.contains(".")) {
            return null;
        }
        int lastIndexOfPoint = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOfPoint);
    }

    /**
     * 根据文件类型和年月划分的文件路径
     *
     * @param ft
     * @return
     */
    public static String location(FileType ft) {
        int year = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        StringJoiner joiner = new StringJoiner("/");
        return joiner.add(ft.getType())
                .add(year + "")
                .add(month + "")
//                .add(date + "")
                .toString()
                ;

    }



    public static byte[] readFile(String fileIncludePath) {
        try {
            File file = new File(fileIncludePath);
            if (!file.exists()) {
                return null;
            }
            return FileUtils.readFileToByteArray(file);
        } catch (Exception ex) {
            log.error("读取文件:"+fileIncludePath+"失败。");
        }
        return null;
    }
}
