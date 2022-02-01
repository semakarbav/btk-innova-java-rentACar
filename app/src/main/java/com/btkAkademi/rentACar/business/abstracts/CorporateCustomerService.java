package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	 Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	 Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	 DataResult<List<CorporateCustomerListDto>> getall();
	 DataResult<CorporateCustomerListDto> getById(int id);
}
