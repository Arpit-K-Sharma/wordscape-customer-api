package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Lamination;

import java.util.List;

public interface LaminationService {
    List<Lamination> getAllLaminations();
    void createLamination(Lamination lamination);
    void updateLamination(String id, Lamination updatedLamination);

    void deleteLamination(String laminationId);
}