package cn.zhsit.common.utils.page;

import java.io.Serializable;


public class Page implements Serializable {

    private long page = 0;
    private Long total=0L;
    private long rows = 10;
    private long showPageSize = 9;
    private long firstPageNum = 0;
    private long lastPageNum = 0;
    private String order;

    private String sort;


    public Long getTotalPageSize() {
        if (total==null||total < 1) {
            return 0L;
        }
        double totalDouble = Double.valueOf(total);
        double rowsDouble = Double.valueOf(rows);
        Double totalPageSizeDouble = Math.ceil(totalDouble / rowsDouble);
        return totalPageSizeDouble.longValue();
    }


    public Long getPage() {
        if (total==null||total < 1) {
            return page;
        }

        long totalPageSize = getTotalPageSize()-1;
        if (page >= totalPageSize) {
            page = totalPageSize;
        }
        if (page < 0) {
            page = 0;
        }
        return page;
    }

    public Page setPage(int page) {
        this.page = page;
        return this;
    }

    public long getRows() {
        return rows;
    }

    public Page setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public Page setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public Page setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public Long getTotal() {
        if (null == total) {
            return 0L;
        }
        return total;
    }

    public Page setTotal(Long total) {
        this.total = total;
        return this;
    }



    public Page setShowPageSize(long showPageSize) {
        this.showPageSize = showPageSize;
        return this;
    }

    public long getFirstPageNum() {
        long halfShowPageSize = showPageSize / 2;
        if ((getPage() + halfShowPageSize) <= getTotalPageSize()) {
            firstPageNum = getPage() - halfShowPageSize;
        } else {
            long lastPageNum = getTotalPageSize();
            firstPageNum = lastPageNum - showPageSize;

        }
        if (firstPageNum < 0) {
            firstPageNum = 0;
        }
        return firstPageNum;
    }

    public long getLastPageNum() {
        long halfShowPageSize = showPageSize / 2;
        if ((getPage() + halfShowPageSize) <= getTotalPageSize()) {
            long firstPageNum = getPage() - halfShowPageSize;
            if (firstPageNum < 0) {
                firstPageNum = 0;
            }
            lastPageNum = firstPageNum + showPageSize;
            if (lastPageNum >= getTotalPageSize()) {
                lastPageNum = getTotalPageSize()-1;
            }
        } else {
            lastPageNum = getTotalPageSize()-1;
        }
        if(lastPageNum<0){
            lastPageNum=0;
        }
        return lastPageNum;
    }

}
