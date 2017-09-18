package cn.zhsit.authority.services;

import cn.zhsit.authority.interceptors.models.Msg;
import cn.zhsit.authority.models.vo.*;
import cn.zhsit.common.exceptions.ZhsException;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;


public interface PersonAuthorityService {

    /**
     * 校验密码是否正确
     *
     * @param person
     * @param msg
     * @return true, 登录名密码匹配；
     * @throws ZhsException
     */
    public boolean checkPwd(RegisterReq person, Msg msg) throws ZhsException;

    /**
     * @param person
     * @param errors
     * @return true success;
     * @throws ZhsException
     */
    public boolean createPerson(RegisterReq person, Errors errors) throws ZhsException;

    boolean login(LoginReq loginReq, Errors errors) throws ZhsException;

    boolean login(LoginReqPc loginReq, Errors errors) throws ZhsException;

    boolean logout();

    boolean modifyPwd(AccountReq accountReq, Errors errors);

    UserResp findCurrentUser();

    UserResp findUserById(String personId);


    boolean modifyEmail(ModifyEmailReq emailReq, Errors errors);

    boolean modifyNickname(ModifyNicknameReq nicknameReq, Errors errors);

    boolean modifyMobile(ModifyMobileReq mobileReq, Errors errors);

    boolean modifyBirthday(ModifyBirthdayReq birthdayReq, Errors errors);

    boolean modify(UserResp user, MultipartFile[] imgFiles) throws Exception;

    boolean modifyHeadPic(MultipartFile[] imgFiles)throws Exception;
}
