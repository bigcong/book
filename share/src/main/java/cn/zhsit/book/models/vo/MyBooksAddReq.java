package cn.zhsit.book.models.vo;

import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import cn.zhsit.generator.models.vo.FileReq;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class MyBooksAddReq {
    private BooksUploaded booksUploaded;
    /**
     * ID
     */
    private String id;
    /**
     * 书名
     */
    @NotBlank(message = "书名不能为空")
    private String name;

    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    private String author;


    /**
     * 出版商
     */
    @NotBlank(message = "出版商不能为空")
    private String publishers;
    /**
     * 版本号/页数
     */
    @NotBlank(message = "版本号/页数不能为空")
    private String pages;

    /**
     * ISBN
     */
    @NotBlank(message = "ISBN不能为空")
    private String isbn;


    @NotBlank(message = "地域不能为空")
    private String area;


    /**
     * 添加文字说明
     */
    private String descr;

    /**
     * 图片列表
     */
    private List<FileReq> fileReqList;
    private String firstPath;
    /**
     * 是否收藏本书
     */
    private boolean collect = false;
    /**
     * 用户是否认证状态:1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；
     */
    private Byte authStatus;

    /**
     * 用户的手机号
     */
    private String mobile;


    public BooksUploaded getBooksUploaded() {
        return booksUploaded;
    }

    public void setBooksUploaded(BooksUploaded booksUploaded) {
        this.booksUploaded = booksUploaded;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setFileReqList(List<FileReq> fileReqList) {
        this.fileReqList = fileReqList;
    }

    /**
     * 拥有者
     */
    private String personId;

    public String getName() {
        return name;
    }

    public MyBooksAddReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MyBooksAddReq setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublishers() {
        return publishers;
    }

    public MyBooksAddReq setPublishers(String publishers) {
        this.publishers = publishers;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public MyBooksAddReq setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public MyBooksAddReq setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDescr() {
        return descr;
    }

    public MyBooksAddReq setDescr(String descr) {
        this.descr = descr;
        return this;
    }

    public boolean isCollect() {
        return collect;
    }

    public MyBooksAddReq setCollect(boolean collect) {
        this.collect = collect;
        return this;
    }

    public List<FileReq> getFileReqList() {
        return fileReqList;
    }


    public MyBooksAddReq addFileReqList(List<ZhsFileGeneral> poList) {
        if (fileReqList == null) {
            fileReqList = new ArrayList<>();
        }
        for (ZhsFileGeneral po : poList) {
            FileReq req = new FileReq();
            BeanUtils.copyProperties(po, req);
//            req.setPath(po.getLocation() + "/" + po.getName());
            req.setPath(po.getLocation() + "/" + po.getThumbnail());
            fileReqList.add(req);
        }
        if (poList.size() > 0) {
            firstPath = poList.get(0).getLocation() + "/" + poList.get(0).getThumbnail();
        }
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public MyBooksAddReq setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public MyBooksAddReq setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public MyBooksAddReq setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getId() {
        return id;
    }

    public MyBooksAddReq setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstPath() {
        return firstPath;
    }

    public MyBooksAddReq setFirstPath(String firstPath) {
        this.firstPath = firstPath;
        return this;
    }
}
