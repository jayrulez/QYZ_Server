package xdb;

import com.goldhuman.Common.Marshal.Marshal;

public interface Bean extends Marshal {
	// 内部特殊属性，公开出来，便于跟踪调试。
	public boolean xdbManaged();
	public Bean    xdbParent();
	public String  xdbVarname();
	public Long    xdbObjId();
	/**
	 * this method is out-of transaction. DO NOT use in transaction.
	 */
	public void _reset_unsafe_();

	// Const 转换接口。
	public Bean toConst();
	public boolean isConst(); // for debug

	// Data
	public boolean isData(); // for debug
}
