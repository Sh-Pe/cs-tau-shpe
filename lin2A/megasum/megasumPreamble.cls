%  DEFAULT PREAMBLE CLASS ~ 2025B-2026A ~ by Shahar Perets
%  intended to be used with LuaLaTeX
% 
%  Copyright (C) 2025  Shahar Perets
%  
%  This program is free software: you can redistribute it and/or modify
%  it under the terms of the GNU General Public License as published by
%  the Free Software Foundation, either version 3 of the License, or   
%  (at your option) any later version.                                 
%  
%  This program is distributed in the hope that it will be useful,     
%  but WITHOUT ANY WARRANTY; without even the implied warranty of      
%  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       
%  GNU General Public License for more details.                        
%  
%  You should have received a copy of the GNU General Public License     
%  along with this program.  If not, see https://www.gnu.org/licenses/.
%  

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{megasumPreamble}[preamble for megasums]
\LoadClassWithOptions{book}
\PassOptionsToClass{a4paper}{book}

%! ~~~ LaTeX stuff ~~~
	\RequirePackage {          % Internal Latex
		calc,
		etoolbox
	}
	
	\DeclareKeys[megasumPreamble]
	{
		name.store = \@document@name,
		name.usage = load,
	}
	\ProcessKeyOptions[megasumPreamble]


