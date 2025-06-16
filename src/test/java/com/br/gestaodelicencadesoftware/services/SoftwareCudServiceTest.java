package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.SoftwareDTO;
import com.br.gestaodelicencadesoftware.entities.SoftwareEntity;
import com.br.gestaodelicencadesoftware.entities.SupplierEntity;
import com.br.gestaodelicencadesoftware.repositories.SoftwareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SoftwareCudServiceTest {

    private SoftwareRepository softwareRepository;
    private SoftwareCudService softwareCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        softwareRepository = mock(SoftwareRepository.class);
        modelMapper = new ModelMapper();
        softwareCudService = new SoftwareCudService(softwareRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        // Arrange
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(1L);

        SoftwareDTO softwareDTO = new SoftwareDTO();
        softwareDTO.setDescription("Software de Teste");
        softwareDTO.setSoftwareVersion("1.0");
        softwareDTO.setSupplierId(supplierEntity);
        softwareDTO.setUrlSite("https://software-teste.com");

        SoftwareEntity softwareEntity = new SoftwareEntity();
        softwareEntity.setSoftwareId(1L);
        softwareEntity.setDescription("Software de Teste");
        softwareEntity.setSoftwareVersion("1.0");
        softwareEntity.setSupplierId(supplierEntity);
        softwareEntity.setUrlSite("https://software-teste.com");
        softwareEntity.setIsDeleted(false);

        when(softwareRepository.save(any(SoftwareEntity.class))).thenReturn(softwareEntity);

        // Act
        ObjectReturnDefault result = softwareCudService.save(softwareDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Software cadastrado com sucesso", result.getMessage());

        SoftwareDTO savedSoftware = (SoftwareDTO) result.getObject();
        assertEquals("Software de Teste", savedSoftware.getDescription());
        assertEquals("1.0", savedSoftware.getSoftwareVersion());
    }

    @Test
    void readSuccessTest() throws Exception {
        // Arrange
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(1L);

        SoftwareEntity softwareEntity = new SoftwareEntity();
        softwareEntity.setSoftwareId(1L);
        softwareEntity.setDescription("Software de Teste");
        softwareEntity.setSoftwareVersion("1.0");
        softwareEntity.setSupplierId(supplierEntity);
        softwareEntity.setUrlSite("https://software-teste.com");
        softwareEntity.setIsDeleted(false);

        SoftwareDTO softwareDTO = new SoftwareDTO();
        softwareDTO.setSoftwareId(1L);

        when(softwareRepository.findById(1L)).thenReturn(Optional.of(softwareEntity));

        // Act
        ObjectReturnDefault result = softwareCudService.read(softwareDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());
    }

    @Test
    void deleteSuccessTest() throws Exception {
        // Arrange
        SoftwareEntity softwareEntity = new SoftwareEntity();
        softwareEntity.setSoftwareId(1L);
        softwareEntity.setDescription("Software de Teste");
        softwareEntity.setIsDeleted(false);

        when(softwareRepository.findById(1L)).thenReturn(Optional.of(softwareEntity));
        when(softwareRepository.save(any(SoftwareEntity.class))).thenReturn(softwareEntity);

        // Act
        ObjectReturnDefault result = softwareCudService.delete(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Software deletado com sucesso", result.getMessage());
    }
}
