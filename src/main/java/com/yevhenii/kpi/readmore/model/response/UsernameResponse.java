package com.yevhenii.kpi.readmore.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameResponse {
    private String username;

    public UsernameResponse() {
    }

    public UsernameResponse(String username) {
        this.username = username;
    }
}
