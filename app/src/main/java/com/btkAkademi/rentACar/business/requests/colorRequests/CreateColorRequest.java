package com.btkAkademi.rentACar.business.requests.colorRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
	@NotNull
	@Size(min=3,max=20 ,message = Messages.invalidColorName)
	private String name;
}
