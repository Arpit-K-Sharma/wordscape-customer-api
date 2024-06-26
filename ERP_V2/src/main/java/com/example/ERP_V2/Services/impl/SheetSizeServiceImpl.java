package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.SheetSize;
import com.example.ERP_V2.Repository.SheetSizeRepo;
import com.example.ERP_V2.Services.SheetSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetSizeServiceImpl implements SheetSizeService {

    @Autowired
    private SheetSizeRepo sheetSizeRepo;

    @Override
    public List<SheetSize> getAllSheetSizes() {
        return sheetSizeRepo.findAll();
    }

    @Override
    public Optional<SheetSize> getSheetSizeById(String id) {
        return sheetSizeRepo.findById(id);
    }

    @Override
    public SheetSize createSheetSize(SheetSize sheetSize) {
        return sheetSizeRepo.save(sheetSize);
    }

    @Override
    public Optional<SheetSize> updateSheetSize(String id, SheetSize sheetSize) {
        return sheetSizeRepo.findById(id).map(existingSheetSize -> {
            existingSheetSize.setSheetSize(sheetSize.getSheetSize());
            existingSheetSize.setValue(sheetSize.getValue());
            existingSheetSize.setSheetLength(sheetSize.getSheetLength());
            existingSheetSize.setSheetBreadth(sheetSize.getSheetBreadth());
            return sheetSizeRepo.save(existingSheetSize);
        });
    }

    @Override
    public boolean deleteSheetSize(String id) {
        return sheetSizeRepo.findById(id).map(sheetSize -> {
            sheetSizeRepo.delete(sheetSize);
            return true;
        }).orElse(false);
    }
}
