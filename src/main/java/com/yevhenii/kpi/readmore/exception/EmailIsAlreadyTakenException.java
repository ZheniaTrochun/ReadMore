package com.yevhenii.kpi.readmore.exception;

public class EmailIsAlreadyTakenException extends RegistrationException {
    public EmailIsAlreadyTakenException() {
        super("Email is already taken, use another one, pls :)");
    }
}
