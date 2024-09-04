import random, time, sys
sys.setrecursionlimit(300000)

# def quicksort(lst, rand=True):

#     """ quick sort of lst """
#     if len(lst) <= 1:
#         return lst
#     else:
#         pivot = random.choice(lst) if rand else lst[0]
#         smaller = [elem for elem in lst if elem < pivot]
#         equal = [elem for elem in lst if elem == pivot]
#         greater = [elem for elem in lst if elem > pivot]

#         return quicksort(smaller, rand) + equal + quicksort(greater, rand)


def timeCalc(fun, *args: object, **kwargs: object) -> float:
    t0 = time.perf_counter()
    fun(*args, **kwargs)
    t1 = time.perf_counter()
    return t1 - t0

# def quick_comparison_ordered_input(n: int, t: int) -> tuple[float, float]:
#     answer1: float = 0
#     answer2: float = 0
#     for i in range(t):
#         # print(f"(1 of 2 operations) {i + 1} out of {t}")

#         toN = list(range(n))
#         time = timeCalc(quicksort, toN, rand=True)

#         answer1 += time / t

#     for i in range(t):
#         # print(f"(2 of 2 operations) {i + 1} out of {t}")

#         toN = list(range(n))
#         time = timeCalc(quicksort, toN, rand=False)

#         answer2 += time / t
    
#     return answer1, answer2


# out: list[tuple[float, float]] = []
# for i in range(5): 
#     print(i)
#     out.append(quick_comparison_ordered_input(10**i, 10 // (i + 1)))

# print(out)
# out = [(2.7021000278182328e-05, 4.733899550046772e-05), (9.999900066759437e-05, 6.085500353947282e-05), (0.0007083679956849664, 0.00012372399942250922), (0.006210790998011362, 0.00018791500042425469), (0.04712328300229274, 0.000281091000942979), (0.4377162309974665, 0.00038526700518559664), (3.019972678004706, 0.00038387499807868153), (23.205113062998862, 0.00044007199903717265)]

# print("".join((
#     f"\\hline ${3 + 3*i}$ & ${out[i][0]}$ & ${out[i][1]}$ \\\\ \n " for i in range(len(out))
# )))

def subset_sum(L, s):
    # subsetsum implementation, as seen in recitation

    if s == 0:
        return True
    if L == []:
        return False

    with_first = subset_sum(L[1:], s - L[0])
    without_first = subset_sum(L[1:], s)
    return with_first or without_first


def subset_sum_efficient_rec(
    L: list[int], s: int, start: int, mem: list[list[bool | None]]
) -> bool:
    # print(f"{start=}, {s=}, {L=}")
    # pprint(mem)
    n = len(L) - start

    if s < 0:
        return False
    if s == 0:
        return True
    if n == 0:
        return False

    if mem[start][s] is not None:
        return bool(mem[start][s])

    with_first = subset_sum_efficient_rec(L, s - L[start], start + 1, mem)
    without_first = subset_sum_efficient_rec(L, s, start + 1, mem)

    out = with_first or without_first
    mem[start][s] = out

    return out


def subset_sum_efficient(L, s) -> bool:
    n = len(L)
    mem: list[list[bool | None]] = [[None for _ in range(s + 1)] for _ in range(n)]
    return subset_sum_efficient_rec(L, s, 0, mem)



# out = []
# for i in range(3, 30, 3):
#     L = list(range(i))
#     print(f"runs {i} #1")
#     f = timeCalc(subset_sum, L, 10)
#     print(f"run {i} #2")
#     s = timeCalc(subset_sum_efficient, L, 10)

#     out.append((f, s))
#     print(out)

