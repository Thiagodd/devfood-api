package com.vault.devfood.domain.exception;

public class EntityInUseException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Entity in use. Check the entity name provided and ensure that no other entities depend on it.";

    public EntityInUseException() {
        super(DEFAULT_MESSAGE);
    }


    public EntityInUseException(Exception cause) {
        super(cause);
    }

    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String message, Exception cause) {
        super(message, cause);
    }
}
