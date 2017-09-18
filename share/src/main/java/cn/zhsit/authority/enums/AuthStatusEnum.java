package cn.zhsit.authority.enums;

public enum AuthStatusEnum {
    //    认证状态:1，未认证；2，认证审核中；3，认证通过；
//    认证结果：1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；
    NoAuth(new Byte("1"))
    ,Authing(new Byte("2"))
    ,AuthOK(new Byte("3"))
    ,AuthNoPass(new Byte("4"))
    ;
    private Byte code;

    AuthStatusEnum(Byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }
}
