package com.example.ERP_V2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperThickness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int thicknessId;

    private int thickness;

    public PaperThickness(int thickness) {
        this.thickness = thickness;
    }
}

