
package xbean;

public interface GroupTitle extends xdb.Bean {
	public GroupTitle copy(); // deep clone
	public GroupTitle toData(); // a Data instance
	public GroupTitle toBean(); // a Bean instance
	public GroupTitle toDataIf(); // a Data instance If need. else return this
	public GroupTitle toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.Title> getTitles(); // 称号信息
	public java.util.Map<Integer, xbean.Title> getTitlesAsData(); // 称号信息

}
