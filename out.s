Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.util.Map$Entry.getValue()" because the return value of "java.util.Map.put(Object, Object)" is null
	at Semantics.IndexedMap.put(IndexedMap.java:22)
	at Semantics.IndexedMap.put(IndexedMap.java:11)
	at Semantics.SymbolTable.add(SymbolTable.java:18)
	at Semantics.SymbolTable.add(SymbolTable.java:27)
	at AST.Visitor.ResolveClasses.hoistClass(ResolveClasses.java:84)
	at AST.Visitor.ResolveClasses.resolve(ResolveClasses.java:15)
	at AST.Visitor.PopulateTable.visit(PopulateTable.java:35)
	at AST.Program.accept(Program.java:16)
	at MiniJava.visitAST(MiniJava.java:110)
	at MiniJava.main(MiniJava.java:40)
