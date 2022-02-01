package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.helpers.CreditCardInfo;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PosSystemService {
	Result checkIfCreditCardIsValid(CreditCardInfo cardInfo);
}