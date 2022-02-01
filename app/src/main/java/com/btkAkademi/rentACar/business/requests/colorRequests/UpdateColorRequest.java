package com.btkAkademi.rentACar.business.requests.colorRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorRequest {
	private int id;
	
	@NotNull
	@Size(min=3, max=20,message=Messages.invalidColorName)
	private String name;

}
