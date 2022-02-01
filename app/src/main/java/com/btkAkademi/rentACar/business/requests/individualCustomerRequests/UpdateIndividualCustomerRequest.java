package com.btkAkademi.rentACar.business.requests.individualCustomerRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class UpdateIndividualCustomerRequest {
	private int id;
	
	@NotNull
	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String tcNumber;

}
