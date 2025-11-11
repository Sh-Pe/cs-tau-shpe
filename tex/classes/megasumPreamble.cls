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

\title{\@document@name}
\author{שחר פרץ}
