package xdb.consts;

import java.util.Map;

import xdb.Consts;

public class ConstEntry<K, V> implements Map.Entry<K, V> {
	private Map.Entry<K, V> e;

	public ConstEntry(Map.Entry<K, V> e) {
		this.e = e;
	}

	@Override
	public K getKey() {
		return Consts.toConst(e.getKey());
	}

	@Override
	public V getValue() {
		return Consts.toConst(e.getValue());
	}

	@Override
	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		return e.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return e.equals(o);
	}

	@Override
	public String toString() {
		return e.toString();
	}
}
