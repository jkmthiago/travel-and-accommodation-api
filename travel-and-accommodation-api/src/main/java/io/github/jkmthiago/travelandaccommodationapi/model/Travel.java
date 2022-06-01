package io.github.jkmthiago.travelandaccommodationapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.jkmthiago.travelandaccommodationapi.enumeration.TravelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Travel {

    private Long id;
    private String orderNumber;
    private String origem;
    private String destino;
    private LocalDateTime ida;
    private LocalDateTime volta;
    private String numeroDeAdultos;
    private String numeroDeCriancas;
    private BigDecimal valorDaPassagem;
    private BigDecimal valorDaParcela;
    private String nomeDoTitular;
    private String numeroDoCartao;
    private String codigoDeSeguranca;
    private String numeroDeParcelas;
    private TravelTypeEnum type;

    public Travel(TravelTypeEnum type) {
        this.type = type;
    }
}
