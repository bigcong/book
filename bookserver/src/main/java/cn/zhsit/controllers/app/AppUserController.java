package cn.zhsit.controllers.app;

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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("m/user")
@Login
public class AppUserController {
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
     * 跳往注册页面
     *
     * @return
     */
    @RequestMapping("/toregister")
    public String toRegister(@ModelAttribute("person") RegisterReq person, Model model) {
        return "app/register";
    }

    /**
     * 注册
     * <p>
     * 用 org.springframework.validation.BindingResult result 或  org.springframework.validation.Errors errors 都可以绑定校验到页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(@Valid @ModelAttribute("person") RegisterReq person, Errors errors, Model model) throws ZhsException {
        if (errors.hasErrors()) {
            return "app/register";
        }

        boolean success = personAuthorityService.createPerson(person, errors);
        if (!success) {
            return "app/register";
        }
        return "redirect:/m/user/tologin";
    }


    /**
     * 跳往登录页面
     *
     * @return
     */
    @RequestMapping("/tologin")
    public String toLogin(@ModelAttribute("login") LoginReq loginReq) {
        return "app/login";
    }

    /**
     * 跳往登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginReq loginReq, Errors errors) throws ZhsException {
        if (errors.hasErrors()) {
            return "app/login";
        }
        boolean success = personAuthorityService.login(loginReq, errors);
        if (success) {
            return "redirect:/m/user/tomine";
        }
        return "app/login";
    }

    /**
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        boolean success = personAuthorityService.logout();
        return "redirect:/m/index";
    }


    /**
     * 跳往个人信息管理页面
     *
     * @return
     */
    @RequestMapping("/toinfomanage")
    public String toInfoManage(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        model.addAttribute("user", userResp);
        return "app/个人信息管理";
    }

