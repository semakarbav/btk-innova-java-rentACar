package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.MaintenanceCarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.adapter.FindexScoreAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private MaintenanceCarService maintenanceCarService;
	private CityService cityService;
	private CarService carService;
	private FindexScoreAdapterService findexScoreAdapterService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private AdditionalServiceService additionalServiceService;
	
	
	
	@Override
	public DataResult<RentalListDto> addCorporateCustomer(CreateRentalRequest createRentalRequest) {
		Result result=BusinessRules.run(
				checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfCarMaintenence(createRentalRequest.getCarId()),
				checkIfCityExists(createRentalRequest.getPickUpCityId())
				/*checkIfCorporateCustomerEnoughFindexScore(corporateCustomerService.getById(
						createRentalRequest.getCustomerId()).getData().getTaxNumber(),
						carService.getByCarId(createRentalRequest.getCarId()).getData().getFindexScore())*/
				
				);
		if(result==null) {
			Rental rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
			
			AdditionalService additionalService;
			for(Integer asId:createRentalRequest.getAdditionalServices()) {
			 additionalService=this.additionalServiceService.getAdditionalServiceById(asId).getData();
			 additionalService.getRentals().add(rental);
			 rental.getAdditionalServices().add(additionalService);
			}
			this.rentalDao.save(rental);
			RentalListDto rentalListDto = this.modelMapperService.forDto().map(rental, RentalListDto.class);
			return new SuccessDataResult<RentalListDto>(rentalListDto, Messages.rentSuccess);
			
		}
		return new ErrorDataResult<RentalListDto>("hata");
	
		
	}
	@Override
	public DataResult<RentalListDto> addIndividualCustomer(CreateRentalRequest createRentalRequest) {

		if (!checkIfCarMaintenence(createRentalRequest.getCarId()).isSuccess() || !checkIfCarAlreadyRented(createRentalRequest.getCarId()).isSuccess()) {
			CarListDto car = getAvailableCar(carService.getByCarId(createRentalRequest.getCarId()).getData().getSegmentId()).getData();
			
			if(car!=null) {
				createRentalRequest.setCarId(car.getId());
			}else return new ErrorDataResult<RentalListDto>(Messages.noAvailableCarInThisSegment);
		}
		Result result=BusinessRules.run(checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfCarMaintenence(createRentalRequest.getCarId()),
				checkIfCityExists(createRentalRequest.getPickUpCityId()),
				/*checkIfIndividualCustomerEnoughFindexScore(individualCustomerService.getById(
						createRentalRequest.getCustomerId()).getData().getTcNumber(),
						carService.getByCarId(createRentalRequest.getCarId()).getData().getFindexScore()),*/
				checkIfCustomerAgeIsEnough(createRentalRequest.getCustomerId(),createRentalRequest.getCarId())
				);

		if (result == null) {
			Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
			
			AdditionalService additionalService;
			for(Integer asId:createRentalRequest.getAdditionalServices()) {
			 additionalService=this.additionalServiceService.getAdditionalServiceById(asId).getData();
			 additionalService.getRentals().add(rental);
			 rental.getAdditionalServices().add(additionalService);
			}
			
			this.rentalDao.save(rental);
			RentalListDto rentalListDto = this.modelMapperService.forDto().map(rental, RentalListDto.class);
			return new SuccessDataResult<RentalListDto>(rentalListDto, Messages.rentSuccess);
		}
		return new ErrorDataResult<RentalListDto>("hata");
		
	}
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result=BusinessRules.run(checkIfControlDate(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate())
				,checkIfControlKilometer(updateRentalRequest.getRentedKilometer(), updateRentalRequest.getReturnedKilometer()),
				checkIfCityExists(updateRentalRequest.getReturnCityId()));
		if(result !=null) {
			return result;
		}
		Rental rental=modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentUpdated);
	}
	
	private Result checkIfControlDate(LocalDate rentDate,LocalDate returnDate) {
		if(returnDate.isAfter(rentDate)) {
			return new SuccessResult();
			
		}
		
		return  new ErrorResult(Messages.returnDateShouldBeAfterTheRentDate);
	}
	private Result checkIfControlKilometer(int rentedKilometer,int returnedKilometer) {
		if( rentedKilometer > returnedKilometer) {
			return new ErrorResult(Messages.returnedKilometerShouldNotBeLowerThanRentedKilometer);
		}
		return new SuccessResult();
	}

	private Result checkIfCustomerExist(int customerId) {
		if (!customerService.findCustomerById(customerId).isSuccess()) {
			return new ErrorResult(Messages.customerNotFound);
		}

		return new SuccessResult();
	}
	private Result checkIfCarMaintenence(int carId) {
		
		if(!maintenanceCarService.CheckIfCarIsInMaintenance(carId).isSuccess()) {
			return new ErrorResult(Messages.carMaintenance);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<Rental> findByCarId(int carId) {
		return new SuccessDataResult<Rental>(this.rentalDao.findByCarId(carId));
	}

	@Override
	public DataResult<Rental> findRentalById(int id) {
		System.out.println(rentalDao.existsById(id));
		if(rentalDao.existsById(id)) {
			return new SuccessDataResult<Rental>(rentalDao.getById(id));
		}
		return new ErrorDataResult<Rental>();
	}
	
	private Result checkIfCityExists(int id) {
		if(!cityService.findCityById(id).isSuccess()) {
			return new ErrorResult(Messages.carInMaintenance);
		}
		return new SuccessResult();
	}
	private Result checkIfIndividualCustomerEnoughFindexScore(String tcNumber,int minScore) {
		if(findexScoreAdapterService.getScoreOfIndividualCustomer(tcNumber).getData()>=minScore) {
			return new SuccessResult("yeterli score");
		}
		return new ErrorResult("yetersiz score");
	}
	private Result checkIfCorporateCustomerEnoughFindexScore(String taxNumber,int minScore) {
		if(findexScoreAdapterService.getScoreOfIndividualCustomer(taxNumber).getData()>=minScore) {
			return new SuccessResult("yeterli score");
		}
		return new ErrorResult("yetersiz score");
	}

	@Override
	public DataResult<RentalListDto> getById(int id) { 
		Rental rental=this.rentalDao.findById(id).get();
		RentalListDto response=modelMapperService.forDto().map(rental, RentalListDto.class);
		return new SuccessDataResult<RentalListDto>(response);
	}

	@Override
	public Result checkIfCarAlreadyRented(int carId) {
		Rental rental = this.rentalDao.findByCarId(carId);
		if(rental != null && LocalDate.now().isBefore(rental.getReturnDate())) {
			return new ErrorResult();
		}
		
		return new SuccessResult("Başarılı");
	}

	@Override
	public DataResult<List<RentalListDto>> getall() {
		List<Rental> rentalList=this.rentalDao.findAll();
		List<RentalListDto> response=rentalList.stream()
				.map(rental->modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentalListDto>>(response);
	}
	
	private Result checkIfCustomerAgeIsEnough(int customerId, int carId) {
		
		int age = Period.between(individualCustomerService.getById(customerId).getData().getBirthDate(), LocalDate.now()).getYears();
		int minAge = carService.getByCarId(carId).getData().getMinAge();
		if(age<minAge) {
			return new ErrorResult(Messages.ageIsNotEnough);
		}
		return new SuccessResult();
		
	} 
	private DataResult<CarListDto> getAvailableCar(int SegmentId) {
		if(carService.getAvailableCarsBySegmentId(SegmentId).isSuccess()) {
			CarListDto car = carService.getByCarId(carService.getAvailableCarsBySegmentId(SegmentId).getData().get(0)).getData();
			return new SuccessDataResult<CarListDto>(car);
		}else return new ErrorDataResult<CarListDto>();
	}
	
	
	

	
	
	
}
