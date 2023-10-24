This subdirectory contains the implementation of a simple demo parser.
The main contents is the minijava.cup file, which contains a
specification of the parser.  The CUP tool processes this
specification into the parser implementation.

Change this into the parser for your implementation of MiniJava.



/* Nonterminals (constructed by parser): */

nonterminal Program Program;
nonterminal MainClass MainClass;
nonterminal ClassDeclList ClassList;
nonterminal ClassDecl ClassDeclaration;
nonterminal VarDecl VarDeclaration;
nonterminal MethodDecl MethodDeclaration;
nonterminal Type Type;
nonterminal Statement Statement;
nonterminal Exp Expression;
nonterminal Identifier Identifier;


/* Precedence declarations: */

precedence left BECOMES;
precedence left AND;
precedence left LESS;
precedence left PLUS, MINUS;
precedence left TIMES;
precedence right BANG;
precedence left DEREF;
