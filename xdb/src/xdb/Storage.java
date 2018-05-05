package xdb;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.goldhuman.Common.Octets;
import com.goldhuman.Common.Marshal.OctetsStream;

/**
 * 存储层接口
 * 
 * <ul>
 * <li> 修改操作不加锁，查询操作加锁。
 * <li>exist,find,walk <b>表锁</b>
 * <li>firstKey, nextKey <b>表锁</b>
 * <li>_walk, _find <b>表锁</b>
 * <li>replaceUnsafe,insertUnsafe,removeUnsafe <b>表锁</b>
 * <li>replace, insert, remove <b>无锁</b>
 * <li>_replace, _insert <b>无锁</b>
 * <li>browse <b>无锁，不能和checkpoint并发</b>
 * </ul>
 * 
 * <p> Storage实际上不需要是 abstract。由于它的一些方法在实现 xdb 时，必须重载。
 * 在 Storage 进行修改和调试时，这些方法会被声明为 abstract，便于发现是否有重载漏掉。
 *
 * @see 全局锁定规则：docs/lock.mht
 * @see share/commom/db.h
 *
 */
public abstract class Storage {
	private final Lock lock = new ReentrantLock();
	private final File file;
	private final Logger logger;

	public final void lock() {
		lock.lock();
	}

