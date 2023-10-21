package com.vault.devfood.api.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Component
public class UriUtils {

    public static URI createURI(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public static URI createURI() {
        return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    }
}
