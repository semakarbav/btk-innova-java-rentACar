package com.btkAkademi.rentACar.core.adapter;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PosSystemAdapterService {
	Result makePayment(String cardNo,String day,String mounth,String cVC);

}
