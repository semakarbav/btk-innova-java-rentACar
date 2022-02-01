package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        CorporateCustomer corporateCustomer=this.modelMapperService.forRequest()
        		.map(createCorporateCustomerRequest, CorporateCustomer.class);
        Result result= BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()),
        		checkIfEmailExists(createCorporateCustomerRequest.getEmail()));
        if(result==null){
            this.corporateCustomerDao.save(corporateCustomer);
            return new SuccessResult();
        }return new ErrorResult();

    }
    @Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
    	Result result= BusinessRules.run(checkIfCompanyNameExists(updateCorporateCustomerRequest.getCompanyName()));
    	if(result != null) {
    		return result;
    	}
    	CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getall() {
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();
		List<CorporateCustomerListDto> response = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer,
						CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
		
	}
	@Override
	public DataResult<CorporateCustomerListDto> getById(int id) {
		if(corporateCustomerDao.existsById(id)) {
			CorporateCustomer corporateCustomer = corporateCustomerDao.findById(id).get();
			CorporateCustomerListDto response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class);
			return new SuccessDataResult<CorporateCustomerListDto>(response); 
		}
		return null;
	}
    private Result checkIfEmailExists(String email){
        var corporateCustomer=this.corporateCustomerDao.getCorporateCustomerByEmail(email);
        if(corporateCustomer==null){
            return new SuccessResult();
        }return new ErrorResult();
    }
    private Result checkIfCompanyNameExists(String companyName){
        var corporateCustomer=this.corporateCustomerDao.getCorporateCustomerByCompanyName(companyName);
        if(corporateCustomer==null){
            return new SuccessResult();

        }return  new ErrorResult();
    }

	

	

}