
package xbean;

public interface Notice extends xdb.Bean {
	public Notice copy(); // deep clone
	public Notice toData(); // a Data instance
	public Notice toBean(); // a Bean instance
	public Notice toDataIf(); // a Data instance If need. else return this
	public Notice toBeanIf(); // a Bean instance If need. else return this

	public <T extends com.goldhuman.Common.Marshal.Marshal> T getData(T _v_); // 
	public boolean isDataEmpty(); // 
	public byte[] getDataCopy(); // 

	public void setData(com.goldhuman.Common.Marshal.Marshal _v_); // 
	public void setDataCopy(byte[] _v_); // 
}
