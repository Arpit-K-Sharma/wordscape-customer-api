package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.OrderStatus;
import com.example.ERP_V2.enums.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String orderId;

    private Date date;

    private Date deadline;

    private String paperSize;

    private int pages;

    private int quantity;

    private String binding;

    @DBRef
    private CoverTreatment coverTreatment;

    @DBRef
    private Paper innerPaper;

    private int innerPaperThickness;

    @DBRef
    private Paper outerPaper;

    private int outerPaperThickness;

    @DBRef
    private Lamination innerLamination;

    @DBRef
    private Lamination outerLamination;

    @DBRef
    private Plate plate;

    private String inkType;

    private String deliveryOption;

    private String remarks;

    private Orientation orientation;

    private OrderStatus status = OrderStatus.PENDING;

    //COSTS

    private BigDecimal bindingRate;

    private BigDecimal innerPaperRate;

    private BigDecimal innerLaminationRate;

    private BigDecimal outerLaminationRate;

    private BigDecimal outerPaperRate;

    private BigDecimal plateRate;

    private int estimatedAmount;

    private String pdfFilename;

    @DBRef
    private User customer;

    @DBRef
    private SalesBook salesBook;

    @DBRef
    private List<SalesRecord> salesRecordList = new ArrayList<>();


    //JOB CARD

    private PrePressUnit prePressUnitList;

    private Delivery delivery;

    private PrePressData prePressData;

    private PaperDetailData paperDetailData;

    private PlateDetailData plateDetailData;

    private PaperData paperData;

    private PressUnitData pressUnitData;

    private BindingData bindingData;

    private CostCalculation costCalculation;

    private ProjectTracking projectTracking;

}

