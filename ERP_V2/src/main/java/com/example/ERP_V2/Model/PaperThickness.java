package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paperThickness")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperThickness {
    @Id
    private String thicknessId;

    private int thickness;

    public PaperThickness(int thickness) {
        this.thickness = thickness;
    }
}