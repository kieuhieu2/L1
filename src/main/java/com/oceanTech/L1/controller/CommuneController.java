package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.CommuneRequest;
import com.oceanTech.L1.entity.Commune;
import com.oceanTech.L1.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commune")
public class CommuneController {
    @Autowired
    private CommuneService communeService;

    @PostMapping
    public Commune createCommune(@RequestBody CommuneRequest request) {
        return communeService.createCommune(request);
    }

    @PutMapping("/{communeName}")
    public Commune updateProvince(@PathVariable String communeName, @RequestBody CommuneRequest request) {
        return communeService.updateCommune(communeName, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCommune(@PathVariable String id) {
        communeService.deleteCommune(id);
        return "Delete commune " + id + " successfully";
    }

    @GetMapping
    public List<Commune> getCommunes() {
        return communeService.getCommunes();
    }

    @GetMapping("/{communeName}")
    public Commune getCommune(@PathVariable String communeName) {
        return communeService.getCommune(communeName);
    }
}
