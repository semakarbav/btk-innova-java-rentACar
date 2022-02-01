package com.btkAkademi.rentACar.business.abstracts;


import java.util.List;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;


public interface RentalService {
	DataResult<RentalListDto> addCorporateCustomer(CreateRentalRequest createRentalRequest);
	DataResult<RentalListDto> addIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	DataResult<Rental>findByCarId(int carId);
	DataResult<Rental> findRentalById(int id);
	DataResult<RentalListDto> getById(int id);
	DataResult<List<RentalListDto>> getall();
	Result checkIfCarAlreadyRented(int carId);
	

}