%! ~~~ Packages Setup ~~~
	\RequirePackage[usenames]{color}
	% Math packages
		\RequirePackage {          % Math
			ifluatex,
			amsmath,
			amssymb,
			amsthm,
			witharrows,
			mathtools,
			cancel,
			bm
		}
		\WithArrowsOptions{displaystyle}
		\renewcommand{\qedsymbol}{$\blacksquare$} % end proofs with \blacksquare. Overwrites the defualts. 
	
	
	% Design
		\RequirePackage {
			multicol,
			titlesec,
			hyperref,
			fancyhdr,
			tocloft
		}
		% Layout
			\RequirePackage [margin=0.9in]         {geometry}
			\RequirePackage [skip=5pt, indent=0pt] {parskip}
			\RequirePackage [normalem]             {ulem}
		% Titles
			\titleformat{\section}       [block]
				{\fontsize{15}{15}}
				{\sen \dotfill \huge \bfseries\thesection \she}
				{0em}
				{}
			\titleformat{\subsection}    [block]
				{\large\itshape}
				{\normalfont\Large\bfseries\en{{\thesubsection}} \,\,$\sim$\,\,}
				{0em}
				{}
			\titleformat{\subsubsection} [block]
				{\normalsize\bfseries}
				{\normalfont\large\bfseries\en{{(\thesubsubsection)}}}
				{0.5em}
				{}
			\setcounter{secnumdepth}{3} % enable subsubsection
			
			\patchcmd{\chapter}{\if@openright\cleardoublepage\else\clearpage\fi}{}{}{} % disable double pages
			\newcommand\npchapter[1] {\chapter[#1]{\textit{#1}}\thispagestyle{empty}\newpage}
			
		% Defaults
			\renewcommand \le{\leqslant}
			\renewcommand \ge{\geqslant}
			\renewcommand \labelitemi{$\bullet$}
		% Hyprref
			\hypersetup{
				colorlinks,
				citecolor=black,
				filecolor=black,
				linkcolor=black,
				urlcolor=blue
			}
		% Headers
			\pagestyle{fancy}
			\fancyhead[C]{\textit{\textbf{\@document@name}}}
			\fancyhead[L,R]{}
			\fancyfoot[R]{\textit{שחר פרץ, 2025}\, \en{{\Large({\thepage})}}}
			\fancyfoot[L]{\textit{\rightmark}}
			\fancyfoot[C]{}
			\renewcommand{\headrule}{\vspace{-7pt}\hfil\rule{200pt}{1pt}}
		% TOC
			\renewcommand{\cfttoctitlefont}    {\Huge\itshape\bfseries}
			\renewcommand{\cftsecleader}       {\cftdotfill{\cftsecdotsep}}
			\renewcommand{\cftsubsecleader}    {\cftdotfill{\cftsecdotsep}} % fix this
			\renewcommand{\cftsubsubsecleader} {\cftdotfill{\cftsecdotsep}}
	
	% Hebrew initialzing
		\RequirePackage [bidi=basic]  {babel}
		\RequirePackage [no-math]     {fontspec}
		\babelprovide   [main, import, Alph=letters] {hebrew}
		\babelprovide   [import] {english}
		\babelfont      [hebrew] {rm} {David CLM}
		\babelfont      [hebrew] {sf} {David CLM}
		\RequirePackage [shortlabels] {enumitem}
		\newlist {hebenum} {enumerate} {1}
	
	% Language Shortcuts
		\newcommand \en[1] {\begin{otherlanguage}{english}#1\end{otherlanguage}}
		\newcommand \sen   {\begin{otherlanguage}{english}}
		\newcommand \she   {\end{otherlanguage}}
		\newcommand \del   {$ \!\! $}
		
		\newcommand \npage {\vfil {\hfil \textbf{\textit{המשך בעמוד הבא}}} \hfil \vfil \pagebreak}
		\newcommand \ndoc  {\dotfill \\ \vfil {\begin{center}
					{\textbf{\textit{שחר פרץ, 2025}} \\
						\scriptsize \textit{קומפל ב־}\en{\LaTeX}\,\textit{ ונוצר באמצעות תוכנה חופשית בלבד}}
			\end{center}} \vfil	}
		
		\newcommand       {\rn}[1]{
			\textup{\uppercase\expandafter{\romannumeral#1}}
		}
		
		\newcommand \middleText[1] {\vfil {\hfil {#1}} \hfil \vfil \newpage}
		\newcommand \envendproof{\vspace{-17pt}}
		\newcommand \mathenvendproof{\par\vspace{-24pt}}
		
		\newcommand{\skipitems}[1]{
			\addtocounter{\@enumctr}{#1}
		}
		\newcommand\firstPage[1] {
			\thispagestyle{empty}
			\,\! % To stablize the next lines on smth
				
			{\vspace{0.5\textheight-2em} 
				{
					\begin{center}
						\textbf{\@document@name} \\ 
						\textit{שחר פרץ $\sim$ #1}
					\end{center}
				}
			}
		}
		
		
%! ~~~ Math Options ~~~

	\DeclareMathOperator      {\dom} {dom}
	\DeclareMathOperator      {\Img} {Im}
	\DeclareMathOperator    {\range} {range}
	\DeclareMathOperator      {\chr} {char}
	\DeclareMathOperator     {\diag} {diag}
	\DeclareMathOperator      {\Hom} {Hom}
	\DeclareMathOperator       {\Sp} {span} 
	\DeclareMathOperator      {\sgn} {sgn} 
	\DeclareMathOperator      {\row} {Row} 
	\DeclareMathOperator      {\adj} {adj} 
	\DeclareMathOperator       {\rk} {rank} 
	\DeclareMathOperator      {\col} {Col} 
	\DeclareMathOperator       {\tr} {tr}
	\DeclareMathOperator      {\lcm} {lcm}
	\DeclareMathOperator      {\sym} {Sym}
	\DeclareMathOperator     {\asym} {ASym}
	\DeclareMathOperator      {\rad} {rad}


%! ~~~ Theorems etc. ~~~
	\definecolor{myblue}      {rgb} {0.2,0.35,0.7}
	\definecolor{mygreen}     {rgb} {0.15,0.65,0.35}
	\definecolor{myyellow}    {rgb} {0.0,0.4,0.5}
	\definecolor{mycyan}      {rgb} {0.05,0.65,0.6}
	\definecolor{myred}       {rgb} {0.05,0.1,0.75}
	\definecolor{mymagenta}   {rgb} {0.1,0.7,0.1}
	
	\theoremstyle{definition}
	\newtheorem {Theorem}     {\color{myblue}משפט}
	\newtheorem {Definition}  {\color{mygreen}הגדרה}
	\newtheorem {Lemma}       {\color{myyellow}למה}
	\newtheorem {Remark}      {\color{mycyan}הערה}
	\newtheorem {Notion}      {\color{myred}סימון}
	\newtheorem {Collary}     {\color{mymagenta}מסקנה}
	\newtheorem {Exercise}    {\normalcolor תרגיל}
	
	\newcommand \cola  [1]    {\begin{Collary}#1\end{Collary}}
	\newcommand \theo  [1]    {\begin{Theorem}#1\end{Theorem}}
	\newcommand \defi  [1]    {\begin{Definition}#1\end{Definition}}
	\newcommand \rmark [1]    {\begin{Remark}#1\end{Remark}}
	\newcommand \lem   [1]    {\begin{Lemma}#1\end{Lemma}}
	\newcommand \noti  [1]    {\begin{Notion}#1\end{Notion}}
	\newcommand \exe   [1]    {\begin{Exercise}#1\end{Exercise}}
	\newcommand \exec  [1]    {\begin{Exercise}[נפוץ]#1\end{Exercise}}
	\newcommand \exeh  [1]    {\begin{Exercise}[קשה]#1\end{Exercise}}
	
\RequirePackage{mathShortcuts}

\title{\@document@name}
\author{שחר פרץ}