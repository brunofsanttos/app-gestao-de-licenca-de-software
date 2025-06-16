package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.ProofDTO;
import com.br.gestaodelicencadesoftware.entities.ProofEntity;
import com.br.gestaodelicencadesoftware.repositories.ProofRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProofCudService {

    private final ProofRepository proofRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProofCudService(ProofRepository proofRepository, ModelMapper modelMapper) {
        this.proofRepository = proofRepository;
        this.modelMapper = modelMapper;
    }

    public ObjectReturnDefault save(ProofDTO proofDTO) throws Exception {
        this.validate(proofDTO);

        ProofEntity proofEntity = modelMapper.map(proofDTO, ProofEntity.class);

        proofEntity = proofRepository.save(proofEntity);

        return new ObjectReturnDefault("Comprovante cadastrado com sucesso", modelMapper.map(proofEntity, ProofDTO.class));
    }

    public ObjectReturnDefault read(ProofDTO proofDTO) throws Exception {
        this.validateParamsSearch(proofDTO);

        ProofEntity proof = proofRepository.findById(proofDTO.getProofId())
                .orElse(null);

        if (proof == null) {
            return new ObjectReturnDefault("Nenhum comprovante encontrado com os parâmetros fornecidos.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMapper.map(proof, ProofDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<ProofEntity> proof = proofRepository.findById(id);

        if (proof.isEmpty()) {
            throw new Exception("Parâmetro inválido");
        }

        proof.get().setIsDeleted(true);
        proofRepository.save(proof.get());

        return new ObjectReturnDefault("Comprovante deletado com sucesso");
    }

    private void validate(ProofDTO proofDTO) throws Exception {
        if (proofDTO.getFileName() == null || proofDTO.getFileName().isEmpty() || proofDTO.getFileName().isBlank()) {
            throw new Exception("É obrigatório informar o nome do arquivo");
        }

        if (proofDTO.getFileUrl() == null || proofDTO.getFileUrl().isEmpty() || proofDTO.getFileUrl().isBlank()) {
            throw new Exception("É obrigatório informar a URL do arquivo");
        }

        if (proofDTO.getFileContentType() == null || proofDTO.getFileContentType().isEmpty() || proofDTO.getFileContentType().isBlank()) {
            throw new Exception("É obrigatório informar o tipo de conteúdo do arquivo");
        }
    }

    private void validateParamsSearch(ProofDTO proofDTO) throws Exception {
        if (proofDTO.getProofId() == null) {
            throw new Exception("É necessário informar o ID do comprovante para consulta");
        }
    }
}
