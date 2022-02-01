package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualDao extends JpaRepository<IndividualCustomer, Integer>{
	IndividualCustomer findByEmail(String email);
	IndividualCustomer findByEmailAndPassword(String email,String password);
	
}
