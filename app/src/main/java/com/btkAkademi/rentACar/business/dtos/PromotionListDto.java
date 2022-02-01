package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionListDto {
	private int id;
	private String promotionCode;
	private double discount;
	private LocalDate startDate;
	private LocalDate finishDate;


}
