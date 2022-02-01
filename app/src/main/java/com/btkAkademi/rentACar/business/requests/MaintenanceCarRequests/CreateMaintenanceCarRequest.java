package com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceCarRequest {
	@NotNull
	private LocalDate maintenanceDate;
	private int carId;
	
  
}
