package io.github.julianomachadoo.rest;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro) {
        this.errors = Collections.singletonList(mensagemErro);
    }
}
