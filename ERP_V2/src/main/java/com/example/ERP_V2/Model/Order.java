package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int orderId;

    @Column(nullable = false)
    private Date date;

    @Column(length = 15)
    private String paperSize;

    private int pages;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "binding_type_id")
    private Binding binding;

    @ManyToOne
    @JoinColumn(name = "cover_treatment_id")
    private CoverTreatment coverTreatment;

    @ManyToOne
    @JoinColumn(name = "inner_paper_type_id")
    private Paper innerPaper;

    private int innerPaperThickness;

    @ManyToOne
    @JoinColumn(name = "outer_paper_type_id")
    private Paper outerPaper;

    private int outerPaperThickness;

    @ManyToOne
    @JoinColumn(name = "lamination_type_id")
    private Lamination lamination;

    @ManyToOne
    @JoinColumn(name = "plate_size_id")
    private Plate plate;

    @Column(length = 20)
    private String inkType;

    @Column
    private String remarks;

    private OrderStatus status = OrderStatus.PENDING;

    //COSTS

    @Column(precision = 6, scale = 2)
    private BigDecimal bindingCost;

    @Column(precision = 6, scale = 2)
    private BigDecimal coverTreatmentCost;

    @Column(precision = 6, scale = 2)
    private BigDecimal innerPaperRate;

    @Column(precision = 6, scale = 2)
    private BigDecimal laminationPrice;

    @Column(precision = 6, scale = 2)
    private BigDecimal outerPaperRate;

    @Column(precision = 6, scale = 2)
    private BigDecimal estimatedAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private SalesBook salesBook;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<SalesRecord> salesRecordList = new ArrayList<>();


    //JOB CARD

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PrePressUnit prePressUnitList;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PrePressData prePressData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaperDetailData paperDetailData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PlateDetailData plateDetailData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaperData paperData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PressUnitData pressUnitData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private BindingData bindingData;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CostCalculation> costCalculationList = new ArrayList<>();

    @OneToOne
    private ProjectTracking projectTracking;

}

