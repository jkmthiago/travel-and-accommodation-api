from random import randint

def gen_pessoa(i):
    return {
        "nome": f"Pessoa {i}",
        "email": f"pessoa{i}@email.com",
        "numero": '+55919'+''.join(['{}'.format(randint(0, 9)) for num in range(8)]),
        "idade": randint(18, 65)
    }