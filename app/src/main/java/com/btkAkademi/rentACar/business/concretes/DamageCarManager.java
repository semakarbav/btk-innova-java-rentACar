package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.DamageCarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.DamageCarListDto;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.CreateDamageCarRequest;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.UpdateDamageCarRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.DamageCarDao;
import com.btkAkademi.rentACar.entities.concretes.DamageCar;

@Service
public class DamageCarManager implements DamageCarService{
	private DamageCarDao damageCarDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public DamageCarManager(DamageCarDao damageCarDao, ModelMapperService modelMapperService) {
		super();
		this.damageCarDao = damageCarDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<DamageCarListDto>> getall() {
		List<DamageCar> damageCarList =this.damageCarDao.findAll();
		List<DamageCarListDto> response = damageCarList.stream()
				          .map(damageCar->modelMapperService.forDto().map(damageCar, DamageCarListDto.class))
				          .collect(Collectors.toList());
		return new SuccessDataResult<List<DamageCarListDto>>(response);
	}

	@Override
	public Result add(CreateDamageCarRequest createDamageCarRequest) {
		DamageCar damageCar= modelMapperService.forRequest().map(createDamageCarRequest, DamageCar.class);
		this.damageCarDao.save(damageCar);
		return new SuccessResult(Messages.carDamageAdded);
	}

	@Override
	public Result update(UpdateDamageCarRequest updateDamageCarRequest) {
		DamageCar damageCar=modelMapperService.forRequest().map(updateDamageCarRequest, DamageCar.class);
		this.damageCarDao.save(damageCar);
		return new SuccessResult(Messages.carDamageUpdated);
	}
	
	

}
