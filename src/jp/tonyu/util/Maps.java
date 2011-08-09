package jp.tonyu.util;

import java.util.Map;

public class Maps {
	public static <K,V> MapIterator<K,V> entries(final Map<K,V> m) {
		return new MapIterator<K,V>(m);
	}
	
	public static <K,V> MapBuilder<K,V> create(K key, V value) {
		return new MapBuilder<K,V>(key,value);
	}
}

