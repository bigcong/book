package cn.zhsit.common.controllers;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.DeviceType;
import cn.zhsit.common.handlers.ZhsContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
    private static Logger log = LoggerFactory.getLogger(ErrorController.class);
//    public static final String pc = "pc";
//    public static final String app = "app";
    @Autowired
    private ZhsConfig zhsConfig;

    @Autowired
    private CacheHelper cacheHelper;

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405"));
            }
        };
    }

    @RequestMapping("/404")
    public String page404() {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getDeviceType() == DeviceType.Mobile) {
            return zhsConfig.getMobile404Html();
        } else {
            return zhsConfig.getPc404Html();
        }
    }
    @RequestMapping("/405")
    public String page405(Exception ex) {
        log.error("405",ex);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getDeviceType() == DeviceType.Mobile) {
            return zhsConfig.getMobile405Html();
        } else {
            return zhsConfig.getPc405Html();
        }
    }

    @RequestMapping("/500")
    public String page500(Exception ex) {
        log.error("500",ex);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getDeviceType() == DeviceType.Mobile) {
            return zhsConfig.getMobile500Html();
        } else {
            return zhsConfig.getPc500Html();
        }
    }

}
