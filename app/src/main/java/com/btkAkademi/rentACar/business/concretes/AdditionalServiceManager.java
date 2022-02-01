package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;


@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,
			ModelMapperService modelMapperService,@Lazy RentalService rentalService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}
	
	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		List<AdditionalService> additionalServices=this.additionalServiceDao.findAll();
		List<AdditionalServiceListDto> response= (List<AdditionalServiceListDto>) additionalServices.stream()
				.map(additionalService -> modelMapperService.forDto()
				.map(additionalService,AdditionalServiceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
	}
	
	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService=this.modelMapperService
				.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionaServiceAdded);
	}
	
	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService additionalService=this.modelMapperService.forRequest()
				.map(updateAdditionalServiceRequest, AdditionalService.class);
				additionalServiceDao.save(additionalService);
				return new SuccessResult(Messages.additionalServiceUpdated);
				
		
	}
	
	/*private Result checkIfRentalIsExists(int rentalId) {
		if(rentalService.findRentalById(rentalId).isSuccess()) {
			return new SuccessResult("kirada");
		}
		return new ErrorResult(Messages.rentalIsNotFound);
	}*/
	

	@Override
	public DataResult<AdditionalServiceListDto> getById(int id) {
		if (additionalServiceDao.existsById(id)) {
			AdditionalService item = additionalServiceDao.findById(id).get();
			AdditionalServiceListDto response = modelMapperService.forDto().map(item,
					AdditionalServiceListDto.class);
			return new SuccessDataResult<AdditionalServiceListDto>(response,"bulundu");
		} 
		else
			return new ErrorDataResult<AdditionalServiceListDto>("hata");

	}

	@Override
	public DataResult<AdditionalService> getAdditionalServiceById(int id) {
         var result = this.additionalServiceDao.findById(id);
		
		if(result.isEmpty()) {
			return new ErrorDataResult<AdditionalService>("Bulunamadı");
		}
		
		return new SuccessDataResult<AdditionalService>(result.get() );
	}
	
	

}
