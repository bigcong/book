package cn.zhsit.controllers.pc;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.enums.AuthStatusEnum;
import cn.zhsit.authority.enums.AuthTypeEnum;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.models.vo.*;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.book.models.po.Authentication;
import cn.zhsit.book.models.vo.*;
import cn.zhsit.book.services.*;
import cn.zhsit.common.exceptions.ZhsException;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.page.Page;
import cn.zhsit.controllers.app.AppUserController;
import cn.zhsit.generator.models.vo.MessageGeneralResp;
import cn.zhsit.generator.services.MessageGeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("p/user")
@Login
public class PcUserController {
    private static Logger log = LoggerFactory.getLogger(AppUserController.class);
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private PersonAuthorityService personAuthorityService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BooksUploadedService booksUploadedService;
    @Autowired
    private BookDesiredService bookDesiredService;
    @Autowired
    private MessageGeneralService messageGeneralService;
    @Autowired
    private CollectingService collectingService;

    @InitBinder
    public void initBinderDateTime(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 跳往我的页面
     *
     * @return
     */
    @RequestMapping("/tomine")
    public String toMine(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        model.addAttribute("user", userResp);
        return "pc/我的";
    }


    /**
     * 跳往登录页面
     *
     * @return
     */
    @RequestMapping("/tologin")
    public String toLogin(@ModelAttribute("login") LoginReqPc loginReq) {
        return "pc/登陆";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginReqPc loginReq, Errors errors) throws ZhsException {
        if (errors.hasErrors()) {
            return "pc/登陆";
        }
        boolean success = personAuthorityService.login(loginReq, errors);
        if (success) {
            return "redirect:/p/index";
        }
        return "pc/登陆";
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        boolean success = personAuthorityService.logout();
        return "redirect:/p/index0";
    }

    /**
     * 跳往注册页面
     *
     * @return
     */
    @RequestMapping("/toregister")
    public String toRegister(@ModelAttribute("person") RegisterReq person, Model model) {
        return "pc/注册";
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(@Valid @ModelAttribute("person") RegisterReq person, Errors errors, Model model) throws ZhsException {
        if (errors.hasErrors()) {
            return "pc/注册";
        }
        boolean success = personAuthorityService.createPerson(person, errors);
        if (!success) {
            return "pc/注册";
        }
        return "redirect:/p/user/tologin";
    }


    /**
     * 跳往个人信息管理页面
     *
     * @return
     */
    @RequestMapping("/toinfomanage")
    public String toInfoManage(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        UserResp user = personAuthorityService.findCurrentUser();
        model.addAttribute("user", user);
        Authentication auth = authenticationService.find(session.getPersonId());
        if (auth == null) {
            return "pc/个人信息管理(未认证)";
        }
        if (AuthTypeEnum.Person.getCode() == auth.getAuthType()) {
            if (AuthStatusEnum.Authing.getCode() == user.getAuthStatus()) {
                return "pc/个人信息管理(认证中)";
            } else if (AuthStatusEnum.AuthOK.getCode() == user.getAuthStatus()) {
                return "pc/个人信息管理-已认证";
            }
        } else {
            if (AuthStatusEnum.Authing.getCode() == user.getAuthStatus()) {
                return "pc/个人信息管理(认证中)";
            } else if (AuthStatusEnum.AuthOK.getCode() == user.getAuthStatus()) {
                return "pc/个人信息管理-已认证";
            }
        }

        return "个人信息管理(未认证)";
    }


    /**
     * 调往 个人信息管理(认证中)--查看认证 页面
     *
     * @return
     */
    @RequestMapping("/myauthdetail")
    public String myAuthDetail(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Person, session.getPersonId());
        if(au==null){
            au = authenticationService.find(AuthTypeEnum.Org, session.getPersonId());
        }
        model.addAttribute("auth", au);
        return "pc/个人信息管理(认证中)--查看认证";
    }

    /**
     * 跳往 个人信息管理--修改页面
     *
     * @return
     */
    @RequestMapping("/tomyinfomodify")
    public String toMyInfoModify(Model model) {
        UserResp user = personAuthorityService.findCurrentUser();
        model.addAttribute("user", user);
        return "pc/个人信息管理--修改";
    }

    /**
     * 修改个人信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/myinfomodify")
    public String myInfoModify(Model model, @ModelAttribute("user") UserResp user, Errors errors
            , @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        boolean success = personAuthorityService.modify(user, imgFiles);

        return "pc/个人信息管理--修改";
    }


    /**
     * 跳往我上传的书籍列表页面
     *
     * @return
     */
    @RequestMapping("/tomybooksuploaded")
    public String toMyBooksUploaded(Model model ,@ModelAttribute("page") Page page) {
        page.setShowPageSize(8);
        page.setRows(6);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<MyBooksListReq> bookList = booksUploadedService.findByPersonId(session.getPersonId(),page);
        model.addAttribute("bookList", bookList);
        return "pc/我上传的书籍";
    }


    /**
     * 删除我的书籍
     *
     * @param bookId
     * @return
     */
    @RequestMapping("/delbook/{bookId}")
    public String delBook(@PathVariable String bookId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = booksUploadedService.delBookWithPersonId(session.getPersonId(), bookId);
        return "redirect:/p/user/tomybooksuploaded";
    }


    /**
     * 跳往编辑我的书籍
     *
     * @param bookId
     * @return
     */
    @RequestMapping("/tomodifybook/{bookId}")
    public String modifyBook(Model model, @PathVariable String bookId) {
        MyBooksAddReq booksAddReq = booksUploadedService.findById(bookId);
        model.addAttribute("book", booksAddReq);
        model.addAttribute("fileList", booksAddReq.getFileReqList());
        return "pc/我上传的书籍-编辑书籍";
    }


    /**
     * 编辑书籍
     *
     * @return
     */
    @RequestMapping("/booksmodify")
    public String myBooksModify(@Valid @ModelAttribute("book") MyBooksAddReq booksAddReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "pc/我上传的书籍-编辑书籍";
        }
        boolean success = booksUploadedService.modifyBook(booksAddReq, errors, imgFiles);
        if (success) {
            return "redirect:/p/user/tomybooksuploaded";
        }
        return "pc/我上传的书籍-编辑书籍";
    }



