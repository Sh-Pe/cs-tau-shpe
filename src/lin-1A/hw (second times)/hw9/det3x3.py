import numpy as np
from fractions import Fraction
mat = input("enter a 3x3 matrix here: ")
A = np.array(eval(mat), dtype=np.int64)
A = A + Fraction()
output: str = f"{A[0][0]}*{A[1][1]}*{A[2][2]} + {A[0][1]}*{A[1][2]}*{A[2][0]} + {A[0][2]}*{A[1][0]}*{A[2][1]} - {A[0][0]}*{A[1][2]}*{A[2][1]} - {A[0][1]}*{A[1][0]}*{A[2][2]} - {A[0][2]}*{A[1][1]}*{A[2][0]}"

def mat_to_latex(mat) -> str:
    output: str = "\\detms{"
    for row in mat:
        for col in row:
            output += parse_num(col, minus_brace=False) + " & "
        output = output[:-2]
        output += "\\\\ \n"
    output += "}"
    return output

def parse_num(num: float | int | np.float64 | np.float32 | Fraction, op=False, minus_brace=True) -> str:
    tol = 0.0001
    if -tol <= num <= tol:
        return "0"
    if -tol <= num % 1 <= tol:
        strnum = str(int(num))
        return (f"({strnum})" if (int(num) <= 0 and minus_brace) else strnum) if not op else f"\\frac{{1}}{{{strnum}}}"
    if isinstance(num, Fraction):
        num = pow(num, -1)
        return f"\\frac{{{num.numerator}}}{{{num.denominator}}}" if op else f"\\frac{{{num.denominator}}}{{{num.numerator}}}"
    else:
        return str(num)

print(f"{mat_to_latex(A)}= {output.replace("*", " \\cdot ")} = {str(eval(output))}")
