package cn.zhsit.authority.enums;


public enum SearchType {
    JiaoHuan("jiaohuan","交换书籍")
    , XinYuan("xinyuan", "心愿书籍")
    ;
    private String code;
    private String descr;

    SearchType(String code, String descr) {
        this.code = code;
        this.descr = descr;
    }

    public String getCode() {
        return code;
    }
}
