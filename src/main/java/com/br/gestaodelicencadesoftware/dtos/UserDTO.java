package com.br.gestaodelicencadesoftware.dtos;

import com.br.gestaodelicencadesoftware.entities.LicenseEntity;
import com.br.gestaodelicencadesoftware.enumeratedTypes.UsersRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String hashPassword;
    private int roleCode;
    private List<LicenseEntity> licenses = new ArrayList<>();
    private Boolean isDeleted;

    public UserDTO(String name, String email, int role) {
        this.name = name;
        this.email = email;
        this.roleCode = role;
    }

    public UserDTO(String email) {
        this.email = email;
    }
}
