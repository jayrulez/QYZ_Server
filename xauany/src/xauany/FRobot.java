package xauany;

public class FRobot {
	
	private static class ReserveRobotidIf extends xdb.Procedure{
		@Override
		protected boolean process(){
			xbean.RobotId robotid = xtable.Robotids.get(0);
			if(robotid == null){
				robotid = xbean.Pod.newRobotId();
				xtable.Robotids.insert(0, robotid);
			}
			if(robotid.getMaxuserid() <= 0){
				long minuserid = -1;
				long maxuserid = -1;
				for(int i = 0; i < xbean.RobotId.ROBOT_COUNT; i++){
					//预留userid
					maxuserid = xtable.Users.getAutoKey().next();
					if(minuserid == -1){
						minuserid = maxuserid;
					}
				}
				
				robotid.setMinuserid(minuserid / 4096);
				robotid.setMaxuserid(maxuserid / 4096);
			}
			
			xdb.Trace.warn("robot userid = " + robotid.getMinuserid() + "----------" + robotid.getMaxuserid());
			return true;
		}
	}
	
	/**
	 * auany在启动的时候调用;预留一部分id给机器人用
	 */
	public static void reserveRobotidIf(){
		new ReserveRobotidIf().call();
	}
	
	public static boolean isRobot(long userid){
		xbean.RobotId robotid = xtable.Robotids.get(0);
		if(robotid != null){
			return userid >= robotid.getMinuserid() && userid <= robotid.getMaxuserid();
		}
		
		return false;
	}
}
