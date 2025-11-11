# Add your implementation to this file

# Replace the 'pass' command with your implementation in each function.
# you may NOT change the signature of the existing functions.
# you can add new functions if needed.

# Change the name of the file to include your ID number (hw1_ID.py).

from typing import MutableSequence, Union
from string import digits


# Question 1a
def union_strings(st1, st2):
    output = ""
    for char in st1:
        if not char in output:
            output += char

    for char in st2:
        if not char in output:
            output += char

    return output


# Question 1b
def format_str(text_to_format, st_to_insert):
    output = ""
    for char in text_to_format:
        if char == "?":
            output += st_to_insert
        else:
            output += char

    return output


# Question 1c
def least_pal(text: str) -> int:
    opText = text[::-1]
    counter = 0

    for i in range(len(text)):
        if text[i] != opText[i]:
            counter += 1

    return int(counter / 2)


# Question 1d
def least_frequent(text):
    # character : count
    sums = dict()

    for char in text:
        if char in sums:
            sums[char] += 1
        else:
            sums[char] = 1

    return min(sums, key=lambda x: sums[x])


def indexIfExists(iterable: MutableSequence, index=0) -> str:
    """helper for longest_common_suffix. Returns an index of `iterable`, if it exists, else empty string."""
    try:
        return iterable[index]
    except:
        return ""


def longest_common_suffix(lst: list[str]) -> str:
    output = ""
    # the longest possible suffix
    check = min(lst, key=len)

    for i in range(len(check)):
        cur = check[-i - 1]

        # check if it's working with all of the strings in the list
        for string in lst:
            if string[-i - 1] != cur:
                return output

        # add the current at the start
        output = cur + output

    return output


# Question 1f
def is_int(text: str) -> bool:
    for char in text:
        if char not in digits:
            return False

    return True


# Question 1g
def merge(text1, text2) -> str:
    output = ""
    text1 = list(text1)
    text2 = list(text2)

    # run on all of the characters in text1
    for i in range(len(text1)):
        t1i = text1[0]

        # add all of the smaller numbers
        for j in range(len(text2)):
            t2j = text2[0]
            if t2j < t1i:
                output += t2j
                del text2[0]
            # as the letters are ordered, we can break here;
            else:
                break

        output += t1i
        del text1[0]

    output += "".join(text2)
    return output


# Question 2a
def is_anagram(st1, st2):
    st1 = list(st1)
    st2 = list(st2)

    if len(st1) == 0:
        return True if len(st2) == 0 else False

    for j in range(len(st2)):
        if st1[0] == st2[j]:
            del st2[j]
            return is_anagram(st1[1:], st2)

    return False


# Question 2c
def is_anagram_v3(st1, st2):
    return sorted(st1) == sorted(st2)


# Question 3a
def eval_mon(monomial: str, val: int) -> int:
    output = 0
    # power
    output += val ** int(monomial[monomial.find("x^") + 2 :])
    output *= int(monomial[1 : monomial.find("x^")])
    # sign
    output *= 1 if monomial[0] == "+" else -1

    return output


# Question 3b
def eval_mon_vec(monomial, val_vec) -> list[int]:
    return [eval_mon(monomial, val) for val in val_vec]


# Question 3c
def eval_pol(polynomial: str, val: int) -> int:
    output = 0
    # required for the last iteration
    polynomial += "+"
    # instead reevaluating each time 'polynomial'. reduces complexity
    lastCall = 0
    for i in range(1, len(polynomial)):
        if polynomial[i] in "+-":
            output += eval_mon(polynomial[lastCall:i], val)
            lastCall = i

    return output


########
# Tester
########


def test():
    # Testing Q1
    if "".join(sorted(union_strings("aabcccdde", "bccay"))) != "abcdey":
        print("error in union_strings - 1")
    if "".join(sorted(union_strings("aabcccdde", ""))) != "abcde":
        print("error in union_strings - 2")

    if format_str("I2?", "CS") != "I2CS":
        print("error in format_str - 1")
    if format_str("???", "W") != "WWW":
        print("error in format_str - 2")
    if format_str("ABBC", "z") != "ABBC":
        print("error in format_str - 3")

    if least_pal("abcdefgh") != 4:
        print("error in least_pal - 1")
    if least_pal("radarr") != 2:
        print("error in least_pal - 2")
    if least_pal("race car") != 1:
        print("error in least_pal - 3")
    if least_pal("tenat") != 1:
        print("error in least_pal - 4")

    if least_frequent("aabcc") != "b":
        print("error in least_frequent - 1")
    if least_frequent("aea.. e") != " ":
        print("error in least_frequent - 2")
    if least_frequent("zzz") != "z":
        print("error in least_frequent - 3")

    if longest_common_suffix(["abccdba", "cba", "zaba"]) != "ba":
        print("error in longest_common_suffix - 1")
    if longest_common_suffix(["hello", "world"]) != "":
        print("error in longest_common_suffix - 2")
    if longest_common_suffix(["intro", "maestro"]) != "tro":
        print("error in longest_common_suffix - 3")
    if longest_common_suffix(["intro"]) != "intro":
        print("error in longest_common_suffix - 4")

    if is_int("12x"):
        print("error in is_int - 1")
    if is_int("-0"):
        print("error in is_int - 2")
    if not is_int("42"):
        print("error in is_int - 3")

    if merge("abcd", "") != "abcd":
        print("error in merge - 1")
    if merge("aabbddfgk", "adkox") != "aaabbdddfgkkox":
        print("error in merge - 2")

    # Testing Q2
    if not is_anagram(
        "tommarvoloriddle",
        "iamlordvoldemort",
    ):
        print("error in is_anagram - 1")
    if is_anagram("abce", "abcd"):
        print("error in is_anagram - 2")
    if not is_anagram("listen", "silent"):
        print("error in is_anagram - 3")

    if not is_anagram_v3(
        "tommarvoloriddle",
        "iamlordvoldemort",
    ):
        print("error in is_anagram_v3 - 1")
    if is_anagram_v3("abce", "abcd"):
        print("error in is_anagram_v3 - 2")
    if not is_anagram_v3("listen", "silent"):
        print("error in is_anagram_v3 - 3")

    # Testing Q3
    if eval_mon("+5x^3", 4) != 320:
        print("error in eval_mon - 1")
    if eval_mon("-5x^0", 1000) != -5:
        print("error in eval_mon - 2")
    if eval_mon("+1x^10", 2) != 1024:
        print("error in eval_mon - 3")

    if eval_mon_vec("+5x^3", [4, -5, 10]) != [320, -625, 5000]:
        print("error in eval_mon_vec - 1")

    if eval_pol("+5x^3-4x^2+7x^1-5x^0", 4) != 279:
        print("error in eval_pol - 1")
    if (
        eval_pol(
            "+1x^0+1x^1+1x^2+1x^3+1x^4+1x^5",
            5,
        )
        != 3906
    ):
        print("error in eval_pol - 2")
    if (
        eval_pol(
            "+1x^0+1x^1+1x^2+1x^3+1x^4+1x^5",
            2,
        )
        != 63
    ):
        print("error in eval_pol - 3")

    print("`test()` completed.")
