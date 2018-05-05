package xdb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import xdb.util.WeakHashSet;

/**
 * <p>Locks原来使用 WeakHashMap管理锁，效率太低：
 * <p>1. 每次查询都会试图去回收; 2. 并发访问效率低.
 * <p>这个类的目的就是提高效率。
 */
public final class Locks {
	/**
	 * The maximum number of segments to allow; used to bound constructor
	 * arguments.
	 */
	private static final int MAX_SEGMENTS = 1 << 16; // slightly conservative
	private final int segmentShift;
	private final int segmentMask;
	private Segment segments[];

	/* ---------------- hash算法和映射规则都是来自 ConcurrentHashMap. -------------- */
	/**
	 * Returns the segment that should be used for key with given hash.
	 * 
	 * @param lockey
	 *            the Lockey
	 * @return the segment
	 */
	private final Segment segmentFor(Lockey lockey) {
		/**
		 * Applies a supplemental hash function to a given hashCode, which
		 * defends against poor quality hash functions. This is critical because
		 * ConcurrentHashMap uses power-of-two length hash tables, that
		 * otherwise encounter collisions for hashCodes that do not differ in
		 * lower or upper bits.
		 */
		// Spread bits to regularize both segment and index locations,
		// using variant of single-word Wang/Jenkins hash.
		int h = lockey.hashCode();
		h += (h << 15) ^ 0xffffcd7d;
		h ^= (h >>> 10);
		h += (h << 3);
		h ^= (h >>> 6);
		h += (h << 2) + (h << 14);
		int hash = h ^ (h >>> 16);

		int index = (hash >>> segmentShift) & segmentMask;
		return segments[index];
	}

	public Locks() {
		this(1024);
	}

	public Locks(int concurrencyLevel) {
		if (concurrencyLevel <= 0)
			throw new IllegalArgumentException();

		if (concurrencyLevel > MAX_SEGMENTS)
			concurrencyLevel = MAX_SEGMENTS;

		// Find power-of-two sizes best matching arguments
		int sshift = 0;
		int ssize = 1;
		while (ssize < concurrencyLevel) {
			++sshift;
			ssize <<= 1;
		}
		this.segmentShift = 32 - sshift;
		this.segmentMask = ssize - 1;
		this.segments = new Segment[ssize];
        for (int i = 0; i < this.segments.length; ++i)
            this.segments[i] = new Segment();
	}

	/* ------------- 实现 ---------------*/
	static final class Segment {
		private final WeakHashSet<Lockey> locks;
		private final Lock sync = new ReentrantLock();

		Segment() {
			locks = new WeakHashSet<Lockey>();
		}

		boolean exist(Lockey key) {
			// 需要sync，get不是线程安全的
			sync.lock();
			try {
				return locks.get(key) != null;
			} finally {
				sync.unlock();
			}
		}

		Lockey get(Lockey key) {			
			sync.lock();
			try {
				Lockey weakey = locks.add(key);
				if (key == weakey)
					key.alloc(); // new key
				return weakey;
			} finally {
				sync.unlock();
			}
		}

		int size() {
			return locks.size();
		}
	}

	public boolean exist(Lockey lockey) {
		return this.segmentFor(lockey).exist(lockey);
	}

	public Lockey get(Lockey lockey) {
		return this.segmentFor(lockey).get(lockey);
	}

	/**
	 * 返回锁的数量，可能包括已经被gc回收的。由于并发执行，这个值只能用于观察。
	 * @return
	 */
	public int size() {
		int ssize = 0;
		for (Segment segment : this.segments)
			ssize += segment.size();
		return ssize;
	}

}
