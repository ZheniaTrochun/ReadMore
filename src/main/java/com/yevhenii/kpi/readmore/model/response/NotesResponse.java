package com.yevhenii.kpi.readmore.model.response;

import lombok.Data;

@Data
public class NotesResponse {
    private String notes;

    public NotesResponse(String notes) {
        this.notes = notes;
    }

    public NotesResponse() {
    }
}
