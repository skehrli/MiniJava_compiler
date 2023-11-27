package Semantics;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;

public class IndexedMap<V> implements Map<String, V> {
    public Map<String, Entry<Integer, V>> wrapped = new LinkedHashMap<>();

    @Override
    public V put(String key, V value) {
        Entry<Integer, V> entry = wrapped.put(key,
            new SimpleEntry<Integer, V>(
                wrapped.containsKey(key) ? wrapped.get(key).getKey()
                    : wrapped.size()
                , value
            )
        );
        if (entry == null) return null;
        return entry.getValue();
    }    

    @Override
    public V get(Object key) {
        Entry<Integer, V> entry = wrapped.get(key);
        if (entry == null) return null;
        return entry.getValue();
    }

    public int position(Object key) {
        Entry<Integer, V> entry = wrapped.get(key);
        if (entry == null) return -1;
        return entry.getKey();
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
        Entry<Integer, V> entry = wrapped.remove(key);
        if (entry == null) return null;
        return entry.getValue();
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
        return wrapped.values().stream().map(Entry::getValue).toList();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return wrapped.entrySet().stream().map(x->new SimpleEntry<>(x.getKey(), x.getValue().getValue())).collect(Collectors.toSet());
    }
}
