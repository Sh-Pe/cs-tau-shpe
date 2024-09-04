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
def divisors(n):
    pass  # TODO replace with your code


#  Q1b
def perfect_numbers(c):
    pass  # TODO replace with your code


#  Q1c
def abundant_density(n):
    pass  # TODO replace with your code


#  Q1e
def semi_perfect_4(n):
    pass  # TODO replace with your code


#  Q1f
def find_gcd(a, b):
    pass  # TODO replace with your code


##############
# QUESTION 2 #
##############
# Q2a
def coin():
    pass  # TODO replace with your code


# Q2b
def sample(v, p):
    pass  # TODO replace with your code


# Q2c
def monty_hall(switch, times):
    pass  # TODO replace with your code


# Q2d
def sample_anagram(st):
    pass  # TODO replace with your code


##############
# QUESTION 3 #
##############
# Q3a
def inc(binary):
    pass  # TODO replace with your code


# Q3b
def add(bin1, bin2):
    pass  # TODO replace with your code


# Q3c
def mod_two(binary, power):
    pass  # TODO replace with your code


# Q3d
def max_bin(lst):
    pass  # TODO replace with your code


##############
# QUESTION 4 #
##############
# Q4a
def assess_office_hour(office_hour, student_schedules_dict):
    pass  # TODO replace with your code


# Q4b1
def merge_intervals(intervals):
    pass  # TODO replace with your code


# Q4b2
def find_perfect_slots(student_schedules_dict):
    pass  # TODO replace with your code


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
    if inc("0") != "1" or \
            inc("1") != "10" or \
            inc("101") != "110" or \
            inc("111") != "1000" or \
            inc(inc("111")) != "1001":
        print("Error in Q3a")

    if add("0", "1") != "1" or \
            add("1", "1") != "10" or \
            add("110", "11") != "1001" or \
            add("111", "111") != "1110":
        print("Error in Q3b")

    if mod_two("110", 2) != "10" or \
            mod_two("101", 2) != "1" or \
            mod_two("111", 4) != "111":
        print("Error in Q3c")

    if max_bin(["1010", "1011"]) != "1011":
        print("Error in Q3d - 1")

    if max_bin(["10", "0", "1"]) != "10":
        print("Error in Q3d - 2")

    # Testing Question 4:
    office_hour = (11, 12)
    student_schedules_dict = {
        'noam': [(8, 10), (10, 12), (15, 18)],
        'larry': [(10, 11), (14, 16)],
        'jeff': [(10, 11), (14, 16)],
    }
    if set(assess_office_hour(office_hour, student_schedules_dict)[0]) != set(['larry', 'jeff']):
        print("Error in Q4a - 1")
    if assess_office_hour(office_hour, student_schedules_dict)[1] != (2 / 3):
        print("Error in Q4a - 2")

    if merge_intervals([(-2, 43), (-700, -9), (20, 52), (52, 60)]) != [(-700, -9), (-2, 60)]:
        print("Error in Q4b1 - 1")
    if merge_intervals([(-2, 43), (-700, -9)]) != [(-700, -9), (-2, 43)]:
        print("Error in Q4b1 - 2")
    if merge_intervals([(8, 10), (10, 12), (10, 11), (15, 18), (14, 16)]) != [(8, 12), (14, 18)]:
        print("Error in Q4b1 - 3")

    if find_perfect_slots(student_schedules_dict) != [(7, 8), (12, 13), (13, 14), (18, 19), (19, 20)]:
        print("Error Q4b2 - 1")
