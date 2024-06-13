package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.Ink;

import java.util.List;

public interface InkService {
    Ink createInk(Ink ink);  // Create new ink

    List<Ink> getAllInks();  // Get all inks

    Ink getInkById(int inkId);  // Get ink by ID

    Ink updateInk(int inkId, Ink ink);  // Update ink

    void deleteInk(int inkId);  // Delete ink
}