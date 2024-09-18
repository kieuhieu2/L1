package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.DistrictRequest;
import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.entity.Province;
import com.oceanTech.L1.repository.CommuneRepository;
import com.oceanTech.L1.repository.DistrictRepository;
import com.oceanTech.L1.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CommuneRepository communeRepository;

    private static final Logger logger = Logger.getLogger(DistrictService.class.getName());

    public District createDistrict(DistrictRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
        .orElseThrow(() -> new RuntimeException("Province not found"));

        District district = new District();
        district.setName(request.getName());
        district.setProvince(province);

        return districtRepository.save(district);
    }

    @Transactional
    public District createDistrictWithCommunes(DistrictRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found"));

        District district = new District();
        district.setName(request.getName());
        district.setProvince(province);
        District savedDistrict = districtRepository.save(district);

        for (Commune commune : request.getCommunes()) {
            logger.info("Adding commune with name: " + commune.getName());
            commune.setDistrict(savedDistrict);
            communeRepository.save(commune);
        }
        savedDistrict.setCommunes(request.getCommunes());
        return savedDistrict;
    }

    @Transactional
    public District updateDistrictWithCommunes(Long districtId, DistrictRequest request) {
        District existingDistrict = districtRepository.findById(districtId)
                .orElseThrow(() -> new RuntimeException("District not found"));

        existingDistrict.setName(request.getName());

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found"));
        existingDistrict.setProvince(province);

        for (Commune commune : request.getCommunes()) {
            Commune existingCommune = communeRepository.findById(commune.getId()).orElse(null);
            if (existingCommune != null) {
                existingCommune.setName(commune.getName());
                existingCommune.setDistrict(existingDistrict);
                communeRepository.save(existingCommune);
            } else {
                commune.setDistrict(existingDistrict);
                communeRepository.save(commune);
            }
        }

        return districtRepository.save(existingDistrict);
    }

    public District updateDistrict(String districtName, DistrictRequest request) {
        District district = getDistrict(districtName);
        if(district == null){
            throw new RuntimeException("District not found");
        }

        Province province = provinceRepository.findById(request.getProvinceId())
                        .orElseThrow(() -> new RuntimeException("Province not found"));

        district.setName(request.getName());
        district.setProvince(province);

        return districtRepository.save(district);
    }

    public void deleteDistrict( String id) {
        try {
            long districtId = Long.parseLong(id);
            districtRepository.deleteById(districtId);
        } catch (Exception e) {
            throw new RuntimeException("Province not found");
        }
    }

    public List<District> getDistricts(){
        return districtRepository.findAll();
    }

    public District getDistrict(String districtName) {
        return districtRepository.findByName(districtName);
    }

    public List<Commune> getCommunesByDistrictId(Long districtId) {
        return communeRepository.findByDistrictId(districtId);
    }

}
