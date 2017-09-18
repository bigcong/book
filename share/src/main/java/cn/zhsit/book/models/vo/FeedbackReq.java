package cn.zhsit.book.models.vo;


public class FeedbackReq {
    /**
     * 意见反馈内容
     */
    private String content;

    /**
     * 联系电话
     */
    private String tel;

    public String getContent() {
        return content;
    }

    public FeedbackReq setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public FeedbackReq setTel(String tel) {
        this.tel = tel;
        return this;
    }
}
