package com.yevhenii.kpi.readmore.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreationResponse {

    private Long id;

    public BookCreationResponse() {
    }

    public BookCreationResponse(Long id) {
        this.id = id;
    }
}
