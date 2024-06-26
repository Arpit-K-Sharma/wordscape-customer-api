package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sheetSize")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetSize {

    @Id
    private String sheetSizeId;

    private String sheetSize;

    private int value;

    public SheetSize(String sheetSize, int value) {
        this.sheetSize = sheetSize;
        this.value = value;
    }
}
