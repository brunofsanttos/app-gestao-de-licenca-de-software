package com.br.gestaodelicencadesoftware.dtos;

public record ObjectReturnDefault(String message, Object object) {
    public ObjectReturnDefault(String message) {
        this(message, null);
    }
}
