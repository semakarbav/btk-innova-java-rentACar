package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
	
	private int rentalId;
	private int totalPrice;
	private LocalDate paymentDate;
	private String promotionCode;
}
