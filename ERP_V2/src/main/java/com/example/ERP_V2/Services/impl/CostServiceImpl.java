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

    @Override
    public void addInitialPaperCosts() {
        List<Paper> initialPapers = Arrays.asList(
                new Paper("Art_Paper", BigDecimal.valueOf(185)),
                new Paper("Art_Board", BigDecimal.valueOf(185)),
                new Paper("Ivory", BigDecimal.valueOf(280)),
                new Paper("Wood Free", BigDecimal.valueOf(200)),
                new Paper("Off White", BigDecimal.valueOf(250))
        );
        paperRepo.saveAll(initialPapers);
    }

    @Override
    public void addInitialPlateCost() {
        List<Plate> initialPLateCosts = Arrays.asList(
                new Plate("18 X 24", BigDecimal.valueOf(400), BigDecimal.valueOf(350)),
                new Plate("19 X 25", BigDecimal.valueOf(300), BigDecimal.valueOf(200)),
                new Plate("20 X 30", BigDecimal.valueOf(500), BigDecimal.valueOf(500))
        );
        plateRepo.saveAll(initialPLateCosts);
    }

    @Override
    public void addInitialBindingCost() {
        List<Binding> initialBindingCosts = Arrays.asList(
                new Binding("Center Stitch", BigDecimal.valueOf(50)),
                new Binding("Juju", BigDecimal.valueOf(150)),
                new Binding("Wiro", BigDecimal.valueOf(100)),
                new Binding("Spiral", BigDecimal.valueOf(120))
        );
        bindingRepo.saveAll(initialBindingCosts);
    }

    @Override
    public void addInitialLaminationCost() {
        List<Lamination> initialLaminationCosts = Arrays.asList(
                new Lamination("Normal Glossy", BigDecimal.valueOf(0.015)),
                new Lamination("Normal Matte", BigDecimal.valueOf(0.015)),
                new Lamination("Thermal Glossy", BigDecimal.valueOf(0.03)),
                new Lamination("Thermal Matte", BigDecimal.valueOf(0.03))
        );
        laminationRepo.saveAll(initialLaminationCosts);
    }

    @Override
    public void addInitialCoverTreatmentCost() {
        List<CoverTreatment> initialCoverTreatmentCosts = Arrays.asList(
                new CoverTreatment("Die Cutting", BigDecimal.valueOf(100)),
                new CoverTreatment("Metal Foiling", BigDecimal.valueOf(150)),
                new CoverTreatment("Spot Varnish", BigDecimal.valueOf(160))
        );
        this.coverTreatmentRepo.saveAll(initialCoverTreatmentCosts);
    }
}
