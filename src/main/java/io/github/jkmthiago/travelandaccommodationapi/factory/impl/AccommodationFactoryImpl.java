package io.github.jkmthiago.travelandaccommodationapi.factory.impl;

import io.github.jkmthiago.travelandaccommodationapi.enumeration.AccommodationTypeEnum;
import io.github.jkmthiago.travelandaccommodationapi.factory.AccommodationFactory;
import io.github.jkmthiago.travelandaccommodationapi.model.Accommodation;

public class AccommodationFactoryImpl implements AccommodationFactory {

	@Override
	public Accommodation createAccommodation (String type) {
		
		if (AccommodationTypeEnum.ONE_WAY.getValue().equals(type)) {
			return new Accommodation(AccommodationTypeEnum.ONE_WAY);
		} else if (AccommodationTypeEnum.MULTI_CITY.getValue().equals(type)) {
			return new Accommodation(AccommodationTypeEnum.MULTI_CITY); 
		}
		
		return new Accommodation(AccommodationTypeEnum.RETURN);
	}

}



