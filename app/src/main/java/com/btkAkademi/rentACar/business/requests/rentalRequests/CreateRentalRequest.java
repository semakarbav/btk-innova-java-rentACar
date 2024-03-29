package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	private LocalDate rentDate;
	private LocalDate returnDate; 
	private int rentedKilometer;
	private int returnedKilometer;
	private int customerId;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	
	private List<Integer> additionalServices;
	
	

}
