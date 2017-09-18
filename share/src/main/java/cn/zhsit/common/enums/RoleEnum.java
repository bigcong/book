package cn.zhsit.common.enums;


public enum RoleEnum {
    //    人员类型:1超级权限人员;
    Super("super", "超级权限人员",null,1),
    ;
    private String code;
    private String name;
    private String descr;
    private int orderNum;

    RoleEnum(String code, String name, String descr, int orderNum) {
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.orderNum=orderNum;
    }

    public String getCode() {
        return code;
    }

    public String getDescr() {
        return descr;
    }

    public String getName() {
        return name;
    }

    public int getOrderNum() {
        return orderNum;
    }
}
