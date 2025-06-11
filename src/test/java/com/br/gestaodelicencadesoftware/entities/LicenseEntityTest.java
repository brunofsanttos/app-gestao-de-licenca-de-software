package com.br.gestaodelicencadesoftware.entities;

import com.br.gestaodelicencadesoftware.repositories.LicenseRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class LicenseEntityTest {

    @PersistenceContext
    private LicenseRepository licenseRepository;

    @Test
    @Rollback
    void testPersistenceSuccess(){

    }

    private void createLicenseEntity() {


        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setKey("123456");
    }

}