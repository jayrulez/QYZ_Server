
package xbean;

public interface RoleChat extends xdb.Bean {
	public RoleChat copy(); // deep clone
	public RoleChat toData(); // a Data instance
	public RoleChat toBean(); // a Bean instance
	public RoleChat toDataIf(); // a Data instance If need. else return this
	public RoleChat toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<String> getChatface(); // 
	public java.util.Set<String> getChatfaceAsData(); // 

}
