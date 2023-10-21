package com.vault.devfood.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.net.URI;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private static final URI BLANK_TYPE = URI.create("about:blank");

    private URI type;

    @Nullable
    private String title;

    @Getter
    private int status;

    @Nullable
    private String detail;

}
