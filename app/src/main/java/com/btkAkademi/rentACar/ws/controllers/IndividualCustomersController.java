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

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomer")
@CrossOrigin
public class IndividualCustomersController {
	
	private IndividualCustomerService individualService;
	public IndividualCustomersController(IndividualCustomerService individualService) {
		super();
		this.individualService = individualService;
	}
	@GetMapping("getall")
	public DataResult<List<IndividualCustomerListDto>> getall(){
		return this.individualService.getAll();
		
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		return this.individualService.add(createIndividualCustomerRequest);
	}
	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		
		return this.individualService.update(updateIndividualCustomerRequest);
	}
}
