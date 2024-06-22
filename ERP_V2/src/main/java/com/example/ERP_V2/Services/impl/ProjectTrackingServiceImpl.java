package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.ProjectTrackingDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Model.ProjectTracking;
import com.example.ERP_V2.Repository.OrderRepo;
import com.example.ERP_V2.Repository.ProjectTrackingRepo;
import com.example.ERP_V2.Services.ProjectTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTrackingServiceImpl implements ProjectTrackingService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    private ProjectTrackingRepo projectTrackingRepo;

    @Override
    public void setProjectTracking(String id, ProjectTrackingDTO projectTrackingDTO) {

        Order order = this.orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));

        ProjectTracking projectTracking = convertToProjectTracking(projectTrackingDTO);

        order.setProjectTracking(projectTracking);
        this.orderRepo.save(order);
    }

    @Override
    public ProjectTrackingDTO getProjectTracking(String id) {

        Order order = this.orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));

        ProjectTrackingDTO projectTrackingDTO = convertToProjectTrackingDTO(order.getProjectTracking());

        return projectTrackingDTO;


    }

    ProjectTracking convertToProjectTracking(ProjectTrackingDTO projectTrackingDTO) {

        ProjectTracking projectTracking = new ProjectTracking();

        projectTracking.setOrderSlip(projectTrackingDTO.isOrderSlip());
        projectTracking.setJobCard(projectTrackingDTO.isJobCard());
        projectTracking.setPaperCutting(projectTrackingDTO.isPaperCutting());
        projectTracking.setPlatePreparation(projectTrackingDTO.isPlatePreparation());
        projectTracking.setPrinting(projectTrackingDTO.isPrinting());
        projectTracking.setPostPress(projectTrackingDTO.isPostPress());
        projectTracking.setDelivery(projectTrackingDTO.isDelivery());
        projectTracking.setInvoice(projectTrackingDTO.isInvoice());
        projectTracking.setEnd(projectTrackingDTO.isEnd());

        return projectTracking;
    }

    public ProjectTrackingDTO convertToProjectTrackingDTO(ProjectTracking projectTracking) {
        ProjectTrackingDTO projectTrackingDTO = new ProjectTrackingDTO();

        projectTrackingDTO.setOrderSlip(projectTracking.isOrderSlip());
        projectTrackingDTO.setJobCard(projectTracking.isJobCard());
        projectTrackingDTO.setPaperCutting(projectTracking.isPaperCutting());
        projectTrackingDTO.setPlatePreparation(projectTracking.isPlatePreparation());
        projectTrackingDTO.setPrinting(projectTracking.isPrinting());
        projectTrackingDTO.setPostPress(projectTracking.isPostPress());
        projectTrackingDTO.setDelivery(projectTracking.isDelivery());
        projectTrackingDTO.setInvoice(projectTracking.isInvoice());
        projectTrackingDTO.setEnd(projectTracking.isEnd());

        return projectTrackingDTO;
    }
}
