package jp.tonyu.util;

public interface MapAction<K,V> {
	public abstract void run(K key, V value);
}
