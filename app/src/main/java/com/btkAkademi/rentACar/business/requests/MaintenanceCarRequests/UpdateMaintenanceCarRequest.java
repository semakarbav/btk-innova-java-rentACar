package com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceCarRequest {
	
	private int id;
	private LocalDate maintenanceDate;
	private LocalDate maintenanceReturnDate;
	private int carId;
}
