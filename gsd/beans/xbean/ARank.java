
package xbean;

public interface ARank extends xdb.Bean {
	public ARank copy(); // deep clone
	public ARank toData(); // a Data instance
	public ARank toBean(); // a Bean instance
	public ARank toDataIf(); // a Data instance If need. else return this
	public ARank toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, Integer> getId2rank(); // roleid-rank
	public java.util.Map<Long, Integer> getId2rankAsData(); // roleid-rank
	public java.util.Map<Integer, xbean.RankRecord> getRecords(); // 
	public java.util.Map<Integer, xbean.RankRecord> getRecordsAsData(); // 
	public long getCreatetime(); // 

	public void setCreatetime(long _v_); // 
}
