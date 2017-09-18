package cn.zhsit.authority.controllers;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.Msg;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.models.vo.RegisterReq;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.common.handlers.ZhsContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("pa")
public class PersonAuthorityController {
    private static Logger log = LoggerFactory.getLogger(PersonAuthorityController.class);
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private PersonAuthorityService personAuthorityService;

    @RequestMapping(value = "v1/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpServletResponse resp, @RequestBody RegisterReq person) throws Exception {
        Msg msg = new Msg().fail();
        boolean success = personAuthorityService.checkPwd(person, msg);
        log.info("登录验证信息person:{}", person.toString());
        if (success) {
            msg.success();
            msg.addVal(Msg.msg, "登录成功");
        }
        return msg.toJson();
    }

    @RequestMapping(value = "v1/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey())
                .setLoginStatus(ZhsSession.LoginStatus.NoLogin);
        return "退出登录状态";
    }


}
