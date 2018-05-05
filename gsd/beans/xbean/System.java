
package xbean;

public interface System extends xdb.Bean {
	public System copy(); // deep clone
	public System toData(); // a Data instance
	public System toBean(); // a Bean instance
	public System toDataIf(); // a Data instance If need. else return this
	public System toBeanIf(); // a Bean instance If need. else return this

	public boolean getInitrobot(); // 
	public java.util.List<Long> getOutrankrobots(); // 
	public java.util.List<Long> getOutrankrobotsAsData(); // 
	public long getGsdfirststarttime(); // 
	public int getRolenumsreach20(); // 
	public int getMaxsystemfamnum(); // 

	public void setInitrobot(boolean _v_); // 
	public void setGsdfirststarttime(long _v_); // 
	public void setRolenumsreach20(int _v_); // 
	public void setMaxsystemfamnum(int _v_); // 
}
