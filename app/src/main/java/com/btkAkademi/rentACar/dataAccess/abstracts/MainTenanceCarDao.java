package com.btkAkademi.rentACar.dataAccess.abstracts;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;


public interface MainTenanceCarDao extends JpaRepository<CarMaintenance, Integer> {
      List<CarMaintenance> getMaintenanceByCarId(int id);
  	
      List<CarMaintenance> findByCarIdAndMaintenanceReturnDateIsNull(int carId);
      
}
