package cn.zhsit.book.services;

import cn.zhsit.authority.enums.AuthTypeEnum;
import cn.zhsit.authority.models.vo.OrgAuthReq;
import cn.zhsit.authority.models.vo.OrgAuthReqPc;
import cn.zhsit.authority.models.vo.PersonAuthReq;
import cn.zhsit.book.models.po.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface AuthenticationService {
    Authentication find(AuthTypeEnum type, String personId);
    Authentication find(String personId);

    boolean createPersonAuth(PersonAuthReq authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException;

    boolean createOrgAuth(OrgAuthReq authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException;
    boolean createOrgAuthPc(OrgAuthReqPc authReq, Errors errors, MultipartFile imgFront, MultipartFile imgBack) throws IOException;
}
