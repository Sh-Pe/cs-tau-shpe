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
def string_to_int(s):
    pass


# Q3_b
def int_to_string(k, n):
    pass


# Q3_c
def sort_strings1(lst, k):
    pass


# Q3_e
def sort_strings2(lst, k):
    pass


##############
# QUESTION 4 #
##############
# Q4_a
def find_almost_1(lst, s):
    pass  # TODO replace with your code


# Q4_b
def sort_almost_k(lst, k):
    pass  # TODO replace with your code

# Q4_1c
def find_percentile_almost_k(lst, k, m):
    pass  # TODO replace with your code


##############
# QUESTION 5 #
##############
# Q5_a
def edit_distance(st1, st2):
    if len(st1) == 0:
        pass  # TODO replace with your code
    if len(st2) == 0:
        pass  # TODO replace with your code
    if st1[0] == st2[0]:
        return edit_distance(st1[1:], st2[1:])
    op1 = edit_distance(st1[1:], st2)
    op2 = edit_distance(st1, st2[1:])
    op3 = edit_distance(st1[1:], st2[1:])
    return min(op1, op2, op3) + 1


# Q5_b
def relevancy_score(text, promote, L):
    pass  # TODO replace with your code


# Q5_c
def PageRank_search(G, t, p, text, pages_desc, pages_promote):
    pass  # TODO replace with your code


##########
# Tester #
##########
def test():
    # Q3
    if string_to_int("aa") != 0 or string_to_int("aba") != 5:
        print("error in string_to_int")
    lst_num = [random.choice(range(5 ** 4)) for i in range(15)]
    for i in lst_num:
        s = int_to_string(4, i)
        if s is None or len(s) != 4:
            print("error in int_to_string")
        if string_to_int(s) != i:
            print("error in int_to_string and/or in string_to_int")

    lst1 = ['aede', 'adae', 'dded', 'deea', 'cccc', 'aacc', 'edea', 'becb', 'daea', 'ccea', 'aacc']
    if sort_strings1(lst1, 4) \
            != ['aacc', 'aacc', 'adae', 'aede', 'becb', 'cccc', 'ccea', 'daea', 'dded', 'deea', 'edea']:
        print("error in sort_strings1")

    if sort_strings2(lst1, 4) \
            != ['aacc', 'aacc', 'adae', 'aede', 'becb', 'cccc', 'ccea', 'daea', 'dded', 'deea', 'edea']:
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

    almost_sorted_lst_3 = [10,7,7,1]
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