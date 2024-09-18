package com.oceanTech.L1.dto.request;

import java.time.LocalDate;

public class CertificateCreateRequest {

    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvince(String province) {
        this.provinceName = province;
    }

    public String getProvince() {
        return provinceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
