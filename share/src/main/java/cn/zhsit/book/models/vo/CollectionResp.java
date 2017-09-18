package cn.zhsit.book.models.vo;

import java.util.Date;


public class CollectionResp {
    /**
     * ID
     */
    private String id;
    //状态
    private boolean rstatus = false;
    //返回给页面给用户显示的消息
    private String msg;

    /**
     * 收藏者
     */
    private String personId;

    /**
     * 上传书籍表id
     */
    private String booksUploadedId;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 图片地址
     */
    private String path;
    public String getMsg() {
        return msg;
    }

    public CollectionResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isRstatus() {
        return rstatus;
    }

    public CollectionResp setRstatus(boolean rstatus) {
        this.rstatus = rstatus;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public CollectionResp setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getBooksUploadedId() {
        return booksUploadedId;
    }

    public CollectionResp setBooksUploadedId(String booksUploadedId) {
        this.booksUploadedId = booksUploadedId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionResp setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CollectionResp setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPath() {
        return path;
    }

    public CollectionResp setPath(String path) {
        this.path = path;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CollectionResp setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getId() {
        return id;
    }

    public CollectionResp setId(String id) {
        this.id = id;
        return this;
    }
}
