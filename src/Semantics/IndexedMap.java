package Semantics;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;

public class IndexedMap<V> implements Map<String, V> {
    private class TaggedValue {
        private int tag;
        private V value;

        public TaggedValue(int tag, V value) {
            this.tag = tag;
            this.value = value;
        }

        public int tag() { return tag; }
        public V value() { return value; }
    }
    public Map<String, TaggedValue> wrapped = new LinkedHashMap<>();

    @Override
    public V put(String key, V value) {
        TaggedValue entry = wrapped.put(key,
            new TaggedValue(
                wrapped.containsKey(key) ? wrapped.get(key).tag()
                    : wrapped.size()
                , value
            )
        );
        if (entry == null) return null;
        return entry.value();
    }    

    @Override
    public V get(Object key) {
        TaggedValue entry = wrapped.get(key);
        if (entry == null) return null;
        return entry.value();
    }

    public int position(Object key) {
        TaggedValue entry = wrapped.get(key);
        if (entry == null) return -1;
        return entry.tag();
    }

    @Override
    public int size() {
        return wrapped.size();
    }

    @Override
    public boolean isEmpty() {
        return wrapped.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return wrapped.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return wrapped.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        TaggedValue entry = wrapped.remove(key);
        if (entry == null) return null;
        return entry.value();
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        for (String key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    @Override
    public void clear() {
        wrapped.clear();
    }

    @Override
    public Set<String> keySet() {
        return wrapped.keySet();
    }

    @Override
    public Collection<V> values() {
        return wrapped.values().stream().map(TaggedValue::value).toList();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return wrapped.entrySet().stream().map(x->new SimpleEntry<>(x.getKey(), x.getValue().value())).collect(Collectors.toSet());
    }
}
