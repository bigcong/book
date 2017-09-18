package cn.zhsit.authority.api.models;

/**
 * Created by Darren on 2017/6/28.
 */
public class Person {

    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 人员类型:1,客户;2职工;3软件系统管理者
     */
    private Byte personType;

    public String getLoginName() {
        return loginName;
    }

    public Person setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public Person setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public Byte getPersonType() {
        return personType;
    }

    public Person setPersonType(Byte personType) {
        this.personType = personType;
        return this;
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", personType=" + personType +
                '}';
    }
}
