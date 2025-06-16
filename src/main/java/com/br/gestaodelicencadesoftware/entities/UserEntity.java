package com.br.gestaodelicencadesoftware.entities;

import com.br.gestaodelicencadesoftware.enumeratedTypes.UsersRoles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String email;

    private String hashPassword;

    private UsersRoles role;

    @OneToMany(mappedBy = "responseId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LicenseEntity> licenses = new ArrayList<>();

    private Boolean isDeleted;
}
