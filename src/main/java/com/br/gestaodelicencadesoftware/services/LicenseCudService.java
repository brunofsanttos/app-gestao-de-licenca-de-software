package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.LicenseDTO;
import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.entities.LicenseEntity;
import com.br.gestaodelicencadesoftware.repositories.LicenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LicenseCudService {

    private final LicenseRepository licenseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LicenseCudService(LicenseRepository licenseRepository, ModelMapper modelMapper) {
        this.licenseRepository = licenseRepository;
        this.modelMapper = modelMapper;
    }

    public ObjectReturnDefault save(LicenseDTO licenseDTO) throws Exception {
        this.validate(licenseDTO);

        LicenseEntity licenseEntity = modelMapper.map(licenseDTO, LicenseEntity.class);

        licenseEntity = licenseRepository.save(licenseEntity);

        return new ObjectReturnDefault("Licença cadastrada com sucesso", modelMapper.map(licenseEntity, LicenseDTO.class));
    }

    public ObjectReturnDefault read(LicenseDTO licenseDTO) throws Exception {
        this.validateParamsSearch(licenseDTO);

        LicenseEntity license = licenseRepository.findById(licenseDTO.getLicenseId())
                .orElse(null);

        if (license == null) {
            return new ObjectReturnDefault("Nenhuma licença encontrada com os parâmetros fornecidos.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMapper.map(license, LicenseDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<LicenseEntity> license = licenseRepository.findById(id);

        if (license.isEmpty()) {
            throw new Exception("Parâmetro inválido");
        }

        license.get().setIsDeleted(true);
        licenseRepository.save(license.get());

        return new ObjectReturnDefault("Licença deletada com sucesso");
    }

    private void validate(LicenseDTO licenseDTO) throws Exception {
        if (licenseDTO.getSoftwareId() == null) {
            throw new Exception("É obrigatório informar o software associado à licença");
        }

        if (licenseDTO.getKey() == null || licenseDTO.getKey().isEmpty() || licenseDTO.getKey().isBlank()) {
            throw new Exception("É obrigatório informar a chave da licença");
        }

        if (licenseDTO.getDueDate() == null) {
            throw new Exception("É obrigatório informar a data de vencimento da licença");
        }

        if (licenseDTO.getAcquisitionDate() == null) {
            licenseDTO.setAcquisitionDate(LocalDate.now());
        }
    }

    private void validateParamsSearch(LicenseDTO licenseDTO) throws Exception {
        if (licenseDTO.getLicenseId() == null) {
            throw new Exception("É necessário informar o ID da licença para consulta");
        }
    }
}
