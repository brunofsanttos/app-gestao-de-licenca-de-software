package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.ProofDTO;
import com.br.gestaodelicencadesoftware.entities.ProofEntity;
import com.br.gestaodelicencadesoftware.repositories.ProofRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProofCudServiceTest {

    private ProofRepository proofRepository;
    private ProofCudService proofCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        proofRepository = mock(ProofRepository.class);
        modelMapper = new ModelMapper();
        proofCudService = new ProofCudService(proofRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        // Arrange
        ProofDTO proofDTO = new ProofDTO();
        proofDTO.setFileName("comprovante.pdf");
        proofDTO.setFileContentType("application/pdf");
        proofDTO.setFileExtension(".pdf");
        proofDTO.setFileUrl("https://storage.example.com/comprovante.pdf");

        ProofEntity proofEntity = new ProofEntity();
        proofEntity.setProofId(1L);
        proofEntity.setFileName("comprovante.pdf");
        proofEntity.setFileContentType("application/pdf");
        proofEntity.setFileExtension(".pdf");
        proofEntity.setFileUrl("https://storage.example.com/comprovante.pdf");
        proofEntity.setIsDeleted(false);

        when(proofRepository.save(any(ProofEntity.class))).thenReturn(proofEntity);

        // Act
        ObjectReturnDefault result = proofCudService.save(proofDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Comprovante cadastrado com sucesso", result.getMessage());

        ProofDTO savedProof = (ProofDTO) result.getObject();
        assertEquals("comprovante.pdf", savedProof.getFileName());
    }

    @Test
    void readSuccessTest() throws Exception {
        // Arrange
        ProofEntity proofEntity = new ProofEntity();
        proofEntity.setProofId(1L);
        proofEntity.setFileName("comprovante.pdf");
        proofEntity.setFileContentType("application/pdf");
        proofEntity.setFileExtension(".pdf");
        proofEntity.setFileUrl("https://storage.example.com/comprovante.pdf");
        proofEntity.setIsDeleted(false);

        ProofDTO proofDTO = new ProofDTO();
        proofDTO.setProofId(1L);

        when(proofRepository.findById(1L)).thenReturn(Optional.of(proofEntity));

        // Act
        ObjectReturnDefault result = proofCudService.read(proofDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());
    }

    @Test
    void deleteSuccessTest() throws Exception {
        // Arrange
        ProofEntity proofEntity = new ProofEntity();
        proofEntity.setProofId(1L);
        proofEntity.setFileName("comprovante.pdf");
        proofEntity.setIsDeleted(false);

        when(proofRepository.findById(1L)).thenReturn(Optional.of(proofEntity));
        when(proofRepository.save(any(ProofEntity.class))).thenReturn(proofEntity);

        // Act
        ObjectReturnDefault result = proofCudService.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Comprovante deletado com sucesso", result.getMessage());
    }
}
