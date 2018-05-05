
package xbean;

public interface BoardRecordEntry extends xdb.Bean {
	public BoardRecordEntry copy(); // deep clone
	public BoardRecordEntry toData(); // a Data instance
	public BoardRecordEntry toBean(); // a Bean instance
	public BoardRecordEntry toDataIf(); // a Data instance If need. else return this
	public BoardRecordEntry toBeanIf(); // a Bean instance If need. else return this

	public long getVal1(); // 
	public int getVal2(); // 
	public long getUpdatetime(); // 更新时间，值相同的记录排序时时间早的在前面

	public void setVal1(long _v_); // 
	public void setVal2(int _v_); // 
	public void setUpdatetime(long _v_); // 更新时间，值相同的记录排序时时间早的在前面
}
