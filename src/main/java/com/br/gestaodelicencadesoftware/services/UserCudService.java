package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.UserDTO;
import com.br.gestaodelicencadesoftware.entities.UserEntity;
import com.br.gestaodelicencadesoftware.enumeratedTypes.UsersRoles;
import com.br.gestaodelicencadesoftware.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCudService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserCudService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ObjectReturnDefault save(UserDTO userDTO) throws Exception {
        this.validate(userDTO);

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        // Definir role padrão se não informada
        if (userEntity.getRole() == null) {
            userEntity.setRole(UsersRoles.fromCode(userDTO.getRoleCode()));
        }

        // Garantir que o campo isDeleted seja false para novos usuários
        userEntity.setIsDeleted(false);

        userEntity = userRepository.save(userEntity);

        return new ObjectReturnDefault("Usuário cadastrado com sucesso", modelMapper.map(userEntity, UserDTO.class));
    }

    public ObjectReturnDefault read(UserDTO userDTO) throws Exception {
        this.validateParamsSearch(userDTO);

        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {
            return new ObjectReturnDefault("Nenhum usuário encontrado com o email fornecido.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMapper.map(user, UserDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("Parâmetro inválido");
        }

        user.get().setIsDeleted(true);
        userRepository.save(user.get());

        return new ObjectReturnDefault("Usuário deletado com sucesso");
    }

    public ObjectReturnDefault updateRole(Long id, UsersRoles role) throws Exception {
        if (role != UsersRoles.ADMIN && role != UsersRoles.USER) {
            throw new Exception("O perfil deve ser ADMIN ou USER");
        }

        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        user.get().setRole(role);
        userRepository.save(user.get());

        return new ObjectReturnDefault("Perfil do usuário atualizado com sucesso", modelMapper.map(user.get(), UserDTO.class));
    }

    private void validate(UserDTO userDTO) throws Exception {
        if (userDTO.getName() == null || userDTO.getName().isEmpty() || userDTO.getName().isBlank()) {
            throw new Exception("É obrigatório informar o nome do usuário");
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || userDTO.getEmail().isBlank()) {
            throw new Exception("É obrigatório informar o email do usuário");
        }

        // Validação de formato de email
        if (!userDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new Exception("Formato de email inválido");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email já cadastrado");
        }

        if (userDTO.getHashPassword() == null || userDTO.getHashPassword().isEmpty() || userDTO.getHashPassword().isBlank()) {
            throw new Exception("É obrigatório informar a senha");
        }
    }

    private void validateParamsSearch(UserDTO userDTO) throws Exception {
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || userDTO.getEmail().isBlank()) {
            throw new Exception("É necessário informar o email do usuário para consulta");
        }
    }
}
