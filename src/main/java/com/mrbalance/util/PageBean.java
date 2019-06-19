package com.mrbalance.util;

import java.util.List;


/**
 * @author: 杜云章
 * @description:
 * @Date: 2019-06-11 17:47
 */
public class PageBean<T> {
    private List<T> rows;
    private Integer currentPage = Integer.valueOf(1);
    private Integer pageSize = Integer.valueOf(10);
    private Integer total;

    public int getPageCount() {
        if (this.total.intValue() % this.pageSize.intValue() == 0) {
            return this.total.intValue() / this.pageSize.intValue();
        }
        return this.total.intValue() / this.pageSize.intValue() + 1;
    }

    public PageBean(List<T> rows, Integer total) {
        this.rows = rows;
        this.total = total;
    }

    public PageBean(List<T> rows, Integer total, Integer currentPage, Integer pageSize) {
        this.rows = rows;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageBean() {
    }

    public boolean isFirst() {
        return (this.currentPage.intValue() == 1) || (this.total.intValue() == 0);
    }

    public boolean isLast() {
        return (this.total.intValue() == 0) || (this.currentPage.intValue() >= getPageCount());
    }

    public boolean isHasNext() {
        return this.currentPage.intValue() < getPageCount();
    }

    public boolean isHasPrev() {
        return this.currentPage.intValue() > 1;
    }

    public Integer getNextPage() {
        if (this.currentPage.intValue() >= getPageCount()) {
            return Integer.valueOf(getPageCount());
        }
        return Integer.valueOf(this.currentPage.intValue() + 1);
    }

    public Integer getPrevPage() {
        if (this.currentPage.intValue() <= 1) {
            return Integer.valueOf(1);
        }
        return Integer.valueOf(this.currentPage.intValue() - 1);
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(null == rows){
            sb.append("rows: ''");
        } else {
            sb.append("rows: ").append(rows.toString());
        }
        sb.append(", currentPage: ").append(currentPage);
        sb.append(", pageSize: ").append(pageSize);
        sb.append(", total: ").append(total);
        sb.append("]");
        return sb.toString();
    }
}
