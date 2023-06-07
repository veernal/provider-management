package com.providermanagement.provider.exception;

import java.time.LocalDateTime;

public class ProviderException extends Exception{

    private static final long serialVersionUID = 2065916542403224036L;

    private final String message;
    private final LocalDateTime time;


    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ProviderException(String message) {
        super();
        this.message = message;
        this.time = LocalDateTime.now();
    }


}
