# Skeleton file for HW5 - Winter 2024 - extended intro to CS

# Add your implementation to this file

# You may add other utility functions to this file,
# but you may NOT change the signature of the existing ones.

# Change the name of the file to include your ID number (hw5_ID.py).
import math
import random


#####################################
# Linked List   (code from lecture) #
#####################################


class Node:
    def __init__(self, val):
        self.value = val
        self.next = None

    def __repr__(self):
        return str(self.value)


class Linked_list:
    def __init__(self, seq=None):
        self.head = None
        self.size = 0
        if seq != None:
            for x in seq[::-1]:
                self.add_at_start(x)

    def __repr__(self):
        out = ""
        p = self.head
        while p != None:
            out += p.__repr__() + ", "
            p = p.next
        return "[" + out[:-2] + "]"  # discard the extra ", " at the end

    def add_at_start(self, val):
        """add node with value val at the list head"""
        tmp = self.head
        self.head = Node(val)
        self.head.next = tmp
        self.size += 1

    def __len__(self):
        """called when using Python's len()"""
        return self.size

    def index(self, val):
        """find index of (first) node with value val in list
        return None of not found"""
        p = self.head
        i = 0  # we want to return the location
        while p != None:
            if p.value == val:
                return i
            else:
                p = p.next
                i += 1
        return None  # in case val not found

    def __getitem__(self, i):
        """called when reading L[i]
        return value of node at index 0<=i<len"""
        assert 0 <= i < len(self)
        p = self.head
        for j in range(0, i):
            p = p.next
        return p.value

    def __setitem__(self, i, val):
        """called when using L[i]=val (indexing for writing)
        assigns val to node at index 0<=i<len"""
        assert 0 <= i < len(self)
        p = self.head
        for j in range(0, i):
            p = p.next
        p.value = val
        return None

    def insert(self, i, val):
        """add new node with value val before index 0<=i<=len"""
        assert 0 <= i <= len(self)
        if i == 0:
            self.add_at_start(val)  # makes changes to self.head
        else:
            p = self.head
            for j in range(0, i - 1):  # get to position i-1
                p = p.next
            # now add new element
            tmp = p.next
            p.next = Node(val)
            p.next.next = tmp
            self.size += 1

    def append(self, val):
        self.insert(self.size, val)

    def pop(self, i):
        """delete element at index 0<=i<len"""
        assert 0 <= i < len(self)
        if i == 0:
            self.head = self.head.next  # bypass first element
        else:  # i >= 1
            p = self.head
            for j in range(0, i - 1):
                p = p.next

            # now p is the element BEFORE index i
            p.next = p.next.next  # bypass element at index i

        self.size -= 1


##############################################
# Binary Search Tree     (code from lecture) #
##############################################


class TreeNode:
    def __init__(self, key, val=None):
        self.key = key
        self.val = val
        self.left = None
        self.right = None

    def __repr__(self):
        return "(" + str(self.key) + ":" + str(self.val) + ")"


