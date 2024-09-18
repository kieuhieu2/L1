package com.oceanTech.L1.dto.request;

import com.oceanTech.L1.entity.Province;

import java.time.LocalDate;
import java.util.Optional;

public class CertificatePublicRequest {
    private Long idCertificate;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String provinceName;

    public Long getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(Long idCertificate) {
        this.idCertificate = idCertificate;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
