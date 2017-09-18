package cn.zhsit.common.enums;


public enum FileType {
    HeadPic("head", "头像")
    ,Book("book","图书")
    ,ID("id","证件")
    ,Feedback("feedback","反馈文件")
    ,Authentication("authentication","认证")
    ;
    private String type;
    private String descr;

    private FileType(String type, String descr) {
        this.type = type;
        this.descr = descr;
    }

    public String getType() {
        return type;
    }
}
