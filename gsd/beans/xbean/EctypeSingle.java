
package xbean;

public interface EctypeSingle extends xdb.Bean {
	public EctypeSingle copy(); // deep clone
	public EctypeSingle toData(); // a Data instance
	public EctypeSingle toBean(); // a Bean instance
	public EctypeSingle toDataIf(); // a Data instance If need. else return this
	public EctypeSingle toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecords(); // key = ectypeid
	public java.util.Map<Integer, xbean.DailyEctypeRecord> getDailyectypebestrecordsAsData(); // key = ectypeid

}
