package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private double dailyPrice;
    private int modelYear;
    private String description;
    private int findexScore;
    private int kilometer;
    private int brandId;
    private int colorId;
    private int minAge;
    private int segmentId;
    private String brandName;
    private String colorName;
    private boolean rentalState;
    

}
