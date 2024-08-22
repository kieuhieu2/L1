package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.CertificateCreateRequest;
import com.oceanTech.L1.entity.Certificate;
import com.oceanTech.L1.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificate")

public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public Certificate createCertificate(@RequestBody CertificateCreateRequest request) {
        return certificateService.createCertificate(request);
    }

    @PutMapping("/{certificateName}")
    public Certificate updateCertificate(@PathVariable String certificateName,@RequestBody CertificateCreateRequest request) {
        return certificateService.updateCertificate(certificateName, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCertificate(@PathVariable String id) {
        certificateService.deleteCertificate(id);
        return "Certificate deleted successfully";
    }

    @GetMapping("/{CertificateName}")
    public Certificate getCertificate(@PathVariable String CertificateName) {
        return certificateService.getCertificate(CertificateName);
    }

    @GetMapping
    public List<Certificate> getCertificates() {
        return certificateService.getCertificates();
    }
}