    /**
     * 跳往认证选择个人还是单位界面
     *
     * @return
     */
    @RequestMapping("/toauthselect")
    public String toAuthenticationSelect() {
        return "app/认证";
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
        return "app/认证--个人";
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
            return "app/认证--个人";
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
            return "redirect:/m/user/toinfomanage";
        }
        return "app/认证--个人";
    }

    /**
     * 跳往单位认证页面
     *
     * @return
     */
    @RequestMapping("/toorgauth")
    public String toOrgAuthentication(@ModelAttribute("auth") OrgAuthReq authReq) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Org, session.getPersonId());
        if (au != null) {
            authReq.setAddress(au.getAddress());
            authReq.setName(authReq.getOrgName() + "-" + au.getName());
            authReq.setOfficeAddress(au.getOfficeAddress());
        }
        return "app/认证--单位";
    }

    /**
     * 提交单位认证资料
     *
     * @param authReq
     * @param errors
     * @param imgFront
     * @param imgBack
     * @return
     * @throws Exception
     */
    @RequestMapping("/orgauth")
    public String orgAuthentication(@Valid @ModelAttribute("auth") OrgAuthReq authReq, Errors errors
            , @RequestParam(name = "imgFront", required = false) MultipartFile imgFront
            , @RequestParam(name = "imgBack", required = false) MultipartFile imgBack) throws Exception {
        if (errors.hasErrors()) {
            return "app/认证--单位";
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Authentication au = authenticationService.find(AuthTypeEnum.Person, session.getPersonId());
        if (au != null) {
            authReq.setAddress(au.getAddress());
            if (authReq.getName().split("-").length > 0) {
                authReq.setOrgName(authReq.getName().split("-")[0]);
                authReq.setName(authReq.getName().split("-")[1]);


            }


            authReq.setName(authReq.getOrgName() + "-" + au.getName());
            authReq.setOfficeAddress(au.getOfficeAddress());
        }


        boolean success = authenticationService.createOrgAuth(authReq, errors, imgFront, imgBack);
        if (success) {
            return "redirect:/m/user/toinfomanage";
        }
        return "app/认证--单位";
    }

    /**
     * 跳往信息是否已经认证界面
     *
     * @return
     */
    @RequestMapping("/topersoninfo")
    public String toPersonInfo(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        UserResp userResp = personAuthorityService.findCurrentUser();
        if (userResp.getAuthStatus() == AuthStatusEnum.NoAuth.getCode()) {
            return "redirect:/m/user/toauthselect";
        }

        Authentication authentication = authenticationService.find(session.getPersonId());
        model.addAttribute("authp", authentication);
        if (authentication.getAuthType() == AuthTypeEnum.Person.getCode()) {
            if (authentication.getAuthStatus() == AuthStatusEnum.Authing.getCode()) {
                return "app/个人信息管理--认证中(个人)";
            } else if (authentication.getAuthStatus() == AuthStatusEnum.AuthOK.getCode()) {
                return "app/个人信息管理--已认证 (个人)";
            }
        } else if (authentication.getAuthType() == AuthTypeEnum.Org.getCode()) {
            if (authentication.getAuthStatus() == AuthStatusEnum.Authing.getCode()) {
                return "app/个人信息管理--认证中(单位)";
            } else if (authentication.getAuthStatus() == AuthStatusEnum.AuthOK.getCode()) {
                return "app/个人信息管理--已认证(单位)";
            }
        }

        return "redirect:/m/user/toinfomanage";
    }


    /**
     * 跳往修改昵称页面
     *
     * @return
     */
    @RequestMapping("/tomodifymobile")
    public String toModifyMobile(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        ModifyMobileReq modifyMobileReq = new ModifyMobileReq();
        modifyMobileReq.setMobile(userResp.getMobile());
        model.addAttribute("mobile", modifyMobileReq);
        return "app/修改手机号";
    }

    /**
     * 修改手机号
     *
     * @return
     */
    @RequestMapping("/modifymobile")
    public String modifyMobile(@Valid @ModelAttribute("mobile") ModifyMobileReq mobileReq, Errors errors) {
        if (errors.hasErrors()) {
            return "app/修改手机号";
        }
        boolean success = personAuthorityService.modifyMobile(mobileReq, errors);
        if (success) {
            return "redirect:/m/user/toinfomanage";
        }
        return "app/修改手机号";
    }

    /**
     * 跳往修改昵称页面
     *
     * @return
     */
    @RequestMapping("/tomodifynickname")
    public String toModifyNickName(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        ModifyNicknameReq nicknameReq = new ModifyNicknameReq();
        nicknameReq.setNickname(userResp.getNickname());
        model.addAttribute("nickname", nicknameReq);
        return "app/修改昵称";
    }

    /**
     * 修改昵称
     *
     * @return
     */
    @RequestMapping("/modifynickname")
    public String modifyNickName(@Valid @ModelAttribute("nickname") ModifyNicknameReq nicknameReq, Errors errors) {
        if (errors.hasErrors()) {
            return "app/修改昵称";
        }
        boolean success = personAuthorityService.modifyNickname(nicknameReq, errors);
        if (success) {
            return "redirect:/m/user/toinfomanage";
        }
        return "app/修改昵称";
    }

    /**
     * 修改头像
     *
     * @return
     */
    @RequestMapping("/modifyheadpic")
    public String modifyHeadPic(@RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        boolean success = personAuthorityService.modifyHeadPic(imgFiles);
        return "redirect:/m/user/toinfomanage";

    }


    /**
     * 跳往修改邮箱页面
     *
     * @return
     */
    @RequestMapping("/tomodifyemail")
    public String toModifyEmail(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        ModifyEmailReq emailReq = new ModifyEmailReq();
        emailReq.setEmail(userResp.getEmail());
        model.addAttribute("email", emailReq);
        return "app/修改邮箱";
    }

    /**
     * 修改邮箱
     *
     * @return
     */
    @RequestMapping("/modifyemail")
    public String modifyEmail(@Valid @ModelAttribute("email") ModifyEmailReq emailReq, Errors errors) {
        if (errors.hasErrors()) {
            return "app/修改邮箱";
        }
        boolean success = personAuthorityService.modifyEmail(emailReq, errors);
        if (success) {
            return "redirect:/m/user/toinfomanage";
        }
        return "app/修改邮箱";
    }

    /**
     * 跳往修改邮箱页面
     *
     * @return
     */
    @RequestMapping("/tomodifybirthday")
    public String toModifyBirthday(Model model) {
        UserResp userResp = personAuthorityService.findCurrentUser();
        ModifyBirthdayReq birthdayReq = new ModifyBirthdayReq();
        birthdayReq.setBirthday(userResp.getBirthday());
//        birthdayReq.setBirthday(new Date());
        model.addAttribute("birthday", birthdayReq);
        return "app/修改出生日期";
    }

    /**
     * 修改邮箱
     *
     * @return
     */
    @RequestMapping("/modifybirthday")
    public String modifyBirthday(@Valid @ModelAttribute("birthday") ModifyBirthdayReq birthdayReq, Errors errors) {
        if (errors.hasErrors()) {
            return "app/修改出生日期";
        }
        boolean success = personAuthorityService.modifyBirthday(birthdayReq, errors);
        if (success) {
            return "redirect:/m/user/toinfomanage";
        }
        return "app/修改出生日期";
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
        return "app/我的";
    }

    /**
     * 跳往我上传的书籍列表页面
     *
     * @return
     */
    @RequestMapping("/tomybooksuploaded")
    public String toMyBooksUploaded(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<MyBooksListReq> bookList = booksUploadedService.findByPersonId(session.getPersonId());
        model.addAttribute("bookList", bookList);
        return "app/我上传的书籍";
    }

    /**
     * 跳往我上传的书籍--新增书籍页面
     *
     * @return
     */
    @RequestMapping("/tomybooksadd")
    public String toMyBooksAdd(@ModelAttribute("mybook") MyBooksAddReq myBooksAddReq) {
        return "app/我上传的书籍--新增书籍";
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
        return "redirect:/m/user/tomybooksuploaded";
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
        return "app/我上传的书籍--修改书籍";
    }

    /**
     * 编辑书籍
     *
     * @return
     */
    @RequestMapping("/booksmodify")
    public String myBooksModify(@Valid @ModelAttribute("book") MyBooksAddReq booksAddReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "app/我上传的书籍--修改书籍";
        }
        boolean success = booksUploadedService.modifyBook(booksAddReq, errors, imgFiles);
        if (success) {
            return "redirect:/m/user/tomybooksuploaded";
        }
        return "app/我上传的书籍--修改书籍";
    }

    /**
     * 我上传的书籍--新增书籍
     *
     * @return
     */
    @RequestMapping("/mybooksadd")
    public String myBooksAdd(@Valid @ModelAttribute("mybook") MyBooksAddReq myBooksAddReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "app/我上传的书籍--新增书籍";
        }
        boolean success = booksUploadedService.createBook(myBooksAddReq, errors, imgFiles);
        if (success) {
            return "redirect:/m/user/tomybooksuploaded";
        }
        return "app/我上传的书籍--新增书籍";
    }

    @RequestMapping("/mybooksimgdel/{imgId}")
    @ResponseBody
    public String myBookImgDel(@PathVariable String imgId) {
        boolean success = booksUploadedService.delImg(imgId);
        return success ? "ok" : "fail";
    }

    /**
     * 跳往我的心愿书单页面
     *
     * @return
     */
    @RequestMapping("/tomybooksdesired")
    public String toMyBooksDesired(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<BookDesiredReq> desiredList = bookDesiredService.findByPersonId(session.getPersonId());
        model.addAttribute("desiredList", desiredList);
        return "app/我的心愿书单";
    }


    /**
     * 跳往我的心愿书单--添加页面
     *
     * @return
     */
    @RequestMapping("/tomybooksdesiredadd")
    public String toMyBooksDesiredAdd(@ModelAttribute("desire") BookDesiredReq req) throws Exception {
        return "app/我的心愿书单--添加";
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
    public String myBooksDesiredAdd(@Valid @ModelAttribute("desire") BookDesiredReq req, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "app/我的心愿书单--添加";
        }
        boolean success = bookDesiredService.createDesire(req, errors, imgFiles);
        if (success) {
            return "redirect:/m/user/tomybooksdesired";
        }
        return "app/我的心愿书单--添加";
    }

    /**
     * 删除我的心愿书单中的书
     *
     * @param id
     * @return
     */
    @RequestMapping("/mybooksdesireddel/{id}")
    @ResponseBody
    public String myBooksDesiredDel(@PathVariable String id) {
        boolean success = bookDesiredService.del(id);
        return success ? "ok" : "fail";
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
        return "app/图书详情--联系主人";
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
        return "app/地址管理";
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
        return "redirect:/m/user/toshippingaddress";
    }

    /**
     * 删除地址
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/deladdress/{addressId}")
    public String delAddress(@PathVariable String addressId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = shippingAddressService.delShippingAddressWithPersonId(session.getPersonId(), addressId);
        return "redirect:/m/user/toshippingaddress";
    }

    /**
     * 跳往添加新地址页面
     *
     * @return
     */
    @RequestMapping("/toaddshippingaddress")
    public String toAddShippingAddress(@ModelAttribute("address") ShippingAddressReq addressReq) {
        return "app/添加新地址";
    }

    /**
     * 跳往编辑收货地址页面
     *
     * @return
     */
    @RequestMapping("/tomodifyshippingaddress/{addressId}")
    public String toModifyShippingAddress(Model model, @PathVariable String addressId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        ShippingAddressReq addressReq = shippingAddressService.findShippingAddressByPersonIdAddressId(session.getPersonId(), addressId);
        model.addAttribute("address", addressReq);
        return "app/添加新地址";
    }

    /**
     * 添加新地址
     *
     * @return
     */
    @RequestMapping("/addshippingaddress/{addressId}")
    public String addShippingAddress(@Valid @ModelAttribute("address") ShippingAddressReq addressReq, Errors errors, @PathVariable(required = false) String addressId) throws ZhsException {
        addressReq.setId(addressId);
        if (errors.hasErrors()) {
            return "app/添加新地址";
        }
        if (null == addressId || "null".equalsIgnoreCase(addressId)) {
            boolean success = shippingAddressService.createShippingAddress(addressReq, errors);
            if (success) {
                return "redirect:/m/user/toshippingaddress";
            }
            return "app/添加新地址";
        } else {
            boolean success = shippingAddressService.modifyShippingAddress(addressReq, errors);
            if (success) {
                return "redirect:/m/user/toshippingaddress";
            }
            return "app/添加新地址";
        }

    }


    /**
     * 跳往我的收藏页面
     *
     * @return
     */
    @RequestMapping("/tomycollecting")
    @Login
    public String toMyCollecting(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<CollectionResp> collectList = collectingService.findCollectingByPerson(session.getPersonId());
        model.addAttribute("collectList", collectList);
        return "app/我的收藏";
    }

    @RequestMapping("/delmycollecting/{collectingId}")
    @ResponseBody
    public String delMyCollection(@PathVariable String collectingId) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean success = collectingService.delWithPersonId(session.getPersonId(), collectingId);
        return success ? "ok" : "fail";
    }


    /**
     * 跳往账户安全页面
     *
     * @return
     */
    @RequestMapping("/toaccount")
    @Login
    public String toAccount(@ModelAttribute("account") AccountReq accountReq) {
        return "app/账户安全";
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
    public String modifyAccount(@Valid @ModelAttribute("account") AccountReq accountReq, Errors errors) {
        if (errors.hasErrors()) {
            return "app/账户安全";
        }
        boolean success = personAuthorityService.modifyPwd(accountReq, errors);
        if (success) {
            return "redirect:/m/user/tomine";
        }
        return "app/账户安全";
    }

    /**
     * 检查是否认证通过
     *
     * @return
     */
    @RequestMapping("/checkauth")
    @ResponseBody
    public String checkAuth() {
        UserResp user = personAuthorityService.findCurrentUser();
        return user.getAuthStatus() == AuthStatusEnum.AuthOK.getCode() ? "ok" : "fail";
    }


    @RequestMapping("/tomymsgcenter")
    @Login
    public String toMyMsgCenter(Model model) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<MessageGeneralResp> msgList = messageGeneralService.findByPerson(session.getPersonId());
        model.addAttribute("msgList", msgList);
        return "app/消息中心";
    }


    @RequestMapping("/tomymsgdetail/{id}")
    @Login
    public String toMyMsgDetail(Model model, @PathVariable("id") String id) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        MessageGeneralResp resp = messageGeneralService.findByPerson(session.getPersonId(), id);
        model.addAttribute("msg", resp);
        return "app/消息中心--详情";
    }


}
