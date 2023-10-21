package com.vault.devfood.api.exceptionhandler;

import lombok.Getter;

import java.net.URI;


@Getter
public enum ProblemType {

    ENTITY_NOT_FOUND("Entity not found", "/entity-not-found"),
    ENTITY_IN_USE("Entity in Use", "/entity-in-use"),
    ERROR_BUSINESS("violation of business rules", "/business-error"),
    MESSAGE_NOT_READABLE("Message not readable", "/message-not-readable");


    //MessageNotReadable

    private String title;
    private URI uri;

    ProblemType(String title, String uri) {
        this.title = title;
        this.uri = URI.create("https://localhost:8080" + uri);
    }
}
