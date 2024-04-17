package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BindingRepo bindingRepo;

    @Autowired
    private CoverTreatmentRepo coverTreatmentRepo;

    @Autowired
    private PaperRepo paperRepo;

    @Autowired
    private LaminationRepo laminationRepo;

    @Autowired
    private PlateRepo plateRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void handleOrder(OrderDTO orderDTO) {
        Order order = this.covertToOrder(orderDTO);
        this.orderRepo.save(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = this.orderRepo.findAll();
        return null;
    }

    private Order covertToOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setDate(orderDTO.getDate());
        order.setPaperSize(orderDTO.getPaperSize());
        order.setPages(orderDTO.getPages());
        order.setQuantity(orderDTO.getQuantity());
        // Set Binding
        order.setBinding(bindingRepo.findByBindingType(orderDTO.getBindingType())
                .orElseThrow(() -> new IllegalArgumentException("Binding not found with name: " + orderDTO.getBindingType())));

        // Set CoverTreatment
        order.setCoverTreatment(coverTreatmentRepo.findByCoverTreatmentType(orderDTO.getCoverTreatmentType())
                .orElseThrow(() -> new IllegalArgumentException("Cover Treatment not found with name: " + orderDTO.getCoverTreatmentType())));

        //Set InnerPaperType
        order.setInnerPaper(paperRepo.findByPaperType(orderDTO.getInnerPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Paper not found with name: " + orderDTO.getCoverTreatmentType())));

        order.setInnerPaperThickness(orderDTO.getInnerPaperThickness());

        // Set OuterPaper
        order.setOuterPaper(paperRepo.findByPaperType(orderDTO.getOuterPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Outer Paper not found with name: " + orderDTO.getOuterPaperType())));

        order.setOuterPaperThickness(orderDTO.getOuterPaperThickness());

        // Set Lamination
        order.setLamination(laminationRepo.findByLaminationType(orderDTO.getLaminationType())
                .orElseThrow(() -> new IllegalArgumentException("Lamination not found with name: " + orderDTO.getLaminationType())));

//        // Set Plate
//        order.setPlate(plateRepo.findByPlateSize(orderDTO.getPlateSize())
//                .orElseThrow(() -> new IllegalArgumentException("Plate not found with name: " + orderDTO.getPlateSize())));

        order.setInkType(orderDTO.getInkType());

        // Set Customer
        order.setCustomer(customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + orderDTO.getCustomerId())));

        order.setRemarks(orderDTO.getRemarks());

        return order;
    }
}
