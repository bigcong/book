package cn.zhsit.common.utils.page;
import java.util.ArrayList;
import java.util.List;



public class ReturnPage<T> extends ArrayList<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int DEFAULT_PAGE_SIZE = 15;
    private int DEFAULT_CURRENTPAGE = 1;
    private int DEFAULT_START = 0;

    private int pageSize;
    private int currentPage;
    private int totalPages;
    private int totalCount;
    private int start;

    public ReturnPage(int totalCount, int pageSize, List<T> objList) {
        this.addAll(objList);
        this.init(totalCount, pageSize);
    }

    public ReturnPage(List<T> objList) {
        this.addAll(objList);
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.currentPage = DEFAULT_CURRENTPAGE;
        this.start=DEFAULT_START;
    }


    public List<T> getObjList() {
        return this;
    }


    private void init(int totalCount, int pageSize) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        if ((totalCount % pageSize) == 0) {
            totalPages = totalCount / pageSize;
        } else {
            totalPages = totalCount / pageSize + 1;
        }

    }

    public void init(int totalCount, int pageSize, int currentPage) {
        this.currentPage = currentPage;
        this.init(totalCount, pageSize);
    }

    public static<T> ReturnPage<T> getInstanceByStartAndLimit(Integer start, Integer limit, List<T> objList) {
        ReturnPage<T> page = new ReturnPage<T>(objList);
        Integer currentPage = page.DEFAULT_CURRENTPAGE;
        Integer pageSize = page.DEFAULT_PAGE_SIZE;
        if (limit!=null) {
            pageSize = limit;
        }
        if (start!=null) {
            page.setStart(start);
            currentPage = start/ pageSize + 1;
        }
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public int getStart() {
        return start;
    }


    public void setStart(int start) {
        this.start = start;
    }
}
