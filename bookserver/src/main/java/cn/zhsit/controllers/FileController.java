package cn.zhsit.controllers;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.common.utils.ZhsFileUtils;
import cn.zhsit.generator.models.vo.FileAuthorityReq;
import cn.zhsit.generator.models.vo.FileReq;
import cn.zhsit.generator.services.ZhsFileGeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("file")
@Login
public class FileController {
    private static Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private ZhsFileGeneralService zhsFileGeneralService;

    @RequestMapping(value = "/getfile", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public void getFile(FileReq f, HttpServletRequest request, HttpServletResponse response) {
        try {
            FileReq file = zhsFileGeneralService.getFile(f);
            if (null != file) {
                byte[] data = file.getFile();
                String fileName = "zhsit.cn";
                ZhsFileUtils.writeFile(request, response, data, fileName);
            } else {
                log.error("未获取到文件");
            }
        } catch (Exception ex) {
            log.error("获取文件时异常:", ex);
        }
    }


    @RequestMapping(value = "/getauthfile", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public void getAuthFile(FileAuthorityReq f, HttpServletRequest request, HttpServletResponse response) {
        try {
            FileAuthorityReq file = zhsFileGeneralService.getAuthFile(f);
            if (null != file) {
                byte[] data = file.getFile();
                String fileName = file.getName();
                ZhsFileUtils.writeFile(request, response, data, fileName);
            }
        } catch (Exception ex) {
            log.error("获取数据权限文件时异常:", ex);
        }
    }


}
