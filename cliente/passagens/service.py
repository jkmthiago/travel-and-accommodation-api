from utils.cls import cls
from art import *
from utils.gen_pessoa import gen_pessoa
from utils import config
import requests

minhas_viagens = []
idas = ["Marabá", "Belém",
          "Brasília", "São paulo",]
voltas = ["São paulo", "Brasília",
            "Belém", "Marabá",]


def init():
    while True:
        cls()
        print("Viagem    ")
        print("1 - Comprar passagem")
        print("2 - Minhas passagems")
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
    print("Qual destino deseja se ir hoje?")
    for i in range(len(idas)):
        print(f"{i+1} - "+ida[i].replace("_", " ") + " - " + volta[i].replace("_", " "))
    ida = idas[int(input("> "))-1]
    volta =voltas[int(input("> "))-1]

    data_entrada = parse_date(input("Insira a data de ida (dd/MM/yyyy): "))
    data_saida = parse_date(input("Insira a data de volta (dd/MM/yyyy): "))
    qtde_adultos = int(input("Insira a quantidade de adultos: "))
    qtde_criancas = int(input("Insira a quantidade de crianças: "))
    parcelas = int(input("Insira a quantidade de parcelas: "))

    body = {
        "ida": ida,
        "volta": volta,
        "data_ida": data_entrada,
        "data_volta": data_saida,
        "adultos": [gen_pessoa(i) for i in range(qtde_adultos)],
        "criancas": [gen_pessoa(i) for i in range(qtde_criancas)],
        "pagamento": {
            "numero_cartao": "2345 6788 1614 7366",
            "nome_escrito": "Gabriel Pensador",
            "cvv": 542,
            "parcelamento": parcelas
        }
    }

    res = requests.post(config.BASE_PATH+"/TravelFactory", json=body)
    if res.status_code == 200:
        minhas_viagens.append(res.json()["passagens"][0]["id"])
        print("Compra realizada com sucesso!")
    else:
        print("Erro ao comprar hospedagem!")
    input("Pressione qualquer tecla para sair...")



    


def minhas_passagens():
    for i in range(len(minhas_viagens)):
        cls()
        print(f"Hospedagem {i+1}: {minhas_viagens[i]}")
    input("Pressione qualquer tecla para voltar...")


def parse_date(date: str) -> str:
    raw = list(reversed(date.split("/")))
    return '-'.join(raw)
