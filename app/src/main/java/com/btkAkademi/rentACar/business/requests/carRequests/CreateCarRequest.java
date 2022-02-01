package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	  @NotNull
	    private double dailyPrice;
	    private int modelYear;
	    private String description;
	    private int findexScore;
	    private int kilometer;
	    private int brandId;
	    private int colorId;
	    private int minAge;	
	    private int segmentId;
}
