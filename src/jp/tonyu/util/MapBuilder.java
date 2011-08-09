package jp.tonyu.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapBuilder<K,V> implements Map<K,V> {
	public MapBuilder(K key, V value) {
		put(key, value);
	}
	public MapBuilder<K, V> p(K key,V value) {
		src.put(key, value);
		return this;
	}
	public final Map<K,V> src=new HashMap<K, V>();
	
	public void clear() {
		src.clear();
	}
	public boolean containsKey(Object key) {
		return src.containsKey(key);
	}
	public boolean containsValue(Object value) {
		return src.containsValue(value);
	}
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return src.entrySet();
	}
	public boolean equals(Object o) {
		return src.equals(o);
	}
	public V get(Object key) {
		return src.get(key);
	}
	public int hashCode() {
		return src.hashCode();
	}
	public boolean isEmpty() {
		return src.isEmpty();
	}
	public Set<K> keySet() {
		return src.keySet();
	}
	public V put(K key, V value) {
		return src.put(key, value);
	}
	public void putAll(Map<? extends K, ? extends V> m) {
		src.putAll(m);
	}
	public V remove(Object key) {
		return src.remove(key);
	}
	public int size() {
		return src.size();
	}
	public Collection<V> values() {
		return src.values();
	}
	
}