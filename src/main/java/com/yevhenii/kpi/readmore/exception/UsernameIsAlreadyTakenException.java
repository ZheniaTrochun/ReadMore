package com.yevhenii.kpi.readmore.exception;

public class UsernameIsAlreadyTakenException extends Exception {
    public UsernameIsAlreadyTakenException() {
        super("Username is already taken, use another one, pls :)");
    }
}
