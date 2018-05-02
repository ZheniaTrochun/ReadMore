package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

@Data
public class FindBookDto {
    private String name;
    private String author;

    public FindBookDto() {
    }

    public FindBookDto(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
