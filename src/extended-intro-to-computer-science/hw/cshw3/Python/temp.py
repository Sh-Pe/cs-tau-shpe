def find_percentile_almost_k_rec(
    lst: list[int], k: int, m: int, start: int, end: int, depth: int = 0
) -> int:
    n = end - start + 1
    length = len(lst)
    middleIndex = n // 2 + start
    middleInterval = (max(middleIndex - k, start), min(middleIndex + k, end))
    beforeMiddle = middleInterval[0] - start
    afterMiddle = length - middleInterval[1] - 1

    if depth >= 30: 
        print("ERR0")
        return

    if beforeMiddle <= m <= beforeMiddle + middleInterval[1] - middleInterval[0]:
        delta = m - beforeMiddle
        interval = lst[middleInterval[0]:middleInterval[1] + 1]
        searchZone = list(enumerate(interval))
        searchZone.sort(key = lambda x: x[1])
        print("  "*depth, f"{middleInterval=}, {middleIndex=}, {beforeMiddle=}, {afterMiddle=}, {delta=}, {n=}, {m=}, {end=}, {start=}")
        print("  "*depth, f"sorted using {searchZone}")
        print("  "*depth,"out", searchZone[delta][0], "+", beforeMiddle)
        return searchZone[delta][0] + beforeMiddle
    
    elif beforeMiddle > m:
        print("++"*depth, f"{middleInterval=}, {middleIndex=}, {beforeMiddle=}, {afterMiddle=}, {end=}, {m=}, {start=}")
        return find_percentile_almost_k_rec(lst, k, m, start, min(end - middleInterval[0] - 1, length - 1), depth + 1)
    elif beforeMiddle + middleInterval[1] - middleInterval[0] < m:
        print("--"*depth, f"{middleInterval=}, {middleIndex=}, {beforeMiddle=}, {afterMiddle=}, {end=}, {m=}, {start=}")
        delta = middleInterval[1] - start
        return find_percentile_almost_k_rec(lst, k, m - delta, max(start + middleInterval[1] + 1, 0), end, depth + 1) + delta

    return 0 # just because mypy wants me to do that


def find_percentile_almost_k(lst: list[int], k: int, m: int) -> int:
    print(f"call for {lst=}, {k=}, {m=}")
    output = lst[find_percentile_almost_k_rec(lst, k, m, start=0, end=len(lst) - 1)]
    print(f"outputed {output}")
    return output

almost_sorted_lst_3 = [10, 7, 7, 1]
if find_percentile_almost_k(almost_sorted_lst_3, 3, 1) != 1:
    print("1. error in find_percentile_almost_k")
if find_percentile_almost_k(almost_sorted_lst_3, 3, 2) != 7:
    print("2. error in find_percentile_almost_k")
if find_percentile_almost_k(almost_sorted_lst_3, 3, 3) != 7:
    print("3. error in find_percentile_almost_k")

almost_sorted_lst_2 = [2, 3, 1, 5, 4, 7, 6, 8, 9, 10, 12, 11, 13]
for i in range(0, 12):
    print(find_percentile_almost_k([2, 3, 1, 5, 4, 7, 6, 8, 9, 10, 12, 11, 13], 3, i))