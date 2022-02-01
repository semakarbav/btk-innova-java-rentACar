package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.MaintenanceCarsListDto;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.CreateMaintenanceCarRequest;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.UpdateMaintenanceCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

public interface MaintenanceCarService {
	DataResult<List<MaintenanceCarsListDto>> getall();

	Result add(CreateMaintenanceCarRequest createMaintenanceCarReequest);

	DataResult<List<CarMaintenance>> getMaintenanceByCarId(int carId);

	DataResult<List<CarMaintenance>> getMaintanenceCar(int carId);

	Result CheckIfCarIsInMaintenance(int carId);
	Result update(UpdateMaintenanceCarRequest updateMaintenanceCarRequest);

}
