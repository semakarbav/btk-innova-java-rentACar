package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	private int id;
	private int rentedKilometer;
	private LocalDate returnDate; 
	private LocalDate rentDate; 
	private int returnedKilometer;
	private int customerId;
	private int carId;
	private int pickupCityId;
	private int returnCityId;

	
}
