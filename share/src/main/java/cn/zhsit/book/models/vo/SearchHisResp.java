package cn.zhsit.book.models.vo;

import java.util.Date;


public class SearchHisResp {
    /**
     * ID
     */
    private String id;

    /**
     * 拥有者
     */
    private String personId;

    /**
     * 搜索关键字
     */
    private String searchKey;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public SearchHisResp setId(String id) {
        this.id = id;
        return this;
    }

    public String getPersonId() {
        return personId;
    }

    public SearchHisResp setPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public SearchHisResp setSearchKey(String searchKey) {
        this.searchKey = searchKey;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SearchHisResp setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
