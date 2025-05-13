# Skeleton file for HW2 - Fall 2023-4 - Extended Intro to CS

# Add your implementation to this file

# you may NOT change the signature of the existing functions.
# you can add new functions if needed.

# Change the name of the file to include your ID number (hw2_ID.py).

import random  # loads python's `random` module in order to use `random.random()` in question 2.


##############
# QUESTION 1 #
##############
#  Q1a
def divisors(n: int) -> list[int]:
    answer = [i for i in range(1, n // 2 + 1) if n % i == 0]
    return answer


#  Q1b
def perfect_numbers(c):
    answer = list()
    i = 2

    while len(answer) != c:
        if sum(divisors(i)) == i:
            answer.append(i)
        i += 1

    return answer


#  Q1c
def abundant_density(n):
    counter = 0
    for i in range(1, n + 1):
        if sum(divisors(i)) > i:
            counter += 1

    return counter / n


#  Q1e
def semi_perfect(n: int, size: int, dividorsLeft: list[int] | None = None) -> bool:
    """helper for semi_perfect_4"""
    # dynamic programming solution exist but not required.

    # first rec call
    if dividorsLeft is None:
        dividorsLeft = divisors(n)

    if size == 0:
        # * return [-1] if n==0 else [0]
        return not n  # true if n = 0, else false

    if len(dividorsLeft) < size:
        # * return [0]
        return False

    removeCur = semi_perfect(
        n,
        size=size,
        dividorsLeft=dividorsLeft[1:],
    )
    addCur = semi_perfect(
        n - dividorsLeft[0],
        size=size - 1,
        dividorsLeft=dividorsLeft[1:],
    )

    return removeCur or addCur


def semi_perfect_4(n: int) -> bool:
    return semi_perfect(n, size=4)


#  Q1f
def find_gcd(a: int, b: int) -> int:
    while b != 0:
        t = b
        b = a % b
        a = t
    return a


##############
# QUESTION 2 #
##############
# Q2a
def coin() -> bool:
    return False if random.random() < 0.5 else True


# Q2b
def sample(v: list[object], p: list[float]):
    chosen = random.random()
    last = 0.0

    for i in range(len(p)):
        if chosen < p[i] + last:
            return v[i]
        else:
            last += p[i]


# Q2c
def monty_hall(switch: bool, times: int) -> float:
    avg: float = 0.0
    rand3 = lambda: int(random.random() * 3)
    rand2 = lambda: int(random.random() * 2)

    for _ in range(times):
        doors = [0, 0, 0]

        car = rand3()
        doors[car] = 1

        chosen = rand3()
        if chosen != car:
            opened = [i for i in range(3) if i != chosen and i != car][0]
        else:
            opened = [i for i in range(3) if i != chosen and i != car][rand2()]

        if switch:
            chosen = [i for i in range(3) if i != chosen and i != opened][0]

        avg += doors[chosen]

    return avg / times


# Q2d
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


##############
# QUESTION 3 #
##############
# Q3a
def inc(binary: str) -> str:
    if binary == "0":
        return "1"

    binary = binary[::-1]
    output: str = ""
    n = len(binary)

    for i in range(n):
        if binary[i] == "1":
            output += "0"
        if binary[i] == "0":
            output += "1"
            break

    if i == n - 1:
        output += "1"
    else:
        output += binary[i + 1 :]

    return output[::-1]


# Q3b
def add(bin1: str, bin2: str) -> str:
    output: str = ""
    if len(bin1) >= len(bin2):
        bin1, bin2 = bin2, bin1

    bin1, bin2 = bin1[::-1], bin2[::-1]

    n = len(bin2)
    unn = len(bin1) - 1

    carry: int = 0
    for i in range(n):
        first_num: int = 0
        if i <= unn:
            first_num = int(bin1[i])
        second_num: int = int(bin2[i])

        val = second_num + first_num + carry

        # a more compact version then match-case everything
        if True:
            output += str(val % 2)
            carry = val - 2 >= 0

    if carry == 1:
        output += "1"

    return output[::-1]


def bin_to_int(binary) -> int:
    """helper for the Q3c, Q3d"""

    val = "0"
    counter: int = 0

    while val != binary:
        val = inc(val)
        counter += 1

    return counter


def log2floor(n: int) -> int:
    """helper for intToBin"""
    count: int = 0
    val: int = 1
    while n >= val:
        val *= 2
        count += 1

    return count - 1


def int_to_bin(n: int) -> str:
    """helper for Q3c, Q3d"""

    if n == 0:
        return "0"

    output: str = ""
    i = int(log2floor(n))

    while i != -1:
        cur = n - 2**i
        if cur >= 0:
            n = cur
            output += "1"
        else:
            output += "0"

        i -= 1

    return output


# Q3c
def mod_two(binary: str, power: int) -> str:
    return int_to_bin(bin_to_int(binary) % 2**power)


# Q3d
def max_bin(lst: list[str]) -> str:
    return max(lst, key=bin_to_int)


##############
# QUESTION 4 #
##############
# Q4a
def assess_office_hour(
    office_hour, student_schedules_dict: dict[str, list[tuple[int, int]]]
) -> tuple[list[str], float]:
    who: list[str] = []

    for student in student_schedules_dict:
        available = True
        for interval in student_schedules_dict[student]:
            if interval[0] <= office_hour[0] and interval[1] >= office_hour[1]:
                available = False
                break

        if available:
            who.append(student)

    if len(student_schedules_dict) == 0:
        relation = 0.0
    else:
        relation = len(who) / len(student_schedules_dict)

    return who, relation


# Q4b1
def merge_intervals(intervals: list[tuple[int, int]]) -> list[tuple[int, int]]:
    intervals = sorted(intervals, key=lambda tpl: tpl[0])
    intervalsMod = [[t[0], t[1]] for t in intervals]
    output: list[list[int]] = [intervalsMod[0]]

    for i in range(1, len(intervalsMod)):
        interval = intervalsMod[i]

        if interval[0] <= output[-1][1]:
            output[-1][1] = max(interval[1], output[-1][1])
        else:
            output.append(interval)

    return [(l[0], l[1]) for l in output]


# Q4b2
def find_perfect_slots(
    student_schedules_dict: dict[str, list[tuple[int, int]]]
) -> list[tuple[int, int]]:
    unavailable: list[tuple[int, int]] = []
    for times in student_schedules_dict.values():
        unavailable += times

    unavailable = merge_intervals(unavailable)

    output: list[tuple[int, int]] = []

    for hour in range(7, 20):
        ok = True
        for interval in unavailable:
            if interval[0] <= hour and interval[1] >= hour + 1:
                ok = False
        if ok:
            output.append((hour, hour + 1))

    return output


##########
# Tester #
##########


def test():
    # Testing Question 1:
    if divisors(6) != [1, 2, 3] or divisors(7) != [1]:
        print("Error in Q1a - 1")

    if perfect_numbers(1) != [6]:
        print("Error in Q1b - 1")

    if perfect_numbers(2) != [6, 28]:
        print("Error in Q1b - 2")

    if abundant_density(20) != 0.15:
        print("Error in Q1c- 1")

    if not semi_perfect_4(20):
        print("Error in Q1e - 1")
    if semi_perfect_4(28):
        print("Error in Q1e - 2")

    if not isinstance(find_gcd(5, 10), int):
        print("Error in Q1f")

    # Testing Question 2:
    for i in range(10):
        if coin() not in {True, False}:
            print("Error in Q2a")
            break

    for i in range(10):
        sampled_elem = sample(list("abc"), [0.4, 0.6, 0.0])
        if sampled_elem not in set("ab"):
            print("Error in Q2b")
            break

    for i in range(10):
        if monty_hall(True, 4) not in {0.0, 0.25, 0.5, 0.75, 1.0}:
            print("Error in Q2c")
            break

    for i in range(10):
        st = "basiparachromatin"
        if sorted(sample_anagram(st)) != sorted(st):
            print("Error in Q2d")
            break

    # Testing Question 3:
    if (
        inc("0") != "1"
        or inc("1") != "10"
        or inc("101") != "110"
        or inc("111") != "1000"
        or inc(inc("111")) != "1001"
    ):
        print("Error in Q3a")

    if (
        add("0", "1") != "1"
        or add("1", "1") != "10"
        or add("110", "11") != "1001"
        or add("111", "111") != "1110"
    ):
        print("Error in Q3b")

    if (
        mod_two("110", 2) != "10"
        or mod_two("101", 2) != "1"
        or mod_two("111", 4) != "111"
    ):
        print("Error in Q3c")

    if max_bin(["1010", "1011"]) != "1011":
        print("Error in Q3d - 1")

    if max_bin(["10", "0", "1"]) != "10":
        print("Error in Q3d - 2")

    # Testing Question 4:
    office_hour = (11, 12)
    student_schedules_dict = {
        "noam": [(8, 10), (10, 12), (15, 18)],
        "larry": [(10, 11), (14, 16)],
        "jeff": [(10, 11), (14, 16)],
    }
    if set(assess_office_hour(office_hour, student_schedules_dict)[0]) != set(
        ["larry", "jeff"]
    ):
        print("Error in Q4a - 1")
    if assess_office_hour(office_hour, student_schedules_dict)[1] != (2 / 3):
        print("Error in Q4a - 2")

    if merge_intervals([(-2, 43), (-700, -9), (20, 52), (52, 60)]) != [
        (-700, -9),
        (-2, 60),
    ]:
        print("Error in Q4b1 - 1")
    if merge_intervals([(-2, 43), (-700, -9)]) != [(-700, -9), (-2, 43)]:
        print("Error in Q4b1 - 2")
    if merge_intervals([(8, 10), (10, 12), (10, 11), (15, 18), (14, 16)]) != [
        (8, 12),
        (14, 18),
    ]:
        print("Error in Q4b1 - 3")

    if find_perfect_slots(student_schedules_dict) != [
        (7, 8),
        (12, 13),
        (13, 14),
        (18, 19),
        (19, 20),
    ]:
        print("Error Q4b2 - 1")