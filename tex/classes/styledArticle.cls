\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{styledArticle}[normal styled article with math support]
\LoadClassWithOptions{article}
\PassOptionsToClass{a4paper}{article}

% Math packages
	\RequirePackage[usenames]{color}
	\RequirePackage{xcolor}
	\RequirePackage{forest}
	\RequirePackage{ifxetex,ifluatex,amssymb,amsmath,mathrsfs,amsthm,witharrows,mathtools,mathdots,centernot}
	\RequirePackage{amsmath}
	\WithArrowsOptions{displaystyle}
	\renewcommand{\qedsymbol}{$\blacksquare$} % end proofs with \blacksquare. Overwrites the defualts. 
	\RequirePackage{cancel,bm}


% Design
	\RequirePackage[labelfont=bf]{caption}
	\RequirePackage[margin=0.6in]{geometry}
	\RequirePackage{multicol}
	\RequirePackage[skip=4pt, indent=0pt]{parskip}
	\RequirePackage[normalem]{ulem}
	\forestset{default}
	\renewcommand\labelitemi{$\bullet$}
	\usepackage{graphicx}
	\graphicspath{ {./} }
	
	\RequirePackage[colorlinks]{hyperref}
	\definecolor{mgreen}{RGB}{25, 160, 50}
	\definecolor{mblue}{RGB}{30, 60, 200}
	\RequirePackage{hyperref}
	\hypersetup{
		colorlinks=true,
		citecolor=mgreen,
		linkcolor=black,
		urlcolor=mblue,
		pdftitle={Document by Shahar Perets},
		%	pdfpagemode=FullScreen,
	}
	\usepackage{yfonts}
	\def\gothstart#1{\noindent\smash{\lower3ex\hbox{\llap{\Huge\gothfamily#1}}}
		\parshape=3 3.1em \dimexpr\hsize-3.4em 3.4em \dimexpr\hsize-3.4em 0pt \hsize}
	\def\frakstart#1{\noindent\smash{\lower3ex\hbox{\llap{\Huge\frakfamily#1}}}
		\parshape=3 1.5em \dimexpr\hsize-1.5em 2em \dimexpr\hsize-2em 0pt \hsize}