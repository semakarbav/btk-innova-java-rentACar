package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
@Service
public class CarManager implements CarService{


	private CarDao carDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public CarManager(CarDao carDao ,ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService=modelMapperService;
	}



	@Override
	public DataResult<List<CarListDto>> getall() {
		List<Car> carList =this.carDao.findAll();
		List<CarListDto> response =  carList.stream()
				          .map(car->modelMapperService.forDto().map(car, CarListDto.class))
				          .collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}



	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car=modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(Messages.carAdded);
	}



	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		 Car car= modelMapperService.forRequest().map(updateCarRequest,Car.class);
	        Result result=BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));
	       
	        if(result==null){
	            this.carDao.save(car);
	            return new SuccessResult();
	        }return new ErrorResult();
	}
	
	  private Result checkIfCarIdExists(int carId){
	        Car car= this.carDao.getCarById(carId);
	        if(car!=null){
	            return new SuccessResult();
	        }return new ErrorResult(Messages.emptyUser);
	    }



	@Override
	public DataResult<CarListDto> getByCarId(int id) {
		if(carDao.existsById(id)){
			CarListDto response=modelMapperService.forDto().map(carDao.findById(id).get(), CarListDto.class);
			return new SuccessDataResult<CarListDto>(response);
		}
		
		return new ErrorDataResult<CarListDto>();
		
	}

	@Override
	public DataResult<List<CarListDto>> getAllBySegmentId(int segmentId) {
		List<Car> cars=carDao.findAllBySegmentId(segmentId);
		List<CarListDto> response=cars.stream().map(car->modelMapperService.forDto().map(car,CarListDto.class))
							.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	@Override
	public DataResult<List<CarListDto>> getCarByColorId(int colorId) {
		List<Car> cars=carDao.findCarByColorId(colorId);
		List<CarListDto> response=cars.stream().map(car->modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
		
	}



	@Override
	public DataResult<List<CarListDto>> getCarByBrandId(int brandId) {
		List<Car> cars=carDao.findCarByBrandId(brandId);
		List<CarListDto> response=cars.stream().map(car->modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
		
	}



	@Override
	public DataResult<List<Integer>> getAvailableCarsBySegmentId(int segmentId) {
		if(carDao.findAvailableCarBySegment(segmentId).size()<1) {
			return new ErrorDataResult<List<Integer>>("Hata");
		}
		return new SuccessDataResult<List<Integer>>(carDao.findAvailableCarBySegment(segmentId));
	}

	@Override
	public Result updateCarRentalState(int carId) {
		Car car = this.carDao.getCarById(carId);
		car.setRentalState(true);
		this.carDao.save(car);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CarListDto>> getAllRentalStateFalse() {
		List<Car> carList =this.carDao.findAllByRentalStateFalse();
		List<CarListDto> response =  carList.stream()
				          .map(car->modelMapperService.forDto().map(car, CarListDto.class))
				          .collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	private Result checkIfCarRentalStateFalse(int carId){
        Car car= this.carDao.getCarById(carId);
        if(car.isRentalState()==false){
            return new SuccessResult("araba kiralanabilir");
        }return new ErrorResult("araba kiralanamaz");
    }




	


	
	

}
