package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.RenewalDTO;
import com.br.gestaodelicencadesoftware.entities.RenewalEntity;
import com.br.gestaodelicencadesoftware.enumeratedTypes.RenewalFrequency;
import com.br.gestaodelicencadesoftware.repositories.RenewalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RenewalCudServiceTest {

    private RenewalRepository renewalRepository;
    private RenewalCudService renewalCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        renewalRepository = mock(RenewalRepository.class);
        modelMapper = new ModelMapper();
        renewalCudService = new RenewalCudService(renewalRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        // Arrange
        RenewalDTO renewalDTO = new RenewalDTO();
        renewalDTO.setFrequency(RenewalFrequency.MONTHLY);
        renewalDTO.setIsRenovateAutomatically(true);

        LocalDate now = LocalDate.now();

        RenewalEntity renewalEntity = new RenewalEntity();
        renewalEntity.setRenewalId(1L);
        renewalEntity.setFrequency(RenewalFrequency.MONTHLY);
        renewalEntity.setRequestDate(now);
        renewalEntity.setIsRenovateAutomatically(true);
        renewalEntity.setIsDeleted(false);

        when(renewalRepository.save(any(RenewalEntity.class))).thenReturn(renewalEntity);

        // Act
        ObjectReturnDefault result = renewalCudService.save(renewalDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Renovação cadastrada com sucesso", result.getMessage());

        RenewalDTO savedRenewal = (RenewalDTO) result.getObject();
        assertEquals(RenewalFrequency.MONTHLY, savedRenewal.getFrequency());
        assertEquals(true, savedRenewal.getIsRenovateAutomatically());
    }

    @Test
    void readSuccessTest() throws Exception {
        // Arrange
        LocalDate now = LocalDate.now();

        RenewalEntity renewalEntity = new RenewalEntity();
        renewalEntity.setRenewalId(1L);
        renewalEntity.setFrequency(RenewalFrequency.MONTHLY);
        renewalEntity.setRequestDate(now);
        renewalEntity.setIsRenovateAutomatically(true);
        renewalEntity.setIsDeleted(false);

        RenewalDTO renewalDTO = new RenewalDTO();
        renewalDTO.setRenewalId(1L);

        when(renewalRepository.findById(1L)).thenReturn(Optional.of(renewalEntity));

        // Act
        ObjectReturnDefault result = renewalCudService.read(renewalDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());
    }

    @Test
    void deleteSuccessTest() throws Exception {
        // Arrange
        RenewalEntity renewalEntity = new RenewalEntity();
        renewalEntity.setRenewalId(1L);
        renewalEntity.setFrequency(RenewalFrequency.MONTHLY);
        renewalEntity.setIsDeleted(false);

        when(renewalRepository.findById(1L)).thenReturn(Optional.of(renewalEntity));
        when(renewalRepository.save(any(RenewalEntity.class))).thenReturn(renewalEntity);

        // Act
        ObjectReturnDefault result = renewalCudService.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Renovação deletada com sucesso", result.getMessage());
    }
}
