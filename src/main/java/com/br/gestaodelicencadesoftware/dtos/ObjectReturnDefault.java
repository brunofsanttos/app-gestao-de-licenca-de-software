package com.br.gestaodelicencadesoftware.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ObjectReturnDefault implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private Object object;

    public ObjectReturnDefault(String message) {
        this.message = message;
    }

    public ObjectReturnDefault(String message, Object object) {
        this.message = message;
        this.object = object;
    }
}
