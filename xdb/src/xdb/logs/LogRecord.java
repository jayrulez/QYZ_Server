package xdb.logs;

import java.util.HashMap;
import java.util.Map;

import xdb.TRecord;
import xdb.TTable;
import xdb.Transaction;
import xdb.TRecord.State;

/**
 * 收集修改日志的总入口。
 * 由xdb.TTable管理。
 * 每个事务线程有自己的实例。
 * 
 * @author lichenghua
 *
 * @param <K>
 * @param <V>
 */
public final class LogRecord<K, V> {
	private Map<K, LogR<K, V>> changed = new HashMap<K, LogR<K, V>>();
	private Listenable seed;
	private int hasListener; // 0 未初始化　1 有 -1 没有

	public LogRecord(Object o) {
		this.seed = (o instanceof xdb.XBean) ? ((xdb.XBean)o).newListenable() : new xdb.logs.ListenableChanged();
		this.seed.setVarName(TRecord.RECORD_VARNAME).makeFullVarName("");
	}

	public String toFullVarName(VarNames names) {
		if (!TRecord.RECORD_VARNAME.equals(names.next()))
			throw new IllegalStateException();
		return seed.toFullVarName(names);
	}

	public void onChanged(TTable<K, V> table, TRecord<K, V> r, LogNotify ln) {
		/*
		 * 没有人监听的时候，不收集日志。see LogRecord.onChanged。
		 * 事务提交第一个修改日志到达的时候，记录 hasListener 状态。
		 * 在这一次事务提交过程中保持不变，提交完成以后在logNotify里面重置状态。
		 * 除了第一个，这里不使用hasListener()调用来判断，是为了防止在收集数据变更的过程中，
		 * 如果listener发生变化，造成数据变更收集不完全。
		 * 原则是：要嘛全部收集，要嘛全部忽略。
		 */
		if (hasListener == 0)
			hasListener = table.hasListener() ? 1 : -1;

		Transaction.current().addLogNotifyTTable(table);

		if (hasListener == -1)
			return;

		LogR<K, V> lr = changed.get(r.getKey());
		if (null == lr)
			changed.put(r.getKey(), lr = new LogR<K, V>(r, seed.copy()));
		if (ln.isAddRemove()) {
			/**
			 * {Merge}只加事务中第一个Savepoint的addRemoveInfo，用于最后的Listener通知判断
			 * 依赖于各个Savepoint按照顺序提交
			 */
			if (null == lr.addRemoveInfo) {
				lr.addRemoveInfo = ln.getAddRemoveInfo();
			}
		} else {
			ln.pop();
			lr.value.setChanged(ln);
		}
	}

	public void logNotify(ListenerMap listenerMap) {
		hasListener = 0;
		for (LogR<K, V> lr : changed.values())
			lr.value.logNotify(lr.r.getKey(), lr.getRecordState(), listenerMap);
		changed.clear();
	}

	private static class LogR<K, V> {
		private TRecord<K, V> r;
		private AddRemoveInfo addRemoveInfo;
		private Listenable value;

		LogR(TRecord<K, V> r, Listenable value) {
			this.r = r;
			this.value = value;
		}
		
		private xdb.logs.RecordState getRecordState() {
			if (null != addRemoveInfo) {
				// 具体见xdb.record.watch.state.xlsx文件的真值表
				if (addRemoveInfo.isCreateCache() && State.ADD == addRemoveInfo.getSavedState() && State.REMOVE == r.getState()) {
					return xdb.logs.RecordState.NONE;	  // 真值表第1项
				} else if (!addRemoveInfo.isCreateCache() && State.ADD == addRemoveInfo.getSavedState() && State.REMOVE == r.getState()) {
					return xdb.logs.RecordState.REMOVED;  // 真值表第2项
				} else if (State.ADD == addRemoveInfo.getSavedState() && State.ADD == r.getState()) {
					return xdb.logs.RecordState.ADDED;    // 真值表第3，4项
				} else if (State.INDB_GET == addRemoveInfo.getSavedState() && State.INDB_REMOVE == r.getState()) {
					return xdb.logs.RecordState.REMOVED;  // 真值表第5，6项
				} else if (State.INDB_GET == addRemoveInfo.getSavedState() && State.INDB_ADD == r.getState()) {
					return xdb.logs.RecordState.ADDED;    // 真值表第7，8项
				} else if (State.INDB_REMOVE == addRemoveInfo.getSavedState() && State.INDB_ADD == r.getState()) {
					return xdb.logs.RecordState.ADDED;    // 真值表第9，10项
				} else if (addRemoveInfo.isCreateCache() && State.INDB_REMOVE == addRemoveInfo.getSavedState() && State.INDB_REMOVE == r.getState()) {
					return xdb.logs.RecordState.REMOVED;  // 真值表第11项
				} else if (!addRemoveInfo.isCreateCache() && State.INDB_REMOVE == addRemoveInfo.getSavedState() && State.INDB_REMOVE == r.getState()) {
					return xdb.logs.RecordState.NONE;     // 真值表第12项
				} else if (addRemoveInfo.isCreateCache() && State.REMOVE == addRemoveInfo.getSavedState() && State.REMOVE == r.getState()) {
					return xdb.logs.RecordState.NONE;     // 真值表第13项
				} else if (addRemoveInfo.isCreateCache() && State.REMOVE == addRemoveInfo.getSavedState() && State.ADD == r.getState()) {
					return xdb.logs.RecordState.ADDED;    // 真值表第14项
				} else if (!addRemoveInfo.isCreateCache() && State.INDB_ADD == addRemoveInfo.getSavedState() && State.INDB_ADD == r.getState()) {
					return xdb.logs.RecordState.ADDED;    // 真值表第15项
				} else if (!addRemoveInfo.isCreateCache() && State.INDB_ADD == addRemoveInfo.getSavedState() && State.INDB_REMOVE == r.getState()) {
					return xdb.logs.RecordState.REMOVED;  // 真值表第16项
				}
				else {
					throw new IllegalStateException("AddRemoveState Error! isCreateCache = " + addRemoveInfo.isCreateCache() + 
							", SavedState = " + addRemoveInfo.getSavedState() + ", State = " + r.getState());
				}
			}
			return xdb.logs.RecordState.CHANGED;
		}
	}
}
