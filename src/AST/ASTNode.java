package AST;

import AST.Visitor.Visitor;
import Semantics.Bottom;
import Semantics.Type;
import java_cup.runtime.ComplexSymbolFactory.Location;

abstract public class ASTNode {
  // Line number in source file.
  public final int line_number;
  Type type = Bottom.get();

  // Constructor
  public ASTNode(Location pos) {
    this.line_number = pos.getLine();
  }
}
