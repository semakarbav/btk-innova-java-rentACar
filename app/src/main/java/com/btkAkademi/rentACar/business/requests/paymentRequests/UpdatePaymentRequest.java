package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentRequest {
	private int id;
	
	private int rentalId;
	private LocalDate paymentDate;
	private int promotionId;
}
