
package xbean;

public interface Forbid extends xdb.Bean {
	public Forbid copy(); // deep clone
	public Forbid toData(); // a Data instance
	public Forbid toBean(); // a Bean instance
	public Forbid toDataIf(); // a Data instance If need. else return this
	public Forbid toBeanIf(); // a Bean instance If need. else return this

	public final static int LOGIN = 1; // 
	public final static int TALK = 2; // 

	public java.util.Map<Integer, xbean.ForbidItem> getItems(); // 
	public java.util.Map<Integer, xbean.ForbidItem> getItemsAsData(); // 

}
