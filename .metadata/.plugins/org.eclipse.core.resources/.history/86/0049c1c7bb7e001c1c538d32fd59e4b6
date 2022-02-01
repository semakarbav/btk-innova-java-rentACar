package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	Result add(CreatePaymentRequest createPaymentRequest);
	DataResult<List<PaymentListDto>> getAllByRentalId(int id);
	DataResult<PaymentListDto> getByRentalId(int rentalId);
	DataResult<Double> getTotalSumByRentalId(int rentalId);
	
}
