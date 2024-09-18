package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.DistrictRequest;
import com.oceanTech.L1.dto.request.ProvinceAllRequest;
import com.oceanTech.L1.dto.request.ProvinceRequest;
import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.entity.Province;
import com.oceanTech.L1.repository.CommuneRepository;
import com.oceanTech.L1.repository.DistrictRepository;
import com.oceanTech.L1.repository.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CommuneRepository communeRepository;

    public Province createProvince(ProvinceRequest request) {
        Province province = new Province();
        province.setName(request.getName());

        return provinceRepository.save(province);
    }

    @Transactional
    public Province createProvinceWithDistricts(Province province, List<District> districts) {
        Province tg = provinceRepository.save(province);
        for (District district : districts) {
            district.setProvince(tg);
            districtRepository.save(district);
        }
        return tg;
    }

    @Transactional
    public Province updateProvinceWithDistricts(String provinceName, List<District> districts) {

        Province existingProvince = provinceRepository.findByName(provinceName);
        if (existingProvince == null) {
            throw new RuntimeException("Province not found");
        }

        existingProvince.setName(provinceName);


        for (District district : districts) {
            District existingDistrict = districtRepository.findById(district.getId()).orElse(null);
            if (existingDistrict != null) {
                existingDistrict.setName(district.getName());
                existingDistrict.setProvince(existingProvince);
                districtRepository.save(existingDistrict);
            } else {
                district.setProvince(existingProvince);
                districtRepository.save(district);
            }
        }

        return provinceRepository.save(existingProvince);
    }


    public Province updateProvince(String provinceName, ProvinceRequest request) {
        Province province = getProvince(provinceName);
        province.setName(request.getName());

        return provinceRepository.save(province);
    }

    public void deleteProvince(String id) {
            try {
                long provinceId = Long.parseLong(id);
                provinceRepository.deleteById(provinceId);
            } catch (Exception e) {
                throw new RuntimeException("Province not found");
            }
    }

    public List<Province> getProvinces(){
        return provinceRepository.findAll();
    }

    public Province getProvince(String provinceName) {
        return provinceRepository.findByName(provinceName);

    }

    public List<District> getDistrictByIdProvince(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    @Transactional
    public Province createProvinceWithDistrictsAndCommunes(ProvinceAllRequest request) {
        // Create and save the new Province
        Province province = new Province();
        province.setName(request.getName());
        Province savedProvince = provinceRepository.save(province);

        // Create and save the new District
        District district = request.getDistrictAll();
        district.setProvince(savedProvince);
        District savedDistrict = districtRepository.save(district);

        // Create and save the new Commune
        Commune commune = request.getCommuneAll();
        commune.setDistrict(savedDistrict);
        communeRepository.save(commune);

        return savedProvince;
    }

}
