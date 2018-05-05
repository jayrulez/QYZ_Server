
package xbean;

public interface RankRecord extends xdb.Bean {
	public RankRecord copy(); // deep clone
	public RankRecord toData(); // a Data instance
	public RankRecord toBean(); // a Bean instance
	public RankRecord toDataIf(); // a Data instance If need. else return this
	public RankRecord toBeanIf(); // a Bean instance If need. else return this

	public long getValue(); // 
	public long getId(); // 

	public void setValue(long _v_); // 
	public void setId(long _v_); // 
}
