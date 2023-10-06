This is the start of a simple compiler which you should modify to
compile the MiniJava language.  The starter code is meant primarily to
show how the toolchain works and how the various parts of the compiler
project plug together.

To demonstrate the basics of scanning and parsing, as well as to
provide some basic structure, we're providing a "compiler" that will
scan and parse "programs" in the following tiny toy programming language:

	program ::= statement | program statement
	statement ::= assignStmt | displayStmt
	assignStmt ::= id = expr ;
	displayStmt ::= display expr ;
	expr ::= id | expr + expr | ( expr )
	id ::= [a-zA-Z][a-zA-Z0-9_]*

(Note: this is only meant for demonstrating the tools. The source files
will need changes and additions, and some things will need to be
deleted, to update the files for the actual MiniJava project.  Once that
is done, the original demonstration programs may well no longer work
because of changes needed to support MiniJava that are not compatible
with the original demo language.)

The AST classes provided are closely based on those on the MiniJava
website, slightly modified to add tracking of line numbers, as well as
adding a separate Display node only used in the toy language, but not
for MiniJava.  The AST classes have also been updated to use Lists with
type parameters instead of the original Vector class used in the code on
the MiniJava website.

All of the compiler source code is in the src directory.

The ScannerDemo and ParserDemo classes are examples of how to use the
scanner and parser.  You will need to create an actual MiniJava class
with the main program for your project, but the test code here should
provide some useful hints.  The rest of the compiler is stored in
several subdirectories:

    Scanner: the implementation of the demo scanner

    Parser: the implementation of the demo parser

    AST: the implementation of the abstract syntax tree

    runtime: interface between compiled code and C environment

	test: demo test cases and supporting files for the compiler

The lib directory stores the jar files for CUP and JFlex that are
needed to build and run the compiler, and a couple of source files from
CUP that may be useful for reference.

SamplePrograms contains a sample program in the example language, which
you can replace with MiniJava programs to be be compiled and run to test
your MiniJava compiler as you develop it.  The SampleMiniJavaPrograms
directory contains larger MiniJava programs for testing, mostly taken
from the MiniJava web site.

You will need to have ant installed locally to build the compiler and
run the demo programs.  If you do not have ant installed, we strongly
suggest you install it using the package at https://ant.apache.org.
This applies particularly to MacOS users, where you should avoid using
the Homebrew package manager.  Although Homebrew does include ant,
it also tends to install its own copy of openjdk with a different
version than the one already installed on the system.  That can create
problems if it is a newer version and creates class files that cannot
be run with the regular version of Java.

The build.xml ant file supports building, running, and testing the demo
compiler scanning and parsing examples.  Look at it for details and use
it as a start for your own project's build sequence.  You can find more
detailed documentation about testing your code in test/README.txt.

In addition to the starter code here, a more extensive test framework
is available on the project main web page.  You are not required to use
this framework, but it has proven useful to groups in the past as a way
of organizing testing for the different phases of the project.  Even if
your group does not adopt it, you should take a careful look at it before
deciding whether or not to use it.

Sources: AST classes and SampleMiniJavaPrograms from the Appel /
Palsberg MiniJava project.  Some code and ideas borrowed from an earlier
UW version by Craig Chambers with modifications by Jonathan Beall and
Hal Perkins.  Updates to include more recent releases of JFlex and CUP
by Hal Perkins, Jan. 2017.  Updates to use recent JFlex ComplexSymbol
class by Nate Yazdani, April 2018.  Updates to improve testing support
by Aaron Johnston, Sep. 2019.  Update to JFlex 1.8.2 by Hal Perkins,
Oct. 2020.
