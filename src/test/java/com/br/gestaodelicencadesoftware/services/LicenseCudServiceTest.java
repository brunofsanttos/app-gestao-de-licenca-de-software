package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.LicenseDTO;
import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.entities.LicenseEntity;
import com.br.gestaodelicencadesoftware.entities.SoftwareEntity;
import com.br.gestaodelicencadesoftware.repositories.LicenseRepository;
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

class LicenseCudServiceTest {

    private LicenseRepository licenseRepository;
    private LicenseCudService licenseCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        licenseRepository = mock(LicenseRepository.class);
        modelMapper = new ModelMapper();
        licenseCudService = new LicenseCudService(licenseRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        // Arrange
        SoftwareEntity softwareEntity = new SoftwareEntity();
        softwareEntity.setSoftwareId(1L);

        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setSoftwareId(softwareEntity);
        licenseDTO.setKey("ABC-DEF-12345");
        licenseDTO.setDueDate(LocalDate.now().plusYears(1));

        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setLicenseId(1L);
        licenseEntity.setSoftwareId(softwareEntity);
        licenseEntity.setKey("ABC-DEF-12345");
        licenseEntity.setAcquisitionDate(LocalDate.now());
        licenseEntity.setDueDate(LocalDate.now().plusYears(1));
        licenseEntity.setIsDeleted(false);

        when(licenseRepository.save(any(LicenseEntity.class))).thenReturn(licenseEntity);

        // Act
        ObjectReturnDefault result = licenseCudService.save(licenseDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Licença cadastrada com sucesso", result.getMessage());

        LicenseDTO savedLicense = (LicenseDTO) result.getObject();
        assertEquals("ABC-DEF-12345", savedLicense.getKey());
    }

    @Test
    void readSuccessTest() throws Exception {
        // Arrange
        SoftwareEntity softwareEntity = new SoftwareEntity();
        softwareEntity.setSoftwareId(1L);

        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setLicenseId(1L);
        licenseEntity.setSoftwareId(softwareEntity);
        licenseEntity.setKey("ABC-DEF-12345");
        licenseEntity.setAcquisitionDate(LocalDate.now());
        licenseEntity.setDueDate(LocalDate.now().plusYears(1));
        licenseEntity.setIsDeleted(false);

        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setLicenseId(1L);

        when(licenseRepository.findById(1L)).thenReturn(Optional.of(licenseEntity));

        // Act
        ObjectReturnDefault result = licenseCudService.read(licenseDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());
    }

    @Test
    void deleteSuccessTest() throws Exception {
        // Arrange
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setLicenseId(1L);
        licenseEntity.setKey("ABC-DEF-12345");
        licenseEntity.setIsDeleted(false);

        when(licenseRepository.findById(1L)).thenReturn(Optional.of(licenseEntity));
        when(licenseRepository.save(any(LicenseEntity.class))).thenReturn(licenseEntity);

        // Act
        ObjectReturnDefault result = licenseCudService.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Licença deletada com sucesso", result.getMessage());
    }
}
