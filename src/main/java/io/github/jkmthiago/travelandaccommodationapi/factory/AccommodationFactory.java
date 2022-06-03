package io.github.jkmthiago.travelandaccommodationapi.factory;

import io.github.jkmthiago.travelandaccommodationapi.model.Accommodation;

public interface AccommodationFactory {
	Accommodation createAccommodation (String type);    
}