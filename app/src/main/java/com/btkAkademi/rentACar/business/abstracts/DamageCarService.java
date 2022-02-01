package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.DamageCarListDto;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.CreateDamageCarRequest;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.UpdateDamageCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface DamageCarService {
	DataResult<List<DamageCarListDto>> getall();
	Result add(CreateDamageCarRequest createDamageCarRequest);
	Result update(UpdateDamageCarRequest updateDamageCarRequest);

}
