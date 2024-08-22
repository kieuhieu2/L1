package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.CommuneRequest;
import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommuneService {
    @Autowired
    private CommuneRepository communeRepository;

    public Commune createCommune(CommuneRequest request) {
        Commune commune = new Commune();
        commune.setName(request.getName());

        return communeRepository.save(commune);
    }

    public Commune updateCommune(String communeName, CommuneRequest request) {
        Commune commune = getCommune(communeName);
        commune.setName(request.getName());

        return communeRepository.save(commune);
    }

    public void deleteCommune(String id) {
        try {
            long communeId = Long.parseLong(id);
            communeRepository.deleteById(communeId);
        } catch (Exception e) {
            throw new RuntimeException("Province not found");
        }
    }

    public List<Commune> getCommunes(){
        return communeRepository.findAll();
    }

    public Commune getCommune(String communeName) {
        return communeRepository.findByName(communeName);

    }
}
