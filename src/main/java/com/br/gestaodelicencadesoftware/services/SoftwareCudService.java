package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.SoftwareDTO;
import com.br.gestaodelicencadesoftware.entities.SoftwareEntity;
import com.br.gestaodelicencadesoftware.repositories.SoftwareRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SoftwareCudService {

    private final SoftwareRepository softwareRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SoftwareCudService(SoftwareRepository softwareRepository, ModelMapper modelMapper) {
        this.softwareRepository = softwareRepository;
        this.modelMapper = modelMapper;
    }

    public ObjectReturnDefault save(SoftwareDTO softwareDTO) throws Exception {
        this.validate(softwareDTO);

        SoftwareEntity softwareEntity = modelMapper.map(softwareDTO, SoftwareEntity.class);

        softwareEntity = softwareRepository.save(softwareEntity);

        return new ObjectReturnDefault("Software cadastrado com sucesso", modelMapper.map(softwareEntity, SoftwareDTO.class));
    }

    public ObjectReturnDefault read(SoftwareDTO softwareDTO) throws Exception {
        this.validateParamsSearch(softwareDTO);

        SoftwareEntity software = softwareRepository.findById(softwareDTO.getSoftwareId())
                .orElse(null);

        if (software == null) {
            return new ObjectReturnDefault("Nenhum software encontrado com os parâmetros fornecidos.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMapper.map(software, SoftwareDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<SoftwareEntity> software = softwareRepository.findById(id);

        if (software.isEmpty()) {
            throw new Exception("Parâmetro inválido");
        }

        software.get().setIsDeleted(true);
        softwareRepository.save(software.get());

        return new ObjectReturnDefault("Software deletado com sucesso");
    }

    private void validate(SoftwareDTO softwareDTO) throws Exception {
        if (softwareDTO.getDescription() == null || softwareDTO.getDescription().isEmpty() || softwareDTO.getDescription().isBlank()) {
            throw new Exception("É obrigatório informar a descrição do software");
        }

        if (softwareDTO.getSupplierId() == null) {
            throw new Exception("É obrigatório informar o fornecedor do software");
        }
    }

    private void validateParamsSearch(SoftwareDTO softwareDTO) throws Exception {
        if (softwareDTO.getSoftwareId() == null) {
            throw new Exception("É necessário informar o ID do software para consulta");
        }
    }
}
