package cn.zhsit.authority.models.vo;

import org.hibernate.validator.constraints.NotBlank;


public class ModifyNicknameReq {
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不可为空")
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public ModifyNicknameReq setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
