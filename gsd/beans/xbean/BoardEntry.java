
package xbean;

public interface BoardEntry extends xdb.Bean {
	public BoardEntry copy(); // deep clone
	public BoardEntry toData(); // a Data instance
	public BoardEntry toBean(); // a Bean instance
	public BoardEntry toDataIf(); // a Data instance If need. else return this
	public BoardEntry toBeanIf(); // a Bean instance If need. else return this

	public int getRanking(); // 
	public long getId(); // 家族战力榜寸的是家族id,其他是存roleid
	public String getName(); // 
	public com.goldhuman.Common.Octets getNameOctets(); // 
	public long getVal1(); // 
	public int getVal2(); // 
	public long getUpdatetime(); // 更新时间，值相同的记录排序时时间早的在前面

	public void setRanking(int _v_); // 
	public void setId(long _v_); // 家族战力榜寸的是家族id,其他是存roleid
	public void setName(String _v_); // 
	public void setNameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setVal1(long _v_); // 
	public void setVal2(int _v_); // 
	public void setUpdatetime(long _v_); // 更新时间，值相同的记录排序时时间早的在前面
}
