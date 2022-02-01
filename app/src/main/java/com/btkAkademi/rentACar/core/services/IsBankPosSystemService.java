package com.btkAkademi.rentACar.core.services;

public class IsBankPosSystemService {
	public boolean makePayment(String cardNo,String day,String mounth,String cVC) {
		if(cardNo.length() == 16 && cVC.length() == 3  ) {
			return true;
		}else {
			return false;
		}
	}
}