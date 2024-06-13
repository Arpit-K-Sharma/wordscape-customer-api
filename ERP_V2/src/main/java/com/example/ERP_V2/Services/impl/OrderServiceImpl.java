package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.CustomerService;
import com.example.ERP_V2.Services.EmailService;
import com.example.ERP_V2.Services.OrderService;
import com.example.ERP_V2.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    @Autowired
    private ProjectTrackingRepo projectTrackingRepo;

    @Override
    public void handleOrder(int customer_id, OrderDTO orderDTO) throws MessagingException {
        Order order = this.covertToOrder(customer_id, orderDTO);

        ProjectTracking projectTracking =  new ProjectTracking();
        projectTracking.setOrderSlip(true);
        order.setProjectTracking(projectTracking);

        this.projectTrackingRepo.save(projectTracking);

        Order savedOrder = this.orderRepo.save(order);

        orderDTO.setOrderId(savedOrder.getOrderId());
        emailService.sendHTMLEmail(order.getCustomer(),orderDTO);
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
        String filePath = "ERP_V2/src/main/resources/static/invoice/" + filename + ".pdf";
        File pdfFile = new File(filePath);

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

    @Override
    public OrderDTO getOrderById(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found !!!"));
        return convertToOrderDTO(order);
    }

    @Override
    public List<Order> getOrderByCustomerId(int id) {
        return this.orderRepo.findAllByCustomerId(id);
    }

    private Order covertToOrder(int id, OrderDTO orderDTO){
        Order order = new Order();
        order.setDate(new Date());
        order.setDeadline(orderDTO.getDeadline());
        order.setPaperSize(orderDTO.getPaperSize());
        order.setPages(orderDTO.getPages());
        order.setQuantity(orderDTO.getQuantity());
        order.setEstimatedAmount(orderDTO.getEstimatedAmount());
        order.setBindingRate(orderDTO.getBindingRate());
        order.setInnerPaperRate(orderDTO.getInnerPaperRate());
        order.setLaminationRate(orderDTO.getLaminationRate());
        order.setOuterPaperRate(orderDTO.getOuterPaperRate());
        order.setDeliveryOption(orderDTO.getDeliveryOption());
        order.setPlateRate(orderDTO.getPlateRate());

        // Set Binding
        order.setBinding(bindingRepo.findByBindingType(orderDTO.getBindingType())
                .orElseThrow(() -> new IllegalArgumentException("Binding not found with name: " + orderDTO.getBindingType())));

        // Set CoverTreatment
        order.setCoverTreatment(coverTreatmentRepo.findByCoverTreatmentType(orderDTO.getCoverTreatmentType())
                .orElseThrow(() -> new IllegalArgumentException("Cover Treatment not found with name: " + orderDTO.getCoverTreatmentType())));

        //Set InnerPaperType
        order.setInnerPaper(paperRepo.findByPaperType(orderDTO.getInnerPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Paper not found with name: " + orderDTO.getInnerPaperType())));

        order.setInnerPaperThickness(orderDTO.getInnerPaperThickness());

        // Set OuterPaper
        order.setOuterPaper(paperRepo.findByPaperType(orderDTO.getOuterPaperType())
                .orElseThrow(() -> new IllegalArgumentException("Outer Paper not found with name: " + orderDTO.getOuterPaperType())));

        order.setOuterPaperThickness(orderDTO.getOuterPaperThickness());

        // Set Lamination
        order.setInnerLamination(laminationRepo.findByLaminationType(orderDTO.getInnerLamination())
                .orElseThrow(() -> new IllegalArgumentException("Lamination not found with name: " + orderDTO.getInnerLamination())));

        order.setOuterLamination(laminationRepo.findByLaminationType(orderDTO.getOuterLamination())
                .orElseThrow(() -> new IllegalArgumentException("Lamination not found with name: " + orderDTO.getOuterLamination())));

//        // Set Plate
//        order.setPlate(plateRepo.findByPlateSize(orderDTO.getPlateSize())
//                .orElseThrow(() -> new IllegalArgumentException("Plate not found with name: " + orderDTO.getPlateSize())));

        order.setInkType(orderDTO.getInkType());


        order.setRemarks(orderDTO.getRemarks());

//         Set Customer
        order.setCustomer(customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id)));

        return order;
    }

    private OrderDTO convertToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setDate(order.getDate());
        orderDTO.setDeadline(order.getDeadline());
        orderDTO.setEstimatedAmount(order.getEstimatedAmount());
        orderDTO.setBindingRate(order.getBindingRate());
        orderDTO.setInnerPaperRate(order.getInnerPaperRate());
        orderDTO.setOuterPaperRate(order.getOuterPaperRate());
        orderDTO.setLaminationRate(order.getLaminationRate());
        orderDTO.setPlateRate(order.getPlateRate());

        orderDTO.setPaperSize(order.getPaperSize());
        orderDTO.setPages(order.getPages());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setBindingType(order.getBinding().getBindingType());
        orderDTO.setCoverTreatmentType(order.getCoverTreatment().getCoverTreatmentType());
        orderDTO.setInnerPaperType(order.getInnerPaper().getPaperType());
        orderDTO.setInnerPaperThickness(order.getInnerPaperThickness());
        orderDTO.setOuterPaperType(order.getOuterPaper().getPaperType());
        orderDTO.setOuterPaperThickness(order.getOuterPaperThickness());
        orderDTO.setInnerLamination(order.getInnerLamination().getLaminationType());
        orderDTO.setOuterLamination(order.getOuterLamination().getLaminationType());
//        orderDTO.setPlateSize(order.getPlate().getPlateSize());
        orderDTO.setInkType(order.getInkType());
        orderDTO.setRemarks(order.getRemarks());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCustomer(order.getCustomer().getFullName());

        orderDTO.setDeliveryOption(order.getDeliveryOption());

        return orderDTO;
    }


    @Override
    public void insertDummyData() {

        // Fetch existing entities from the database
        Binding binding = bindingRepo.findById(1).orElse(null); // Replace 1 with the actual ID
        CoverTreatment coverTreatment = coverTreatmentRepo.findById(1).orElse(null); // Replace 1 with the actual ID
        Paper innerPaper = paperRepo.findById(1).orElse(null); // Replace 1 with the actual ID
        Paper outerPaper = paperRepo.findById(2).orElse(null); // Replace 2 with the actual ID
        Lamination lamination = laminationRepo.findById(1).orElse(null); // Replace 1 with the actual ID
        Plate plate = plateRepo.findById(1).orElse(null); // Replace 1 with the actual ID

        // Create dummy Customer object
        Customer dummyCustomer = new Customer(
                "John Doe",
                "1234 Elm Street",
                "john.doe@example.com",
                "password123",
                "555-1234",
                "Doe Enterprises",
                true
        );

        customerRepo.save(dummyCustomer);

        // Create dummy Order object 1
        Order order1 = new Order();
        order1.setDate(new Date());
        order1.setPaperSize("A4");
        order1.setPages(100);
        order1.setQuantity(50);
        order1.setBinding(binding); // Replace with actual Binding object if available
        order1.setCoverTreatment(coverTreatment); // Replace with actual CoverTreatment object if available
        order1.setInnerPaper(innerPaper); // Replace with actual Paper object if available
        order1.setInnerPaperThickness(120);
        order1.setOuterPaper(outerPaper); // Replace with actual Paper object if available
        order1.setOuterPaperThickness(200);
        order1.setInnerLamination(lamination); // Replace with actual Lamination object if available
        order1.setOuterLamination(lamination); // Replace with actual Lamination object if available
        order1.setPlate(plate); // Replace with actual Plate object if available
        order1.setInkType("CMYK");
        order1.setRemarks("Sample order 1");
        order1.setCustomer(dummyCustomer);

        orderRepo.save(order1);

        // Create dummy Order object 2
        Order order2 = new Order();
        order2.setDate(new Date());
        order2.setPaperSize("A5");
        order2.setPages(200);
        order2.setQuantity(100);
        order2.setBinding(binding); // Replace with actual Binding object if available
        order2.setCoverTreatment(coverTreatment); // Replace with actual CoverTreatment object if available
        order2.setInnerPaper(innerPaper); // Replace with actual Paper object if available
        order2.setInnerPaperThickness(130);
        order2.setOuterPaper(outerPaper); // Replace with actual Paper object if available
        order2.setOuterPaperThickness(210);
        order2.setInnerLamination(lamination); // Replace with actual Lamination object if available
        order2.setOuterLamination(lamination); // Replace with actual Lamination object if available
        order2.setPlate(plate); // Replace with actual Plate object if available
        order2.setInkType("RGB");
        order2.setRemarks("Sample order 2");
        order2.setCustomer(dummyCustomer);

        orderRepo.save(order2);

        // Create dummy Order object 3
        Order order3 = new Order();
        order3.setDate(new Date());
        order3.setPaperSize("A3");
        order3.setPages(150);
        order3.setQuantity(75);
        order3.setBinding(binding); // Replace with actual Binding object if available
        order3.setCoverTreatment(coverTreatment); // Replace with actual CoverTreatment object if available
        order3.setInnerPaper(innerPaper); // Replace with actual Paper object if available
        order3.setInnerPaperThickness(140);
        order3.setOuterPaper(outerPaper); // Replace with actual Paper object if available
        order3.setOuterPaperThickness(220);
        order3.setInnerLamination(lamination); // Replace with actual Lamination object if available
        order3.setOuterLamination(lamination); // Replace with actual Lamination object if available
        order3.setPlate(plate); // Replace with actual Plate object if available
        order3.setInkType("CMYK");
        order3.setRemarks("Sample order 3");
        order3.setCustomer(dummyCustomer);

        orderRepo.save(order3);
    }

    @Override
    public void cancelOrder(int id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found !!!"));
        order.setStatus(OrderStatus.CANCELED);
        this.orderRepo.save(order);
    }


}
