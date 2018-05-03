package com.yevhenii.kpi.readmore.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtils {
    public static ResponseEntity<Void> okOrBadRequest(Boolean success) {

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
