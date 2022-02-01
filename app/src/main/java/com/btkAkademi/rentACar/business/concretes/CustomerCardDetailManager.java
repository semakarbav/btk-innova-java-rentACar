package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CustomerCardDetailListDto;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.CreateCustomerCardDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.UpdateCustomerCardDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerCardDetailDao;
import com.btkAkademi.rentACar.entities.concretes.CustomerCardDetail;

@Service
public class CustomerCardDetailManager implements CustomerCardDetailService{
	private ModelMapperService modelMapperService;
	private CustomerCardDetailDao customerCardDetailDao;
	
	@Autowired
	public CustomerCardDetailManager(ModelMapperService modelMapperService,
			CustomerCardDetailDao customerCardDetailDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.customerCardDetailDao = customerCardDetailDao;
}

	@Override
	public DataResult<List<CustomerCardDetailListDto>> findCustomerPaymentDetailsByCustomerId(int customerId) {
		List<CustomerCardDetail> customerPaymentDetails = customerCardDetailDao.findAllByCustomerId(customerId);
		List<CustomerCardDetailListDto> response = customerPaymentDetails.stream().map(customerPaymentDetail -> modelMapperService.forDto().map(customerPaymentDetail, CustomerCardDetailListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<CustomerCardDetailListDto> getById(int id) {
		if(customerCardDetailDao.existsById(id)) {
			CustomerCardDetail customerPaymentDetail = (CustomerCardDetail) customerCardDetailDao.findById(id).get();
			CustomerCardDetailListDto response = modelMapperService.forDto().map(customerPaymentDetail, CustomerCardDetailListDto.class);
			return new SuccessDataResult<CustomerCardDetailListDto>(response);
		}
		return new ErrorDataResult<CustomerCardDetailListDto>();
	}

	@Override
	public Result add(CreateCustomerCardDetailRequest createCustomerPaymentDetailRequest) {
		CustomerCardDetail customerPaymentDetail = this.modelMapperService.forRequest()
				.map(createCustomerPaymentDetailRequest, CustomerCardDetail.class);
		this.customerCardDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.customerPaymentDetailAdded);
	}

	@Override
	public Result update(UpdateCustomerCardDetailsRequest updateCustomerPamentDetails) {
		CustomerCardDetail customerPaymentDetail = this.modelMapperService.forRequest()
				.map(updateCustomerPamentDetails, CustomerCardDetail.class);
		this.customerCardDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.customerPaymentDetailUpdated);
	}
}