class BinarySearchTree:
    def __init__(self, root=None, size=0):
        self.root = root
        self.size = size

    def __repr__(
        self,
    ):  # you don't need to understand the implementation of this method
        def printree(root):
            if not root:
                return ["#"]

            root_key = str(root.key)
            left, right = printree(root.left), printree(root.right)

            lwid = len(left[-1])
            rwid = len(right[-1])
            rootwid = len(root_key)

            result = [(lwid + 1) * " " + root_key + (rwid + 1) * " "]

            ls = len(left[0].rstrip())
            rs = len(right[0]) - len(right[0].lstrip())
            result.append(
                ls * " "
                + (lwid - ls) * "_"
                + "/"
                + rootwid * " "
                + "\\"
                + rs * "_"
                + (rwid - rs) * " "
            )

            for i in range(max(len(left), len(right))):
                row = ""
                if i < len(left):
                    row += left[i]
                else:
                    row += lwid * " "

                row += (rootwid + 2) * " "

                if i < len(right):
                    row += right[i]
                else:
                    row += rwid * " "

                result.append(row)

            return result

        return "\n".join(printree(self.root))

    def lookup(self, key):
        """return value of node with key if exists, else None"""
        node = self.root
        while node != None:
            if key == node.key:
                return node.val  # found!
            elif key < node.key:
                node = node.left
            else:
                node = node.right
        return None

    def insert(self, key, val):
        """insert node with key,val into tree.
        if key already there, just update its value"""

        parent = None  # this will be the parent of the new node
        node = self.root

        while node != None:  # keep descending the tree
            if key == node.key:
                node.val = val  # update the val for this key
                return

            parent = node
            if key < node.key:
                node = node.left
            else:
                node = node.right

        if parent == None:  # was empty tree, need to update root
            self.root = TreeNode(key, val)
        elif key < parent.key:
            parent.left = TreeNode(key, val)  # "hang" new node as left child
        else:
            parent.right = TreeNode(key, val)  # "hang"    ...     right child

        self.size += 1
        return None

    def minimum(self):
        """return value of node with minimal key"""

        if self.root == None:
            return None  # empty tree has no minimum
        node = self.root
        while node.left != None:
            node = node.left
        return node.val

    def depth(self):
        """return depth of tree, uses recursion"""

        def depth_rec(node):
            if node == None:
                return -1
            else:
                return 1 + max(depth_rec(node.left), depth_rec(node.right))

        return depth_rec(self.root)


##############
# QUESTION 1 #
##############
class LLLNode:
    def __init__(self, val):
        self.next_list = []
        self.val = val

    def __repr__(self):
        st = "Value: " + str(self.val) + "\n"
        st += "Neighbors:" + "\n"
        for p in self.next_list:
            st += " - Node with value: " + str(p.val) + "\n"
        return st[:-1]


class LogarithmicLinkedList:
    def __init__(self):
        self.head = None
        self.len = 0

    def __len__(self):
        return self.len

    def add_at_start(self, val) -> None:
        node: LLLNode = LLLNode(val)
        if len(self) == 0:
            self.head = node
            self.len = 1
            return None

        count = 0
        node.next_list.append(self.head)
        cur = lambda: node.next_list[-1]

        while True:
            try:
                node.next_list.append(cur().next_list[count])
            except:
                break
            count += 1

        self.head = node
        self.len += 1
        return None

    add = add_at_start
    __add__ = add_at_start

    def __getitem__(self, i):
        path = bin(i)
        cur = self.head

        for i in range(1, len(path) - 1):
            if path[-i] != "0":
                cur = cur.next_list[i - 1]

        return cur.val

    # Optional - improve this code!
    # TODO
    def __contains__(self, val):
        p = self.head
        k = 1
        while k != 0:
            if p.val == val:
                return True
            k = 0
            m = len(p.next_list)
            while k < m and p.next_list[k].val <= val:
                k += 1
            if k > 0:
                p = p.next_list[k - 1]
        return False


##############
# QUESTION 2 #
##############


def gen1():
    sum = 0

    while True:
        for i in range(sum + 1):
            if i != 0:
                yield i, sum - i
                if sum - i != 0:
                    yield i, -sum + i
            if sum - i != 0:
                yield -i, sum - i
            yield -i, -sum + i

        sum += 1


def gen2(g):
    partial_sum = 0
    gen = g

    while True:
        partial_sum += next(gen)
        yield partial_sum


def gen3(g):
    pass  # replace this with your code (or don't, if there does not exist such generator with finite delay)


# TODO: test
def gen4(g):
    ans = True
    sign = 0

    while sign == 0:
        cur = next(g)
        yield ans
        forward = next(g)
        if cur > forward:
            sign = -1
            prev = forward
        if cur < forward:
            sign = 1
            prev = forward
        yield ans

    while True:
        cur = next(g)
        if sign == 1:
            if cur < prev:
                ans = False
        if sign == -1:
            if cur > prev:
                ans = False
        prev = cur
        yield ans


