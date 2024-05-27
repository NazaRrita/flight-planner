package io.codelex.flightplanner.domain;

import java.util.List;
import java.util.Objects;

public class PageResult<T> {
    private Integer page;
    private Integer totalItems;
    private List<T> items;

    public PageResult(Integer page, Integer totalItems, List<T> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResult that = (PageResult) o;
        return Objects.equals(page, that.page) && Objects.equals(totalItems, that.totalItems) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, totalItems, items);
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "page=" + page +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
