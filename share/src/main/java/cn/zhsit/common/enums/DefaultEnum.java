package cn.zhsit.common.enums;


public enum DefaultEnum {
    //是默认
    isDefault((byte)2, true),
    //不是默认
    notDefault((byte)1, false);

    private byte code;
    private boolean bool;

    DefaultEnum(byte code,boolean bool) {
        this.bool = bool;
        this.code = code;
    }

    public static DefaultEnum of(Byte code) {
        if (code == null) {
            return notDefault;
        }
        if (code == 2) {
            return isDefault;
        }
        return notDefault;
    }

    public byte getCode() {
        return code;
    }

    public boolean isBool() {
        return bool;
    }
}
