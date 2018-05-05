package lx.gs.friend;

import cfg.Const;
import cfg.achievement.CounterType;
import cfg.friend.MaimaiRelationshipType;
import gnet.link.Onlines;
import lx.gs.friend.msg.SFriendOnlineNotify;
import lx.gs.friend.msg.SGuardLoginNotify;

import java.util.HashMap;


public enum FFriendModule implements gs.Module, gs.RoleCreateListener, gs.RoleLoginListener {

	INSTANCE;
	
	public final static int MAX_FRIENDLIST = 100;
	public final static int MAX_BLACKLIST = 200;
	public final static int MAX_ENEMYLIST = 100;
	public final static int RANDOM_FRIEND_NUM = 8;
	public final static int	RANDOM_FRIEND_LEVEL_GAP = 10;

    final static HashMap<Integer, Integer> relation2achievetype = new HashMap<>();
    static {
        relation2achievetype.put(MaimaiRelationshipType.BanLvNan, CounterType.COMPANION_NUM);
        relation2achievetype.put(MaimaiRelationshipType.BanLvNv, CounterType.COMPANION_NUM);
        relation2achievetype.put(MaimaiRelationshipType.YiXiong, CounterType.YIXIONG_NUM);
        relation2achievetype.put(MaimaiRelationshipType.YiJie, CounterType.YIJIE_NUM);
        relation2achievetype.put(MaimaiRelationshipType.YiDi, CounterType.YIDI_NUM);
        relation2achievetype.put(MaimaiRelationshipType.YiMei, CounterType.YIMEI_NUM);
        relation2achievetype.put(MaimaiRelationshipType.LanYan, CounterType.TOTAL_SOULMATE_NUM);
        relation2achievetype.put(MaimaiRelationshipType.HongYan, CounterType.TOTAL_SOULMATE_NUM);
        relation2achievetype.put(MaimaiRelationshipType.XiongDi, CounterType.TOTAL_CONFIDANT_NUM);
        relation2achievetype.put(MaimaiRelationshipType.GuiMi, CounterType.TOTAL_CONFIDANT_NUM);
        relation2achievetype.put(MaimaiRelationshipType.SuDi, Const.NULL);
    }

	@Override
	public void start() {
		
	}

	@Override
	public void onRoleCreateInProcedure(long roleid) {
		xbean.RoleFriendsInfo info = xbean.Pod.newRoleFriendsInfo();
		info.setIsallowfriendgetmm(cfg.friend.Const.FRIEND_GET_MM);
		info.setIsallowstrangergetmm(cfg.friend.Const.STRANGE_GET_MM);
		xtable.Rolefriendsinfo.add(roleid, info);
	}

	@Override
	public void onRoleDeleteInProcedure(long roleid) {
//		 FFriend.onRoleDelete(roleid);
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		SFriendOnlineNotify onlinenotify = new SFriendOnlineNotify();
		onlinenotify.roleid = roleid;
		onlinenotify.online = 1;
		FFriend.multicastAllFriend(roleid, onlinenotify);
		FFriend.multicastAllEnemy(roleid, onlinenotify);

		for (long idolid : cfg.CfgMgr.idol.keySet()) {
			xbean.IdolCharmInfo charminfo = FFriend.getIdolCharmInfo(idolid);
			if(charminfo.getGuardid() == roleid){
				xbean.RoleInfo myroleinfo = xtable.Roleinfos.get(roleid);
				if(myroleinfo != null){
					SGuardLoginNotify guardloginnotify = new SGuardLoginNotify();
					guardloginnotify.guardid = roleid;
					guardloginnotify.guardname = myroleinfo.getName();
					guardloginnotify.idolid = idolid;
					Onlines.getInstance().broadcast(guardloginnotify);
				}
			}
		}
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		SFriendOnlineNotify onlinenotify = new SFriendOnlineNotify();
		onlinenotify.roleid = roleid;
		onlinenotify.online = 0;
		FFriend.multicastAllFriend(roleid, onlinenotify);
		FFriend.multicastAllEnemy(roleid, onlinenotify);
	}
	
}