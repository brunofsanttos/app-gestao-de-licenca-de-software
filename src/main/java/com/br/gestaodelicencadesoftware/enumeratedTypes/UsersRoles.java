package com.br.gestaodelicencadesoftware.enumeratedTypes;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UsersRoles {
    ADMIN(1),
    USER(2);

    private final int code;

    UsersRoles(int code) {
        this.code = code;
    }

    public static UsersRoles fromCode(int code) {
        return Arrays.stream(values())
                .filter(role -> role.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código de role inválido: " + code));
    }

}
