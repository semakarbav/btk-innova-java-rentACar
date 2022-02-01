package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.MaintenanceCarService;

import com.btkAkademi.rentACar.business.dtos.MaintenanceCarsListDto;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.CreateMaintenanceCarRequest;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.UpdateMaintenanceCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/maintenencecars")
@CrossOrigin
public class MaintenancesController {

	private MaintenanceCarService maintenanceCarService;

	public MaintenancesController( MaintenanceCarService maintenanceCarService) {
		super();
		this.maintenanceCarService = maintenanceCarService;
	}
	@GetMapping("getall")
	public DataResult<List<MaintenanceCarsListDto>> getall(){
		return this.maintenanceCarService.getall();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateMaintenanceCarRequest createMaintenanceCarReequest) {
		return this.maintenanceCarService.add(createMaintenanceCarReequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody UpdateMaintenanceCarRequest updateMaintenanceCarRequest) {
		return this.maintenanceCarService.update(updateMaintenanceCarRequest);
	}
}
