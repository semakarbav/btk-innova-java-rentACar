package com.btkAkademi.rentACar.business.requests.damageCarRequests;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageCarRequest {
	private int id;
	
	@NotNull
	private String description;
	private int carId;

}
