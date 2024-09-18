package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    Commune findByName(String name);

    @Query("SELECT c FROM Commune c WHERE c.district.id = :districtId")
    List<Commune> findByDistrictId(@Param("districtId") Long districtId);

    @Query("SELECT c FROM Commune c WHERE c.name = :name AND c.district.name = :district")
    List<Commune> findByNameAndDistrict(@Param("name") String name, @Param("district") String district);
}
