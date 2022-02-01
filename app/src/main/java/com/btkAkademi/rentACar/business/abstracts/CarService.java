package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;


import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;


public interface CarService {
	DataResult<List<CarListDto>> getall();
	DataResult<List<CarListDto>> getAllRentalStateFalse();  //rental state true ise araba kirada
	DataResult<CarListDto> getByCarId(int id);
	DataResult<List<CarListDto>> getCarByColorId(int colorId);
	DataResult<List<CarListDto>> getCarByBrandId(int brandId);
    DataResult<List<CarListDto>> getAllBySegmentId(int segmentId);
	//DataResult<List<CarListDto>> getAllAvailableCar();
    Result updateCarRentalState(int carId);
	DataResult<List<Integer>> getAvailableCarsBySegmentId(int segmentId);
	Result add(CreateCarRequest createCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
}
