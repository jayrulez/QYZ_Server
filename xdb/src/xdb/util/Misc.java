package xdb.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 乱七八糟的杂物箱。
 * 自行翻阅。
 */
public class Misc {

	private static Misc misc = new Misc();

	public static Misc getInstance() {
		return misc;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ConcurrentHashMap 默认的并发级别为16。略嫌少了，现在都16核了。
	//                   一般来说，并发级别在一个应用系统中使用一样的设置是合理的。
	//                   在此提供统一的配置入口，如果需要修改，会比较方便。
	//
	// 建议使用这里的辅助方法来创建 ConcurrentMap。
	private volatile int concurrencyLevel = 256;

	/**
	 * 设置并发级别。新的并发级别只影响新建的 ConcurrentMap。
	 */
	public synchronized void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
		return new ConcurrentHashMap<K, V>(16, 0.75f, misc.concurrencyLevel);
	}

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap(int initialCapacity) {
		return new ConcurrentHashMap<K, V>(initialCapacity, 0.75f, misc.concurrencyLevel);
	}

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap(
			int initialCapacity, float loadFactor, int concurrencyLevel) {
		return new ConcurrentHashMap<K, V>(initialCapacity, loadFactor, concurrencyLevel);
	}
}
