package cn.zhsit.book.models.po;

import java.io.Serializable;

/**
* 冯先生 
* 61947666@qq.com 
* 15652963646 
*/
public class BooksHot implements Serializable {
    /**
     * 
     */
    private String id;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}