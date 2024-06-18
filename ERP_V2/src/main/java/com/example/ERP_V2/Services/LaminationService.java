package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Lamination;

import java.util.List;

public interface LaminationService {
    List<Lamination> getAllLaminations();
    void createLamination(Lamination lamination);
    void updateLamination(int id, Lamination updatedLamination);

    void deleteLamination(int laminationId);
}