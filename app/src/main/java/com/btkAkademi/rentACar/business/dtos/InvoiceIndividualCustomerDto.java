package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceIndividualCustomerDto {
	private int id;
	private String tcNumber;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate rentDate;
	private LocalDate returnedDate;
	private double totalPrice;
	private LocalDate creationDate;
	//private List<AdditionalServiceListDto> additonalServices;
}
