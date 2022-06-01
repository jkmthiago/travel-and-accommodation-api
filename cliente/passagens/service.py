from utils.cls import cls
from art import *
from utils import config
from utils.gen_pessoa import gen_pessoa
import requests
from datetime import datetime
from dateutil import parser
from random import randint

minhas_viagens = []

def init():
    while True:
        cls()
        tprint("Passagens    :)", font="doom")
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
    voos = get_voos()
    while True:
        cls()
        print(f"Foram encontrados {len(voos)} voos")
        print("1 - Listar todos")
        print("2 - Filtrar")
        print("0 - Voltar")
        user = int(input("> "))
        if user == 0:
            break
        if user == 1:
            listar_voo(voos)
        if user == 2:
            filtrar()


def listar_voo(voos):
    i = 1
    for item in voos:
        print("ID: "+str(i))
        print("Empresa: "+item["empresa"])
        print("Origem: "+item["origem"])
        print("Destino: "+item["destino"])
        print("Data saida: " +
              parser.parse(item["data_saida"]).strftime("%d/%m/%y %H:%M"))
        print("Data chegada: " +
              parser.parse(item["data_chegada"]).strftime("%d/%m/%y %H:%M"))
        print("Duracao: "+str(item["duracao_minutos"])+" minutos")
        print("-------------------------------------------------------------------")
        i = i + 1
    user = input("> ")
    if user == "" or user == "0":
        return
    comprar(voos, int(user)-1)


def filtrar():
    cls()
    origem = input("Insira a origem: ")
    destino = input("Insira o destino: ")
    ida = input("Insira a data de ida (dd/MM/yyyy): ")
    volta = input("Insira a data de volta (dd/MM/yyyy): ")

    res = requests.get(
        config.BASE_PATH+f"/voos/ida?origem={origem.lower()}&destino={destino}&ida={ida}")
    if res.json()["quantidade"] == 0:
        cls()
        print("Não foram encontrados voos para os criterios desejados.")
        input("Pressione qualquer tecla para voltar...")
        return
    listar_voo(res.json()["voos_disponiveis"])


def comprar(voos, id):
    qtde_pessoas = int(input("Insira a quantidade de pessoas: "))
    poltronas = []
    for i in range(qtde_pessoas):
        poltronas.append(int(input(f"Insira a poltrona para a pessoa {i+1}: ")))
    body = {
        "id_voo": voos[id]["id"],
        "pessoas": [gen_pessoa(i) for i in range(qtde_pessoas)],
        "assentos": poltronas,
        "pagamento": {
            "numero_cartao": "1234 4567 8901 1234",
            "validade": "03/24",
            "nome_escrito": "unifesspakkkk",
            "cvv": 666,
            "parcelamento": 12
        }
    }
    res = requests.post(config.BASE_PATH+"/passagens", json=body)
    if res.status_code != 200:
        print(
            f"\nOcorreu um erro ao comprar a passagem!\nResposta do servidor: {res.text}")
        input("Pressione qualquer tecla para voltar...\n")
        return
    print(res.json())
    input("Pressione qualquer tecla para voltar...")
    minhas_pss.append(res.json()["id"])


def minhas_passagens():
    for i in range(len(minhas_pss)):
        cls()
        print(f"Passagem {i+1}: {minhas_pss[i]}")
    input("Pressione qualquer tecla para voltar...")


def get_voos() -> list:
    body = requests.get(config.BASE_PATH+"/voos").json()
    return body["voos_disponiveis"]
