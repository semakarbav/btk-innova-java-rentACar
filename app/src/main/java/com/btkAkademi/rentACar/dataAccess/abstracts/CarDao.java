package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {
	Car getCarById(int id);
	List<Car> findCarByColorId(int colorId);
	List<Car> findCarByBrandId(int brandId);
    List<Car> findAllBySegmentId(int segmentId);
    List<Car> findAllByRentalStateFalse();
	
	/*@Query(value = "select cars.id as rental_id,\r\n" + "	rentals.return_date\r\n" + "from cars\r\n"
			+ "left join maintenance_cars on cars.id = maintenance_cars.car_id and maintenance_cars.maintenance_return_date is null\r\n"
			+ "left join rentals on cars.id = rentals.car_id and (rentals.return_date is null or rentals.return_date>NOW())\r\n"
			+ "where maintenance_cars.id is null and rentals.id is null and cars.segment_id =?1	and cars.city_id =?2 limit 1", nativeQuery = true)*/
	List<Integer> findAvailableCarBySegment(int segmentId);
	
	/*@Query(value = "select cars.id as carId, rentals.rent_date, \r\n"
			+ "rentals.return_date,mc.maintenance_return_date \r\n"
			+ "from cars \r\n"
			+ "left join rentals\r\n"
			+ "on rentals.car_id=cars.id \r\n"
			+ "left join maintenance_cars\r\n"
			+ "on maintenance_cars.car_id=cars.id\r\n"
			+ "where rentals.return_date<NOW() OR maintenance_cars.maintenance_return_date<NOW()",nativeQuery = true)
	List<Car> getAllAvailableCar();*/
}
