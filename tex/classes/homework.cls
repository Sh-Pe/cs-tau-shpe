\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{homework}[styled homework page]
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
	\RequirePackage{titlesec}
	\titleformat{\section}[block]
		{\huge}
		{\sen \dotfill (\textbf{\thesection})\dotfill\she}
		{0em}
		{}

	\author{שחר פרץ}
