import json
from random import random
from utils.bcolor import bcolors
from utils.cls import cls
from utils.gen_pessoa import gen_pessoa
from utils import config
import requests

minhas_hospedagens_ok = []
hoteis = ["Ibis", "Goldem Village",
          "Pallacce Hotel", "Porto Bello Hotel"]


def init():
    while True:
        cls()
        print(bcolors.HEADER + "Hospedagem" + bcolors.ENDC)
        print("1 - Comprar hospedagem")
        print("2 - Minhas hospedagens")
        print("0 - Voltar")
        user = int(input("> "))
        if user == 0:
            break

        if user == 1:
            comprar_hospedagem()
        elif user == 2:
            minhas_hospedagens()


def comprar_hospedagem():
    cls()
    id_id = random.randint(1, 999999)
    orderNumber = random.randint(100000, 999999)
    destino = input("Digite o destino inde deseja se hospedar: ")
    print("Qual hotel deseja se hospedar?")
    for i in range(len(hoteis)):
        print(f"{i+1} - "+hoteis[i].replace("_", " "))
    hotel = hoteis[int(input("> "))-1]

    data_entrada = input("Insira a data de entrada AAAA-MM-DD -> ") + "T" + input("Insira a hora de entrada HH:MM:SS -> " + "Z")
    data_saida = input("Insira a data de saida AAAA-MM-DD -> ") + "T" + input("Insira a hora de saida HH:MM:SS -> " + "Z")
    quartos = input("Insira a quantidade de quartos -> ")
    qtde_adultos = input("Insira a quantidade de adultos -> ")
    qtde_criancas = input("Insira a quantidade de crianças -> ")
    valorDasDiarias = random.uniform(100.0, 2500.0)
    valorDasDiarias = round(valorDasDiarias, 2)
    print(f"Valor das diárias: R$ {valorDasDiarias}")
    parcelas_id = input("Insira a quantidade de parcelas -> ")
    parcelas = valorDasDiarias / int(parcelas_id)
    parcelas = round(parcelas, 2)
    print(f"Valor das parcelas: R$ {parcelas}")
    nomeDoTitular = input("Insira o nome do titular -> ")
    numeroDoCartao = input("Insira o número do cartão -> ")
    codigoDeSeguranca = input("Insira o código de segurança -> ")

    body = {
        "id": id_id,
        "orderNumber": str(orderNumber),
        "destino": destino,
        "hotel": hotel,
        "entrada": data_entrada,
        "saida": data_saida,
        "numeroDeQuartos": quartos,
        "numeroDeAdultos": qtde_adultos,
        "numeroDeCriancas": qtde_criancas,
        "valorDasDiarias": str(valorDasDiarias),
        "valorDaParcela": str(parcelas),
        "nomeDoTitular": nomeDoTitular,
        "numeroDoCartao": numeroDoCartao,
        "codigoDeSeguranca": codigoDeSeguranca,
        "numeroDeParcelas": parcelas_id,
        "type":"ONE-WAY"
    }

    res = requests.post(config.BASE_PATH+"/api-accommodations/accommodations", json=body)
    if res.status_code == 201:
        print("Compra realizada com sucesso!")
    else:
        print("Erro ao comprar hospedagem!")
    input("Pressione qualquer tecla para sair...")



    


def minhas_hospedagens():
    cls()
    res = requests.get(config.BASE_PATH+"/api-accommodations/accommodations")
    for i in range(len(res.json())):
        print(f"Hospedadgem {i+1}: ")
        print(json.dumps(res.json()[i], indent=4))
    input("Pressione qualquer tecla para voltar...")


def parse_date(date: str) -> str:
    raw = list(reversed(date.split("/")))
    return '-'.join(raw)
