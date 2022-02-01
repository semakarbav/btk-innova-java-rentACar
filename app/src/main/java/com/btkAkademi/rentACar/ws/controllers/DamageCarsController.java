package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.btkAkademi.rentACar.business.abstracts.DamageCarService;
import com.btkAkademi.rentACar.business.dtos.DamageCarListDto;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.CreateDamageCarRequest;
import com.btkAkademi.rentACar.business.requests.damageCarRequests.UpdateDamageCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/damageCars")
@CrossOrigin
public class DamageCarsController {
	private DamageCarService damageCarService;

	public DamageCarsController(DamageCarService damageCarService) {
		super();
		this.damageCarService = damageCarService;
	}
	@GetMapping("getall")
	public DataResult<List<DamageCarListDto>> getall(){
		return this.damageCarService.getall();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateDamageCarRequest createDamageCarRequest) {
		return this.damageCarService.add(createDamageCarRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateDamageCarRequest updateDamageCarRequest) {
		return this.damageCarService.update(updateDamageCarRequest);
	}
	
}
