
package xbean;

public interface ARankInfo extends xdb.Bean {
	public ARankInfo copy(); // deep clone
	public ARankInfo toData(); // a Data instance
	public ARankInfo toBean(); // a Bean instance
	public ARankInfo toDataIf(); // a Data instance If need. else return this
	public ARankInfo toBeanIf(); // a Bean instance If need. else return this


}
