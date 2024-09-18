package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.CertificateCreateRequest;
import com.oceanTech.L1.dto.request.CertificatePublicRequest;
import com.oceanTech.L1.entity.Certificate;
import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.exception.AppException;
import com.oceanTech.L1.exception.ErrorCode;
import com.oceanTech.L1.repository.CertificateRepository;
import com.oceanTech.L1.repository.EmployeeRepository;
import com.oceanTech.L1.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    public Certificate createCertificate(CertificateCreateRequest request) {
        Certificate certificate = new Certificate();

        certificate.setName(request.getName());
        certificate.setValidFrom(request.getValidFrom());
        certificate.setValidTo(request.getValidTo());
//        certificate.setProvince(request.getProvince());

        return certificateRepository.save(certificate);
    }

    public void deleteCertificate(String id) {
        try {
            long certificateId = Long.parseLong(id);
            certificateRepository.deleteById(certificateId);
        } catch (Exception e) {
            throw new RuntimeException("Certificate not found");
        }
    }

    public Certificate getCertificate(String CertificateName) {
        return certificateRepository.findByName(CertificateName);
    }


    public List<Certificate> getCertificates() {
        return certificateRepository.findAll();
    }
}
