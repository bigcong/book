package cn.zhsit.book.models.vo;

import java.util.Date;


public class BookResp {
    /**
     * ID
     */
    private String id;

    /**
     * 拥有者
     */
    private String personId;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * 出版商
     */
    private String publishers;

    /**
     * 版本号/页数
     */
    private String pages;

    /**
     * 添加文字说明
     */
    private String descr;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 图地址
     */
    private String path;

    public String getId() {
        return id;
    }

    public BookResp setId(String id) {
        this.id = id;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public BookResp setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookResp setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookResp setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookResp setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getPublishers() {
        return publishers;
    }

    public BookResp setPublishers(String publishers) {
        this.publishers = publishers;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public BookResp setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public String getDescr() {
        return descr;
    }

    public BookResp setDescr(String descr) {
        this.descr = descr;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BookResp setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public BookResp setPath(String path) {
        this.path = path;
        return this;
    }
}
