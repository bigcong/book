package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


public class ModifyBirthdayReq {

    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public ModifyBirthdayReq setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
