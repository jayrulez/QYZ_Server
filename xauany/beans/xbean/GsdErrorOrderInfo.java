
package xbean;

public interface GsdErrorOrderInfo extends xdb.Bean {
	public GsdErrorOrderInfo copy(); // deep clone
	public GsdErrorOrderInfo toData(); // a Data instance
	public GsdErrorOrderInfo toBean(); // a Bean instance
	public GsdErrorOrderInfo toDataIf(); // a Data instance If need. else return this
	public GsdErrorOrderInfo toBeanIf(); // a Bean instance If need. else return this

	public xbean.UncompletedOrderInfo getOrder(); // 
	public int getReason(); // 

	public void setReason(int _v_); // 
}
