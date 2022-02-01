package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.DamageCar;

public interface DamageCarDao extends JpaRepository<DamageCar, Integer>{

}
