package com.example.ERP_V2;

import com.example.ERP_V2.Services.AdminService;
import com.example.ERP_V2.Services.CostService;
import com.example.ERP_V2.Services.OrderService;
import com.example.ERP_V2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpV2Application implements CommandLineRunner {

	@Autowired
	private CostService costService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ErpV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.costService.addInitialPaperCosts();
		this.costService.addInitialPlateCost();
		this.costService.addInitialBindingCost();
		this.costService.addInitialLaminationCost();
		this.costService.addInitialCoverTreatmentCost();
		this.costService.addInitialInks();
		this.costService.addInitialPaperSizes();
		this.costService.addInitialPaperThicknesses();
		this.adminService.createAdmin();
//		this.orderService.insertDummyData();
//		this.userService.insertDummyData();
	}
}
