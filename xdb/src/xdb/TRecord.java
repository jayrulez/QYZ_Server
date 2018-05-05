package xdb;

import xdb.logs.AddRemoveInfo;
import xdb.logs.LogNotify;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;

/**
 * 记录实现。内部实现类。
 * 管理记录的状态，锁。
 *
 * @param <K> key type
 * @param <V> value type
 */
public final class TRecord<K, V> extends XBean {
	private V value;
	private final Lockey lockey;
	private State state;

	///////////////////////////////////////
	// see TTableCacheConcurrentMap
	private volatile long lastAccessTime = System.nanoTime();

	void access() {
		this.lastAccessTime = System.nanoTime();
	}

	public final long getLastAccessTime() {
		return lastAccessTime;
	}

	///////////////////////////////////////
	public static enum State {
		INDB_GET,    // 查询装载进入
		INDB_REMOVE, // 删除，在数据库中存在该记录。
		INDB_ADD,    // 新增记录，在数据库中存在该记录。这种情况原因：删除操作还没有flush，又执行了增加。
		ADD,         // 新增的记录
		REMOVE,      // 新增操作还没有flush，又执行了删除。此状态记录只存在于事务过程中，事务结束就会从cache中删除。
	}

	@Override
	public final String toString() {
		return getTable().getName() + "," + lockey + "," + state;
	}

	final TTable<K, V> getTable() {
		@SuppressWarnings("unchecked")
		TTable<K, V> table = (TTable<K, V>)xdbParent();
		return table;
	}

	public final K getKey() {
		@SuppressWarnings("unchecked")
		K key = (K)lockey.getKey();
		return key;
	}

	private final OctetsStream marshalKey() {
		return getTable().marshalKey(getKey());
	}

	private final OctetsStream marshalValue() {
		// 不判断value是否null。非法调用直接抛异常。
		return getTable().marshalValue(value);
	}

	public final State getState() {
		return state;
	}

	/**
	 * flush的时候，需要修改state，比较不爽。
	 *
	 * @param state
	 */
	final void setState(State state) {
		this.state = state;
	}

	@Override
	final void xdbLogNotify(xdb.logs.LogNotify notify) {
		getTable().onRecordChanged(this, notify);
		//Trace.debug("Record changed state = " + state + " key=" + lockey.getKey());
	}

	/**
	 * 提交时,CacheAddLog通过这个检查record状态。
	 * @return true，保持cache。 false，清除cache。
	 */
	final boolean commit() {
		switch (state) {
//		case INDB_REMOVE: break;
//		case INDB_ADD:    break;
//		case ADD:         break;
		case REMOVE:   return false;
		case INDB_GET: throw new XError("xdb:invalid record state");
		default: break;
		}
		// Trace.debug("Record add/remove state = " + state + " key=" + lockey.getKey());
		return true;
	}

	/**
	 * 回滚时，CacheAddLog通过这个检查record状态。
	 * @return true，清除cache。 false，保持cache。
	 */
	final boolean rollback() {
//		switch (state) {
		// 回滚的时候不严格检查。
//		case INDB_GET: throw new XError("xdb:invalid record state");
//		case INDB_REMOVE: break;
//		case INDB_ADD: break;
//		case ADD: break; 
//		case REMOVE: break;
//		}
		return true;
	}

	final Lockey getLockey() {
		return lockey;
	}

	public static final String RECORD_VARNAME = "value";

	final LogKey getLogKey() {
		return new LogKey(this, RECORD_VARNAME);
	}

	TRecord(TTable<K, V> table, V value, Lockey lockey, boolean remove_exists) {
		this(table, value, lockey, remove_exists ? State.INDB_REMOVE : State.REMOVE);
	}

	TRecord(TTable<K, V> table, V value, Lockey lockey, State state) {
		super(table, RECORD_VARNAME);

		// key 只能是常变量。常变量不需要 XdbParent。
		//Logs.xdbParent(lockey.getKey(), this, RECORD_VARNAME);
		if (null != value) { // 删除时，创建的记录对象 value 为 null。
			Logs.xdbParent(value, this, RECORD_VARNAME, State.INDB_GET != state);
		}

		this.value  = value;
		this.lockey = lockey;
		this.state  = state;
	}

	private final void _remove() {
		Logs.xdbParent(value, null, null);
		Transaction.currentSavepoint().addIfAbsent(getLogKey(), new LogAddRemove());
		value = null;
	}

	final boolean remove() {
		switch (state) {
		case INDB_GET: _remove(); state = State.INDB_REMOVE; return true;
		case INDB_ADD: _remove(); state = State.INDB_REMOVE; return true;
		case ADD:      _remove(); state = State.REMOVE; return true;
		default: break;
		// case REMOVE: return false;
		// case INDB_REMOVE: return false;
		}
		return false;
	}

	private final void _add(V value) {
		Logs.xdbParent(value, this, RECORD_VARNAME);
		Transaction.currentSavepoint().addIfAbsent(getLogKey(), new LogAddRemove());
		this.value = value;
	}

	final boolean add(V value) {
		switch (state) {
//		case INDB_GET: return false;
//		case INDB_ADD: return false;
//		case ADD:      return false;
		case INDB_REMOVE: _add(value); state = State.INDB_ADD; return true;
		case REMOVE:      _add(value); state = State.ADD; return true;
		default: break;
		}
		return false;
	}

