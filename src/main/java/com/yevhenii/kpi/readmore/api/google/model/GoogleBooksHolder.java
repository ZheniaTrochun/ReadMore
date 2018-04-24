package com.yevhenii.kpi.readmore.api.google.model;

import java.util.List;

public class GoogleBooksHolder {

    private String kind;

    private List<GoogleBookResponse> items;

    public GoogleBooksHolder() {
    }

    public GoogleBooksHolder(String kind, List<GoogleBookResponse> items) {
        this.kind = kind;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<GoogleBookResponse> getItems() {
        return items;
    }

    public void setItems(List<GoogleBookResponse> items) {
        this.items = items;
    }
}
