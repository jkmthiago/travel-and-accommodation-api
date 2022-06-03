from utils.bcolor import bcolors
from utils.cls import cls
from passagens import service as passagens
from hospedagem import service as hospedagem

import requests


def run():
    while True:
        cls()
        print(bcolors.BOLD + "PAGODEVER VIAJAR E HOSPEDAR" + bcolors.ENDC)
        print("\n")
        print("Passagens    e    Hospedagem")
        print("1 - Comprar passagem")
        print("2 - Hospedagem")
        print("0 - Sair")
        user = int(input("> "))
        if user == 0:
            break

        if user == 1:
            passagens.init()
        elif user == 2:
            hospedagem.init()


if __name__ == "__main__":
    run()
