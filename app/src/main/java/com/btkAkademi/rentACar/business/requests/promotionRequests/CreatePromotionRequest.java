package com.btkAkademi.rentACar.business.requests.promotionRequests;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionRequest {
	
	private String promotionCode;
	private double discount;
	private LocalDate startDate;
	private LocalDate finishDate;

}
