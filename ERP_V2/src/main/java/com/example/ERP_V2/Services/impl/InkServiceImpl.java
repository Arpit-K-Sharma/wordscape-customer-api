package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Ink;
import com.example.ERP_V2.Repository.InkRepo;
import com.example.ERP_V2.Services.InkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InkServiceImpl implements InkService {

    @Autowired
    private InkRepo inkRepository;

    @Override
    public Ink createInk(Ink ink) {
        return inkRepository.save(ink);
    }

    @Override
    public List<Ink> getAllInks() {
        return inkRepository.findAll();
    }

    @Override
    public Ink getInkById(int inkId) {
        return inkRepository.findById(inkId)
                .orElseThrow(() -> new RuntimeException("Ink not found"));
    }

    @Override
    public Ink updateInk(int inkId, Ink updatedInk) {
        Ink existingInk = getInkById(inkId);
        // Update only if the provided inkType is not null
        if (updatedInk.getInkType() != null) {
            existingInk.setInkType(updatedInk.getInkType());
        }
        return inkRepository.save(existingInk);
    }

    @Override
    public void deleteInk(int inkId) {
        Ink existingInk = getInkById(inkId);
        inkRepository.delete(existingInk);
    }
}
