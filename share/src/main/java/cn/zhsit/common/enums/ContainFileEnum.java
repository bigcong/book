package cn.zhsit.common.enums;


public enum ContainFileEnum {

    NoContain((byte) 1, "不含附件"), Contain((byte) 2, "含附件");
    private byte type;
    private String descr;

    private ContainFileEnum(byte type, String descr) {
        this.type = type;
        this.descr = descr;
    }

    public byte getType() {
        return type;
    }
}
