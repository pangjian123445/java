package com.example.demo.common.response;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author qincq
 * @since 2022-2-8
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse implements Serializable {
    private static final long serialVersionUID = 1681725324966798212L;
    /**
     * 数据总条数
     */
    private int total;

    /**
     * 数据记录
     */
    private Object records;

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 每页的数量
     */
    private int pageSize;

    /**
     * 当前页的数量
     */
    private int size;

    /**
     * 总页数
     */
    private int pages;


    public PageResponse setPageInfo(PageInfo pageInfo) {
        this.total = (int) pageInfo.getTotal();
        this.records = pageInfo.getList();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.size = pageInfo.getSize();
        this.pages = pageInfo.getPages();
        return this;
    }

    public PageResponse setTotal(int total) {
        this.total = total;
        return this;
    }

    public PageResponse setRecords(Object records) {
        this.records = records;
        return this;
    }

    public PageResponse setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public PageResponse setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageResponse setSize(int size) {
        this.size = size;
        return this;
    }

    public PageResponse setPages(int pages) {
        this.pages = pages;
        return this;
    }
}