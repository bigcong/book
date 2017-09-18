package cn.zhsit.book.models.vo;

import cn.zhsit.book.models.po.BookDesired;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


public class BookDesiredReq {
    private BookDesired t;

    private String id;

    /**
     * 书名
     */
    @NotBlank(message = "书名不能为空")
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
     * 图路径
     */
    private String path;

    public String getId() {
        return id;
    }

    public BookDesiredReq setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookDesiredReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDesiredReq setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BookDesiredReq setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public BookDesiredReq setPath(String path) {
        this.path = path;
        return this;
    }
}
