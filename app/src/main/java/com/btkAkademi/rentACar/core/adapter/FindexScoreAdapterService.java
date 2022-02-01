package com.btkAkademi.rentACar.core.adapter;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface FindexScoreAdapterService {
	DataResult<Integer> getScoreOfIndividualCustomer(String tcNumber) ;
	DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber);
}
