import time
from typing import Callable
from math import log2
from sys import set_int_max_str_digits

set_int_max_str_digits(100000)


def control_digit(id_num):
    assert isinstance(id_num, str)
    assert len(id_num) == 8

    total = 0

    for i in range(8):
        val = int(id_num[i])
        if i % 2 == 0:
            total += val
        else:
            if val < 5:
                total += 2 * val
            else:
                total += ((2 * val) % 10) + 1

        print(f"{i=}, {id_num[i]=}, {val=}, {total=}")

    total = total % 10
    check_digit = (10 - total) % 10

    return str(check_digit)


print("out:", control_digit("33455896"))


def is_anagram_v2(st1: str, st2: str) -> bool:
    for ch in st1:
        if st1.count(ch) != st2.count(ch):
            return False

    return True


def zeros(num):
    m = num
    cnt = 0
    while m > 0:
        if m % 10 == 0:
            cnt = cnt + 1
        m = m // 10

    return cnt


def zeros2(num):
    cnt = 0
    snum = str(num)
    for digit in snum:
        if digit == "0":
            cnt = cnt + 1

    return cnt


def zeros3(num):
    cnt = str.count(str(num), "0")
    return cnt


def timeit(func: Callable, *, prt=True, **kwargs) -> tuple[float, object]:
    t0 = time.perf_counter()
    out = func(**kwargs)
    t1 = time.perf_counter()

    if prt:
        print(f"func {func.__name__}() time: {t1 - t0}, output: {out}")
    return (t1 - t0, out)


if True:
    lst1 = [2**100, 2**250, 2**600, 2**1400]
    n = 100000

    lst2 = [10**n - 1, 10 ** (n - 1)]
    print(len(str(lst2[0])))
    print(len(str(lst2[1])))
    # print(lst2)

    for num in [10**100000 - 1, 10**99999]:
        print(f"~~~~ 2**{int(log2(num))} ~~~~")
        timeit(zeros, num=num)
        timeit(zeros2, num=num)
        timeit(zeros3, num=num)

# print(timeit(zeros, num=2**100))
# print(timeit(zeros, num=2**250))

def simple_loop(num): 
    cnt = 0
    for i in range(num): 
        cnt = cnt + 1

# print(timeit(simple_loop, num=2**1000))5
