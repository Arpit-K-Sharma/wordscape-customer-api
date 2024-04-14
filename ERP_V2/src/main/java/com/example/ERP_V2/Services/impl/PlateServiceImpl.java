package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Plate;
import com.example.ERP_V2.Repository.PlateRepo;
import com.example.ERP_V2.Services.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlateServiceImpl implements PlateService {

    @Autowired
    private PlateRepo plateRepository; // assuming PlateRepository is a Spring Data JPA repository

    @Override
    public List<Plate> getAllPlates() {
        return plateRepository.findAll();
    }

    @Override
    public void createPlate(Plate plate) {
         plateRepository.save(plate);
    }

    @Override
    public void updatePlate(Long id, Plate updatedPlate) {
    }
}
