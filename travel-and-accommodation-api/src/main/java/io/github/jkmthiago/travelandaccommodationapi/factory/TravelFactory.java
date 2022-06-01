package io.github.jkmthiago.travelandaccommodationapi.factory;

import io.github.jkmthiago.travelandaccommodationapi.model.Travel;

public interface TravelFactory {
	Travel createTravel (String type);
}