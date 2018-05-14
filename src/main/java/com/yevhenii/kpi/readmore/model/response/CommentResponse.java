package com.yevhenii.kpi.readmore.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponse {

    private Long id;
    private Integer rating;
    private String description;
    private String author;
    private Boolean deletable;

    public CommentResponse() {
    }

    public CommentResponse(Long id, Integer rating, String description, String author, Boolean deletable) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.author = author;
        this.deletable = deletable;
    }
}
