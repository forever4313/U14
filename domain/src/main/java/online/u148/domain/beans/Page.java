package online.u148.domain.beans;

/**
 * @author: Kevin Dong
 * @date:2016/4/8
 * @email:dongkai@nodescm.com
 */
public class Page {


    public static int DEFAULT_PAGE_SIZE = 5;
    /**
     * pageSize : 5
     * curPage : 1
     * totalPageCount : 1
     * totalCount : 5
     */

    private int pageSize =  DEFAULT_PAGE_SIZE;
    private int curPage;
    private int totalPageCount;
    private int totalCount;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
