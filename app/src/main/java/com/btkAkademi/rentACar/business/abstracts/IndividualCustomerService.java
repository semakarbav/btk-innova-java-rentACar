package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> getAll();
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	 DataResult<IndividualCustomerListDto> getById(int id);
	 
	 
	
}
