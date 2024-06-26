package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.SheetSize;

import java.util.List;
import java.util.Optional;

public interface SheetSizeService {

    List<SheetSize> getAllSheetSizes();

    Optional<SheetSize> getSheetSizeById(String id);

    SheetSize createSheetSize(SheetSize sheetSize);

    Optional<SheetSize> updateSheetSize(String id, SheetSize sheetSize);

    boolean deleteSheetSize(String id);
}
