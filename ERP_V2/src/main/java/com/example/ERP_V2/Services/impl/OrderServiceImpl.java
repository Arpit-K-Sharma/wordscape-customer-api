package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.EmailService;
import com.example.ERP_V2.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private EmailService emailService;

    @Override
    public void handleOrder(OrderDTO orderDTO) throws MessagingException {
        Order order = this.covertToOrder(orderDTO);

        Customer customer = new Customer();
        customer.setFullName(orderDTO.getName());
        customer.setCompanyName(orderDTO.getCompanyName());
        customer.setEmail(orderDTO.getEmail());
        customer.setAddress(orderDTO.getAddress());
        customer.setStatus(true);

        order.setCustomer(customer);

        customer.getOrderList().add(order);

        this.customerRepo.save(customer);

        Order savedOrder = this.orderRepo.save(order);
        orderDTO.setOrderId(savedOrder.getOrderId());

        emailService.sendHTMLEmail(customer.getEmail(),orderDTO);
//        emailService.sendEmail(customer.getEmail(),customer.getFullName());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return this.orderRepo.findAll().stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] getInvoiceById(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
        String filename = order.getOrderId() + "_" + order.getCustomer().getFullName().replaceAll(" ","_");
        File pdfFile = new File("D:\\Spring\\ERP_V2\\ERP_V2\\src\\main\\resources\\static\\invoice\\" + filename + ".pdf");

        if (!pdfFile.exists()) {
            throw new RuntimeException("Invoice file not found for order ID: " + id);
        }

        try (FileInputStream inputStream = new FileInputStream(pdfFile)) {
            byte[] fileContent = new byte[(int) pdfFile.length()];
            int bytesRead = inputStream.read(fileContent);

            if (bytesRead < 0) {
                throw new IOException("Failed to read invoice file content");
            }

            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("Error reading invoice file: " + e.getMessage());
        }
    }

    private Order covertToOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setDate(new Date());
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

        order.setRemarks(orderDTO.getRemarks());

        // Set Customer
//        order.setCustomer(customerRepo.findById(orderDTO.getCustomerId())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + orderDTO.getCustomerId())));

        return order;
    }

    private OrderDTO convertToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setDate(order.getDate());
        orderDTO.setPaperSize(order.getPaperSize());
        orderDTO.setPages(order.getPages());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setBindingType(order.getBinding().getBindingType());
        orderDTO.setCoverTreatmentType(order.getCoverTreatment().getCoverTreatmentType());
        orderDTO.setInnerPaperType(order.getInnerPaper().getPaperType());
        orderDTO.setInnerPaperThickness(order.getInnerPaperThickness());
        orderDTO.setOuterPaperType(order.getOuterPaper().getPaperType());
        orderDTO.setOuterPaperThickness(order.getOuterPaperThickness());
        orderDTO.setLaminationType(order.getLamination().getLaminationType());
//        orderDTO.setPlateSize(order.getPlate().getPlateSize());
        orderDTO.setInkType(order.getInkType());
        orderDTO.setRemarks(order.getRemarks());
        orderDTO.setName(order.getCustomer().getFullName());

        return orderDTO;
    }
}

