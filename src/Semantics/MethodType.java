package Semantics;

import AST.Identifier;

public interface MethodType extends Type {
    default boolean addParam(String s, InstanceType i) {
        return false;
    }
    default boolean addParam(Identifier s, InstanceType i) {
        return this.addParam(s.toString(), i);
    }

    default InstanceType getParam(String s) {
        return Bottom.get();
    }
    default InstanceType getParam(Identifier s) {
        return this.getParam(s.toString());
    }

    default InstanceType getVariable(String s) {
        return Bottom.get();
    }
    default InstanceType getVariable(Identifier s) {
        return this.getParam(s.toString());
    }

    default boolean addVariable(String s, InstanceType i) {
        return false;
    }
    default boolean addVariable(Identifier s, InstanceType i) {
        return this.addVariable(s.toString(), i);
    }

    default InstanceType getReturn() {
        return Bottom.get();
    }

    default int params() { return 0; }
}

