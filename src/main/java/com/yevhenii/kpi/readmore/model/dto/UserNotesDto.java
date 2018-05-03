package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserNotesDto {

    private String notes;
    @NotNull
    private Long bookId;

    public UserNotesDto() {
    }

    public UserNotesDto(String notes, Long bookId) {
        this.notes = notes;
        this.bookId = bookId;
    }
}
