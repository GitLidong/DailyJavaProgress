package com.lidong.others.my_hashmap;

public interface MyMap<K, V> {
	V get(K k);

	V put(K k, V v);

	interface Entry<K, V> {

		public K getKey();

		public V getValue();
	}

}
