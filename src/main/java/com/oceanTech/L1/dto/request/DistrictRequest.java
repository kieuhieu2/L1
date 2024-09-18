package com.oceanTech.L1.dto.request;

import com.oceanTech.L1.entity.Commune;

import java.util.List;

public class DistrictRequest {
    private String name;
    private Long provinceId;
    private List<Commune> communes;

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Commune> getCommunes() {
        return communes;
    }
}
