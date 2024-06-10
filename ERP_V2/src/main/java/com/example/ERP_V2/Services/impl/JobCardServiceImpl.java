package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.JobCardService;
import com.example.ERP_V2.enums.OrderStatus;
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
        Order order = this.convertToOrder(orderId, jobCardDTO);
        order.getProjectTracking().setJobCard(true);
        this.orderRepo.save(order);
    }

    @Override
    public Order getJobCardById(int id) {
        return this.orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found"));
    }

    private Order convertToOrder(int orderId, JobCardDTO jobCardDTO) {
        Order oldOrder = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found !!!"));

        // Set order_id for each associated entity
        jobCardDTO.getPrePressUnitList().setOrder(oldOrder);
        jobCardDTO.getDelivery().setOrder(oldOrder);
        jobCardDTO.getPrePressData().setOrder(oldOrder);
        jobCardDTO.getPaperDetailData().setOrder(oldOrder);
        jobCardDTO.getPlateDetailData().setOrder(oldOrder);
        jobCardDTO.getPaperData().setOrder(oldOrder);
        jobCardDTO.getPressUnitData().setOrder(oldOrder);
        jobCardDTO.getBindingData().setOrder(oldOrder);

        //Set rest foreign keys
        PlateDetailData plateDetailData = jobCardDTO.getPlateDetailData();

        for (PlateData plateData : plateDetailData.getPlateData()) {
            plateData.setPlateDetailData(plateDetailData);
        }

        //PAPER DATA
        jobCardDTO.getPaperData().getPaperData0().setPaperData(jobCardDTO.getPaperData());

        PaperData paperData = jobCardDTO.getPaperData();

        for (PaperData1 paperData1 : paperData.getPaperData1()) {
            paperData1.setPaperData(paperData);
        }
        for (PaperData2 paperData2 : paperData.getPaperData2()) {
            paperData2.setPaperData(paperData);
        }
        for (PaperData3 paperData3 : paperData.getPaperData3()) {
            paperData3.setPaperData(paperData);
        }

        //PRESSUNITDATA
        PressUnitData pressUnitData = jobCardDTO.getPressUnitData();

        for (PressData pressData: pressUnitData.getPressData()) {
            pressData.setPressUnitData(pressUnitData);
        }



        // Update the properties of the Order entity
        oldOrder.setPrePressUnitList(jobCardDTO.getPrePressUnitList());
        oldOrder.setDelivery(jobCardDTO.getDelivery());
        oldOrder.setDeadline(jobCardDTO.getDeadline());
        oldOrder.setPrePressData(jobCardDTO.getPrePressData());
        oldOrder.setPaperDetailData(jobCardDTO.getPaperDetailData());
        oldOrder.setPlateDetailData(jobCardDTO.getPlateDetailData());
        oldOrder.setPaperData(jobCardDTO.getPaperData());
        oldOrder.setPressUnitData(jobCardDTO.getPressUnitData());
        oldOrder.setBindingData(jobCardDTO.getBindingData());
        oldOrder.setStatus(OrderStatus.APPROVED);
        return oldOrder;
    }
}
