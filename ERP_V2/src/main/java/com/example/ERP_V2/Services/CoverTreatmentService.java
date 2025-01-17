package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.CoverTreatment;

import java.util.List;

public interface CoverTreatmentService {
    List<CoverTreatment> getAllCoverTreatments();
    void createCoverTreatment(CoverTreatment coverTreatment);
    void updateCoverTreatment(String id, CoverTreatment updatedCoverTreatment);

    void deleteCoverTreatment(String coverTreatmentId);
}