def gen5(g1, g2):
    pass  # replace this with your code (or don't, if there does not exist such generator with finite delay)


def gen6(g1, g2):
    pass  # replace this with your code (or don't, if there does not exist such generator with finite delay)


def gen7():
    def gen_mod_i(i: int):
        x = 1
        while True:
            if x % i == 0:
                return x

    i == 1
    while True:
        yield gen_mod_i(1)
        i += 1


##############
# QUESTION 3 #
##############


def lowest_common_ancestor_rec(
    tree: BinarySearchTree, node: TreeNode, n1: TreeNode, n2: TreeNode, iter=0
) -> TreeNode:
    # print("  "*iter, f"{iter=} {node=}")
    if n1 == n2:
        return n1
    elif n1 > node.val and n2 > node.val:
        node = node.right
    elif n1 < node.val and n2 < node.val:
        node = node.left
    else:
        return node

    return lowest_common_ancestor_rec(tree, node, n1, n2, iter=iter + 1)


def lowest_common_ancestor(t: BinarySearchTree, n1: TreeNode, n2: TreeNode) -> TreeNode:
    return lowest_common_ancestor_rec(t, t.root, n1, n2)


def build_balanced(n):
    tree = BinarySearchTree()
    for i in range(n):
        for j in range(2 ** (n - i - 1), 2**n, 2 ** (n - i)):
            tree.insert(j, j)

    return tree


def subtree_sum(t: BinarySearchTree, k):
    return subtree_sum_rec(t.root, k, add=False)


def subtree_sum_rec(node: TreeNode | None, k, add: bool = False) -> int:
    if node is None:
        return 0

    if node.key == k:
        add = True

    left = subtree_sum_rec(node.left, k, add)
    right = subtree_sum_rec(node.right, k, add)

    result = left + right
    if add:
        result += node.key

    return result


##############
# QUESTION 5 #
##############
def prefix_suffix_overlap(lst: list[str], k: int) -> list[tuple[int, int]]:
    n = len(lst)
    output: set[tuple[int, int]] = set()

    for i in range(n):
        lsiSuffix = lst[i][:k]
        for j in range(n):
            if i == j:
                continue
            lsj_prefix = lst[j][-k:]
            if lsiSuffix == lsj_prefix:
                output.add((i, j))

    return list(output)


class Dict:
    def __init__(self, m, hash_func=hash):
        """initial hash table, m empty entries"""
        self.table = [[] for i in range(m)]
        self.hash_mod = lambda x: hash_func(x) % m

    def __repr__(self):
        L = [self.table[i] for i in range(len(self.table))]
        return "".join(
            [str(i) + " " + str(L[i]) + "\n" for i in range(len(self.table))]
        )

    def insert(self, key, value):
        """insert key,value into table
        Allow repetitions of keys"""
        i = self.hash_mod(key)  # hash on key only
        item = [key, value]  # pack into one item
        self.table[i].append(item)

    def find(self, key) -> list[int]:
        """returns ALL values of key as a list, empty list if none"""
        hashval: int = self.hash_mod(key)
        inhash: list[list[int]] = self.table[hashval]
        result: list = [l[1] for l in inhash if l[0] == key]

        return result


def prefix_suffix_overlap_hash1(lst: list[str], k: int) -> list[tuple[int, int]]:
    n = len(lst)
    prefixes = Dict(n)
    output: list[tuple[int, int]] = []

    for i in range(n):
        prefix = lst[i][:k]
        prefixes.insert(prefix, i)

    for i in range(n):
        suffix = lst[i][-k:]
        for prefixInd in prefixes.find(suffix):
            if prefixInd != i:
                output.append((prefixInd, i))

    return output


##############
# QUESTION 6 #
##############


def int_size(N):
    return math.floor(math.log(N, 2)) + 1


def maxmatch(T, p, W=2**12 - 1, L=2**5 - 1):
    assert isinstance(T, str)
    n = len(T)
    m = 0
    k = 0
    for offset in range(1, 1 + min(p, W)):
        match_len = 0
        j = p - offset
        while match_len < min(n - p, L) and T[j + match_len] == T[p + match_len]:
            match_len += 1
        if match_len > k:
            k = match_len
            m = offset
    return m, k


