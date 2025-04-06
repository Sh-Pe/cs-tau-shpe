from fractions import Fraction
from typing import TypeVar, Any

import numpy
import numpy as np

Matrix = TypeVar("Matrix", bound=np.ndarray)
MatrixOperation = TypeVar("MatrixOperation", bound=str)

p: int = 0


def opening():
    # Use a breakpoint in the code line below to debug your script.
    print("pls make sure you have the following in your LaTeX preamble:")
    latex_preamble: str = r"""
\makeatletter
\newlength\min@xx
\newcommand*\xxrightarrow[1]{\begingroup
	\settowidth\min@xx{$\m@th\scriptstyle#1$}
	\@xxrightarrow}
\newcommand*\@xxrightarrow[2][]{
	\sbox8{$\m@th\scriptstyle#1$}  % subscript
	\ifdim\wd8>\min@xx \min@xx=\wd8 \fi
	\sbox8{$\m@th\scriptstyle#2$} % superscript
	\ifdim\wd8>\min@xx \min@xx=\wd8 \fi
	\xrightarrow[{\mathmakebox[\min@xx]{\scriptstyle#1}}]
	{\mathmakebox[\min@xx]{\scriptstyle#2}}
	\endgroup}
\makeatother
\newcommand\tmat[2]   {\cl{\begin{matrix}
			#1
		\end{matrix}\, \middle\vert\, \begin{matrix}
			#2
\end{matrix}}}

\makeatletter
\newcommand\rrr[1]    {\xxrightarrow{1}{#1}}
\newcommand\rrt[2]    {\xxrightarrow{1}[#2]{#1}}
\newcommand\mat[2]    {M_{#1\times#2}}
\newcommand\gmat      {\mat{m}{n}(\F)}
\newcommand\tomat     {\, \dequad \longrightarrow}
\newcommand\pms[1]    {\begin{pmatrix}
		#1
\end{pmatrix}}
\newcommand\op        {^{-1}}"""
    print(latex_preamble)


def gaussian_elimination(mat: Matrix) -> list[tuple[Matrix, list[MatrixOperation]]]:
    output: list[tuple[Matrix, list[MatrixOperation]]] = [(mat, [])]
    for i in range(0, min(mat.shape[0], mat.shape[1])):
        # first row being e_i
        new_mat = output[-1][0].copy()
        decrease = new_mat[i][i]
        operation = f"R_{i} -> R_{i} \\cdot {parse_num(decrease, op=True)}"
        if decrease == 0: continue
        new_mat[i] *= oppose(decrease)
        output.append((new_mat, [operation]))

        # eliminating the other rows
        new_mat = output[-1][0].copy()
        operations: list[MatrixOperation] = []
        # no rows to eliminate
        if i + 1 > mat.shape[0]: continue

        for j in range(i + 1, mat.shape[0]):
            decrease = new_mat[j][i]
            if decrease == 0: continue
            new_mat[j] -= new_mat[i] * decrease
            operations.append(f"R_{j} -> R_{j} - {parse_num(decrease)} \\cdot R_{i}")
        output.append((new_mat, operations))

    for i in range(min(mat.shape[0], mat.shape[1]) - 1, -1, -1):
        # preform reduction on the upper lines
        new_mat = output[-1][0].copy()
        operations: list[MatrixOperation] = []

        if i - 1 < 0: continue
        for j in range(i - 1, -1, -1):
            if new_mat[i][i] == 0: continue
            decrease = new_mat[j][i]
            if decrease == 0: continue
            operations.append(f"R_{j} -> R_{j} - {parse_num(decrease)} \\cdot R_{i}")
            new_mat[j] -= new_mat[i] * decrease
        output.append((new_mat, operations))

    return output


def mat_to_latex(mat: Matrix) -> str:
    output: str = "\\pms{"
    for row in mat:
        for col in row:
            output += parse_num(col) + " & "
        output = output[:-2]
        output += "\\\\ \n"
    output += "}"
    return output


def elimination_to_latex(elimination: list[tuple[Matrix, list[MatrixOperation]]]) -> str:
    output: str = r"\begin{align*}\tomat"
    first = True;
    for mat, operations in elimination:
        if operations:
            for operations_chunk in np.array_split(operations, max(len(operations) // 2, 1)):
                if len(operations_chunk) % 2 == 1:
                    output += fr"\rrr{{{operations_chunk[0]}}}"
                    operations_chunk = operations_chunk[1:]
                if len(operations_chunk) == 0:
                    continue
                output += fr"\rrt{{{operations_chunk[0]}}}{{{operations_chunk[1]}}}"
        else:
            if first:
                first = False
            else:
                continue
        output += "&" + mat_to_latex(mat) + r" \\"
    return output + "\end{align*}"


def parse_num(num: float | int | numpy.float64 | numpy.float32 | Fraction, op=False) -> str:
    tol = 0.0001
    if -tol <= num <= tol:
        return "0"
    if -tol <= num % 1 <= tol:
        strnum = str(int(num))
        return strnum if not op else f"\\frac{{1}}{{{strnum}}}"
    if isinstance(num, Fraction):
        num = pow(num, -1)
        return f"\\frac{{{num.numerator}}}{{{num.denominator}}}" if not op else f"\\frac{{{num.denominator}}}{{{num.numerator}}}"
    else:
        return str(num)


def main():
    opening()
    mat: str = input("enter a matrix in a python-like format: ")
    print(elimination_to_latex(gaussian_elimination(numpy.array(eval(mat), dtype=numpy.int64) + Fraction())))


def field_handler(num) -> Any:
    if p == 0:
        return num
    else:
        return num % p


def oppose(num) -> Any:
    if p == 0:
        return pow(num, -1)
    else:
        for op in range(p):
            if op * num % p == 0:
                return op


if __name__ == '__main__':
    main()
