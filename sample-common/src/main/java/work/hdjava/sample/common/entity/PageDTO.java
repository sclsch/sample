package work.hdjava.sample.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @Author: suncl
 * @Date: 2021/7/9 18:41
 */
public class PageDTO<T> implements Serializable {

    private List<T> list;
    /**
     * 当前页
     */
    private long page;
    /**
     * 页大小
     */
    private long limit;
    private long totalPage;
    private long count;


    public PageDTO(List<T> content, long page, long pageSize, long totalPage) {
        this.list = content;
        this.page = page;
        this.limit = pageSize;
        this.totalPage = totalPage;
    }

    public PageDTO(List<T> content, long page, long pageSize, long totalPage,long totalElements) {
        this.list = content;
        this.page = page;
        this.limit = pageSize;
        this.totalPage = totalPage;
        this.count = totalElements;
    }


    public PageDTO() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
