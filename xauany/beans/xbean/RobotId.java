
package xbean;

public interface RobotId extends xdb.Bean {
	public RobotId copy(); // deep clone
	public RobotId toData(); // a Data instance
	public RobotId toBean(); // a Bean instance
	public RobotId toDataIf(); // a Data instance If need. else return this
	public RobotId toBeanIf(); // a Bean instance If need. else return this

	public final static int ROBOT_COUNT = 10000; // 预留机器人数量

	public long getMinuserid(); // 
	public long getMaxuserid(); // 

	public void setMinuserid(long _v_); // 
	public void setMaxuserid(long _v_); // 
}
