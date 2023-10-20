import math

def general_form(n):
    return int((2 ** (math.log(n,4))) - 1)

# def general_form(n):
#     return int(math.log(n,4) - 1)

def recursive(n):
    if n == 1:
        return 0
    
    return int(n + 2 * recursive(n/4))

def general_form(n):
    numerator = 1 - math.pow(0.5,math.log(n,4))
    denominator = 1 - 0.5
    return n * (numerator / denominator)

for i in range(0,9):
    n = 4**i
    print(f"{recursive(n)} == {general_form(n)}")