
package xbean;

public interface GlobalAllActivity extends xdb.Bean {
	public GlobalAllActivity copy(); // deep clone
	public GlobalAllActivity toData(); // a Data instance
	public GlobalAllActivity toBean(); // a Bean instance
	public GlobalAllActivity toDataIf(); // a Data instance If need. else return this
	public GlobalAllActivity toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfos(); // 
	public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> getOpeninfosAsData(); // 
	public java.util.Map<Integer, xbean.GlobalActivity> getDatas(); // 
	public java.util.Map<Integer, xbean.GlobalActivity> getDatasAsData(); // 

}
