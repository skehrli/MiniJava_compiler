	.text
	.globl asm_main
asm_main:		# entry point of program
Exception in thread "main" java.util.UnknownFormatConversionException: Conversion = 'r'
	at java.base/java.util.Formatter$FormatSpecifier.conversion(Formatter.java:2855)
	at java.base/java.util.Formatter$FormatSpecifier.<init>(Formatter.java:2891)
	at java.base/java.util.Formatter.parse(Formatter.java:2747)
	at java.base/java.util.Formatter.format(Formatter.java:2671)
	at java.base/java.io.PrintStream.format(PrintStream.java:1209)
	at AST.Visitor.CodeGenerationVisitor.visit(CodeGenerationVisitor.java:203)
	at AST.IntegerLiteral.accept(IntegerLiteral.java:15)
	at AST.Visitor.CodeGenerationVisitor.visit(CodeGenerationVisitor.java:116)
	at AST.Print.accept(Print.java:15)
	at AST.Visitor.CodeGenerationVisitor.visit(CodeGenerationVisitor.java:35)
	at AST.MainClass.accept(MainClass.java:17)
	at AST.Visitor.CodeGenerationVisitor.visit(CodeGenerationVisitor.java:22)
	at AST.Program.accept(Program.java:16)
	at MiniJava.visitAST(MiniJava.java:110)
	at MiniJava.main(MiniJava.java:42)
