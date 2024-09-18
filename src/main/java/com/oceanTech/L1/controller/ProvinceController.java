package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.ProvinceAllRequest;
import com.oceanTech.L1.dto.request.ProvinceRequest;
import com.oceanTech.L1.entity.District;
import com.oceanTech.L1.entity.Province;
import com.oceanTech.L1.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public Province createProvince(@RequestBody ProvinceRequest request) {
        return provinceService.createProvince(request);
    }

    @PostMapping("/createWithDistricts")
    public Province createProvinceWithDistricts(@RequestBody ProvinceRequest request) {
        Province province = new Province();
        province.setName(request.getName());
        return provinceService.createProvinceWithDistricts(province, request.getDistricts());
    }

    @PutMapping("/updateWithDistricts/{provinceName}")
    public Province updateProvinceWithDistricts(@PathVariable String provinceName, @RequestBody ProvinceRequest request) {
        return provinceService.updateProvinceWithDistricts(provinceName, request.getDistricts());
    }

    @PutMapping("/{provinceName}")
    public Province updateProvince(@PathVariable String provinceName, @RequestBody ProvinceRequest request) {
        return provinceService.updateProvince(provinceName, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProvince(@PathVariable String id) {
        provinceService.deleteProvince(id);
        return "Delete province " + id + " successfully";
    }

    @GetMapping
    public List<Province> getProvinces() {
        return provinceService.getProvinces();
    }

    @GetMapping("/{provinceName}")
    public Province getProvince(@PathVariable String provinceName) {
        return provinceService.getProvince(provinceName);
    }

    @GetMapping("/getDistrict/{provinceId}")
    public List<District> getDistrictByIdProvince(@PathVariable Long provinceId) {
        return provinceService.getDistrictByIdProvince(provinceId);
    }


    @PostMapping("/createWithDistrictsAndCommunes")
    public Province createProvinceWithDistrictsAndCommunes(@RequestBody ProvinceAllRequest request) {
        return provinceService.createProvinceWithDistrictsAndCommunes(request);
    }
}
