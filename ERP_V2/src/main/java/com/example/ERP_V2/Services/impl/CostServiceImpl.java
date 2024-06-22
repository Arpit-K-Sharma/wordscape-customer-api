package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private PaperRepo paperRepo;

    @Autowired
    private PlateRepo plateRepo;

    @Autowired
    private BindingRepo bindingRepo;

    @Autowired
    private LaminationRepo laminationRepo;

    @Autowired
    private CoverTreatmentRepo coverTreatmentRepo;

    @Autowired
    private InkRepo inkRepo;

    @Autowired
    private PaperSizeRepo paperSizeRepo;

    @Autowired
    private PaperThicknessRepo paperThicknessRepo;

    @Override
    public void addInitialPaperCosts() {
        if (paperRepo.count() == 0) {
            List<Paper> initialPapers = Arrays.asList(
                    new Paper("Art Paper", BigDecimal.valueOf(185)),
                    new Paper("Art Board", BigDecimal.valueOf(185)),
                    new Paper("Ivory", BigDecimal.valueOf(280)),
                    new Paper("Wood Free", BigDecimal.valueOf(200)),
                    new Paper("Off White", BigDecimal.valueOf(250))
            );
            paperRepo.saveAll(initialPapers);
        }
    }

    @Override
    public void addInitialPlateCost() {
        if (plateRepo.count() == 0) {
            List<Plate> initialPLateCosts = Arrays.asList(
                    new Plate("19 X 25", BigDecimal.valueOf(300), BigDecimal.valueOf(200)),
                    new Plate("20 X 30", BigDecimal.valueOf(500), BigDecimal.valueOf(500))
            );
            plateRepo.saveAll(initialPLateCosts);
        }
    }

    @Override
    public void addInitialBindingCost() {
        if (bindingRepo.count() == 0) {
            List<Binding> initialBindingCosts = Arrays.asList(
                    new Binding("Center Stitch", BigDecimal.valueOf(50)),
                    new Binding("Juju", BigDecimal.valueOf(150)),
                    new Binding("Wiro", BigDecimal.valueOf(100)),
                    new Binding("Spiral", BigDecimal.valueOf(120)),
                    new Binding("Perforation", BigDecimal.valueOf(100)),
                    new Binding("Padding", BigDecimal.valueOf(100))
            );
            bindingRepo.saveAll(initialBindingCosts);
        }
    }

    @Override
    public void addInitialLaminationCost() {
        if (laminationRepo.count() == 0) {
            List<Lamination> initialLaminationCosts = Arrays.asList(
                    new Lamination("Normal Glossy", BigDecimal.valueOf(0.015)),
                    new Lamination("Normal Matte", BigDecimal.valueOf(0.015)),
                    new Lamination("Thermal Glossy", BigDecimal.valueOf(0.03)),
                    new Lamination("Thermal Matte", BigDecimal.valueOf(0.03))
            );
            laminationRepo.saveAll(initialLaminationCosts);
        }
    }

    @Override
    public void addInitialCoverTreatmentCost() {
        if (coverTreatmentRepo.count() == 0) {
            List<CoverTreatment> initialCoverTreatmentCosts = Arrays.asList(
                    new CoverTreatment("Die Cutting", BigDecimal.valueOf(100)),
                    new CoverTreatment("Metal Foiling", BigDecimal.valueOf(150)),
                    new CoverTreatment("Spot Varnish", BigDecimal.valueOf(160))
            );
            coverTreatmentRepo.saveAll(initialCoverTreatmentCosts);
        }
    }

    @Override
    public void addInitialInks() {
        if (inkRepo.count() == 0) {
            List<Ink> initialInks = Arrays.asList(
                    new Ink("B/W"),
                    new Ink("CMYK"),
                    new Ink("Spot")
            );
            inkRepo.saveAll(initialInks);
        }
    }

    @Override
    public void addInitialPaperSizes() {
        List<PaperSize> papers = paperSizeRepo.findAll();
        if (paperSizeRepo.count() == 0) {
            List<PaperSize> initialPaperSizes = Arrays.asList(
                    new PaperSize("A2", "420 x 594 mm"),
                    new PaperSize("A3", "297 x 420 mm"),
                    new PaperSize("A4", "210 x 297 mm")
            );
            paperSizeRepo.saveAll(initialPaperSizes);
        }
    }

    @Override
    public void addInitialPaperThicknesses() {
        if (paperThicknessRepo.count() == 0) {
            List<PaperThickness> initialThicknesses = Arrays.asList(
                    new PaperThickness(70),
                    new PaperThickness(80),
                    new PaperThickness(90),
                    new PaperThickness(100),
                    new PaperThickness(110),
                    new PaperThickness(120),
                    new PaperThickness(128),
                    new PaperThickness(130),
                    new PaperThickness(140),
                    new PaperThickness(150),
                    new PaperThickness(160),
                    new PaperThickness(170),
                    new PaperThickness(180),
                    new PaperThickness(190),
                    new PaperThickness(200),
                    new PaperThickness(210),
                    new PaperThickness(220),
                    new PaperThickness(230),
                    new PaperThickness(240),
                    new PaperThickness(250),
                    new PaperThickness(260),
                    new PaperThickness(270),
                    new PaperThickness(280),
                    new PaperThickness(290),
                    new PaperThickness(300)
            );
            paperThicknessRepo.saveAll(initialThicknesses);
        }
    }
}
