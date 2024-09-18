package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findByName(String name);

    @Query("SELECT c FROM Certificate c WHERE c.id = :id")
    Certificate findCustomId(@Param("id") Long id);
}
