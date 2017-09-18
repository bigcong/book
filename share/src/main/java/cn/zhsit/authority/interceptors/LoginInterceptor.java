package cn.zhsit.authority.interceptors;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.Msg;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.vo.UserResp;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.DeviceType;
import cn.zhsit.common.handlers.ZhsContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private PersonAuthorityManager personAuthorityManager;
    @Autowired
    private ZhsConfig zhsConfig;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ApplicationContext context;

    Map<String, Object> beans = null;


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ZhsSession.LoginStatus loginStatus = session.checkLoginStatus(zhsConfig.getExpirySecond());
        if (loginStatus == ZhsSession.LoginStatus.Logged) {
            return true;
        }
        if (null == beans) {
            beans = context.getBeansWithAnnotation(Login.class);
        }
        HandlerMethod method = (HandlerMethod) handler;
        Object clazzBean = method.getBean();
        boolean containThisBean = beans.containsValue(clazzBean);
        if (!containThisBean && !method.hasMethodAnnotation(Login.class)) {
            return true;
        }

        goLogin(req, resp);
        return false;
    }

    //去登录
    private void goLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginUrl = null;
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getDeviceType() == DeviceType.Mobile) {
            if (session.getPersonId() == null) {
                loginUrl = zhsConfig.getMobileLoginUrl();
            } else {
                loginUrl = zhsConfig.getMobileLoginUrl();
            }
        } else {
            if (session.getPersonId() == null) {
                loginUrl = zhsConfig.getPcLoginUrl();
            } else {
                loginUrl = zhsConfig.getPcLoginUrl();
            }
        }


        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
            Msg msg = new Msg();
            msg.ajaxGo();
            msg.addVal("zhsGoUrl", request.getContextPath() + loginUrl);
            msg.addVal("msg", "请登录");
            response.getWriter().print(msg.toJson());
            return;
        }
        response.sendRedirect(request.getContextPath() + loginUrl);
    }

    @Autowired
    private PersonAuthorityService personAuthorityService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
        if(model==null){return;}
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ZhsSession.LoginStatus loginStatus = session.checkLoginStatus(zhsConfig.getExpirySecond());
        if (loginStatus == ZhsSession.LoginStatus.Logged) {
            UserResp userResp = personAuthorityService.findCurrentUser();
            if (userResp.getNickname() == null) {
                userResp.setNickname("");
            }
            model.addObject("user", userResp);
        }else {
            UserResp userResp = new   UserResp();
            userResp.setNickname("");
            model.addObject("user", userResp);
        }
    }
}
