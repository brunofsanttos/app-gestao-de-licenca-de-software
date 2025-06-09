package com.br.gestaodelicencadesoftware.services;

import com.br.gestaodelicencadesoftware.dto.SupplierDTO;
import com.br.gestaodelicencadesoftware.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMaper;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMaper = modelMapper;
    }

    public void saveSupplier(SupplierDTO supplierDTO) throws Exception {

    }

    private void validadeIsUnique(SupplierDTO supplierDTO) {
        // Todo: Implementar validação para garantir que não exista dois fornecedores com o mesmo CNPJ e razao social.
    }
}
