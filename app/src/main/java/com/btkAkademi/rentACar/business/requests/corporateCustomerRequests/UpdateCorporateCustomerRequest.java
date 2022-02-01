package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	private int id;
	
	@NotNull
    private String email;
    private String password;
    private String companyName;
    private String taxNumber;

}
