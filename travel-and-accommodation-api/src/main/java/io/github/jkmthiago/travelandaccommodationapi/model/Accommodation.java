package io.github.jkmthiago.travelandaccommodationapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.jkmthiago.travelandaccommodationapi.enumeration.AccommodationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Accommodation {

    private Long id;
    private String orderNumber;
    private String destino;
    private String hotel;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private String numeroDeQuartos;
    private String numeroDeAdultos;
    private String numeroDeCriancas;
    private BigDecimal valorDasDiarias;
    private BigDecimal valorDaParcela;
    private String nomeDoTitular;
    private String numeroDoCartao;
    private String codigoDeSeguranca;
    private String numeroDeParcelas;
    private AccommodationTypeEnum type;

    public Accommodation(AccommodationTypeEnum type) {
        this.type = type;
    }
}
