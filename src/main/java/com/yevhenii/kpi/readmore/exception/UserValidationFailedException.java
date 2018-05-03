package com.yevhenii.kpi.readmore.exception;

public class UserValidationFailedException extends RegistrationException {
    public UserValidationFailedException() {
        super("Username, email and password must not be empty!");
    }
}
