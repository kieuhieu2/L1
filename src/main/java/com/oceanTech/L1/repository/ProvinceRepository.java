package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
//    Province findByName(String name);
    Province findByName(String name);
}
