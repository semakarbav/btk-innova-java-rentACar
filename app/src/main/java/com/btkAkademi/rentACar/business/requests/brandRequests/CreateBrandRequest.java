package com.btkAkademi.rentACar.business.requests.brandRequests;

import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	@NotNull
	@Size(min=3 ,max=20 ,message = Messages.invalidBrandName)
	private String name;
}
