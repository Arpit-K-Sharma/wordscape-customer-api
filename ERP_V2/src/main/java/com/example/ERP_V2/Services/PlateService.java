package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Plate;

import java.util.List;

public interface PlateService {
    List<Plate> getAllPlates();
    void createPlate(Plate plate);
    void updatePlate(Long id, Plate updatedPlate);
}
