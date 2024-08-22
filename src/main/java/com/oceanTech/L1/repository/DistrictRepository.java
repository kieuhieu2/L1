package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByName(String name);

    @Query("SELECT d FROM District d WHERE d.province.id = :provinceId")
    List<District> findByProvinceId(@Param("provinceId") Long provinceId);
}
