package Semantics;

import AST.Identifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DeclaredClass implements ClassType, ScopedType {
    public final IndexedMap<InstanceType> instances = new IndexedMap<>(); // Just the fields a class directly declared.
    private final IndexedMap<DeclaredClass> vtable = new IndexedMap<>();
    public final IndexedMap<MethodType> methods = new IndexedMap<>();
    public final HashSet<String> unrecognized = new HashSet<>();
    private final Map<String, Integer> fields = new HashMap<>(); // All the fields a class has access to.
    private boolean modifiedm = true, modifiedf = true;
    public String superclass = null, name;

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
        modifiedm = true;
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
        modifiedf = true;
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
        if (!modifiedm) return vtable;
        vtable.clear();
        vtable.putAll(this.superclass().vtable());
        methods.keySet().stream().forEach(key->vtable.put(key, this));
        modifiedm = false;
        return vtable;
    }

    public Map<String, Integer> fields() {
        if (!modifiedf) return fields;
        fields.clear();
        fields.putAll(this.superclass().fields());
        int index = fields.size();
        for (String declaredfield : instances.keySet()) {
            fields.put(declaredfield, index++);
        }
        modifiedf = false;
        return fields;
    }
}
