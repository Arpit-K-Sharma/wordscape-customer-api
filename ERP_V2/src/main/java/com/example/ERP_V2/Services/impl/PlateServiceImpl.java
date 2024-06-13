package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Plate;
import com.example.ERP_V2.Repository.PlateRepo;
import com.example.ERP_V2.Services.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void updatePlate(int id, Plate updatedPlate) {
        Plate existingPlate = plateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plate with id " + id + " not found"));

        // Update fields if they are not null in the updatedPlate object
        if (updatedPlate.getPlateSize() != null) {
            existingPlate.setPlateSize(updatedPlate.getPlateSize());
        }
        if (updatedPlate.getPlateRate() != null) {
            existingPlate.setPlateRate(updatedPlate.getPlateRate());
        }
        if (updatedPlate.getInkRate() != null) {
            existingPlate.setInkRate(updatedPlate.getInkRate());
        }

        // Save the updated plate
        plateRepository.save(existingPlate);
    }
}