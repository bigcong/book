package cn.zhsit.authority.enums;


public enum AuthTypeEnum {
    //    认证类别:1,个人；2，单位；
    Person(new Byte("1"))
    ,Org(new Byte("2"))
    ;
    private Byte code;

    AuthTypeEnum(Byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }
}
