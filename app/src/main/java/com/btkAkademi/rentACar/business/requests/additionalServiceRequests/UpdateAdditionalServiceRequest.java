package com.btkAkademi.rentACar.business.requests.additionalServiceRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServiceRequest {
	private int id;
	@NotNull
	private String name;
	private double price;

}
