
package xbean;

public interface BoardRecord extends xdb.Bean {
	public BoardRecord copy(); // deep clone
	public BoardRecord toData(); // a Data instance
	public BoardRecord toBean(); // a Bean instance
	public BoardRecord toDataIf(); // a Data instance If need. else return this
	public BoardRecord toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.BoardRecordEntry> getRecords(); // 
	public java.util.Map<Integer, xbean.BoardRecordEntry> getRecordsAsData(); // 

}
