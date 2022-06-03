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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHotel() {
        return this.hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public LocalDateTime getEntrada() {
        return this.entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return this.saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public String getNumeroDeQuartos() {
        return this.numeroDeQuartos;
    }

    public void setNumeroDeQuartos(String numeroDeQuartos) {
        this.numeroDeQuartos = numeroDeQuartos;
    }

    public String getNumeroDeAdultos() {
        return this.numeroDeAdultos;
    }

    public void setNumeroDeAdultos(String numeroDeAdultos) {
        this.numeroDeAdultos = numeroDeAdultos;
    }

    public String getNumeroDeCriancas() {
        return this.numeroDeCriancas;
    }

    public void setNumeroDeCriancas(String numeroDeCriancas) {
        this.numeroDeCriancas = numeroDeCriancas;
    }

    public BigDecimal getValorDasDiarias() {
        return this.valorDasDiarias;
    }

    public void setValorDasDiarias(BigDecimal valorDasDiarias) {
        this.valorDasDiarias = valorDasDiarias;
    }

    public BigDecimal getValorDaParcela() {
        return this.valorDaParcela;
    }

    public void setValorDaParcela(BigDecimal valorDaParcela) {
        this.valorDaParcela = valorDaParcela;
    }

    public String getNomeDoTitular() {
        return this.nomeDoTitular;
    }

    public void setNomeDoTitular(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    public String getNumeroDoCartao() {
        return this.numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getCodigoDeSeguranca() {
        return this.codigoDeSeguranca;
    }

    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    public String getNumeroDeParcelas() {
        return this.numeroDeParcelas;
    }

    public void setNumeroDeParcelas(String numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public AccommodationTypeEnum getType() {
        return this.type;
    }

    public void setType(AccommodationTypeEnum type) {
        this.type = type;
    }
}
