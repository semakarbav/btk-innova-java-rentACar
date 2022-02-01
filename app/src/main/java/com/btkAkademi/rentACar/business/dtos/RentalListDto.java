package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalListDto {
	private int id;
	private LocalDate rentDate;
	private int rentedKilometer;
	private LocalDate returnDate;
	private int returnedKilometer;
	private int carId;
	private int customerId;
	
    

}
