package io.github.jkmthiago.travelandaccommodationapi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.jkmthiago.travelandaccommodationapi.enumeration.AccommodationTypeEnum;
import io.github.jkmthiago.travelandaccommodationapi.factory.AccommodationFactory;
import io.github.jkmthiago.travelandaccommodationapi.factory.impl.AccommodationFactoryImpl;
import io.github.jkmthiago.travelandaccommodationapi.model.Accommodation;


@Service

public class AccommodationService {
    private AccommodationFactory factory;
    private List<Accommodation> accommodations;

    public void createFactory() {
        if (factory == null) {
            factory = new AccommodationFactoryImpl();
        }    
    }

    public void createAccommodationList() {
        if (accommodations == null) {
            accommodations = new ArrayList<>();
        }
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private long parseId(JSONObject accommodation) {
        return Long.valueOf((int) accommodation.get("id"));
    }

    private BigDecimal parseValorDasDiarias(JSONObject accommodation) {
        return new BigDecimal((String) accommodation.get("valorDasDiarias"));
    }

    private BigDecimal parseValorDaParcela(JSONObject accommodation) {
        return new BigDecimal((String) accommodation.get("valorDaParcela"));
    }

    private LocalDateTime parseEntrada(JSONObject accommodation) {
        var entrada = (String) accommodation.get("entrada");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(entrada, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    private LocalDateTime parseSaida(JSONObject accommodation) {
        var saida = (String) accommodation.get("saida");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(saida, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    public boolean isStartDateGreaterThanEndDate(Accommodation accommodation) {
        if(accommodation.getSaida() == null) return false;
        return accommodation.getEntrada().isAfter(accommodation.getSaida());
    }

    private void setAccommodationValues (JSONObject jsonAccommodation, Accommodation accommodation){

        String orderNumber = (String) jsonAccommodation.get("orderNumber");
        String type = (String) jsonAccommodation.get("type");

        accommodation.setOrderNumber(orderNumber != null ? orderNumber : accommodation.getOrderNumber());
        accommodation.setValorDasDiarias(jsonAccommodation.get("valorDasDiarias") != null ? parseValorDasDiarias(jsonAccommodation) : accommodation.getValorDasDiarias());
        accommodation.setValorDaParcela(jsonAccommodation.get("valorDaParcela") != null ? parseValorDaParcela(jsonAccommodation) : accommodation.getValorDaParcela());
        accommodation.setEntrada(jsonAccommodation.get("entrada") != null ? parseEntrada(jsonAccommodation) : accommodation.getEntrada());
        accommodation.setSaida(jsonAccommodation.get("saida") != null ? parseSaida(jsonAccommodation) : accommodation.getSaida());
        accommodation.setType(type != null ? AccommodationTypeEnum.getEnum(type) : accommodation.getType());
    }

    public Accommodation create(JSONObject jsonAccommodation) {
        
        createFactory();

        Accommodation accommodation = factory.createAccommodation((String) jsonAccommodation.get("type"));
        accommodation.setId(parseId(jsonAccommodation));
        setAccommodationValues(jsonAccommodation, accommodation);

        return accommodation;
    }

    public Accommodation update(Accommodation accommodation, JSONObject jsonAccommodation) {
        setAccommodationValues(jsonAccommodation, accommodation);
        return accommodation;
    }

    public void add(Accommodation accommodation) {
        createAccommodationList();
        accommodations.add(accommodation);
    }

    public List<Accommodation> find() {
        createAccommodationList();
        return accommodations;
    }

    public Accommodation findById(long id) {
        return accommodations.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
    }

    public void delete() {
        accommodations.clear();
    }

    public void clearObjects() {
        factory = null;
        accommodations = null;
    }
}
