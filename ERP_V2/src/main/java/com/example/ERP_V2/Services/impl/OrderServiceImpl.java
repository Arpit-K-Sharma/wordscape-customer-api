package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.*;
import com.example.ERP_V2.Services.CustomerService;
import com.example.ERP_V2.Services.EmailService;
import com.example.ERP_V2.Services.OrderService;
import com.example.ERP_V2.Services.PDFService;
import com.example.ERP_V2.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProjectTrackingRepo projectTrackingRepo;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private CustomerService customerService;

    @Value("${aws.s3.order.path}")
    private String pdfDirectory;

    @Value("${aws.s3.invoice.path}")
    private String invoiceDirectory;

    @Override
    public void handleOrder(String customer_id, OrderDTO orderDTO) throws MessagingException {

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
    public PaginatedResponse<OrderDTO> getAllOrders(Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {

        checkValidSortFields(sortField);

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Order> pagedResult = orderRepo.findAll(pageable);

        List<OrderDTO> orders = pagedResult.hasContent() ?
                pagedResult.getContent().stream()
                        .map(this::convertToOrderDTO)
                        .collect(Collectors.toList()) : new ArrayList<>();

        long totalElements = pagedResult.getTotalElements();

        return new PaginatedResponse<>(orders, totalElements);
    }

    public void checkValidSortFields(String sortField){

        // List of valid sort fields
        List<String> validSortFields = Arrays.asList("date", "deadline", "orderId");
        if (!validSortFields.contains(sortField)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortField);
        }
    }

    @Override
    public byte[] getInvoiceById(String id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" ));
        String filename = order.getOrderId() + "_" + order.getCustomer().getFullName().replaceAll(" ","_");
        String filePath = invoiceDirectory + filename + ".pdf";
        return this.pdfService.downloadPdf(filePath);
    }

    @Override
    public OrderDTO getOrderById(String id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found !!!"));
        return convertToOrderDTO(order);
    }

    @Override
    public List<Order> getOrderByCustomerId(String id) {
        return this.orderRepo.findByCustomerUserId(id);
    }

    private Order covertToOrder(String id, OrderDTO orderDTO){
        Order order = new Order();
        order.setDate(new Date());
        order.setDeadline(orderDTO.getDeadline());
        order.setPaperSize(orderDTO.getPaperSize());
        order.setPages(orderDTO.getPages());
        order.setQuantity(orderDTO.getQuantity());
        order.setEstimatedAmount(orderDTO.getEstimatedAmount());
        order.setBindingRate(orderDTO.getBindingRate());
        order.setInnerPaperRate(orderDTO.getInnerPaperRate());
        order.setInnerLaminationRate(orderDTO.getInnerLaminationRate());
        order.setOuterLaminationRate(orderDTO.getOuterLaminationRate());
        order.setOuterPaperRate(orderDTO.getOuterPaperRate());
        order.setDeliveryOption(orderDTO.getDeliveryOption());
        order.setPlateRate(orderDTO.getPlateRate());
        order.setPdfFilename(orderDTO.getPdfFile());
        order.setOrientation(orderDTO.getOrientation());

        // Set Binding
//        order.setBinding(bindingRepo.findByBindingType(orderDTO.getBindingType())
//                .orElseThrow(() -> new IllegalArgumentException("Binding not found with name: " + orderDTO.getBindingType())));

        order.setBinding(orderDTO.getBindingType().stream().collect(Collectors.joining(",")));

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

        order.setInkType(orderDTO.getInkType().stream().collect(Collectors.joining(",")));


        order.setRemarks(orderDTO.getRemarks());

//         Set Customer
        order.setCustomer(userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found ")));

        order.getCustomer().setCompanyName(orderDTO.getCompanyName());

        order.setCostCalculation(orderDTO.getCostCalculation());

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
        orderDTO.setInnerLaminationRate(order.getInnerLaminationRate());
        orderDTO.setOuterLaminationRate(order.getOuterLaminationRate());
        orderDTO.setPlateRate(order.getPlateRate());

        orderDTO.setPaperSize(order.getPaperSize());
        orderDTO.setPages(order.getPages());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setBindingType(Collections.singletonList(order.getBinding()));
        orderDTO.setCoverTreatmentType(order.getCoverTreatment().getCoverTreatmentType());
        orderDTO.setInnerPaperType(order.getInnerPaper().getPaperType());
        orderDTO.setInnerPaperThickness(order.getInnerPaperThickness());
        orderDTO.setOuterPaperType(order.getOuterPaper().getPaperType());
        orderDTO.setOuterPaperThickness(order.getOuterPaperThickness());
        orderDTO.setInnerLamination(order.getInnerLamination().getLaminationType());
        orderDTO.setOuterLamination(order.getOuterLamination().getLaminationType());
//        orderDTO.setPlateSize(order.getPlate().getPlateSize());
        orderDTO.setInkType(Collections.singletonList(order.getInkType()));
        orderDTO.setRemarks(order.getRemarks());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCustomer(order.getCustomer().getFullName());

        orderDTO.setDeliveryOption(order.getDeliveryOption());

        orderDTO.setPdfFile(order.getPdfFilename());

        orderDTO.setDelivery(order.getDelivery());

        orderDTO.setOrientation(order.getOrientation());

        return orderDTO;
    }


    @Override
    public void insertDummyData() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Fetch existing entities from the database
        Binding binding = bindingRepo.findById("1").orElse(null); // Replace 1 with the actual ID
        CoverTreatment coverTreatment = coverTreatmentRepo.findById("1").orElse(null); // Replace 1 with the actual ID
        Paper innerPaper = paperRepo.findById("1").orElse(null); // Replace 1 with the actual ID
        Paper outerPaper = paperRepo.findById("1").orElse(null); // Replace 2 with the actual ID
        Lamination lamination = laminationRepo.findById("1").orElse(null); // Replace 1 with the actual ID
        Plate plate = plateRepo.findById("1").orElse(null); // Replace 1 with the actual ID

        // Create dummy Customer object
        User dummyCustomer = new User(
                "John Doe",
                "1234 Elm Street",
                "john.doe@example.com",
                "password123",
                "555-1234",
                "Doe Enterprises",
                true
        );

        userRepo.save(dummyCustomer);

        // Create dummy Order object 1
        Order order1 = new Order();
        order1.setDate(dateFormat.parse("2023-06-01"));
        order1.setPaperSize("A4");
        order1.setPages(100);
        order1.setQuantity(50);
        order1.setBinding("Wiro"); // Replace with actual Binding object if available
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
        order2.setDate(dateFormat.parse("2023-06-15"));
        order2.setPaperSize("A5");
        order2.setPages(200);
        order2.setQuantity(100);
        order2.setBinding("Spiral"); // Replace with actual Binding object if available
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
        order3.setDate(dateFormat.parse("2023-06-30"));
        order3.setPaperSize("A3");
        order3.setPages(150);
        order3.setQuantity(75);
        order3.setBinding("Juju"); // Replace with actual Binding object if available
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
    public void cancelOrder(String id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order Not found !!!"));
        order.setStatus(OrderStatus.CANCELED);
        this.orderRepo.save(order);
    }

    @Override
    public byte[] getOrderPdf(String orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found "));
        String fullFilePath = pdfDirectory + order.getPdfFilename();
        return pdfService.downloadPdf(fullFilePath);
    }

    @Override
    public String saveOrderPdfFile(PdfUploadDTO pdfUploadDTO, String customer_id) throws IOException {
        String filename = generateFilename(pdfUploadDTO.getPdfFile(),customer_id);
        String fullFilePath = pdfDirectory + filename;
        pdfService.savePdfFile(pdfUploadDTO.getPdfFile().getInputStream(), fullFilePath);
        return filename;
    }

    public String generateFilename(MultipartFile pdfFile, String customerId) {
        String originalFilename = org.springframework.util.StringUtils.cleanPath(pdfFile.getOriginalFilename());
        String extension = org.springframework.util.StringUtils.getFilenameExtension(originalFilename);

        if (extension == null || extension.isEmpty()) {
            extension = "pdf"; // Default to pdf if no extension found
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());
        String fullName = getCustomerName(customerId);

        return customerId + "_" + fullName + "_" + currentDateTime + "." + extension;
    }

    private String getCustomerName(String customer_id) {
        CustomerDTO customerDTO = this.customerService.getCustomer(customer_id);
        return customerDTO.getFullName().replaceAll(" ", "_");
    }

}

