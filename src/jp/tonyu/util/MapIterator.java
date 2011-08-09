package jp.tonyu.util;

import java.util.Map;

public class MapIterator<K, V> {
	Map<K,V> target;
	public void each(MapAction<K,V> mapAction) {
		for (Map.Entry<K, V> e:target.entrySet()) {
			mapAction.run(e.getKey(),e.getValue());
		}
	}
	public MapIterator(Map<K, V> target) {
		super();
		this.target = target;
	}


}