	final V getValue() {
		return value;
		// value 在删除以后会被设置成null。直接返回即可。
		// 可以在下面检查状态，并且检测错误。用于调试。
		/*
		switch (state) {
		case INDB_GET:    return value;
		case INDB_ADD:    return value;
		case ADD:         return value;
		case INDB_REMOVE: return null;
		case REMOVE:      return null;
		}
		return null;
		*/
	}

	private class LogAddRemove implements Log {
		private V saved_value;
		private State saved_state;

		LogAddRemove() {
			saved_value = value;
			saved_state = state;
		}

		@Override
		public void commit() {
			xdbLogNotify(new LogNotify(new AddRemoveInfo(false, saved_state)));
		}

		@Override
		public void rollback() {
			value = saved_value;
			state = saved_state;
		}

		@Override
		public String toString() {
			return "state=" + saved_state + " value=" + saved_value;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// 在 snapshot 之前，这些变量只由 Checkpoint 线程填写。
	// 在 snapshot 之后，下面的状态变为只读，以后都是读取操作。
	// 不需要锁保护。
	private OctetsStream   snapshotKey   = null;
	private OctetsStream   snapshotValue = null;
	private State          snapshotState = null;

	/**
	 * 多趟系列化。和事务并发执行。<b>记录锁.</b>
	 */
	final boolean tryMarshalN() {
		if (!lockey.tryLock())
			return false;
		try {
			marshal0();
		} finally {
			lockey.unlock();
		}
		return true;
	}

	/**
	 * 系列化。并发不安全，保护由调用者完成。使用时必须在记录锁或者flush锁的保护下。
	 */
	final void marshal0() {
		// 使用和flush一致的状态来处理encode。 不根据 value 是否为 null 来判断。
		switch (state) {
		case INDB_GET: case ADD: case INDB_ADD:
			snapshotKey = this.marshalKey();
			snapshotValue = this.marshalValue();
			break;
		case INDB_REMOVE:
			snapshotKey = this.marshalKey();
			break;
		case REMOVE:
			// see TStorage.marshalN()
			break;
		}
	}

	/**
	 * 完成记录状态更新和cache更新。
	 * 和事务互斥。snapshot 在 flush 锁内执行。
	 */
	final void snapshot() {
		snapshotState = this.state;
		switch (state) {
		case INDB_GET:
			// do nothing
			break;
		case INDB_REMOVE:
			getTable().getCache().remove(getKey());
			break;
		case INDB_ADD:
			setState(TRecord.State.INDB_GET);
			break;
		case ADD:
			setState(TRecord.State.INDB_GET);
			break; 
		case REMOVE:
			// see TStorage.marshalN()
			Trace.info("snapshot REMOVE record=" + this); // 提高级别看看发生频率。应该很少。
			getTable().getCache().remove(getKey());
			break;
		}
	}

	/**
	 * 保存修改的记录到数据库中。仅在Checkpoint线程中调用。
	 * flush和事务并发执行，都是只读访问。
	 * @param storage
	 * @return false 表示此时记录是REMOVE状态。用来统计用。
	 */
	final boolean flush(TStorage<K, V> storage) {
		switch (snapshotState) {
		case INDB_GET:
			storage.flushKeySize += snapshotKey.size();
			storage.flushValueSize += snapshotValue.size();
			storage.replaceUnsafe(snapshotKey, snapshotValue);
			break;
		case INDB_ADD:
			storage.flushKeySize += snapshotKey.size();
			storage.flushValueSize += snapshotValue.size();
			storage.replaceUnsafe(snapshotKey, snapshotValue);
			break;
		case ADD:
			storage.flushKeySize += snapshotKey.size();
			storage.flushValueSize += snapshotValue.size();
			storage.insertUnsafe(snapshotKey, snapshotValue);
			break; 
		case INDB_REMOVE:
			storage.flushKeySize += snapshotKey.size();
			storage.removeUnsafe(snapshotKey);
			break;
		case REMOVE:
			// see TStorage.marshalN()
			return false;
		}
		return true;
	}

	/**
	 * 线程不安全。调用时保护。
	 */
	final void clear() {
		this.snapshotKey = null;
		this.snapshotValue = null;
		this.snapshotState = null;
	}

	//////////////////////////////////////////////
	/**
	 * 根据记录状态判断记录是否存在，<b>已经处于记录锁保护下</b>
	 */
	final boolean exist() {
		Trace.info("TRecord.exist " + this);
		switch (snapshotState) {
		case INDB_GET: case INDB_ADD: case ADD:
			return true;
		case INDB_REMOVE: case REMOVE:
			return false;
		}
		throw new XError("invalid record state");
	}

	/**
	 * 根据记录状态返回将要写入db的记录值，<b>已经处于记录锁保护下</b>
	 */
	final OctetsStream find() {
		Trace.info("TRecord.find " + this);
		switch (snapshotState) {
		case INDB_GET: case INDB_ADD: case ADD:
			return OctetsStream.wrap(Octets.wrap(snapshotValue.array(), snapshotValue.size()));
		case INDB_REMOVE: case REMOVE:
			return null;
		}
		throw new XError("invalid record state");
	}
}
