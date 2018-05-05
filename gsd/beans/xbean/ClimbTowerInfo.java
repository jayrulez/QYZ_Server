
package xbean;

public interface ClimbTowerInfo extends xdb.Bean {
	public ClimbTowerInfo copy(); // deep clone
	public ClimbTowerInfo toData(); // a Data instance
	public ClimbTowerInfo toBean(); // a Bean instance
	public ClimbTowerInfo toDataIf(); // a Data instance If need. else return this
	public ClimbTowerInfo toBeanIf(); // a Bean instance If need. else return this

	public int getMaxfloorid(); // 
	public int getCosttime(); // 秒数

	public void setMaxfloorid(int _v_); // 
	public void setCosttime(int _v_); // 秒数
}
