package cn.zhsit.authority.services.impl;

import cn.zhsit.authority.enums.AuthStatusEnum;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.Msg;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.managers.PersonAuthorityManager;
import cn.zhsit.authority.models.po.PersonAuthority;
import cn.zhsit.authority.models.vo.*;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.constants.ZhsConstants;
import cn.zhsit.common.enums.FileType;
import cn.zhsit.common.enums.ServiceNameEnum;
import cn.zhsit.common.exceptions.ZhsException;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.helpers.ZhsFileHelper;
import cn.zhsit.common.utils.ZhsMD5;
import cn.zhsit.common.utils.ZhsOrderNumUtil;
import cn.zhsit.common.utils.ZhsStringUtil;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.generator.models.po.ZhsFileAuthorityGeneral;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class PersonAuthorityServiceImpl implements PersonAuthorityService {
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private PersonAuthorityManager personAuthorityManager;
    @Autowired
    private ZhsConfig zhsConfig;

    @Override
    public boolean checkPwd(RegisterReq person, Msg msg) throws ZhsException {
        String loginName = person.getLoginName();
        String loginPwd = person.getPwd();
        if (loginName == null || loginName.trim().length() < 1 || loginPwd == null || loginPwd.trim().length() < 1) {
            msg.addVal(Msg.msg, "用户名和密码不可为空！");
            return false;
        }
        PersonAuthority pInDB = personAuthorityManager.selectByLoginName(person.getLoginName());
        if (null == pInDB) {
            msg.addVal(Msg.msg, "用户名或密码错误！");
            return false;
        }

        String loginMD5Pwd = ZhsMD5.md5Twice(loginPwd);
        if (!loginMD5Pwd.equals(pInDB.getPwd())) {
            msg.addVal(Msg.msg, "用户名或密码错误！");
            return false;
        }
        cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey())
                .setLoginStatus(ZhsSession.LoginStatus.Logged)
                .setPersonId(pInDB.getId());
        return true;
    }

    @Override
    public boolean createPerson(RegisterReq person, Errors errors) throws ZhsException {
        boolean success = true;
        if (!person.getPwd().equals(person.getRepeatPwd())) {
            errors.rejectValue("repeatPwd", "repeatPwd", "两次输入密码不同！ ");
            success = false;
        }
        if (ZhsStringUtil.isNotBlank(person.getLoginName())) {
            person.setLoginName(person.getLoginName().trim());
            PersonAuthority p = personAuthorityManager.selectByLoginName(person.getLoginName());
            if (p != null) {
                errors.rejectValue("loginName", "loginName", "此用户名已经被占用！");
                success = false;
            } else if (StringUtils.contains(person.getLoginName(), "@")) {
                errors.rejectValue("loginName", "loginName", "用户名不可含有@符号！ ");
                success = false;
            } else if (NumberUtils.isDigits(person.getLoginName()) && person.getLoginName().length() == 11) {
                errors.rejectValue("loginName", "loginName", "用户名不可以是11位数字 ");
                success = false;
            }
        }
        if (ZhsStringUtil.isNotBlank(person.getEmail())) {
            PersonAuthority p = personAuthorityManager.selectByEmail(person.getEmail());
            if (p != null) {
                errors.rejectValue("email", "email", "此邮箱已经被占用! ");
                success = false;
            } else if (1 != StringUtils.countMatches(person.getEmail(), "@")) {
                errors.rejectValue("email", "email", "邮箱格式不正确! ");
                success = false;
            }
        }
        if (ZhsStringUtil.isNotBlank(person.getMobile())) {
            PersonAuthority p = personAuthorityManager.selectByEmail(person.getMobile());
            if (p != null) {
                errors.rejectValue("mobile", "mobile", "此手机号已经存在！ ");
                success = false;
            } else if (!NumberUtils.isDigits(person.getMobile())) {
                errors.rejectValue("mobile", "mobile", "手机号格式不正确！ ");
                success = false;
            }
        }
        if (ZhsStringUtil.isAllBlank(person.getLoginName(), person.getEmail(), person.getMobile())) {
            errors.rejectValue("loginName", "loginName", "手机号、邮箱、用户名至少填一个！");
            success = false;
        }
        if (!success) {
            return false;
        }
        PersonAuthority po = new PersonAuthority();
        BeanUtils.copyProperties(person, po);
        po.setId(ZhsUnique.unique25());
        po.setPwd(ZhsMD5.md5Twice(person.getPwd()));
        po.setAuthStatus(AuthStatusEnum.NoAuth.getCode());
        po.setCreateTime(Calendar.getInstance().getTime());
        po.setModifyTime(Calendar.getInstance().getTime());
        try {
            return personAuthorityManager.insert(po) == 1;
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                if (ZhsStringUtil.contains(e.getMessage(), ZhsConstants.index_unique_personauthority_mobile)) {
                    errors.rejectValue("mobile", "mobile", "此手机号已经存在！");
                }
                if (ZhsStringUtil.contains(e.getMessage(), ZhsConstants.index_unique_personauthority_email)) {
                    errors.rejectValue("email", "email", "此邮箱已经被占用！");
                }
                if (ZhsStringUtil.contains(e.getMessage(), ZhsConstants.index_unique_personauthority_login_name)) {
                    errors.rejectValue("loginName", "loginName", "此用户名已经被占用！");
                }
            }
        }
        return false;
    }


    @Override
    public boolean login(LoginReq loginReq, Errors errors) throws ZhsException {
        PersonAuthority p = personAuthorityManager.selectByMobile(loginReq.getMobile());
        if (p == null) {
            p = personAuthorityManager.selectByLoginName(loginReq.getMobile());
        }


        if (p == null) {
            errors.rejectValue("pwd", "pwd", "账号/手机号或密码不正确！");
            return false;
        }
        String pwd = loginReq.getPwd();
        String pwdReq = ZhsMD5.md5Twice(pwd);
        if (!p.getPwd().equals(pwdReq)) {
            errors.rejectValue("pwd", "pwd", "账号/手机号或密码不正确！");
            return false;
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        session.setLoginStatus(ZhsSession.LoginStatus.Logged);
        session.setPersonId(p.getId());
        return true;
    }

    @Override
    public boolean login(LoginReqPc loginReq, Errors errors) throws ZhsException {
        PersonAuthority p = null;
        String flag = loginReq.getName();
        if (flag.length() == 11 && NumberUtils.isDigits(flag)) {
            p = personAuthorityManager.selectByMobile(flag);
        } else if (StringUtils.contains(flag, "@")) {
            p = personAuthorityManager.selectByEmail(flag);
        } else {
            p = personAuthorityManager.selectByLoginName(flag);
        }

        if (p == null) {
            errors.rejectValue("pwd", "pwd", "手机号/用户名/邮箱或密码不正确！");
            return false;
        }
        String pwd = loginReq.getPwd();
        String pwdReq = ZhsMD5.md5Twice(pwd);
        if (!p.getPwd().equals(pwdReq)) {
            errors.rejectValue("pwd", "pwd", "手机号/用户名/邮箱或密码不正确！");
            return false;
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        session.setLoginStatus(ZhsSession.LoginStatus.Logged);
        session.setPersonId(p.getId());
        return true;
    }

    @Override
    public boolean logout() {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        personAuthorityManager.removeCache(session.getPersonId());
        session.setLoginStatus(ZhsSession.LoginStatus.NoLogin);
        return true;
    }

    @Override
    public boolean modifyPwd(AccountReq accountReq, Errors errors) {
        if (!accountReq.getNewPwd().equals(accountReq.getRepeatNewPwd())) {
            errors.rejectValue("repeatNewPwd", "repeatNewPwd", "新密码和再次输入密码不同！");
            return false;
        }
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority person = personAuthorityManager.selectByKey(session.getPersonId());
        String oldPwd = accountReq.getOldPwd();
        oldPwd = ZhsMD5.md5Twice(oldPwd);
        if (!person.getPwd().equals(oldPwd)) {
            errors.rejectValue("oldPwd", "oldPwd", "原密码不对！");
            return false;
        }
        String newPwd = accountReq.getNewPwd();
        newPwd = ZhsMD5.md5Twice(newPwd);
        PersonAuthority personAuthority = new PersonAuthority();
        personAuthority.setId(person.getId());
        personAuthority.setPwd(newPwd);
        personAuthority.setModifyTime(Calendar.getInstance().getTime());
        return personAuthorityManager.updateByKey(personAuthority) == 1;
    }

    @Override
    public UserResp findCurrentUser() {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority po = personAuthorityManager.selectByKey(session.getPersonId());
        UserResp user = new UserResp();
        user.setId(po.getId());
        user.setLoginName(po.getLoginName());
        user.setMobileStar(ZhsStringUtil.replaceStar(po.getMobile(), 3, 4));
        user.setMobile(po.getMobile());
        user.setNickname(po.getNickname());
        user.setHeadPic(po.getHeadPic());
        user.setEmail(po.getEmail());
        user.setBirthday(po.getBirthday());
        user.setAuthStatus(po.getAuthStatus());
        String authStatusStr = null;
        switch (po.getAuthStatus()) {
            case 1:
                authStatusStr = "未认证";
                break;
            case 2:
                authStatusStr = "认证中";
                break;
            case 3:
                authStatusStr = "已认证";
                break;
            default:
                authStatusStr = "未认证";
        }
        user.setAuthStatusStr(authStatusStr);
        return user;
    }

    @Override
    public UserResp findUserById(String personId) {
        PersonAuthority person = personAuthorityManager.selectByKey(personId);
        UserResp user = new UserResp();
        user.setMobileStar(ZhsStringUtil.replaceStar(person.getMobile(), 3, 4));
        user.setMobile(person.getMobile());
        user.setNickname(person.getNickname());
        user.setHeadPic(person.getHeadPic());
        user.setEmail(person.getEmail());
        user.setBirthday(person.getBirthday());
        user.setAuthStatus(person.getAuthStatus());
        String authStatusStr = null;
        switch (person.getAuthStatus()) {
            case 1:
                authStatusStr = "未认证";
                break;
            case 2:
                authStatusStr = "认证中";
                break;
            case 3:
                authStatusStr = "已认证";
                break;
            default:
                authStatusStr = "未认证";
        }
        user.setAuthStatusStr(authStatusStr);
        return user;
    }

    @Override
    public boolean modifyEmail(ModifyEmailReq emailReq, Errors errors) {
        //校验邮箱是否重复
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority personAuthority = personAuthorityManager.selectByEmail(emailReq.getEmail());
        if (personAuthority != null && !personAuthority.getId().equals(session.getPersonId())) {
            errors.rejectValue("email", "email", "邮箱已被别人占用！");
        }
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setEmail(emailReq.getEmail());
        po.setModifyTime(Calendar.getInstance().getTime());
        return personAuthorityManager.updateByKey(po) == 1;
    }

    @Override
    public boolean modifyNickname(ModifyNicknameReq nicknameReq, Errors errors) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setNickname(nicknameReq.getNickname());
        po.setModifyTime(Calendar.getInstance().getTime());
        return personAuthorityManager.updateByKey(po) == 1;
    }

    @Override
    public boolean modifyMobile(ModifyMobileReq mobileReq, Errors errors) {
        //校验手机号是否重复
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority personAuthority = personAuthorityManager.selectByMobile(mobileReq.getMobile());
        if (personAuthority != null && !personAuthority.getId().equals(session.getPersonId())) {
            errors.rejectValue("mobile", "mobile", "手机号已被别人占用！");
        }
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setMobile(mobileReq.getMobile());
        po.setModifyTime(Calendar.getInstance().getTime());
        return personAuthorityManager.updateByKey(po) == 1;
    }

    @Override
    public boolean modifyBirthday(ModifyBirthdayReq birthdayReq, Errors errors) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setBirthday(birthdayReq.getBirthday());
        po.setModifyTime(Calendar.getInstance().getTime());
        return personAuthorityManager.updateByKey(po) == 1;
    }

    @Override
    public boolean modify(UserResp user, MultipartFile[] imgFiles) throws Exception {
        Date current = Calendar.getInstance().getTime();
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setNickname(user.getNickname());
        po.setBirthday(user.getBirthday());
        po.setEmail(user.getEmail());
        po.setModifyTime(current);

        String id = session.getPersonId();
        long baseOrderNum = ZhsOrderNumUtil.currentBaseNum();
        int start = 0;
        List<ZhsFileAuthorityGeneral> addFileList = new ArrayList<>();
        if (null == imgFiles || imgFiles.length < 1) {
        } else {
            for (int i = 0; i < imgFiles.length; i++) {
                MultipartFile mf = imgFiles[i];
                if (mf.getBytes() == null || mf.getBytes().length < 1) {
                    continue;
                }
                ZhsFileAuthorityGeneral f = new ZhsFileAuthorityGeneral();
                f.setServiceId(id);
                f.setServiceName(ServiceNameEnum.PersonAuthority.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum = baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(), f, FileType.HeadPic, mf, orderNum);
                if (writeFileSuccess) {
                    addFileList.add(f);
                }
            }
        }
        if (addFileList.size() > 0) {
            po.setHeadPic(addFileList.get(0).getId());
        }
        return personAuthorityManager.updateByKey(po, addFileList) == 1;
    }

    @Override
    public boolean modifyHeadPic(MultipartFile[] imgFiles) throws Exception {
        Date current = Calendar.getInstance().getTime();
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        PersonAuthority po = new PersonAuthority();
        po.setId(session.getPersonId());
        po.setModifyTime(current);

        String id = session.getPersonId();
        long baseOrderNum = ZhsOrderNumUtil.currentBaseNum();
        int start = 0;
        List<ZhsFileAuthorityGeneral> addFileList = new ArrayList<>();
        if (null == imgFiles || imgFiles.length < 1) {
        } else {
            for (int i = 0; i < imgFiles.length; i++) {
                MultipartFile mf = imgFiles[i];
                if (mf.getBytes() == null || mf.getBytes().length < 1) {
                    continue;
                }
                ZhsFileAuthorityGeneral f = new ZhsFileAuthorityGeneral();
                f.setServiceId(id);
                f.setServiceName(ServiceNameEnum.PersonAuthority.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum = baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(), f, FileType.HeadPic, mf, orderNum);
                if (writeFileSuccess) {
                    addFileList.add(f);
                }
            }
        }
        if (addFileList.size() > 0) {
            po.setHeadPic(addFileList.get(0).getId());
        } else {
            return false;
        }
        return personAuthorityManager.updateByKey(po, addFileList) == 1;
    }

}
