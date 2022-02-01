package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("api/rentals")
@CrossOrigin
public class RentalsController {
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	@GetMapping("getall")
	public DataResult<List<RentalListDto>> getall(){
		return this.rentalService.getall();
	}
	
	@PostMapping("addCorporateCustomer")
	public DataResult<RentalListDto> addCorporateCustomer(@RequestBody  CreateRentalRequest createRentalRequest) {
			return this.rentalService.addCorporateCustomer(createRentalRequest);
	}
	@PostMapping("addIndividualCustomer")
	public  DataResult<RentalListDto> addIndividualCustomer(@RequestBody  CreateRentalRequest createRentalRequest) {
		System.out.println(createRentalRequest);
			return this.rentalService.addIndividualCustomer(createRentalRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
			return this.rentalService.update(updateRentalRequest);
	}
}
