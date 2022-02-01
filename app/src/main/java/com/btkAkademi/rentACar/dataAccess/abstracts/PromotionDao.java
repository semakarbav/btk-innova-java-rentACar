package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Promotion;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface PromotionDao extends JpaRepository<Promotion, Integer>{
	 Promotion findByPromotionCode(String code);
	 Promotion findPromotionById(int id);
	 Promotion findPromotionByPromotionCode(String promotionCode);
}
