def sum_by_elf(calories):
    sums = []
    curr = 0
    for line in calories:
        if line == "\n":
            sums.append(curr)
            curr = 0
        else:
            curr = curr + int(line)

    sums.sort()
    return sums

def main():
    with open('input.txt') as f:
        lines = [line for line in f]
        sums_by_elf = sum_by_elf(lines)
        print(sums_by_elf[-1])
        print(sum(sums_by_elf[-3:]))

main()
