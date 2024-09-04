import random

def sample(v: list[object], p: list[float]):
    chosen = random.random()
    last = 0.0

    for i in range(len(p)):
        if chosen < p[i] + last:
            return v[i]
        last += p[i]

def sample_anagram(st) -> str:
    n: int = len(st)
    output: str = ""
    st = list(st)

    for i in range(n):
        num = n - i
        rand: int = sample(list(range(num)), [1 / num] * num)
        output += st[rand]

        del st[rand]

    return output

def test1(): 
    st = "abc"
    counts: dict[str, int] = {}
    for _ in range(10_000): 
        output = sample_anagram(st)
        if output in counts: 
            counts[output] += 1
        else: 
            counts[output] = 1

    print(counts)

def divisors(n: int) -> list[int]:
    answer = [i for i in range(1, n // 2 + 1) if n % i == 0]
    return answer

#  Q1c
def abundant_density(n):
    counter = 0
    for i in range(1, n + 1):
        if sum(divisors(i)) > i:
            counter += 1

    return counter / n

for n in [50, 500, 2500, 5000, 7500, 10000]: 
    print(abundant_density(n))

