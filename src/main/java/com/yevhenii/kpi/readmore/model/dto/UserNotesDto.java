package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

@Data
public class UserNotesDto {

    private String notes;
    private Long bookId;

    public UserNotesDto() {
    }

    public UserNotesDto(String notes, Long bookId) {
        this.notes = notes;
        this.bookId = bookId;
    }
}
