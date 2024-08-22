package com.oceanTech.L1.dto.request;

import com.oceanTech.L1.entity.District;

import java.util.List;

public class ProvinceRequest {
    private String name;
    private List<District> districts;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
