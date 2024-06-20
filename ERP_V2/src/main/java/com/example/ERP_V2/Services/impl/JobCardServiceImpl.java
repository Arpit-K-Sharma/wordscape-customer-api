package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.JobCardService;
import com.example.ERP_V2.enums.OrderStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found"));
        order.getCustomer().setPassword(null);
        return order;
    }

    @Override
    public void updateJobCard(int orderId, JobCardDTO jobCardDTO) {
        Order oldOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found !!!"));


        updateOrderFromDTO(oldOrder, jobCardDTO);


        orderRepo.save(oldOrder);


    }

    @Override
    public void updateDeadline(int order_id, String jsonPayload) {
        Order order = orderRepo.findById(order_id).orElseThrow(() -> new RuntimeException(("Order Not Found")));

        try {
            // Parse the JSON payload
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonPayload);
            String deadline = jsonNode.get("deadline").asText();

            // Parse the date string
            if (deadline != null && !deadline.isEmpty()) {
                try {
                    LocalDate localDate = LocalDate.parse(deadline);
                    Date deadlineDate = Date.from(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant());

                    order.setDeadline(deadlineDate);
                    orderRepo.save(order);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle parsing exception
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle JSON parsing exceptions
        }
    }

    private void updateOrderFromDTO(Order oldOrder, JobCardDTO jobCardDTO) {
        if (jobCardDTO.getPrePressUnitList() != null) {
            jobCardDTO.getPrePressUnitList().setOrder(oldOrder);
            oldOrder.setPrePressUnitList(jobCardDTO.getPrePressUnitList());
        }

        if (jobCardDTO.getDelivery() != null) {
            jobCardDTO.getDelivery().setOrder(oldOrder);
            oldOrder.setDelivery(jobCardDTO.getDelivery());
        }

        if (jobCardDTO.getPrePressData() != null) {
            jobCardDTO.getPrePressData().setOrder(oldOrder);
            oldOrder.setPrePressData(jobCardDTO.getPrePressData());
        }

        if (jobCardDTO.getPaperDetailData() != null) {
            jobCardDTO.getPaperDetailData().setOrder(oldOrder);
            oldOrder.setPaperDetailData(jobCardDTO.getPaperDetailData());
        }

        if (jobCardDTO.getPlateDetailData() != null) {
            jobCardDTO.getPlateDetailData().setOrder(oldOrder);
            PlateDetailData plateDetailData = jobCardDTO.getPlateDetailData();

            for (PlateData plateData : plateDetailData.getPlateData()) {
                plateData.setPlateDetailData(plateDetailData);
            }
            oldOrder.setPlateDetailData(jobCardDTO.getPlateDetailData());
        }

        if (jobCardDTO.getPaperData() != null) {
            jobCardDTO.getPaperData().setOrder(oldOrder);
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

            oldOrder.setPaperData(jobCardDTO.getPaperData());
        }

        if (jobCardDTO.getPressUnitData() != null) {
            jobCardDTO.getPressUnitData().setOrder(oldOrder);
            PressUnitData pressUnitData = jobCardDTO.getPressUnitData();

            for (PressData pressData: pressUnitData.getPressData()) {
                pressData.setPressUnitData(pressUnitData);
            }
            oldOrder.setPressUnitData(jobCardDTO.getPressUnitData());
        }

        if (jobCardDTO.getBindingData() != null) {
            jobCardDTO.getBindingData().setOrder(oldOrder);
            oldOrder.setBindingData(jobCardDTO.getBindingData());
        }

        if (jobCardDTO.getCostCalculation() != null) {
            jobCardDTO.getCostCalculation().setOrder(oldOrder);
            oldOrder.setCostCalculation(jobCardDTO.getCostCalculation());
        }
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
        jobCardDTO.getCostCalculation().setOrder(oldOrder);

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
        oldOrder.setPrePressData(jobCardDTO.getPrePressData());
        oldOrder.setPaperDetailData(jobCardDTO.getPaperDetailData());
        oldOrder.setPlateDetailData(jobCardDTO.getPlateDetailData());
        oldOrder.setPaperData(jobCardDTO.getPaperData());
        oldOrder.setPressUnitData(jobCardDTO.getPressUnitData());
        oldOrder.setBindingData(jobCardDTO.getBindingData());
        oldOrder.setCostCalculation(jobCardDTO.getCostCalculation());
        oldOrder.setStatus(OrderStatus.APPROVED);
        return oldOrder;
    }
}
