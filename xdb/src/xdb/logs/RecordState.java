package xdb.logs;

/**
 * 
 * 记录变更状态。
 * 
 * @author lichenghua
 *
 */
public enum RecordState {
	ADDED,   // 记录新增
	REMOVED, // 记录删除
	CHANGED, // 记录可能发生改变，是否真的变化由 Listenable 的内部状态决定。
	NONE,    // 根据真值表计算出没有变化
}

