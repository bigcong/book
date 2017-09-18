package cn.zhsit.authority.enums;


public enum ReadStatusEnum {
    NoRead((byte) 1,"未读"),
    Read((byte)2,"已读");

    private byte code;
    private String descr;


    private ReadStatusEnum (byte code,String descr) {
        this.code = code;
        this.descr=descr;
    }

    public byte getCode() {
        return code;
    }
}