    /**
     * 跳往我上传的书籍--新增书籍页面
     *
     * @return
     */
    @RequestMapping("/tomybooksadd")
    public String toMyBooksAdd(@ModelAttribute("mybook") MyBooksAddReq myBooksAddReq) {
        return "pc/我上传的书籍-新增书籍";
    }

    /**
     * 我上传的书籍--新增书籍
     *
     * @return
     */
    @RequestMapping("/mybooksadd")
    public String myBooksAdd(@Valid @ModelAttribute("mybook") MyBooksAddReq myBooksAddReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "pc/我上传的书籍-新增书籍";
        }
        boolean success = booksUploadedService.createBook(myBooksAddReq, errors, imgFiles);
        if (success) {
            return "redirect:/p/user/tomybooksuploaded";
        }
        return "pc/我上传的书籍-新增书籍";
    }

    /**
     * 跳往我的心愿书单页面
     *
     * @return
     */
    @RequestMapping("/tomybooksdesired")
    public String toMyBooksDesired(Model model,@ModelAttribute("page") Page page) {
        page.setShowPageSize(8);
        page.setRows(10);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<BookDesiredReq> desiredList = bookDesiredService.findByPersonId(session.getPersonId(),page);
        model.addAttribute("desiredList", desiredList);
        return "pc/我的心愿书单";
    }

    /**
     * 跳往我的心愿书单--添加书单页面
     *
     * @return
     */
    @RequestMapping("/tomybooksdesiredadd")
    public String toAddBooksDesired(Model model,@ModelAttribute("desire") BookDesiredReq req) {
        model.addAttribute("rstatus", false);
        return "pc/我的心愿书单--添加书单";
    }

    /**
     * 添加我的心愿书单
     *
     * @param req
     * @param errors
     * @param imgFiles
     * @return
     * @throws Exception
     */
    @RequestMapping("/mybooksdesiredadd")
    public String myBooksDesiredAdd(Model model,@Valid @ModelAttribute("desire") BookDesiredReq req, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "pc/我的心愿书单--添加书单";
        }
        boolean success = bookDesiredService.createDesire(req, errors, imgFiles);
        if (success) {
//            return "redirect:/p/user/tomybooksdesired";
            model.addAttribute("rstatus", true);
            model.addAttribute("rmsg", "操作成功！");
        }
        return "pc/我的心愿书单--添加书单";
    }
    /**
     * 跳往我的收藏页面
     *
     * @return
     */
    @RequestMapping("/tomycollecting")
    @Login
    public String toMyCollecting(Model model,@ModelAttribute("page") Page page) {
        page.setShowPageSize(8);
        page.setRows(6);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<CollectionResp> collectList = collectingService.findCollectingByPerson(session.getPersonId(),page);
        model.addAttribute("collectList", collectList);
        return "pc/我的收藏";
    }


    /**
     * 跳往我的收货地址管理页面
     *
     * @return
     */
    @RequestMapping("/toshippingaddress")
    public String toShippingAddress(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<ShippingAddressResp> list = shippingAddressService.findShippingAddressListByPersonId(session.getPersonId());
        model.addAttribute("addressList", list);
        return "pc/收货地址管理";
    }


    /**
     * 设置为默认地址
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/setdefault/{addressId}")
    public String setDefault(@PathVariable String addressId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = shippingAddressService.setShippingAddressDefaultByPersonId(session.getPersonId(), addressId);
        return "redirect:/p/user/toshippingaddress";
    }


    /**
     * 跳往编辑收货地址页面
     *
     * @return
     */
    @RequestMapping("/tomodifyshippingaddress/{addressId}")
    public String toModifyShippingAddress(Model model, @PathVariable String addressId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ShippingAddressEditPcReq address = shippingAddressService.findShippingAddressEditByPersonIdAddressId(session.getPersonId(), addressId);
        model.addAttribute("address", address);
        return "pc/收货地址管理--编辑地址";
    }


    /**
     * 编辑收货地址
     *
     * @return
     */
    @RequestMapping("/modifyshippingaddress")
    public String modifyShippingAddress(Model model, @Valid @ModelAttribute("address") ShippingAddressEditPcReq address, Errors errors) {
        if (errors.hasErrors()) {
            return "pc/收货地址管理--编辑地址";
        }
        boolean success = shippingAddressService.modifyShippingAddressPc(address, errors);
        if (success) {
            return "redirect:/p/user/toshippingaddress";
        }
        return "pc/收货地址管理--编辑地址";

    }


    /**
     * 跳往新增地址页面
     *
     * @param address
     * @return
     */
    @RequestMapping("/toaddshippingaddress")
    public String toAddShippingAddress(@ModelAttribute("address") ShippingAddressEditPcReq address) {
        return "pc/收货地址管理--新增地址";
    }

    /**
     * 新增地址
     *
     * @param model
     * @param address
     * @param errors
     * @return
     */
    @RequestMapping("/addshippingaddress")
    public String addShippingAddress(Model model, @Valid @ModelAttribute("address") ShippingAddressEditPcReq address, Errors errors) {
        if (errors.hasErrors()) {
            return "pc/收货地址管理--新增地址";
        }
        boolean success = shippingAddressService.createShippingAddressPc(address, errors);
        if (success) {
            return "redirect:/p/user/toshippingaddress";
        }
        return "pc/收货地址管理--新增地址";
    }

    @RequestMapping("/deladdress/{addressId}")
    public String delAddress(@PathVariable String addressId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = shippingAddressService.delShippingAddressWithPersonId(session.getPersonId(), addressId);
        return "redirect:/p/user/toshippingaddress";
    }
    /**
     * 跳往账户安全页面
     *
     * @return
     */
    @RequestMapping("/toaccount")
    @Login
    public String toAccount(Model model, @ModelAttribute("account") AccountReq accountReq) {
        model.addAttribute("rstatus", false);
        return "pc/账户安全";
    }


    /**
     * 修改账户
     *
     * @param accountReq
     * @param errors
     * @return
     */
    @RequestMapping("/modifyaccount")
    @Login
    public String modifyAccount(Model model, @Valid @ModelAttribute("account") AccountReq accountReq, Errors errors) {
        model.addAttribute("rstatus", false);
        if (errors.hasErrors()) {
            return "pc/账户安全";
        }
        boolean success = personAuthorityService.modifyPwd(accountReq, errors);
        if (success) {
            model.addAttribute("rstatus", true);
            model.addAttribute("rmsg", "操作成功！");
            return "pc/账户安全";
        }
        return "pc/账户安全";
    }

    @RequestMapping("/tomymsgcenter")
    @Login
    public String toMyMsgCenter(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<MessageGeneralResp> msgList = messageGeneralService.findByPerson(session.getPersonId());
        model.addAttribute("msgList", msgList);
        return "pc/消息列表";
    }

    @RequestMapping("/delmymsg/{msgId}")
    @Login
    @ResponseBody
    public String delMyMsg(Model model, @PathVariable("msgId") String msgId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = messageGeneralService.delByPerson(session.getPersonId(), msgId);
        return success ? "ok" : "fail";
//        return "redirect:/p/user/tomymsgcenter";
    }


    /**
     * 跳到联系主人页面
     *
     * @param model
     * @param bookId
     * @return
     */
    @RequestMapping("/contractowner/{bookId}")
    public String contractOwner(Model model, @ModelAttribute @PathVariable("bookId") String bookId) {
        MyBooksAddReq booksAddReq = booksUploadedService.findById(bookId);
        UserResp user = personAuthorityService.findUserById(booksAddReq.getPersonId());
        model.addAttribute("user", user);
        ShippingAddressResp shippingAddressResp = shippingAddressService.findShippingAddressByPersonId(booksAddReq.getPersonId());
        model.addAttribute("ship", shippingAddressResp);
        return "pc/首页--商品详情--联系主人";
    }

    /**
     * 跳往认证选择个人还是单位界面
     *
     * @return
     */
    @RequestMapping("/toauthselect")
    public String toAuthenticationSelect() {
        UserResp user= personAuthorityService.findCurrentUser();
        if(AuthStatusEnum.Authing.getCode()==user.getAuthStatus()){
            return "redirect:/p/user/toinfomanage";
        }
        return "pc/认证";
    }


    /**
     * 跳往个人认证页面
     *
     * @return
     */
    @RequestMapping("/topersonauth")
    public String toPersonAuthentication(@ModelAttribute("auth") PersonAuthReq authReq) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Person, session.getPersonId());
        if (au != null) {
            authReq.setMobile(au.getMobile());
            authReq.setName(au.getName());
            authReq.setAddress(au.getAddress());
        }
        return "pc/认证--个人认证";
    }


    /**
     * 跳往单位认证页面
     *
     * @return
     */
    @RequestMapping("/toorgauth")
    public String toOrgAuthentication(@ModelAttribute("auth") OrgAuthReqPc authReq) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Org, session.getPersonId());
        if (au != null) {
            authReq.setMobile(au.getMobile());
            authReq.setName(au.getName());
            authReq.setOfficeAddress(au.getOfficeAddress());
        }
        return "pc/认证--单位认证";
    }


    /**
     * 提交单位认证资料
     * @param authReq
     * @param errors
     * @param imgFront
     * @param imgBack
     * @return
     * @throws Exception
     */
    @RequestMapping("/orgauth")
    public String orgAuthentication(@Valid @ModelAttribute("auth") OrgAuthReqPc authReq, Errors errors
            , @RequestParam(name = "imgFront", required = false) MultipartFile imgFront
            , @RequestParam(name = "imgBack", required = false) MultipartFile imgBack) throws Exception {
        if (errors.hasErrors()) {
            return "pc/认证--单位认证";
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Person, session.getPersonId());
        if (au != null) {
            authReq.setMobile(au.getMobile());
            authReq.setName(au.getName());
            authReq.setOfficeAddress(au.getOfficeAddress());
        }
        boolean success = authenticationService.createOrgAuthPc(authReq, errors, imgFront, imgBack);
        if (success) {
//            return  "\"pc/认证--单位认证\";";
            return "redirect:/p/user/toinfomanage";
        }
        return "pc/认证--单位认证";
    }

    /**
     * 提交个人认证资料
     *
     * @param authReq
     * @param errors
     * @param imgFront
     * @param imgBack
     * @return
     * @throws Exception
     */
    @RequestMapping("/personauth")
    public String personAuthentication(@Valid @ModelAttribute("auth") PersonAuthReq authReq, Errors errors
            , @RequestParam(name = "imgFront", required = false) MultipartFile imgFront
            , @RequestParam(name = "imgBack", required = false) MultipartFile imgBack) throws Exception {
        if (errors.hasErrors()) {
            return "pc/认证--个人认证";
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Person, session.getPersonId());
        if (au != null) {
            authReq.setMobile(au.getMobile());
            authReq.setName(au.getName());
            authReq.setAddress(au.getAddress());
        }
        boolean success = authenticationService.createPersonAuth(authReq, errors, imgFront, imgBack);
        if (success) {
            return "redirect:/p/user/toinfomanage";
        }
        return "pc/认证--个人认证";
    }


}
