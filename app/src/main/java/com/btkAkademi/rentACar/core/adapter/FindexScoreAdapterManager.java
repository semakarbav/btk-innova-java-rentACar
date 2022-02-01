package com.btkAkademi.rentACar.core.adapter;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.services.FindexScoreService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
@Service
public class FindexScoreAdapterManager implements FindexScoreAdapterService {

	@Override
	public DataResult<Integer> getScoreOfIndividualCustomer(String tcNumber) {
		FindexScoreService findexScoreService=new FindexScoreService();
		return new SuccessDataResult<Integer>(findexScoreService.getScoreOfIndividualCustomer(tcNumber));
	}

	@Override
	public DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber) {
		FindexScoreService findexScoreService=new FindexScoreService();
		return new SuccessDataResult<Integer>(findexScoreService.getScoreOfCorporateCustomer(taxNumber));
	}

	

}
