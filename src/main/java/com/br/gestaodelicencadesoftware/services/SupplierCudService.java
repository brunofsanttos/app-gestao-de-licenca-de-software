package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dtos.ObjectReturnDefault;
import com.br.gestaodelicencadesoftware.dtos.SupplierDTO;
import com.br.gestaodelicencadesoftware.entities.SupplierEntity;
import com.br.gestaodelicencadesoftware.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SupplierCudService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMaper;

    @Autowired
    public SupplierCudService(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMaper = modelMapper;
    }

    public ObjectReturnDefault save(SupplierDTO supplierDTO) throws Exception {
        this.validade(supplierDTO);

        SupplierEntity supplierEntity = modelMaper.map(supplierDTO, SupplierEntity.class);

        supplierEntity = supplierRepository.save(supplierEntity);

        return new ObjectReturnDefault("Fornecedor cadastrado com sucesso", modelMaper.map(supplierEntity, SupplierDTO.class));
    }

    public ObjectReturnDefault read(SupplierDTO supplierDTO) throws Exception {
        this.validadeParamsSearch(supplierDTO);

        SupplierEntity supplier = !supplierDTO.getCnpj().isEmpty()
                ? supplierRepository.findByCnpj(supplierDTO.getCnpj())
                : supplierRepository.findByCompanyName(supplierDTO.getCompanyName());

        if (supplier == null) {
            return new ObjectReturnDefault("Nenhum fornecedor encontrado com os parâmetros fornecidos.");
        }

        return new ObjectReturnDefault("Retorno da consulta", modelMaper.map(supplier, SupplierDTO.class));
    }

    public ObjectReturnDefault delete(Long id) throws Exception {
        Optional<SupplierEntity> supplier = supplierRepository.findById(id);

        if (supplier.isEmpty()) {
            throw new Exception("Parâmetro invalido");
        }

        supplier.get().setIsDeleted(true);
        supplierRepository.save(supplier.get());

        return new ObjectReturnDefault("Fornecedor deletado com sucesso");

    }

    private void validade(SupplierDTO supplierDTO) throws Exception {
        if (supplierRepository.existsByCnpj(supplierDTO.getCnpj())) {
            throw new Exception("CNPJ já cadastrado");
        }

        if (supplierDTO.getCompanyName().isEmpty() || supplierDTO.getCompanyName().isBlank()) {
            throw new Exception("É obrigatório informar o nome da empresa");
        }
    }

    private void validadeParamsSearch(SupplierDTO supplierDTO) throws Exception {
        if (supplierDTO.getCnpj().isEmpty() || supplierDTO.getCnpj().isBlank() &&
                supplierDTO.getCompanyName().isEmpty() || supplierDTO.getCompanyName().isBlank()) {
            throw new Exception("Nenhum parâmetro informado para consulta");

        }
    }
}
