package com.edoc.backend.repositories;

import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Admission> {

}
