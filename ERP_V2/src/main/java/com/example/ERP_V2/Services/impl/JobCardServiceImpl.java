package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.JobCardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobCardServiceImpl implements JobCardService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    private BindingRepo bindingRepo;

    @Autowired
    private CoverTreatmentRepo coverTreatmentRepo;

    @Autowired
    private PaperRepo paperRepo;

    @Autowired
    private LaminationRepo laminationRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createJobCard(int orderId, JobCardDTO jobCardDTO) {
        Order order = convertToOrder(orderId, jobCardDTO);

        Order oldOrder = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found !!!"));

        modelMapper.map(order, oldOrder);

        orderRepo.save(oldOrder);

    }


    private Order convertToOrder(int orderId,JobCardDTO jobCardDTO){

        Order order = new Order();

//        Order

        order.setOrderId(orderId);
        order.setDate(jobCardDTO.getOrderDTO().getDate());
        order.setPaperSize(jobCardDTO.getOrderDTO().getPaperSize());
        order.setPages(jobCardDTO.getOrderDTO().getPages());
        order.setQuantity(jobCardDTO.getOrderDTO().getQuantity());

//        Set Binding
        order.setBinding(bindingRepo.findByBindingType(jobCardDTO.getOrderDTO().getBindingType())
                .orElseThrow(() -> new IllegalArgumentException("Binding not found with name: " + jobCardDTO.getOrderDTO().getBindingType())));

        // Set CoverTreatment
        order.setCoverTreatment(coverTreatmentRepo.findByCoverTreatmentType(jobCardDTO.getOrderDTO().getCoverTreatmentType())
                .orElseThrow(() -> new IllegalArgumentException("Cover Treatment not found with name: " + jobCardDTO.getOrderDTO().getCoverTreatmentType())));

        //Set InnerPaperType
        order.setInnerPaper(paperRepo.findByPaperType(jobCardDTO.getOrderDTO().getInnerPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Paper not found with name: " + jobCardDTO.getOrderDTO().getCoverTreatmentType())));

        order.setInnerPaperThickness(jobCardDTO.getOrderDTO().getInnerPaperThickness());

        // Set OuterPaper
        order.setOuterPaper(paperRepo.findByPaperType(jobCardDTO.getOrderDTO().getOuterPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Outer Paper not found with name: " + jobCardDTO.getOrderDTO().getOuterPaperType())));

        order.setOuterPaperThickness(jobCardDTO.getOrderDTO().getOuterPaperThickness());

        // Set Lamination
        order.setLamination(laminationRepo.findByLaminationType(jobCardDTO.getOrderDTO().getLaminationType())
                .orElseThrow(() -> new IllegalArgumentException("Lamination not found with name: " + jobCardDTO.getOrderDTO().getLaminationType())));


//        JobCard
        order.setJobTitle(jobCardDTO.getJobTitle());
        order.setServiceRequired(jobCardDTO.getServiceRequired());
        order.setType(jobCardDTO.getType());
        order.setSize(jobCardDTO.getSize());
        order.setPrintRun(jobCardDTO.getPrintRun());
        order.setNoOfSides(jobCardDTO.getNoOfSides());
        order.setDeadline(jobCardDTO.getDeadline());

        order.setPrePressUnitList(jobCardDTO.getPrePressUnitList());
        order.setPlateUnitList(jobCardDTO.getPlateUnitList());
        order.setPaperunitList(jobCardDTO.getPaperunitList());
        order.setBindingUnitList(jobCardDTO.getBindingUnitList());
        order.setPressUnitList(jobCardDTO.getPressUnitList());
        order.setProjectDetailsList(jobCardDTO.getProjectDetailsList());
        order.setPaperUsedList(jobCardDTO.getPaperUsedList());
        order.setContractJobList(jobCardDTO.getContractJobList());
        order.setCostCalculationList(jobCardDTO.getCostCalculationList());

        return order;
    }
}
