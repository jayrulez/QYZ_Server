package lx.gs.marriage;

import cfg.CfgMgr;
import lx.gs.item.FItem;
import lx.gs.marriage.msg.SMarriageInfo;

public final class FMarriage {
	public final static int NORMAL_CAILI = CfgMgr.marrigeconfig.marrigepack.get(0).marrigepackid;//普通彩礼
	public final static int LUXURY_CAILI = CfgMgr.marrigeconfig.marrigepack.get(1).marrigepackid;//豪华彩礼
	public final static int XIUSHU = CfgMgr.marrigeconfig.divorceitemid;//休书
    public final static long PROPOSE_INTERVAL = 3 * 60 * 1000;//向不同的人求婚的时间间隔
    public final static int DIVORCE_MAIL_ID = 12;//离婚邮件通知
	public static xbean.RoleMarriage getMarriageInfo(long roleid){
		xbean.RoleMarriage marryInfo = xtable.Rolemarriageinfo.get(roleid);
		if(marryInfo == null){
			marryInfo = xbean.Pod.newRoleMarriage();
			xtable.Rolemarriageinfo.insert(roleid, marryInfo);
		}
		return marryInfo;
	}
	
	/**
	 * 判断角色拥有的彩礼类型
	 * @param roleid
	 * @return 返回0表示没有彩礼
	 */
	public static int cailiType(long roleid){
		if(FItem.getItemNum(roleid, FMarriage.NORMAL_CAILI, true) > 0){
			return NORMAL_CAILI;
		}
		if(FItem.getItemNum(roleid, FMarriage.LUXURY_CAILI, true) > 0){
			return LUXURY_CAILI;
		}
		return 0;
	}


    public static xio.Protocol createInfo(long roleid){
        SMarriageInfo response = new SMarriageInfo();
        xbean.RoleMarriage info = getMarriageInfo(roleid);
        response.coupleroleid = info.getCoupleroleid();
        response.curproposeid = info.getCurproposeid();
        response.startproposetime = info.getStartproposetime();
        return response;
    }
}
