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

import io.github.jkmthiago.travelandaccommodationapi.enumeration.TravelTypeEnum;
import io.github.jkmthiago.travelandaccommodationapi.factory.TravelFactory;
import io.github.jkmthiago.travelandaccommodationapi.factory.impl.TravelFactoryImpl;
import io.github.jkmthiago.travelandaccommodationapi.model.Travel;

@Service

public class TravelService {
    
    private TravelFactory factory;
    
    private List<Travel> travels;

    public void createFactory() {
        if (factory == null) {
            factory = new TravelFactoryImpl();
        }    
    }

    public void createTravelList() {
        if (travels == null) {
            travels = new ArrayList<>();
        }
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private long parseId(JSONObject travel) {
        return Long.valueOf((int) travel.get("id"));
    }

    private BigDecimal parseValorDaPassagem(JSONObject travel) {
        return new BigDecimal((String) travel.get("valorDaPassagem"));
    }

    private BigDecimal parseValorDaParcela(JSONObject travel) {
        return new BigDecimal((String) travel.get("valorDaParcela"));
    }

    private LocalDateTime parseIda(JSONObject travel) {
        var ida = (String) travel.get("ida");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(ida, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    private LocalDateTime parseVolta(JSONObject travel) {
        var volta = (String) travel.get("volta");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		return ZonedDateTime.parse(volta, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    public boolean isStartDateGreaterThanEndDate(Travel travel) {
        if(travel.getVolta() == null) return false;
        return travel.getIda().isAfter(travel.getVolta());
    }

    private void setTravelValues (JSONObject jsonTravel, Travel travel){

        String orderNumber = (String) jsonTravel.get("orderNumber");
        String origem = (String) jsonTravel.get("origem");
        String destino = (String) jsonTravel.get("destino");
        String numeroDeAdultos = (String) jsonTravel.get("numeroDeAdultos");
        String numeroDeCriancas = (String) jsonTravel.get("numeroDeCriancas");
        String nomeDoTitular = (String) jsonTravel.get("nomeDoTitular");
        String numeroDoCartao = (String) jsonTravel.get("numeroDoCartao");
        String codigoDeSeguranca = (String) jsonTravel.get("codigoDeSeguranca");
        String numeroDeParcelas = (String) jsonTravel.get("numeroDeParcelas");
        String type = (String) jsonTravel.get("type");

        travel.setOrderNumber(orderNumber != null ? orderNumber : travel.getOrderNumber());
        travel.setOrigem(origem != null ? origem : travel.getOrigem());
        travel.setDestino(destino != null ? destino : travel.getDestino());
        travel.setNumeroDeAdultos(numeroDeAdultos != null ? numeroDeAdultos : travel.getNumeroDeAdultos());
        travel.setNumeroDeCriancas(numeroDeCriancas != null ? numeroDeCriancas : travel.getNumeroDeCriancas());
        travel.setNomeDoTitular(nomeDoTitular != null ? nomeDoTitular : travel.getNomeDoTitular());
        travel.setNumeroDoCartao(numeroDoCartao != null ? numeroDoCartao : travel.getNumeroDoCartao());
        travel.setCodigoDeSeguranca(codigoDeSeguranca != null ? codigoDeSeguranca : travel.getCodigoDeSeguranca());
        travel.setNumeroDeParcelas(numeroDeParcelas != null ? numeroDeParcelas : travel.getNumeroDeParcelas());
        travel.setValorDaPassagem(jsonTravel.get("valorDaPassagem") != null ? parseValorDaPassagem(jsonTravel) : travel.getValorDaPassagem());
        travel.setValorDaParcela(jsonTravel.get("valorDaParcela") != null ? parseValorDaParcela(jsonTravel) : travel.getValorDaParcela());
        travel.setIda(jsonTravel.get("ida") != null ? parseIda(jsonTravel) : travel.getIda());
        travel.setVolta(jsonTravel.get("volta") != null ? parseVolta(jsonTravel) : travel.getVolta());
        travel.setType(type != null ? TravelTypeEnum.getEnum(type) : travel.getType());
    }

    public Travel create(JSONObject jsonTravel) {
        
        createFactory();

        Travel travel = factory.createTravel((String) jsonTravel.get("type"));
        travel.setId(parseId(jsonTravel));
        setTravelValues(jsonTravel, travel);

        return travel;
    }

    public Travel update(Travel travel, JSONObject jsonTravel) {
        setTravelValues(jsonTravel, travel);
        return travel;
    }

    public void add(Travel travel) {
        createTravelList();
        travels.add(travel);
    }

    public List<Travel> find() {
        createTravelList();
        return travels;
    }

    public Travel findById(long id) {
        return travels.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
    }

    public void delete() {
        travels.clear();
    }

    public void clearObjects() {
        factory = null;
        travels = null;
    }
}
