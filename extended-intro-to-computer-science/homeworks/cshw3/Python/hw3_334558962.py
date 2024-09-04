# Skeleton file for HW3 - Spring 2024 - extended intro to CS

# Add your implementation to this file

# you may NOT change the signature of the existing functions.
# you can add new functions if needed.

# Change the name of the file to include your ID number (hw3_ID.py).

import random  # loads python's random module to use in Q5


##############
# QUESTION 3 #
##############
# Q3_a
def char_to_int(c: str) -> int:
    """helper for functions in Q11 (run in O(1))"""
    if c not in ["a", "b", "c", "d", "e"]:
        raise ValueError("only str a, b, c, d, e are allowed")
    else:
        return ord(c) - 97


def int_to_char(i: int) -> str:
    """helper for functions in Q11 (run in O(1))"""
    if i not in range(0, 5):
        raise ValueError("only intgers in range(1, 6) are allowed")
    else:
        return chr(i + 97)


def string_to_int(s: str) -> int:
    n: int = len(s)
    s = s[::-1]

    output: int = 0
    for i in range(n):
        output += 5 ** (i) * char_to_int(s[i])

    return output


# Q3_b
def int_to_string(k: int, n: int) -> str:
    output = ""

    for i in range(k):
        scale = 5 ** (k - i - 1)
        output += int_to_char(
            n // scale
        )  # e.g. 69 // 25 = 2, then 19 // 5 = 3 and last 4 // = 4 => (2, 3, 4) = "bcd"
        remove = scale * (n // scale)  # equal to n % scale
        n -= remove

    return output


# Q3_c
def sort_strings1(lst: list[str], k: int) -> list[str]:
    """sorts strings in alphabetic order"""
    n: int = len(lst)
    output: list[str] = []

    # create an empty list
    memory: list[list[int, str]] = [[0, ""] for _ in range(5**k)]  # exactly O(5**k)

    # assign to the `memory` the number of times that each value [=index] is shown in the list.
    for i in range(n):
        value = string_to_int(lst[i])  # exactly \Theta(kn)
        memory[value][0] += 1
        memory[value][1] = lst[i]

    # filling `output`
    for i in range(5**k):
        curVal: int = memory[i][0]  # exactly \Theta(5**k)
        if curVal == 0:
            continue

        revalue = memory[i][1]
        for _ in range(curVal):
            output.append(revalue)  # exactly \Theta(n)

    return output


# Q3_e
def sort_strings2(lst: list[str], k: int) -> list[str]:
    n = len(lst)
    output: list[str] = []

    for i in range(5**k):
        cur = int_to_string(k, i)
        for j in range(n):
            if lst[j] == cur:
                output.append(lst[j])

    return output


##############
# QUESTION 4 #
##############
# Q4_a
def find_rec(lst, s, start, end):
    """a recursive implementation of binary search based algorithem, modified for almost sorted lists"""

    n = end - start
    newIndent = n // 2
    curIndex = newIndent + start

    # * Base cases
    if n == 0:
        return None
    curVal = lst[curIndex]
    if curVal == s:
        return curIndex
    elif n == 1:
        return None
    if lst[curIndex - 1] == s:
        return curIndex - 1
    elif n == 2:
        return None
    elif lst[curIndex + 1] == s:
        return curIndex + 1

    # * Rec calls
    elif curVal > s:
        recCall = find_rec(lst, s, end=end - newIndent - 2, start=start)
        if recCall is not None:
            return recCall
        else:
            return None

    elif curVal < s:
        recCall = find_rec(lst, s, end=end, start=start + newIndent + 2)
        if recCall is not None:
            return recCall
        else:
            return None


def find_almost_1(lst: list, s: int):
    n = len(lst)
    result = find_rec(lst, s, 0, n)
    return result


# Q4_b
def sort_almost_k(lst: list[int], k: int) -> None:
    n = len(lst)
    for i in range(n):
        for j, val in enumerate(
            list(sorted((lst[j] for j in range(i, min(k + i + 1, n)))))
        ):
            lst[i + j] = val

    return


# Q4_1c
def find_percentile_almost_k(lst: list[int], k: int, m: int) -> int:
    startIndex: int = max(0, m - k)
    endIndex: int = min(len(lst), m + k)
    interval: list[int] = [lst[i] for i in range(startIndex, endIndex)]  # O(k)
    sort_almost_k(interval, k)  # O(k)
    # print(f"{interval=}")

    return interval[m - 1 - startIndex]


##############
# QUESTION 5 #
##############
# Q5_a
def edit_distance(st1, st2):
    if len(st1) == 0:
        return len(st2)
    if len(st2) == 0:
        return len(st1)
    if st1[0] == st2[0]:
        return edit_distance(st1[1:], st2[1:])
    op1 = edit_distance(st1[1:], st2)
    op2 = edit_distance(st1, st2[1:])
    op3 = edit_distance(st1[1:], st2[1:])
    return min(op1, op2, op3) + 1


# Q5_b
def relevancy_score(text, promote, L: list[str]) -> int:
    if L == []:
        return 99999999999999999999999999
    k0 = min((edit_distance(text, s) for s in L))

    score = (promote + 1) / (1 + k0**2)
    return score


# Q5_c
def chooseRandomPage(
    text: str, pages_desc: list[list[str]], pages_promote: list[int | bool]
):
    n = len(pages_desc)
    scores = [
        relevancy_score(
            text,
            pages_promote[i],
            pages_desc[i],
        )
        for i in range(n)
    ]
    total = sum(scores)
    chances: list[float] = [score / total for score in scores]

    rand = random.random()
    cur: float = 0.0
    prev: float = 0.0

    # print(f"{chances=}")
    for i in range(n):
        cur += chances[i]
        # print(f"{prev=}, {cur=}")
        if prev <= rand < cur:
            chosen = i
            break

        prev += chances[i]

    return chosen


def PageRank_search(
    G: list[list[int]],
    t: int,
    p: float,
    text: str,
    pages_desc: list[list[str]],
    pages_promote: list[bool | int],
):
    page = chooseRandomPage(text, pages_desc, pages_promote)
    n = len(G)
    output = [0 for _ in range(n)]
    output[page] += 1

    for _ in range(t - 1):
        map = G[page]
        rand: float = random.random()
        if map != [] and rand < p:
            desc_map = [pages_desc[i] * (i in map) for i in range(n)]
            promote_map = [pages_promote[i] * (2 * (i in map) - 1) for i in range(n)]
            page = chooseRandomPage(text, desc_map, promote_map)
        else:
            page = chooseRandomPage(text, pages_desc, pages_promote)

        output[page] += 1

    return [out / t for out in output]


# out = PageRank_search(
#     G=[[1, 2], [2], [0, 1], [0, 1, 3], [2], []],
#     t=10000,
#     p=0.3,
#     text="hello",
#     pages_desc=[
#         ["lll", "world"],
#         ["don't", "choose", "me"],
#         ["hello", "o"],
#         ["test", "hel"],
#         ["llo"],
#         ["hell"],
#     ],
#     pages_promote=[True, False, False, True, False, True],
# )

# print(out)
# print(sum(out))


##########
# Tester #
##########
def test():
    # Q3
    if string_to_int("aa") != 0 or string_to_int("aba") != 5:
        print("error in string_to_int")
    lst_num = [random.choice(range(5**4)) for i in range(15)]
    for i in lst_num:
        s = int_to_string(4, i)
        if s is None or len(s) != 4:
            print("error in int_to_string")
        if string_to_int(s) != i:
            print("error in int_to_string and/or in string_to_int")

    lst1 = [
        "aede",
        "adae",
        "dded",
        "deea",
        "cccc",
        "aacc",
        "edea",
        "becb",
        "daea",
        "ccea",
        "aacc",
    ]
    if sort_strings1(lst1, 4) != [
        "aacc",
        "aacc",
        "adae",
        "aede",
        "becb",
        "cccc",
        "ccea",
        "daea",
        "dded",
        "deea",
        "edea",
    ]:
        print("error in sort_strings1")

    if sort_strings2(lst1, 4) != [
        "aacc",
        "aacc",
        "adae",
        "aede",
        "becb",
        "cccc",
        "ccea",
        "daea",
        "dded",
        "deea",
        "edea",
    ]:
        print("error in sort_strings2")

    # Q4
    almost_sorted_lst = [2, 1, 3, 5, 4, 7, 6, 8, 9]
    if find_almost_1(almost_sorted_lst, 5) != 3:
        print("error in find_almost_1")
    if find_almost_1(almost_sorted_lst, 50) is not None:
        print("error in find_almost_1")

    almost_sorted_lst_2 = [2, 3, 1, 5, 4, 7, 6, 8, 9]
    res = sort_almost_k(almost_sorted_lst_2, 2)
    if res is not None:
        print("error in sort_almost_k")
    if almost_sorted_lst_2 != sorted(almost_sorted_lst_2):
        print("error in sort_almost_k")

    almost_sorted_lst_3 = [10, 7, 7, 1]
    if find_percentile_almost_k(almost_sorted_lst_3, 3, 1) != 1:
        print("error in find_percentile_almost_k")
    if find_percentile_almost_k(almost_sorted_lst_3, 3, 2) != 7:
        print("error in find_percentile_almost_k")
    if find_percentile_almost_k(almost_sorted_lst_3, 3, 3) != 7:
        print("error in find_percentile_almost_k")

    # Q5
    if edit_distance("sport", "spotr") != 2 or edit_distance("workout", "wrkout") != 1:
        print("error in edit_distance")

    if relevancy_score("spotr", True, ["sport", "gym", "workout"]) != 0.4:
        print("error in relevancy_score")


# test()