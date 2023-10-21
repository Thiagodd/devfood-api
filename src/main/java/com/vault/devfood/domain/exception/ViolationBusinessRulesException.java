package com.vault.devfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ViolationBusinessRulesException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6833690666460129933L;

    public ViolationBusinessRulesException(String message) {
        super(message);
    }

    public ViolationBusinessRulesException(Throwable cause) {
        super(cause);
    }
}