	public final void lockInterruptibly() {
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException ex) {
			throw new XLockInterrupted("Storage:" + file.toString());
		}
	}

	public final void unlock() {
		lock.unlock();
	}

	///////////////////////////////////////////////////////////////
	// checkpoint: marshalN -> lock(w){ marshal0 + snapshot } -> flush -> checkpoint
	int marshalN() { return 0; }
	int marshal0() { return 0; }
	int snapshot() { return 0; }
	int flush()    { return 0; }
	/*
	修改的时候使用 abstract，免得遗漏。
	abstract int marshalN();
	abstract int marshal0();
	abstract int snapshot();
	abstract int flush();
	*/
	/**
	 * 打开以后，不能更改配置。
	 */
	public Storage(Logger logger, XdbConf xconf, TableConf tconf) {
		this(logger, xconf.getTableHome(), tconf.getName(), tconf.getCacheHigh(), tconf.getCacheLow());
	}

	public Storage(Logger logger, File tableHome, String tableName, int cacheHigh, int cacheLow) {
		// 记住数据表文件。防止运行中配置被修改。
		file = new File(tableHome, tableName); // table.getName());

		handle = open(logger.getHandle(), file.toString(), cacheHigh, cacheLow);
		if (0 == handle)
			throw new XError("Storage open faild : " + file);
		this.logger = logger;
	}

	public final Logger getLogger() {
		return logger;
	}

	//////////////////////////////////////////////////////////////////
	// 数据存取接口。
	/**
	 * 测试key是否存在。<b>锁：Locked</b>。
	 */
	public final boolean exist(OctetsStream key) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			return exist(handle, key.array(), key.size());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 查找并返回记录。如果不存在则返回null。<b>锁：Locked</b>。
	 */
	public final OctetsStream find(OctetsStream key) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			byte [] bytes = find(handle, key.array(), key.size());
			if (null == bytes)
				return null;

			return OctetsStream.wrap(Octets.wrap(bytes, bytes.length));

		} finally {
			lock.unlock();
		}
	}

	/**
	 * 插入记录，如果存在则覆盖，如果不存在则添加。<b>锁：Locked</b>。
	 * @throws 通过异常报告错误。
	 */
	public final void replaceUnsafe(OctetsStream key, OctetsStream value) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			if (false == replace(handle, key.array(), key.size(), value.array(), value.size()))
				throw new XError("replace faild");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 插入记录，如果记录已经存在则抛出异常。<b>锁：Locked</b>。
	 * @throws 通过异常报告错误。
	 */
	public final void insertUnsafe(OctetsStream key, OctetsStream value) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			if (false == insert(handle, key.array(), key.size(), value.array(), value.size()))
				throw new XError("insert faild");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 删除记录。如果记录不存在则抛出异常。<b>锁：Locked</b>。
	 * @throws 通过异常报告错误。
	 */
	public final void removeUnsafe(OctetsStream key) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			if (false == remove(handle, key.array(), key.size()))
				throw new XError("remove faild");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 插入记录，如果存在则覆盖，如果不存在则添加。<b>锁：Not Locked</b>。
	 * @return 通过返回值报告错误。
	 */
	public boolean replace(OctetsStream key, OctetsStream value) {
		if (0 == handle)
			throw new XError("storage has closed");

		return replace(handle, key.array(), key.size(), value.array(), value.size());
	}

	/**
	 * 插入记录，如果记录已经存在返回false。<b>锁：Not Locked</b>。
	 * @return 通过返回值报告错误。
	 */
	public boolean insert(OctetsStream key, OctetsStream value) {
		if (0 == handle)
			throw new XError("storage has closed");

		return insert(handle, key.array(), key.size(), value.array(), value.size());
	}

	/**
	 * 删除记录。如果记录不存在则抛出异常。<b>锁：Not Locked</b>。
	 * @return 通过返回值报告错误。
	 */
	public boolean remove(OctetsStream key) {
		if (0 == handle)
			throw new XError("storage has closed");

		return remove(handle, key.array(), key.size());
	}

	/**
	 * 通过索引遍历表。<b>锁：Locked</b>。
	 */
	public void walk(IWalk iw) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");
			walk(handle, iw);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 直接遍历数据文件，需要和checkpoint互斥。<b>锁：Not Lock</b>。
	 */
	public void browse(IWalk ib, int cachesize) {
		if (0 == handle)
			throw new XError("storage has closed");
		browse(handle, ib, cachesize);
	}

	public static interface IWalk {
		/**
		 * 记录回调接口。
		 * @param key
		 * @param data
		 * @return true = continue walk; false = break walk;
		 */
		public boolean onRecord(byte[] key, byte[] data);
	}

	/**
	 * 通过索引遍历表。<b>锁：Locked</b>。数据保持压缩状态。
	 */
	public void _walk(IWalk iw) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");
			_walk(handle, iw);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 直接遍历数据文件。<b>锁：Not Locked</b>。数据保持压缩状态。
	 */
	public void _browse(IWalk iw, int cachesize) {
		if (0 == handle)
			throw new XError("storage has closed");
		_browse(handle, iw, cachesize);
	}

	/**
	 * 查找并返回记录。如果不存在则返回null。<b>锁：Locked</b>。数据保持压缩状态。
	 */
	public final OctetsStream _find(OctetsStream key) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			byte [] bytes = _find(handle, key.array(), key.size());
			if (null == bytes)
				return null;

			return OctetsStream.wrap(Octets.wrap(bytes, bytes.length));

		} finally {
			lock.unlock();
		}
	}

	/**
	 * 插入记录，如果存在则覆盖，如果不存在则添加。<b>锁：Not Locked</b>。
		 * <p><b>value 必须是压缩过后的数据</b>
	 * @return 通过返回值报告错误。
	 */
	public boolean _replace(OctetsStream key, OctetsStream value) {
		if (0 == handle)
			throw new XError("storage has closed");

		return _replace(handle, key.array(), key.size(), value.array(), value.size());
	}

	/**
	 * 插入记录，如果记录已经存在则返回false。<b>锁：Not Locked</b>。
		 * <p><b>value 必须是压缩过后的数据</b>
	 * @return 通过返回值报告错误。
	 */
	public boolean _insert(OctetsStream key, OctetsStream value) {
		if (0 == handle)
			throw new XError("storage has closed");

		return _insert(handle, key.array(), key.size(), value.array(), value.size());
	}

	/**
	 * 压缩。线程安全。
	 */
	public final static OctetsStream _compress(OctetsStream data) {
		byte [] result = _compress(data.array(), data.size());
		return OctetsStream.wrap(Octets.wrap(result, result.length));
	}

	/**
	 * 解压缩。线程安全。
	 */
	public final static OctetsStream _uncompress(OctetsStream data) {
		byte [] result = _uncompress(data.array(), data.size());
		return OctetsStream.wrap(Octets.wrap(result, result.length));
	}

	/**
	 * 返回表的第一个key。<b>锁：Locked</b>。
	 * @return null if storage is empty.
	 */
	public final OctetsStream firstKey() {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			byte [] result = firstKey(handle);
			if (null == result)
				return null;
			return OctetsStream.wrap(Octets.wrap(result, result.length));
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 根据指定key，返回下一个key（索引顺序）。<b>锁：Locked</b>。
	 * @param key
	 * @return null if EOF.
	 */
	public final OctetsStream nextKey(OctetsStream key) {
		lock.lock();
		try {
			if (0 == handle)
				throw new XError("storage has closed");

			byte [] result = nextKey(handle, key.array(), key.size());
			if (null == result)
				return null;
			return OctetsStream.wrap(Octets.wrap(result, result.length));
		} finally {
			lock.unlock();
		}
	}

	//////////////////////////////////////////////////////////////////////////
	// native implement
	private long handle;

	private native void walk(long handle, IWalk iw);
	private native void browse(long handle, IWalk iw, int cachesize);
	private native boolean exist(long handle, byte [] key, int keysize);
	private native byte[] find(long handle, byte [] key, int keysize);
	private native boolean replace(long handle, byte [] key, int keysize, byte [] value, int valuesize);
	private native boolean insert(long handle, byte [] key, int keysize, byte [] value, int valuesize);
	private native boolean remove(long handle, byte [] key, int keysize);

	private native long open(long logger, String file, int cachehight, int cachelow);
	private native void close(long handle);

	// checkpoint support
	private native void snapshot_create(long handle);
	private native void snapshot_release(long handle);

	// raw
	private native void _walk(long handle, IWalk iw);
	private native void _browse(long handle, IWalk iw, int cachesize);
	private native byte[] _find(long handle, byte [] key, int keysize);
	private native boolean _replace(long handle, byte [] key, int keysize, byte [] value, int valuesize);
	private native boolean _insert(long handle, byte [] key, int keysize, byte [] value, int valuesize);

	private native byte[] firstKey(long handle);
	private native byte[] nextKey(long handle, byte[] key, int keysize);

	public static native byte[] _compress(byte[] data, int datasize);
	public static native byte[] _uncompress(byte[] data, int datasize);

	/**
	 * 表的文件信息。
	 * @return
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * 关闭表。以后所有的访问都将失败。
	 */
	public void close() {
		if (0 == handle)
			throw new XError("storage has closed");
		close(handle);
		handle = 0;
	}

	@Override
	protected void finalize() throws Throwable {
		if (0 != handle)
			close(handle);
		super.finalize();
	}

	/**
	 * 创建快照。
	 */
	public final void snapshot_create() {
		if (0 == handle)
			throw new XError("storage has closed");
		snapshot_create(handle);
	}

	/**
	 * 释放快照。
	 */
	public final void snapshot_release() {
		if (0 == handle)
			throw new XError("storage has closed");
		snapshot_release(handle);
	}

	private class KeyIterator implements java.util.Iterator<OctetsStream> {
		private OctetsStream cur = null;
		private OctetsStream next;

		KeyIterator() {
			next = Storage.this.firstKey();
		}

		@Override
		public boolean hasNext() {
			return null != next;
		}

		@Override
		public OctetsStream next() {
			if (null == cur)
				throw new IllegalStateException();
			cur = next;
			next = Storage.this.nextKey(cur);
			return cur;
		}

		@Override
		public void remove() {
			if (null == cur)
				throw new IllegalStateException();
			Storage.this.remove(cur); // skip error
			cur = null;
		}
	}

	/**
	 * 包装 firstKey, nextKey
	 * 意思不大，想用就用。
	 */
	public final java.util.Iterator<OctetsStream> iterator() {
		return new KeyIterator();
	}
}
