package com.btkAkademi.rentACar.core.adapter;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.helpers.CreditCardInfo;
import com.btkAkademi.rentACar.core.services.IsBankPosSystemService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class PosSystemAdapter implements PosSystemAdapterService{

	@Override
	public Result makePayment(String cardNo, String day, String mounth, String cVC) {
		IsBankPosSystemService service = new IsBankPosSystemService();
		if(service.makePayment(cardNo, day, mounth, cVC))
		{
			return new SuccessResult();
		}
		return new ErrorResult(Messages.wrongCardInfo);
	}
}
