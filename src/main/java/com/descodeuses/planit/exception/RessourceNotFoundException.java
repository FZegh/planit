package com.descodeuses.planit.exception;

import java.io.Serial;

public class RessourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RessourceNotFoundException(String message) {
        super(message);
    }


}
