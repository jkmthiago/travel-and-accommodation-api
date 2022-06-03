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

    public String getOrigem() {
        return this.origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getIda() {
        return this.ida;
    }

    public void setIda(LocalDateTime ida) {
        this.ida = ida;
    }

    public LocalDateTime getVolta() {
        return this.volta;
    }

    public void setVolta(LocalDateTime volta) {
        this.volta = volta;
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

    public BigDecimal getValorDaPassagem() {
        return this.valorDaPassagem;
    }

    public void setValorDaPassagem(BigDecimal valorDaPassagem) {
        this.valorDaPassagem = valorDaPassagem;
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

    public TravelTypeEnum getType() {
        return this.type;
    }

    public void setType(TravelTypeEnum type) {
        this.type = type;
    }
}
