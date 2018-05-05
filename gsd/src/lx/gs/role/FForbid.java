package lx.gs.role;

public class FForbid {
	private FForbid(){
	}
	
	/**
	 * 角色是否被禁止登陆
	 * @param roleid
	 * @return
	 */
	public static boolean isForbidLogin(long roleid){
		return isForbid(roleid, xbean.Forbid.LOGIN);
	}
	
	public static xbean.ForbidItem getLoginItem(long roleid){
		return getItem(roleid, xbean.Forbid.LOGIN);
	}
	
	public static xbean.ForbidItem getTalkItem(long roleid){
		return getItem(roleid, xbean.Forbid.TALK);
	}
	
	/**
	 * 角色是否被禁止发言
	 * @param roleid
	 * @return
	 */
	public static boolean isForbidTalk(long roleid){
		return isForbid(roleid, xbean.Forbid.TALK);
	}
	
	private static boolean isForbid(long roleid, int forbiditemid){
		return null != getItem(roleid, forbiditemid);
	}
	
	public static xbean.ForbidItem getItem(long roleid, int forbiditemid){
		xbean.Forbid forbid = xtable.Forbids.get(roleid);
		if(forbid == null){
			return null;
		}
		xbean.ForbidItem item = forbid.getItems().get(forbiditemid);
		if(item != null){
			if(System.currentTimeMillis() < item.getForbidrealsetime()){
				return item;
			}
			else{
				forbid.getItems().remove(forbiditemid);//禁止已经解除
			}
		}
		
		return null;
	}
	
	/**
	 * 禁止某个角色的某项动作
	 * @param roleid
	 * @param forbiditemid
	 * @param forbidtime
	 * @param desc
	 * @param notifytouser
	 */
	public static void forbid(long roleid, int forbiditemid, long forbidtime, String desc, String notifytouser){
		xbean.Forbid forbid = xtable.Forbids.get(roleid);
		if(forbid == null){
			forbid = xbean.Pod.newForbid();
			xtable.Forbids.insert(roleid, forbid);
		}
		xbean.ForbidItem item = xbean.Pod.newForbidItem();
		item.setForbidtimeinterval(forbidtime);
		item.setForbidrealsetime(System.currentTimeMillis() + forbidtime);
		item.setDesc(desc);
		item.setNotifytouser(notifytouser);
		
		forbid.getItems().put(forbiditemid, item);
	}
	
	/**
	 * 解除禁止动作
	 * @param roleid
	 * @param forbiditemid
	 */
	public static void delete(long roleid, int forbiditemid){
		xbean.Forbid forbid = xtable.Forbids.get(roleid);
		if(forbid != null){
			forbid.getItems().remove(forbiditemid);
		}
	}
}
