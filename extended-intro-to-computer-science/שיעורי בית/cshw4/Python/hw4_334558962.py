# Skeleton file for HW4 - Winter 2024 - extended intro to CS

# Add your implementation to this file

# You may add other utility functions to this file,
# but you may NOT change the signature of the existing ones.

# Change the name of the file to include your ID number (hw4_ID.py).
import math, random, time

##############
# Question 1 #
##############


def quicksort(lst, rand=True):
    """quick sort of lst"""
    if len(lst) <= 1:
        return lst
    else:
        pivot = random.choice(lst) if rand else lst[0]
        smaller = [elem for elem in lst if elem < pivot]
        equal = [elem for elem in lst if elem == pivot]
        greater = [elem for elem in lst if elem > pivot]

        return quicksort(smaller, rand) + equal + quicksort(greater, rand)


def timeCalc(fun, *args: object, **kwargs: object) -> float:
    t0 = time.perf_counter()
    fun(*args, **kwargs)
    t1 = time.perf_counter()
    return t1 - t0


def quick_comparison_random_input(n: int, t: int) -> tuple[float, float]:
    answer1: float = 0
    answer2: float = 0
    tillN = lambda: list[int](range(n))
    for i in range(t):
        # print(f"(1 of 2 operations) {i + 1} out of {t}")

        toN: list[int] = tillN()
        random.shuffle(toN)
        time: float = timeCalc(quicksort, toN, rand=True)

        answer1 += time / t

    for i in range(t):
        # print(f"(2 of 2 operations) {i + 1} out of {t}")

        toN = tillN()
        random.shuffle(toN)
        time = timeCalc(quicksort, toN, rand=False)

        answer2 += time / t

    # print("outputing")
    return answer1, answer2


def quick_comparison_ordered_input(n: int, t: int) -> tuple[float, float]:
    answer1: float = 0
    answer2: float = 0
    for i in range(t):
        # print(f"(1 of 2 operations) {i + 1} out of {t}")

        toN = list(range(n))
        time = timeCalc(quicksort, toN, rand=True)

        answer1 += time / t

    for i in range(t):
        # print(f"(2 of 2 operations) {i + 1} out of {t}")

        toN = list(range(n))
        time = timeCalc(quicksort, toN, rand=False)

        answer2 += time / t

    return answer1, answer2


##############
# Question 2 #
##############
# 2b
def max_v1_rec_improved(L, start, end):
    """helper function for max_v1_improved"""
    n = end - start

    if n == 1:
        return L[start]

    mid = start + n // 2
    first_half = max_v1_rec_improved(L, start, mid)
    second_half = max_v1_rec_improved(L, mid, end)

    return max(first_half, second_half)


# 2b
def max_v1_improved(L):
    n = len(L)

    return max_v1_rec_improved(L, 0, n)


def max_v2_rec_improved(L, start):
    """used in max_v2_improved"""
    n = len(L) - start
    cur = L[start]

    if n == 1:
        return cur

    without_left = max_v2_rec_improved(L, start + 1)

    return max(without_left, cur)


def max_v2_improved(L):
    return max_v2_rec_improved(L, 0)


# 2c
def reverse_rec(L, end):
    """used in reverse()"""
    if end == len(L) - 1:
        return [L[end]]

    return reverse_rec(L, end + 1) + [L[end]]


def reverse(L):
    return reverse_rec(L, 0)


##############
# Question 3 #
##############
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


# print(subset_sum_efficient([2, 6, 3, 4, 0, 0], 5))
# print(subset_sum_efficient([2, 6, 3, 4, 0, 0], 1))
# print(subset_sum_efficient([2, 1, 0, 3, 0, 0, 0, 0, 0, 2], 9))
# print(subset_sum_efficient([2, 1, 0, 3, 0, 0, 0, 0, 0, 2], 8))
# print(subset_sum_efficient([2, 1, 0, 3, 0, 0, 0, 0, 0, 2], 7))
# print(subset_sum_efficient([2, 1, 0, 3, 0, 0, 0, 0, 0, 2], 6))
# print(subset_sum_efficient([2, 6, 3, 4], 9))


##############
# Question 4 #
##############
# 4a
def legal_path(A, vertices):
    return all(A[vertices[i]][vertices[i + 1]] for i in range(len(vertices) - 1))


# 4c
def path_v2(A, s, t, k):
    if k == 0:
        return s == t

    if k == 1:
        return bool(A[s][k])

    for i in range(len(A)):
        mid = k // 2
        if path_v2(A, s, i, mid) and path_v2(A, i, t, k - mid):
            return True
    return False


# 4d # Fix this code without deleting any existing code #
def path_v4(A, s, t):
    L = [False for i in range(len(A))]
    return path_rec(A, s, t, L)


def path_rec(A, s, t, L):
    # print(f"{s=}, {t=}, {L=}")
    if L[s] != False: 
        return False

    if s == t:
        return True

    for i in range(len(A)):
        if A[s][i] == 1:
            if path_rec(A, i, t, L):
                return True
            L[i] = True
            
    return False


##############
# Question 5 #
##############
# 5a
def can_create_once(s: int, L: list[int], start: int = 0) -> bool:
    n: int = len(L) - start

    if n == 0:
        return not s

    cur: int = L[start]

    subtract: bool = can_create_once(s - cur, L, start + 1)
    add: bool = can_create_once(s + cur, L, start + 1)

    return subtract or add


