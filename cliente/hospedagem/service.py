from utils.cls import cls
from art import *
from utils.gen_pessoa import gen_pessoa
from utils import config
import requests

minhas_hospedagens = []
hoteis = ["Ibis", "Porto bello",
          "Amazônia palace", "Goldem hotel",]


def init():
    while True:
        cls()
        print("Hospedagem    :)")
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
    print("Qual hotel deseja se hospedar?")
    for i in range(len(hoteis)):
        print(f"{i+1} - "+hoteis[i].replace("_", " "))
    hotel = hoteis[int(input("> "))-1]

    data_entrada = parse_date(input("Insira a data de entrada (dd/MM/yyyy): "))
    data_saida = parse_date(input("Insira a data de saida (dd/MM/yyyy): "))
    quartos = [int(input("Insira a quantidade de quartos: "))]
    qtde_adultos = int(input("Insira a quantidade de adultos: "))
    qtde_criancas = int(input("Insira a quantidade de crianças: "))
    parcelas = int(input("Insira a quantidade de parcelas: "))

    body = {
        "hotel": hotel,
        "data_entrada": data_entrada,
        "data_saida": data_saida,
        "quartos": quartos,
        "adultos": [gen_pessoa(i) for i in range(qtde_adultos)],
        "criancas": [gen_pessoa(i) for i in range(qtde_criancas)],
        "pagamento": {
            "numero_cartao": "2345 6788 1614 7366",
            "nome_escrito": "Gabriel Pensador",
            "cvv": 542,
            "parcelamento": parcelas
        }
    }

    res = requests.post(config.BASE_PATH+"/AccommodationFactory", json=body)
    if res.status_code == 200:
        minhas_hospedagens.append(res.json()["hospedagens"][0]["id"])
        print("Compra realizada com sucesso!")
    else:
        print("Erro ao comprar hospedagem!")
    input("Pressione qualquer tecla para sair...")



    


def minhas_hospedagens():
    for i in range(len(minhas_hospedagens)):
        cls()
        print(f"Hospedagem {i+1}: {minhas_hospedagens[i]}")
    input("Pressione qualquer tecla para voltar...")


def parse_date(date: str) -> str:
    raw = list(reversed(date.split("/")))
    return '-'.join(raw)
