package cn.zhsit.common.enums;


public enum ServiceNameEnum {

    Feedback("t_feedback", "反馈")
    , Authentication("t_authentication", "认证")
    ,BooksUploaded("t_books_uploaded","上传书籍表")
    ,BookDesired("t_book_desired","心愿书籍表")
    ,PersonAuthority("t_person_authority","用户表")
    ;
    private String service;
    private String descr;

    ServiceNameEnum(String service, String descr) {
        this.service = service;
        this.descr = descr;
    }

    public String getService() {
        return service;
    }

    public static ServiceNameEnum of(String service) {
        ServiceNameEnum[] sns = ServiceNameEnum.values();
        for (int i = 0; i < sns.length; i++) {
            ServiceNameEnum sn = sns[i];
            if (sn.getService().equals(service)) return sn;
        }
        return null;
    }
}
