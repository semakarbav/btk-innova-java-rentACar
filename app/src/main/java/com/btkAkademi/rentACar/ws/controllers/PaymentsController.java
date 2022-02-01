package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentsController {
	private PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
			return this.paymentService.add(createPaymentRequest);
}
	@GetMapping("getByRentalId/{rentalId}")
	public DataResult<PaymentListDto> getByRentalId(@PathVariable int rentalId) {
		return this.paymentService.getByRentalId(rentalId);
		
	}
	
	@GetMapping("gettotalsumbyrentalid/{rentalId}")
	public DataResult<Double> getTotalSumByRentalId(@PathVariable int rentalId ) {
		return this.paymentService.getTotalSumByRentalId(rentalId);
		
	}
}