# 5b
def can_create_twice(s: int, L: list[int], start=0) -> bool:
    n: int = len(L) - start

    if n == 0:
        return not s

    cur = L[start]

    subtract: bool = can_create_twice(s - cur, L, start + 1)  # -n
    add: bool = can_create_twice(s + cur, L, start + 1)  # +n
    subtractTwice: bool = can_create_twice(s - 2 * cur, L, start + 1)  # -n -n
    addTwice: bool = can_create_twice(s + 2 * cur, L, start + 1)  # +n +n
    doNothing: bool = can_create_twice(s, L, start + 1)  # +n -n

    return subtract or add or subtractTwice or addTwice or doNothing


# 5c
def valid_brackets_placement_rec(lst: list[str]) -> list[str]:
    n = len(lst)
    if n == 1:
        return [lst[0]]
    if n == 3:
        return [eval(lst[0] + lst[1] + lst[-1])]
    out: list[str] = []
    for place in range(1, n, 2):
        left: list[str] = valid_brackets_placement_rec(lst[:place])
        right: list[str] = valid_brackets_placement_rec(lst[place + 1 :])
        to_add = [eval(str(l) + lst[place] + str(r)) for r in right for l in left]
        out += to_add
    return out


def valid_brackets_placement(s: int, lst: list[str]) -> bool:
    return s in valid_brackets_placement_rec(lst)


##############
# QUESTION 6 #
##############
class RationalNumber:
    def __init__(self, p: int, q: int) -> None:
        """Represents rational number by its canonical form"""

        g = math.gcd(p, q)  # defineded above

        self.unique_p = p // g
        self.unique_q = q // g

    def __eq__(self, other: object):
        if not isinstance(other, RationalNumber):
            return NotImplemented

        return other.unique_p == self.unique_p and other.unique_q == self.unique_q

    def is_int(self) -> bool:
        return self.unique_q == 1

    def __mul__(self, other):
        resultp = self.unique_p * other.unique_p
        resultq = self.unique_q * other.unique_q
        result = RationalNumber(resultp, resultq)
        # print(f"{self=}, {other=}, {result=}")
        return result

    def __add__(self, other):
        resultp = self.unique_p * other.unique_q + other.unique_p * self.unique_q
        resultq = self.unique_q * other.unique_q

        return RationalNumber(resultp, resultq)

    def divides(self, other):
        return (RationalNumber(self.unique_q, self.unique_p) * other).is_int()

    def __repr__(self):
        return f"{self.unique_p} / {self.unique_q}"


##########
# Tester #
##########
def test():

    # 2b
    if max_v1_improved([1, 5, 3, 4, -1]) != 5 or max_v1_improved([1]) != 1:
        print("error in max_v1_improved")

    if max_v2_improved([1, 5, 3, 4, -1]) != 5 or max_v2_improved([1]) != 1:
        print("error in max_v2_improved")

    # 2c
    if reverse([1, 5, "hello"]) != ["hello", 5, 1] or reverse([1]) != [1]:
        print("error in reverse")

    # 3a
    if subset_sum([1, 2, 4, 8, 16], 32) != subset_sum_efficient([1, 2, 4, 8, 16], 32):
        print("error in subset_sum_efficient")

    # 4a
    A = [
        [0, 1, 1, 0, 0],
        [1, 0, 1, 0, 0],
        [0, 0, 0, 1, 0],
        [1, 0, 0, 0, 0],
        [0, 0, 0, 0, 0],
    ]
    if (
        not legal_path(A.copy(), [0, 1, 2, 3])
        or not legal_path(A.copy(), [0, 1, 2, 3, 0, 1])
        or legal_path(A.copy(), [1, 2, 3, 4])
    ):
        print("error in legal_path")

    # 5a
    if (
        not can_create_once(6, [5, 2, 3])
        or not can_create_once(-10, [5, 2, 3])
        or can_create_once(9, [5, 2, 3])
        or can_create_once(7, [5, 2, 3])
    ):
        print("error in can_create_once")

    # 5b
    if (
        not can_create_twice(6, [5, 2, 3])
        or not can_create_twice(9, [5, 2, 3])
        or not can_create_twice(7, [5, 2, 3])
        or can_create_once(19, [5, 2, 3])
    ):
        print("error in can_create_twice")

    # 5c
    lst = ["6", "-", "4", "*", "2", "+", "3"]
    if (
        not valid_brackets_placement(10, lst.copy())
        or not valid_brackets_placement(1, lst.copy())
        or valid_brackets_placement(5, lst.copy())
    ):
        print("error in valid_brackets_placement")

    # Q6
    num1 = RationalNumber(10, 4)
    if num1.unique_p != 5 or num1.unique_q != 2:
        print("2 - error in RationalNumber's __init__")

    num2 = RationalNumber(20, 8)
    if num1 != num2:
        print("2 - error in RationalNumber's __eq__")

    num2 = RationalNumber(6, 3)
    if not num2.is_int:
        print("2 - error in RationalNumber's is_int")

    num2 = RationalNumber(12, 8)
    res = num1 * num2
    if res.unique_p != 15 or res.unique_q != 4:
        print("2 - error in RationalNumber's __mul__")

    res = num1 + num2
    if res.unique_p != 4 or res.unique_q != 1:
        print("2 - error in RationalNumber's __add__")

    num2 = RationalNumber(5, 1)
    if not num1.divides(num2):
        print("2 - error in RationalNumber's divides")


# test()