# Modify this code #
def LZW_compress_v2(text, c, W=2**12 - 1, L=2**5 - 1):
    intermediate = []
    n = len(text)
    p = 0
    rep_len = int_size(W) + int_size(L) + 1
    while p < n:
        m, k = maxmatch(text, p, W, L)
        length = 0
        for i in range(p - m, min(n, p - m + k)):
            length += len(c[text[i]]) + 1

        if length <= rep_len:
            intermediate.append(text[p])
            p += 1
        else:
            intermediate.append([m, k])
            p += k

    return intermediate


# Modify this code #
def inter_to_bin_v2(intermediate, c, W=2**12 - 1, L=2**5 - 1):
    W_width = int_size(W)
    L_width = int_size(L)
    bits = []

    for elem in intermediate:
        if isinstance(elem, str):
            bits.append("0")
            bits.append(c[elem])
        else:
            bits.append("1")
            m, k = elem
            bits.append((bin(m)[2:]).zfill(W_width))
            bits.append((bin(k)[2:]).zfill(L_width))

    return "".join(ch for ch in bits)


# This does not require any changes #
def LZW_decompress(intermediate):
    text_lst = []
    for i in range(len(intermediate)):
        if type(intermediate[i]) == str:
            text_lst.append(intermediate[i])
        else:
            m, k = intermediate[i]
            for j in range(k):
                text_lst.append(text_lst[-m])
    return "".join(text_lst)


##############
# TESTS      #
##############
def test():
    # Q1
    lst = LogarithmicLinkedList()
    lst.add_at_start(1)
    lst.add_at_start("hello")
    lst.add_at_start(True)
    if lst[0] != True or len(lst) != 3:
        print("1 - error in LogarithmicLinkedList")

    # Q4
    t = build_balanced(4)

    if t.size != 15 or t.depth() != 3:
        print("4 - error in build_balanced")

    if (
        lowest_common_ancestor(t, 4, 7).key != 4
        or lowest_common_ancestor(t, 2, 12).key != 8
    ):
        print("4 - error in lowest_common_ancestor or build_balanced")

    if subtree_sum(t, 6) != 18 or subtree_sum(t, 12) != 84:
        print("4 - error in subtree_sum")
    # Q5
    lst = ["abcd", "cdab", "aaaa", "bbbb", "abff"]
    k = 2
    if sorted(prefix_suffix_overlap(lst, k)) != sorted([(0, 1), (1, 0), (4, 1)]):
        print("5 - error in prefix_suffix_overlap")

    d = Dict(3)
    d.insert("a", 56)
    d.insert("a", 34)
    if sorted(d.find("a")) != sorted([56, 34]) or d.find("b") != []:
        print("5 - error in Dict.find")

    lst = ["abcd", "cdab", "aaaa", "bbbb", "abff"]
    k = 2
    if sorted(prefix_suffix_overlap_hash1(lst, k)) != sorted([(0, 1), (1, 0), (4, 1)]):
        print("5 - error in prefix_suffix_overlap_hash1")

    # Q6
    c = {"a": "0", "b": "10", "c": "110", "d": "1110", "e": "1111"}
    W = 2**5 - 1
    L = 2**3 - 1
    if LZW_compress_v2("abcdeabccde", c, W, L) != [
        "a",
        "b",
        "c",
        "d",
        "e",
        "a",
        "b",
        "c",
        [6, 3],
    ] or LZW_compress_v2("ededaaaaa", c, W, L) != [
        "e",
        "d",
        [2, 2],
        "a",
        "a",
        "a",
        "a",
        "a",
    ]:
        print("Error in Q3b - LZW_compress_v2")
    if inter_to_bin_v2(["e", "d", [2, 2]], c, W, L) != "0111101110100010010":
        print("Error in Q3b - inter_to_bin_v2")


test()