package cn.zhsit.book.models.vo;


public class CollectionReq {


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
     * 图片路径
     */
    private String path;

    public String getPersonId() {
        return personId;
    }

    public CollectionReq setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getBooksUploadedId() {
        return booksUploadedId;
    }

    public CollectionReq setBooksUploadedId(String booksUploadedId) {
        this.booksUploadedId = booksUploadedId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CollectionReq setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPath() {
        return path;
    }

    public CollectionReq setPath(String path) {
        this.path = path;
        return this;
    }
}
