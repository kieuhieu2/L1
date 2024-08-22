package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.DistrictRequest;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping
    public District createProvince(@RequestBody DistrictRequest request) {
        return districtService.createDistrict(request);
    }

    @PutMapping("/{districtName}")
    public District updateDistrict(@PathVariable String districtName, @RequestBody DistrictRequest request) {
        return districtService.updateDistrict(districtName, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDistrict(@PathVariable String id) {
        districtService.deleteDistrict(id);
        return "Delete district " + id + " successfully";
    }

    @GetMapping
    public List<District> getDistricts() {
        return districtService.getDistricts();
    }

    @GetMapping("/{districtName}")
    public District getDistrict(@PathVariable String districtName) {
        return districtService.getDistrict(districtName);
    }
}
