to_sort = []

with open('input.txt', 'r') as f:
    for line in f:
        to_sort.append(line.strip())

to_sort.sort()

for line in to_sort:
    print(line)