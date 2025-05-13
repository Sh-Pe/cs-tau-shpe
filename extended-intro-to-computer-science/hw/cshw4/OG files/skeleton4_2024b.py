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
    """ quick sort of lst """
    if len(lst) <= 1:
        return lst
    else:
        pivot = random.choice(lst) if rand else lst[0]
        smaller = [elem for elem in lst if elem < pivot]
        equal = [elem for elem in lst if elem == pivot]
        greater = [elem for elem in lst if elem > pivot]

        return quicksort(smaller, rand) + equal + quicksort(greater, rand)


def quick_comparison_random_input(n, t):
    pass  # replace with your code


def quick_comparison_ordered_input(n, t):
    pass  # replace with your code


##############
# Question 2 #
##############
#2b
def max_v1_improved(L):
    pass  # replace with your code


def max_v2_improved(L):
    pass  # replace with your code

#2c
def reverse(L):
    pass  # replace with your code


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

def subset_sum_efficient(L, s):
    pass  # replace this with your code


##############
# Question 4 #
##############
# 4a
def legal_path(A, vertices):
    pass  # replace this with your code


# 4c
def path_v2(A, s, t, k):
    if k == 0:
        return s == t

    # ADD YOUR CODE HERE #

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
    if s == t:
        return True

    for i in range(len(A)):    
        if A[s][i] == 1:
            if path_rec(A, i, t, L):
                return True
    return False


##############
# Question 5 #
##############

# 5a

def can_create_once(s, lst):

    pass  # replace this with your code


# 5b

def can_create_twice(s, lst):

    pass  # replace this with your code


# 5c

def valid_brackets_placement(s, lst):

    pass  # replace this with your code



##############
# QUESTION 6 #
##############
class RationalNumber:
    def __init__(self, p, q):
        """ Represents rational number by its canonical form """
        # Add your code here #

        # self.unique_p =
        # self.unique_q =

    def __eq__(self, other):
        pass  # replace this with your code

    def is_int(self):
        pass  # replace this with your code

    def __mul__(self, other):
        pass  # replace this with your code

    def __add__(self, other):
        pass  # replace this with your code

    def divides(self, other):
        pass  # replace this with your code



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
    if subset_sum([1, 2, 4, 8, 16], 32) !=  subset_sum_efficient([1, 2, 4, 8, 16], 32):
        print("error in subset_sum_efficient")

    # 4a
    A = [[0, 1, 1, 0, 0], [1, 0, 1, 0, 0], [0, 0, 0, 1, 0], [1, 0, 0, 0, 0], [0, 0, 0, 0, 0]]
    if not legal_path(A.copy(), [0, 1, 2, 3]) or \
            not legal_path(A.copy(), [0, 1, 2, 3, 0, 1]) or \
            legal_path(A.copy(), [1, 2, 3, 4]):
        print("error in legal_path")

    # 5a
    if not can_create_once(6, [5, 2, 3]) or not can_create_once(-10, [5, 2, 3]) \
            or can_create_once(9, [5, 2, 3]) or can_create_once(7, [5, 2, 3]):
        print("error in can_create_once")

    # 5b
    if not can_create_twice(6, [5, 2, 3]) or not can_create_twice(9, [5, 2, 3]) \
            or not can_create_twice(7, [5, 2, 3]) or can_create_once(19, [5, 2, 3]):
        print("error in can_create_twice")

    # 5c
    lst = ['6', '-', '4', '*', '2', '+', '3']
    if not valid_brackets_placement(10, lst.copy()) or \
            not valid_brackets_placement(1, lst.copy()) or \
            valid_brackets_placement(5, lst.copy()):
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

