package com.oceanTech.L1.dto.request;

import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.entity.District;

public class ProvinceAllRequest {
    private String name;
    private District districtAll;
    private Commune CommuneAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrictAll() {
        return districtAll;
    }

    public void setDistrictAll(District districtAll) {
        this.districtAll = districtAll;
    }

    public Commune getCommuneAll() {
        return CommuneAll;
    }

    public void setCommuneAll(Commune communeAll) {
        CommuneAll = communeAll;
    }
}
