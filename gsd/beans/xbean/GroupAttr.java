
package xbean;

public interface GroupAttr extends xdb.Bean {
	public GroupAttr copy(); // deep clone
	public GroupAttr toData(); // a Data instance
	public GroupAttr toBean(); // a Bean instance
	public GroupAttr toDataIf(); // a Data instance If need. else return this
	public GroupAttr toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, Float> getAttrs(); // 
	public java.util.Map<Integer, Float> getAttrsAsData(); // 

}
