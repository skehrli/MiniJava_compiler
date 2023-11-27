package Semantics;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;

public class IndexedMap<V> implements Map<String, V> {
    private Map<String, Entry<Integer, V>> wrapped = new LinkedHashMap<>();

    @Override
    public V put(String key, V value) {
        return wrapped.put(key,
            new SimpleEntry<Integer, V>(
                wrapped.containsKey(key) ? wrapped.get(key).getKey()
                    : wrapped.size()
                , value
            )
        ).getValue();
    }    

    @Override
    public V get(Object key) {
        return wrapped.get(key).getValue();
    }

    public int position(Object key) {
        return wrapped.get(key).getKey();
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
        return wrapped.remove(key).getValue();
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
