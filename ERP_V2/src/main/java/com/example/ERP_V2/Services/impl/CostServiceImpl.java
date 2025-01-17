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

    @Autowired
    private SheetSizeRepo sheetSizeRepo;

    @Override
    public void addInitialPaperCosts() {
        if (paperRepo.count() == 0) {
            List<Paper> initialPapers = Arrays.asList(
                    new Paper("Art Paper", BigDecimal.valueOf(185),90, 200),
                    new Paper("Art Board", BigDecimal.valueOf(185), 200, 300),
                    new Paper("Ivory", BigDecimal.valueOf(280), 180, 210),
                    new Paper("Wood Free", BigDecimal.valueOf(200), 60, 120),
                    new Paper("Off White", BigDecimal.valueOf(250), 70, 100),
                    new Paper("Art Matte Paper", BigDecimal.valueOf(185), 90, 200),
                    new Paper("Map Litho", BigDecimal.valueOf(150), 60, 80),
                    new Paper("Weightless Paper", BigDecimal.valueOf(150), 60, 80),
                    new Paper("None", BigDecimal.valueOf(1), 0, 0)
            );
            paperRepo.saveAll(initialPapers);
        }
    }

    @Override
    public void addInitialPlateCost() {
        if (plateRepo.count() == 0) {
            List<Plate> initialPLateCosts = Arrays.asList(
                    new Plate("18 X 24", 18, 24, BigDecimal.valueOf(300), BigDecimal.valueOf(175), BigDecimal.valueOf(200)),
                    new Plate("19 X 25", 19, 25, BigDecimal.valueOf(300),BigDecimal.valueOf(200), BigDecimal.valueOf(200)),
                    new Plate("20 X 30", 20, 30, BigDecimal.valueOf(500), BigDecimal.valueOf(300), BigDecimal.valueOf(500))
            );
            plateRepo.saveAll(initialPLateCosts);
        }
    }

    @Override
    public void addInitialBindingCost() {
        if (bindingRepo.count() == 0) {
            List<Binding> initialBindingCosts = Arrays.asList(
                    new Binding("Center Stitch", BigDecimal.valueOf(3)),
                    new Binding("Hard Cover (Juju)", BigDecimal.valueOf(150)),
                    new Binding("Wiro", BigDecimal.valueOf(100)),
                    new Binding("Perforation", BigDecimal.valueOf(100)),
                    new Binding("Folding", BigDecimal.valueOf(200)),
                    new Binding("Perfect Binding", BigDecimal.valueOf(5)),
                    new Binding("Die Cutting", BigDecimal.valueOf(100)),
                    new Binding("Metal Foiling", BigDecimal.valueOf(150)),
                    new Binding("Spot Varnish", BigDecimal.valueOf(160)),
                    new Binding("None", BigDecimal.valueOf(1))
            );
            bindingRepo.saveAll(initialBindingCosts);
        }
    }

    @Override
    public void addInitialLaminationCost() {
        if (laminationRepo.count() == 0) {
            List<Lamination> initialLaminationCosts = Arrays.asList(
                    new Lamination("Thermal Glossy", BigDecimal.valueOf(0.02)),
                    new Lamination("Thermal Matte", BigDecimal.valueOf(0.03)),
                    new Lamination("None", BigDecimal.valueOf(1))
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
                    new CoverTreatment("Spot Varnish", BigDecimal.valueOf(160)),
                    new CoverTreatment("None", BigDecimal.valueOf(1))
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
                    new PaperSize("A2", "420 x 594 mm", 16.5, 23.4),
                    new PaperSize("A3", "297 x 420 mm", 11.7, 16.5),
                    new PaperSize("A4", "210 x 297 mm", 8.3, 11.7)
            );
            paperSizeRepo.saveAll(initialPaperSizes);
        }
    }

    @Override
    public void addInitialPaperThicknesses() {
        if (paperThicknessRepo.count() == 0) {
            List<PaperThickness> initialThicknesses = Arrays.asList(
                    new PaperThickness(60),
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

    @Override
    public void addInitialSheetSizes() {
        if (sheetSizeRepo.count() == 0) {
            List<SheetSize> initialSheetSizes = Arrays.asList(
                    new SheetSize("20 X 30", 20, 30 ,600),
                    new SheetSize("24 X 36", 24, 36 ,864)
            );
            sheetSizeRepo.saveAll(initialSheetSizes);
        }
    }

}
