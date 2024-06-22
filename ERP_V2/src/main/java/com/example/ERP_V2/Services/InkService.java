package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Ink;

import java.util.List;

public interface InkService {
    Ink createInk(Ink ink);  // Create new ink

    List<Ink> getAllInks();  // Get all inks

    Ink getInkById(String inkId);  // Get ink by ID

    Ink updateInk(String inkId, Ink ink);  // Update ink

    void deleteInk(String inkId);  // Delete ink
}