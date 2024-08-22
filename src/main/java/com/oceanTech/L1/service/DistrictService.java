package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.DistrictRequest;
import com.oceanTech.L1.dto.request.ProvinceRequest;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.entity.Province;
import com.oceanTech.L1.repository.DistrictRepository;
import com.oceanTech.L1.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    public District createDistrict(DistrictRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
        .orElseThrow(() -> new RuntimeException("Province not found"));

        District district = new District();
        district.setName(request.getName());
        district.setProvince(province);

        return districtRepository.save(district);
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
}
