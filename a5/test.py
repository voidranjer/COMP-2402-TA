def find_equal_pairs(data):
    pairs = [tuple(map(int, line.split())) for line in data.split('\n') if line.strip()]
    equal_pairs = [(a, b) for a, b in pairs if a == b]
    return equal_pairs

data = """
2 2
2 5
9 2
4 2
0 3
6 6
3 9
"""

equal_pairs = find_equal_pairs(data)

for pair in equal_pairs:
    print(pair)
