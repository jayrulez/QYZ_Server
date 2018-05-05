
package xbean;

public interface GlobalActivityOpenInfos extends xdb.Bean {
	public GlobalActivityOpenInfos copy(); // deep clone
	public GlobalActivityOpenInfos toData(); // a Data instance
	public GlobalActivityOpenInfos toBean(); // a Bean instance
	public GlobalActivityOpenInfos toDataIf(); // a Data instance If need. else return this
	public GlobalActivityOpenInfos toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<xbean.TimeRange> getInfos(); // 
	public java.util.List<xbean.TimeRange> getInfosAsData(); // 
	public boolean getEnable(); // 

	public void setEnable(boolean _v_); // 
}
