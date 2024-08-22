package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    Commune findByName(String name);
}
