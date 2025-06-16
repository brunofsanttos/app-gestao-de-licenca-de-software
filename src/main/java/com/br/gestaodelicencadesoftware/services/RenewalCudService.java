package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.RenewalDTO;
import com.br.gestaodelicencadesoftware.entities.RenewalEntity;
import com.br.gestaodelicencadesoftware.repositories.RenewalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RenewalCudService {

    private final RenewalRepository renewalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RenewalCudService(RenewalRepository renewalRepository, ModelMapper modelMapper) {
        this.renewalRepository = renewalRepository;
        this.modelMapper = modelMapper;
    }

    public ObjectReturnDefault save(RenewalDTO renewalDTO) throws Exception {
        this.validate(renewalDTO);

        RenewalEntity renewalEntity = modelMapper.map(renewalDTO, RenewalEntity.class);

        if (renewalEntity.getRequestDate() == null) {
            renewalEntity.setRequestDate(LocalDate.now());
        }

        renewalEntity = renewalRepository.save(renewalEntity);

        return new ObjectReturnDefault("Renovação cadastrada com sucesso", modelMapper.map(renewalEntity, RenewalDTO.class));
    }

    public ObjectReturnDefault read(RenewalDTO renewalDTO) throws Exception {
        this.validateParamsSearch(renewalDTO);

        RenewalEntity renewal = renewalRepository.findById(renewalDTO.getRenewalId())
                .orElse(null);

        if (renewal == null) {
            return new ObjectReturnDefault("Nenhuma renovação encontrada com os parâmetros fornecidos.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMapper.map(renewal, RenewalDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<RenewalEntity> renewal = renewalRepository.findById(id);

        if (renewal.isEmpty()) {
            throw new Exception("Parâmetro inválido");
        }

        renewal.get().setIsDeleted(true);
        renewalRepository.save(renewal.get());

        return new ObjectReturnDefault("Renovação deletada com sucesso");
    }

    private void validate(RenewalDTO renewalDTO) throws Exception {
        if (renewalDTO.getFrequency() == null) {
            throw new Exception("É obrigatório informar a frequência de renovação");
        }

        if (renewalDTO.getIsRenovateAutomatically() == null) {
            renewalDTO.setIsRenovateAutomatically(false);
        }
    }

    private void validateParamsSearch(RenewalDTO renewalDTO) throws Exception {
        if (renewalDTO.getRenewalId() == null) {
            throw new Exception("É necessário informar o ID da renovação para consulta");
        }
    }
}
