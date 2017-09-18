package cn.zhsit.book.models.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


public class MyBooksListReq {
    /**
     * ID
     */
    private String id;

    /**
     * 书名
     */

    private String name;

    /**
     * 作者
     */
    private String author;


    /**
     * 出版商
     */
    private String publishers;
    /**
     * 版本号/页数
     */
    private String pages;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * 添加文字说明
     */
    private String descr;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 封面图片地址
     */
//    private String coverPicPath;
    private String path;

    public String getName() {
        return name;
    }

    public MyBooksListReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MyBooksListReq setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublishers() {
        return publishers;
    }

    public MyBooksListReq setPublishers(String publishers) {
        this.publishers = publishers;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public MyBooksListReq setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public MyBooksListReq setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDescr() {
        return descr;
    }

    public MyBooksListReq setDescr(String descr) {
        this.descr = descr;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MyBooksListReq setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

//    public String getCoverPicPath() {
//        return coverPicPath;
//    }
//
//    public MyBooksListReq setCoverPicPath(String coverPicPath) {
//        this.coverPicPath = coverPicPath;
//        return this;
//    }


    public String getPath() {
        return path;
    }

    public MyBooksListReq setPath(String path) {
        this.path = path;
        return this;
    }

    public String getId() {
        return id;
    }

    public MyBooksListReq setId(String id) {
        this.id = id;
        return this;
    }
}
