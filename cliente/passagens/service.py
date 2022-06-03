import json
import random
from utils.bcolor import bcolors
from utils.cls import cls
from utils import config
from utils.gen_pessoa import gen_pessoa
import requests
from datetime import datetime
from dateutil import parser
from random import randint

minhas_viagens = []
destinos = ["Marabá - Belém - 15:00", "Belém - Marabá - 14:00",
          "Marabá - São Paulo - 21:30", "São Paulo - Marabá - 11:30",
          "Marabá - Rio de Janeiro - 23:00", "Rio de Janeiro - Marabá - 13:00", ""]
horarios = ["T15:00:00Z", "T14:00:00Z", "T21:30:00Z", "T11:30:00Z", "T23:00:00Z", "T13:00:00Z"]

def init():
    while True:
        cls()
        print(bcolors.HEADER + "Passagens" + bcolors.ENDC)
        print("1 - Comprar passagem")
        print("2 - Minhas passagens")
        print("0 - Voltar")
        user = int(input("> "))
        if user == 0:
            break

        if user == 1:
            comprar_passagem()
        elif user == 2:
            minhas_passagens()


def comprar_passagem():
    cls()
    id_id = random.randint(1, 999999)
    orderNumber = random.randint(100000, 999999)
    print("Para onde você quer viajar hoje?")
    for i in range(len(destinos)):
        print(f"{i+1} - "+destinos[i].replace("_", " "))
    ida_id = input("Destino de Ida -> ")
    ida = destinos[int(ida_id)-1]
    print(ida)
    print("Em caso de apenas ida digite 7 no próximo tópico")
    volta_id = input("Destino de Volta -> ")
    volta = destinos[int(volta_id)-1]
    print(volta)

    data_ida = input("Insira a data de ida AAAA-MM-DD -> ") + horarios[int(ida_id)-1]
    print(data_ida)
    data_volta = input("Insira a data de saida AAAA-MM-DD -> ")
    data_volta =  data_volta+horarios[int(volta_id)-1] if data_volta != "" else "2022-12-30T00:00:00Z"
    
    qtde_adultos = input("Insira a quantidade de adultos -> ")
    qtde_criancas = input("Insira a quantidade de crianças -> ")
    valorDaPassagem = random.uniform(100.0, 2500.0)
    valorDaPassagem = round(valorDaPassagem, 2)
    print(f"Valor da passagem: R$ {valorDaPassagem}")
    parcelas_id = input("Insira a quantidade de parcelas -> ")
    parcelas = valorDaPassagem / int(parcelas_id)
    parcelas = round(parcelas, 2)
    print(f"Valor das parcelas: R$ {parcelas}")
    nomeDoTitular = input("Insira o nome do titular -> ")
    numeroDoCartao = input("Insira o número do cartão -> ")
    codigoDeSeguranca = input("Insira o código de segurança -> ")

    body = {
        "id": id_id,
        "orderNumber": str(orderNumber),
        "origem": ida,
        "destino": volta,
        "ida": str(data_ida),
        "volta": data_volta,
        "numeroDeAdultos": qtde_adultos,
        "numeroDeCriancas": qtde_criancas,
        "valorDaPassagem": str(valorDaPassagem),
        "valorDaParcela": str(parcelas),
        "nomeDoTitular": nomeDoTitular,
        "numeroDoCartao": numeroDoCartao,
        "codigoDeSeguranca": codigoDeSeguranca,
        "numeroDeParcelas": parcelas_id,
        "type":"ONE-WAY"
    }

    res = requests.post(config.BASE_PATH+"/api-travels/travels", json=body)
    if res.status_code == 201:
        print("Compra realizada com sucesso!")
    else:
        print("Erro ao comprar hospedagem!")
    input("Pressione qualquer tecla para sair...")

def minhas_passagens():
    cls()
    res = requests.get(config.BASE_PATH+"/api-travels/travels")
    for i in range(len(res.json())):
        print(f"Passagem {i+1}: ")
        print(json.dumps(res.json()[i], indent=4))
    input("Pressione qualquer tecla para voltar...")


def get_voos() -> list:
    body = requests.get(config.BASE_PATH+"/voos").json()
    return body["voos_disponiveis"]
