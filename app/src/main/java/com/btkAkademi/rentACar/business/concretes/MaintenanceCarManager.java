package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.MaintenanceCarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.MaintenanceCarsListDto;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.CreateMaintenanceCarRequest;
import com.btkAkademi.rentACar.business.requests.MaintenanceCarRequests.UpdateMaintenanceCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.MainTenanceCarDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class MaintenanceCarManager implements MaintenanceCarService {

	private MainTenanceCarDao mainTenanceCarDao;
	private RentalService rentalService;
	private CarService carService;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public MaintenanceCarManager(MainTenanceCarDao mainTenanceCarDao,ModelMapperService modelMapperService,
			@Lazy RentalService rentalService, CarService carService) {
		this.mainTenanceCarDao=mainTenanceCarDao;
		this.modelMapperService=modelMapperService;
		this.rentalService=rentalService;
		this.carService=carService;
	}
	@Override
	public DataResult<List<MaintenanceCarsListDto>> getall() {
		List<CarMaintenance> carMaintenances =this.mainTenanceCarDao.findAll();
		List<MaintenanceCarsListDto> response = carMaintenances.stream()
				.map(carMaintenance->modelMapperService.forDto().map(carMaintenance, MaintenanceCarsListDto.class))
				.collect(Collectors.toList());
				        
				return new SuccessDataResult<List<MaintenanceCarsListDto>>(response);
		
	}
	@Override
	public Result add(CreateMaintenanceCarRequest createMaintenanceCarReequest) {
		Result result=BusinessRules.run(checkIfCarIsRented(createMaintenanceCarReequest.getCarId()),
				checkIfCarIsExists(createMaintenanceCarReequest.getCarId()),
				CheckIfCarIsInMaintenance(createMaintenanceCarReequest.getCarId()));
		if(result!=null) {
			return result;
		}
		CarMaintenance carMaintenance= modelMapperService.forRequest().map(createMaintenanceCarReequest, CarMaintenance.class);
		this.mainTenanceCarDao.save(carMaintenance);
		return new SuccessResult(Messages.maintenanceCarAdded);
		
	}
	@Override
	public Result update(UpdateMaintenanceCarRequest updateMaintenanceCarRequest) {
		
		Result result=BusinessRules.run(checkIfCarIsRented(updateMaintenanceCarRequest.getCarId()),
				checkIfCarIsExists(updateMaintenanceCarRequest.getCarId()),
				CheckIfCarIsInMaintenance(updateMaintenanceCarRequest.getCarId()),
				checkIfControlDate(updateMaintenanceCarRequest.getMaintenanceDate(),updateMaintenanceCarRequest.getMaintenanceReturnDate()));
		if(result!=null) {
			return result;
		}
		CarMaintenance carMaintenance=modelMapperService.forRequest().map(updateMaintenanceCarRequest, CarMaintenance.class);
		this.mainTenanceCarDao.save(carMaintenance);
		return new SuccessResult(Messages.maintenanceCarUpdated);
	}
	
	@Override
	public DataResult<List<CarMaintenance>> getMaintenanceByCarId(int carId) {
		 return new SuccessDataResult<List<CarMaintenance>>(this.mainTenanceCarDao.getMaintenanceByCarId(carId));
	}
	@Override
	public DataResult<List<CarMaintenance>> getMaintanenceCar(int carId) {
		return new SuccessDataResult<List<CarMaintenance>>(this.mainTenanceCarDao.findByCarIdAndMaintenanceReturnDateIsNull(carId));
	}
	
	
    private Result checkIfCarIsRented(int carId) {
    	Rental rental = this.rentalService.findByCarId(carId).getData();
		if(rental != null && LocalDate.now().isBefore(rental.getReturnDate())) {
			return new ErrorResult("araba kirada");
		}
		
		return new SuccessResult("Başarılı");
	}
    
    private Result checkIfControlDate(LocalDate maintenanceDate,LocalDate maintenanceReturnDate) {
		if(maintenanceReturnDate.isAfter(maintenanceDate)) {
			return new SuccessResult();
			
		}
		
		return  new ErrorResult(Messages.maintenanceReturnDateShouldBeAfterTheMaintenanceDate);
	}
    private Result checkIfCarIsExists(int carId) {
		if(!carService.getByCarId(carId).isSuccess()) {
			return new ErrorResult(Messages.carIdNotExists);
		}
		else return new SuccessResult();
	}
	@Override
	public Result CheckIfCarIsInMaintenance(int carId) {
		if(this.mainTenanceCarDao.findByCarIdAndMaintenanceReturnDateIsNull(carId)!=null) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}
	

	
	
	
	
	

}
