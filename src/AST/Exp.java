package AST;

import AST.Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class Exp extends ASTNode {
    public Semantics.InstanceType expType;

    public Exp(Location pos) {
        super(pos);
    }

    public abstract void accept(Visitor v);
}
