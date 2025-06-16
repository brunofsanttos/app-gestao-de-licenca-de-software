package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.SupplierDTO;
import com.br.gestaodelicencadesoftware.entities.SupplierEntity;
import com.br.gestaodelicencadesoftware.repositories.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SupplierCudServiceTest {

    private SupplierRepository supplierRepository;
    private SupplierCudService supplierCudService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        supplierRepository = mock(SupplierRepository.class);
        modelMapper = new ModelMapper();
        supplierCudService = new SupplierCudService(supplierRepository, modelMapper);
    }

    @Test
    void saveSuccessTest() throws Exception {
        SupplierDTO supplierDTO = new SupplierDTO("Fornecedor Teste", "12345678901234");
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(1L);
        supplierEntity.setCompanyName("Fornecedor Teste");
        supplierEntity.setCnpj("12345678901234");
        supplierEntity.setIsDeleted(false);

        supplierEntity.setCnpj("12345678901234");

        when(supplierRepository.existsByCnpj("12345678901234")).thenReturn(false);
        when(supplierRepository.save(any(SupplierEntity.class))).thenReturn(supplierEntity);

        ObjectReturnDefault result = supplierCudService.save(supplierDTO);

        assertNotNull(result);
        assertEquals("Fornecedor cadastrado com sucesso", result.getMessage());

        SupplierDTO savedSupplier = (SupplierDTO) result.getObject();
        assertEquals("Fornecedor Teste", savedSupplier.getCompanyName());
        assertEquals("12345678901234", savedSupplier.getCnpj());
    }

    @Test
    void readSuccessTest() throws Exception {
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(1L);
        supplierEntity.setCompanyName("Fornecedor Teste");
        supplierEntity.setCnpj("12345678901234");
        supplierEntity.setIsDeleted(false);

        when(supplierRepository.findByCnpj("12345678901234")).thenReturn(supplierEntity);

        ObjectReturnDefault result = supplierCudService.read(new SupplierDTO("Fornecedor Teste", "12345678901234"));

        assertNotNull(result);
        assertEquals("Retorno da consulta", result.getMessage());

    }

    @Test
    void deleteSuccessTest() throws Exception {
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(1L);
        supplierEntity.setCompanyName("Fornecedor Teste");
        supplierEntity.setCnpj("12345678901234");
        supplierEntity.setIsDeleted(true);

        when(supplierRepository.findById(1L)).thenReturn(java.util.Optional.of(supplierEntity));
        when(supplierRepository.save(any(SupplierEntity.class))).thenReturn(supplierEntity);

        ObjectReturnDefault result = supplierCudService.delete(1L);

        assertNotNull(result);
        assertEquals("Fornecedor deletado com sucesso", result.getMessage());
    }
}