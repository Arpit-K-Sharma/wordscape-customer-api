package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.OrderRepo;
import com.example.ERP_V2.Services.JobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobCardServiceImpl implements JobCardService {

    @Autowired
    OrderRepo orderRepo;
    @Override
    public void createJobCard(int orderId, JobCardDTO jobCardDTO) {

    }


    private Order convertToOrder(int orderId,JobCardDTO jobCardDTO){
        Order oldOrder = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not Found"));

        Order order = new Order();

        order.setOrderId(oldOrder.getOrderId());
        order.setDate(oldOrder.getDate());
//        order.setPaperSize();



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
