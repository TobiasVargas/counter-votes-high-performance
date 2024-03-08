package br.dev.tobias.domain.dto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public class PageDTO<T> {
    private final int size;
    private final int index;
    private final int pageCount;
    private final Long total;
    private final List<T> data;

    public PageDTO(int size, int index, int pageCount, Long total, List<T> data) {
        this.size = size;
        this.index = index;
        this.pageCount = pageCount;
        this.total = total;
        this.data = data;
    }

    public PageDTO(PanacheQuery<?> query, List<T> data) {
        this(query.page().size, query.page().index, query.pageCount(), query.stream().count(), data);
    }

    public int getSize() {
        return size;
    }

    public int getIndex() {
        return index;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }
}
