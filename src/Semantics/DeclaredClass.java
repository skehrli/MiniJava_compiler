package Semantics;

import AST.Identifier;

import java.util.HashSet;

public class DeclaredClass implements ClassType, ScopedType {
    public final IndexedMap<InstanceType> instances = new IndexedMap<>();
    private final IndexedMap<DeclaredClass> vtable = new IndexedMap<>();
    public final IndexedMap<MethodType> methods = new IndexedMap<>();
    public final HashSet<String> unrecognized = new HashSet<>();
    public String superclass = null, name;
    private boolean modified = true;

    public DeclaredClass(String name) {
        this.name = name;
    }

    public DeclaredClass(Identifier name) {
        this(name.toString());
    }

    public boolean addMethod(String s, MethodType m) {
        if (Type.valid(methods.get(s)))
            return false;
        methods.put(s, m);
        modified = true;
        return true;
    }

    public MethodType getMethod(String s) {
        MethodType result = methods.get(s);
        if (!Type.valid(result)) {
            return superclass().getMethod(s);
        }
        return result;
    }

    public boolean addField(String s, InstanceType i) {
        if (Type.valid(instances.get(s)))
            return false;
        instances.put(s, i);
        modified = true;
        return true;
    }

    public InstanceType getField(String s) {
        InstanceType result = instances.get(s);
        if (!Type.valid(result)) {
            return superclass().getField(s);
        }
        return result;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public boolean setSuperclass(String s) {
        superclass = s;
        return true;
    }

    @Override
    public ClassType superclass() {
        if (superclass == null)
            return Top.get();
        return Top.symTable.get(superclass);
    }

    @Override
    public boolean seenUnrecognized(String s) {
        return unrecognized.contains(s);
    }

    @Override
    public void addUnrecognized(String s) {
        unrecognized.add(s);
    }
    public String name() { return name; }

    public IndexedMap<DeclaredClass> vtable() {
        if (!modified) return vtable;
        vtable.clear();
        vtable.putAll(this.superclass().vtable());
        methods.keySet().stream().forEach(key->vtable.put(key, this));
        modified = false;
        return vtable;
    }
}
