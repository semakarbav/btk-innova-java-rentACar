package com.btkAkademi.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto(); //entityden Dto
	ModelMapper forRequest(); //dtodan entity

}
