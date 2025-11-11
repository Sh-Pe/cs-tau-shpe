# Skeleton file for HW1 - Spring 2024 - Extended Intro to CS

# Add your implementation to this file

# Replace the 'pass' command with your implementation in each function.
# you may NOT change the signature of the existing functions.
# you can add new functions if needed.

# Change the name of the file to include your ID number (hw1_ID.py).


# Question 4a
def union_strings(st1: str, st2: str) -> str:
    output: str = ""
    for char in st1 + st2:
        if not char in output:
            output += char

    return output


# Question 4b
def format_str(text_to_format: str, st_to_insert: str) -> str:
    output: str = ""

    for char in text_to_format:
        if char == "?":
            output += st_to_insert
        else:
            output += char

    return output


# Question 4c
def least_pal(text: str) -> int:
    op_text: str = text[::-1]
    counter: int = 0
    n = len(text)

    for i in range(n):
        if text[i] != op_text[i]:
            counter += 1

    return counter // 2


# Question 4d
def least_frequent(text: str) -> str:
    sums: dict[str, int] = dict()  # character : count

    for char in text:
        if char in sums:
            sums[char] += 1
        else:
            sums[char] = 1

    return min(sums, key=lambda x: sums[x])


# Question 4e
def longest_common_suffix(lst: list[str]):
    output: str = ""
    # the longest possible suffix
    base: str = min(lst, key=len)

    for i in range(len(base)):
        index: int = -i - 1
        current = base[index]

        # check if it's working with all of the strings in the list
        for string in lst:
            if string[index] != current:
                return output

        # add the current at the start
        output = current + output

    return output


# Question 4f
def is_int(text: str) -> bool:
    # edge cases. order matters. 
    if text == "0":
        return True
    if text == "" or text == "-":
        return False
    if text[0] == "-":
        text = text[1:]
    if text[0] == "0":
        return False

    digits = "1234567890"
    output = all((char in digits for char in text))
    return output


# Question 4g
def merge(text1: str, text2: str) -> str:
    output = ""
    lst1: list[str] = list(text1)
    lst2: list[str] = list(text2)

    # run on all of the characters in text1
    for i in range(len(lst1)):
        t1i = lst1[0]

        # add all of the smaller numbers
        for j in range(len(lst2)):
            t2j = lst2[0]
            if t2j < t1i:
                output += t2j
                del lst2[0]
            # as the letters are ordered, we can break here;
            else:
                break

        output += t1i
        del lst1[0]

    output += "".join(lst2)
    return output


# Question 5a
def is_anagram(st1: str, st2: str) -> bool:
    l1: list[str] = list(st1)
    l2: list[str] = list(st2)

    if len(st1) == 0:
        return True if len(st2) == 0 else False

    for i in range(len(l1)):
        char = l1[i]
        if char in l2:
            del l2[l2.index(char)]
        else:
            return False

    return not l2  # empty or not


# Question 5b
def is_anagram_v2(st1: str, st2: str) -> bool:
    for ch in st1 + st2:
        if st1.count(ch) != st2.count(ch):
            return False

    return True


# Question 5c
def is_anagram_v3(st1: str, st2: str) -> bool:
    return sorted(st1) == sorted(st2)


# Question 6a
def eval_mon(monomial: str, val: int) -> int:
    output: int = 0
    # power
    output += val ** int(monomial[monomial.find("x^") + 2 :])
    output *= int(monomial[1 : monomial.find("x^")])
    # sign
    output *= 1 if monomial[0] == "+" else -1

    return output


# Question 6b
def eval_pol(polynomial: str, val: int) -> int:
    output: int = 0
    # required for the last iteration
    polynomial += "+"
    # instead reevaluating each time 'polynomial'. reduces complexity
    lastCall: int = 0
    for i in range(1, len(polynomial)):
        if polynomial[i] in "+-":
            output += eval_mon(polynomial[lastCall:i], val)
            lastCall = i

    return output


########
# Tester
########


def test():
    # Testing Q4
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

    # Testing Q5
    if not is_anagram("tommarvoloriddle", "iamlordvoldemort"):
        print("error in is_anagram - 1")
    if is_anagram("abce", "abcd"):
        print("error in is_anagram - 2")
    if not is_anagram("listen", "silent"):
        print("error in is_anagram - 3")

    if not is_anagram_v2("tommarvoloriddle", "iamlordvoldemort"):
        print("error in is_anagram_v2 - 1")
    if is_anagram_v2("abce", "abcd"):
        print("error in is_anagram_v2 - 2")
    if not is_anagram_v2("listen", "silent"):
        print("error in is_anagram_v2 - 3")

    if not is_anagram_v3("tommarvoloriddle", "iamlordvoldemort"):
        print("error in is_anagram_v3 - 1")
    if is_anagram_v3("abce", "abcd"):
        print("error in is_anagram_v3 - 2")
    if not is_anagram_v3("listen", "silent"):
        print("error in is_anagram_v3 - 3")

    # Testing Q6
    if eval_mon("+5x^3", 4) != 320:
        print("error in eval_mon - 1")
    if eval_mon("-5x^0", 1000) != -5:
        print("error in eval_mon - 2")
    if eval_mon("+1x^10", 2) != 1024:
        print("error in eval_mon - 3")

    if eval_pol("+5x^3-4x^2+7x^1-5x^0+10x^11", 4) != 41943319:
        print("error in eval_pol - 1")
    if eval_pol("+1x^0+1x^1+1x^2+1x^3+1x^4+1x^5", 5) != 3906:
        print("error in eval_pol - 2")
    if eval_pol("+11x^0+1x^1+1x^2+1x^3+1x^4+1x^5", 2) != 73:
        print("error in eval_pol - 3")

    print("`test()` completed.")

# test()