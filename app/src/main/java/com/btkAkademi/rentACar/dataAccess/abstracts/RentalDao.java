package com.btkAkademi.rentACar.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer>{
	 Rental findByCarId(int carId);
	 Rental findByCarIdAndReturnDateIsNull(int carId);
	 List<Rental> findAllByCustomerId(int customerId);

}
