package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.UserDTO;
import com.br.gestaodelicencadesoftware.entities.UserEntity;
import com.br.gestaodelicencadesoftware.enumeratedTypes.UsersRoles;
import com.br.gestaodelicencadesoftware.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserCudServiceTest {

    private UserRepository userRepository;
    private UserCudService userCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        userCudService = new UserCudService(userRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setName("João Silva");
        userDTO.setEmail("joao.silva@example.com");
        userDTO.setHashPassword("password123");
        userDTO.setRoleCode(2);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("João Silva");
        userEntity.setEmail("joao.silva@example.com");
        userEntity.setHashPassword("password123");
        userEntity.setRole(UsersRoles.USER);
        userEntity.setIsDeleted(false);

        when(userRepository.existsByEmail("joao.silva@example.com")).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        ObjectReturnDefault result = userCudService.save(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Usuário cadastrado com sucesso", result.getMessage());

        UserDTO savedUser = (UserDTO) result.getObject();
        assertEquals("João Silva", savedUser.getName());
        assertEquals("joao.silva@example.com", savedUser.getEmail());
        assertEquals(UsersRoles.USER.getCode(), savedUser.getRoleCode());
    }

    @Test
    void readSuccessTest() throws Exception {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("João Silva");
        userEntity.setEmail("joao.silva@example.com");
        userEntity.setHashPassword("password123");
        userEntity.setRole(UsersRoles.USER);
        userEntity.setIsDeleted(false);

        when(userRepository.findByEmail("joao.silva@example.com")).thenReturn(userEntity);

        // Act
        ObjectReturnDefault result = userCudService.read(new UserDTO("joao.silva@example.com"));

        // Assert
        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());
    }

    @Test
    void deleteSuccessTest() throws Exception {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("João Silva");
        userEntity.setEmail("joao.silva@example.com");
        userEntity.setRole(UsersRoles.USER);
        userEntity.setIsDeleted(false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        ObjectReturnDefault result = userCudService.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Usuário deletado com sucesso", result.getMessage());
    }

    @Test
    void updateRoleSuccessTest() throws Exception {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("João Silva");
        userEntity.setEmail("joao.silva@example.com");
        userEntity.setRole(UsersRoles.USER);
        userEntity.setIsDeleted(false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        ObjectReturnDefault result = userCudService.updateRole(1L, UsersRoles.USER);

        // Assert
        assertNotNull(result);
        assertEquals("Perfil do usuário atualizado com sucesso", result.getMessage());
    }
}
