package xdb;

import com.goldhuman.Common.Marshal.OctetsStream;

public final class TableSys extends Table {
	// open 以后这几个变量本身只有读取操作，不需要锁保护。
	private StorageSys storage;
	private xdb.util.AutoKeys autoKeys;

	public xdb.util.AutoKeys getAutoKeys() {
		return autoKeys;
	}

	//////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getName() {
		return "_sys_";
	}

	@Override
	public Persistence getPersistence() {
		return Persistence.DB;
	}

	@Override
	Storage open(XdbConf xconf, Logger logger) {
		if (null != storage)
			throw new XError("TableSys has opened : " + getName());

		TableConf tconf = xconf.getTableConf(getName());
		if (null == tconf)
			tconf = new TableConf(getName(), 1, 1);

		storage = new StorageSys(logger, xconf, tconf);

		// 装载系统表数据记录
		autoKeys = storage.loadAutoKeys(xconf, tconf);

		// 保存在storage.flush里面实现，在Checkpoint.flush操作统一刷新。
		//Trace.debug("load:" + autoKeys.toString());
		return storage;
	}

	@Override
	void close() {
		if (null != storage) {
			storage.close();
			storage = null;
		}
	}

	private final class StorageSys extends Storage {
		StorageSys(Logger logger, XdbConf xconf, TableConf tconf) {
			super(logger, xconf, tconf);
		}

		xdb.util.AutoKeys loadAutoKeys(XdbConf xconf, TableConf tconf) {
			final int localId = xconf.getLocalid();
			if (localId < 0) // 有配置，但禁用了 autoKey。
				return null;

			keyOfAutoKeys = new OctetsStream().marshal("xdb.util.AutoKeys." + String.valueOf(localId), xdb.Const.IO_CHARSET);
			OctetsStream valueOfAutoKeys = super.find(keyOfAutoKeys);

			// key 改名兼容。让 key 中包含 localId，这样使得合服时更加顺利。
			// rename "xdb.util.AutoKeys" to "xdb.util.AutoKeys." + localId
			OctetsStream oldKey = new OctetsStream().marshal("xdb.util.AutoKeys", Const.IO_CHARSET);
			OctetsStream oldValue = super.find(oldKey);
			if (null != oldValue) {
				if (null != valueOfAutoKeys)
					throw new XError("autoKeys corrupt: new? old?");

				super.replaceUnsafe(keyOfAutoKeys, oldValue);
				super.removeUnsafe(oldKey);
				// 如果 XDB 没有任何修改，checkpoint不会被调用。see Checkpoint.flush checkpoint. 
				// 这里通过把 AutoKeys 设成 dirty，伪造修改。当然不伪造，已有有修改再一次保存也是可以的，也无伤大雅。
				return new xdb.util.AutoKeys(oldValue, localId, true);
			}

			return new xdb.util.AutoKeys(valueOfAutoKeys, localId, false);
		}

		private OctetsStream keyOfAutoKeys = null; // 记住key，防止配置修改，也有点优化作用。

		/**
		 * 并发
		 * 1 autoKey 的所有访问都在内存中，snapshotValue 仅在 Checkpoint 线程里面访问。
		 * 2 flush步骤里面，不需要拦截对 storage 的访问。see TStorage & TRecord。
		 * 3 marshalN, marshal0, snapshot 和 flush 不会并发，不并发保护。
		 */
		private OctetsStream snapshotValue = null;

		@Override
		int marshalN() {
			return 0;
		}

		@Override
		int marshal0() {
			if (null != TableSys.this.autoKeys && TableSys.this.autoKeys.isDirty()) {
				snapshotValue = TableSys.this.autoKeys.encodeValue(-1922908697795782568L);
				return 1;
			}
			return 0;
		}

		@Override
		int snapshot() {
			return null != snapshotValue ? 1 : 0;
		}

		@Override
		int flush() {
			if (null != snapshotValue) {
				super.replaceUnsafe(keyOfAutoKeys, snapshotValue);
				snapshotValue = null;
				return 1;
			}
			return 0;
		}

	}

}
