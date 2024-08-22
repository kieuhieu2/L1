package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findByName(String name);
}
