package com.btkAkademi.rentACar.business.requests.damageCarRequests;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageCarRequest {
	
	@NotNull
	private String description;
	private int carId;
}
