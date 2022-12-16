package ru.geekbrains.store.api;

import java.util.List;

public class PageDto<E> {

    public List<E> items;
    public int page;

    private int totalPages;

    public PageDto() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<E> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
