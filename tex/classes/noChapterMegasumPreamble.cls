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
\ProvidesClass{noChapterMegasumPreamble}[preamble for megasums]
\LoadClassWithOptions{article}
\PassOptionsToClass{a4paper}{article}

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
		\RequirePackage {
			ifluatex,
			amsmath,
			amssymb,
			amsthm,
			witharrows,
			mathtools,
			cancel,
			bm,
			esdiff
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
			\RequirePackage [margin=1in]           {geometry}
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
			\fancyfoot[R]{\textit{שחר פרץ, 2026}\, \en{{\Large({\thepage})}}}
			\fancyfoot[L]{\textit{\rightmark}}
			\fancyfoot[C]{}
			\renewcommand{\headrule}{\vspace{-7pt}\hfil\rule{150pt}{1pt}}
		% TOC
			\renewcommand{\cfttoctitlefont}    {\Huge\itshape\bfseries}
			\renewcommand{\cftsecleader}       {\cftdotfill{\cftsecdotsep}}
			\renewcommand{\cftsubsecleader}    {\cftdotfill{\cftsecdotsep}} % fix this
			\renewcommand{\cftsubsubsecleader} {\cftdotfill{\cftsecdotsep}}
		% Theorems
			\RequirePackage{xcolor}
			\definecolor{myblue}      {rgb} {0.2,0.35,0.7}
			\definecolor{mygreen}     {rgb} {0.15,0.65,0.35}
			\definecolor{myyellow}    {rgb} {0.0,0.4,0.5}
			\definecolor{mycyan}      {rgb} {0.05,0.65,0.6}
			\definecolor{myred}       {rgb} {0.05,0.1,0.75}
			\definecolor{mymagenta}   {rgb} {0.1,0.7,0.1}

			\newtheoremstyle{mystyle}% name of the style to be used
				{\parskip}
				{}
				{}
				{0pt}
				{\bfseries}
				{.}
				{ }
				{\thmname{#1}\thmnumber{ #2}\textnormal{\thmnote{ (#3)}}}

			\theoremstyle{mystyle}
			\newtheorem {Theorem}                  {\color{myblue}משפט}     [subsection]
			\newtheorem {Claim}       [Theorem]    {\color{myblue}טענה}
			\newtheorem {Lemma}       [Theorem]    {\color{myyellow}למה}
			\newtheorem {Definition}               {\color{mygreen}הגדרה}   [subsection]
			\newtheorem {Notion}      [Definition] {\color{myred}סימון}
			\newtheorem {Collary}                  {\color{mymagenta}מסקנה} [subsection]
			\newtheorem {Exercise}                 {\normalcolor תרגיל}     [subsection]

			\newtheoremstyle{myremark}% name of the style to be used
				{\parskip}
				{}
				{}
				{0pt}
				{\itshape\bfseries}
				{.}
				{ }
				{\thmname{#1}\thmnumber{ #2}\textnormal{\thmnote{ (#3)}}}
			\theoremstyle{myremark}
			\newtheorem {Remark}      {\color{mycyan}הערה}

			\newcommand \cola  [1]    {\begin{Collary}#1\end{Collary}}
			\newcommand \theo  [1]    {\begin{Theorem}#1\end{Theorem}}
			\newcommand \clm   [1]    {\begin{Claim}#1\end{Claim}}
			\newcommand \defi  [1]    {\begin{Definition}#1\end{Definition}}
			\newcommand \rmark [1]    {\begin{Remark}#1\end{Remark}}
			\newcommand \lem   [1]    {\begin{Lemma}#1\end{Lemma}}
			\newcommand \noti  [1]    {\begin{Notion}#1\end{Notion}}
			\newcommand \exe   [1]    {\begin{Exercise}#1\end{Exercise}}
			\newcommand \exec  [1]    {\begin{Exercise}[נפוץ]#1\end{Exercise}}
			\newcommand \exeh  [1]    {\begin{Exercise}[קשה]#1\end{Exercise}}

\title{\@document@name}
\author{שחר פרץ}
