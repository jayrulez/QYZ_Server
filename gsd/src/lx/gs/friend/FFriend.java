package lx.gs.friend;

import cfg.friend.MaimaiRelationshipType;
import cfg.role.GenderType;
import gnet.link.Role;
import lx.gs.achievement.FAchievement;
import lx.gs.friend.msg.SAcceptMMNotify;
import lx.gs.friend.msg.SAddEnemyNotify;
import lx.gs.friend.msg.SDeleteMMForProposeNotify;
import lx.gs.friend.msg.SDeleteMMNotify;
import xbean.RoleRelation;
import xdb.Lockeys;

import java.util.*;

public class FFriend {

	/**
	 * 获取角色的所有好友相关的表信息
	 * 
	 * @param roleid
	 * @return
	 */
	public static xbean.RoleFriendsInfo getRoleFriendsInfo(long roleid) {
		xbean.RoleFriendsInfo info = xtable.Rolefriendsinfo.get(roleid);
        if (null == info) {
            info = xbean.Pod.newRoleFriendsInfo();
            xtable.Rolefriendsinfo.insert(roleid, info);
        }
		return info;
	}

	public static xbean.IdolCharmInfo getIdolCharmInfo(long idolid) {
		xbean.IdolCharmInfo charminfo = xtable.Idolcharm.get(idolid);
		if (charminfo == null) {
			charminfo = xbean.Pod.newIdolCharmInfo();
			charminfo.setCharm(0);
			charminfo.setGuarddegree(0);
			charminfo.setGuardid(0);
			charminfo.setGuardtime(0L);
			xtable.Idolcharm.add(idolid, charminfo);
		}
		return charminfo;
	}

	private static void removeFriend(long myroleid, long toroleid) {
		xbean.RoleFriendsInfo info = xtable.Rolefriendsinfo.get(myroleid);
		info.getFriends().remove(toroleid);
	}

	public static void setMMAuthorization(long roleid, int allowfriend, int allowstranger) {
		xbean.RoleFriendsInfo info = xtable.Rolefriendsinfo.get(roleid);
		info.setIsallowfriendgetmm(allowfriend);
		info.setIsallowstrangergetmm(allowstranger);
	}

	public static void onRoleDelete(long roleid) {
		xbean.RoleFriendsInfo info = FFriend.getRoleFriendsInfo(roleid);
		for (java.util.Map.Entry<Long, xbean.RoleFriend> e : info.getFriends().entrySet()) {
			removeFriend(e.getKey(), roleid);
		}
		xtable.Rolefriendsinfo.remove(roleid);
	}

	public static boolean isFriendOfRole(long myroleid, long toroleid) {
		xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myroleid);
		xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toroleid);
		if (null == myinfo || null == toinfo) {
			return false;
		}
        return myinfo.getFriends().containsKey(toroleid) && toinfo.getFriends().containsKey(myroleid);
    }

    public static void updateRelationAchievement(long roleid, Map<Integer, RoleRelation> relations, int relation) {
        if(relation == MaimaiRelationshipType.SuDi){
            return;
        }
        final int achievementType = FFriendModule.relation2achievetype.get(relation);
        FAchievement.updateCounter(roleid, achievementType, relations.entrySet().stream()
                .filter(f -> FFriendModule.relation2achievetype.get(f.getKey()) == achievementType).mapToInt(f -> f.getValue().getRolelist().size()).sum());
    }

	public static List<lx.gs.friend.msg.RoleShowInfo> getRandomOnLineRoles(long myroleid) {
		List<lx.gs.friend.msg.RoleShowInfo> result = new ArrayList<lx.gs.friend.msg.RoleShowInfo>();

		Map<Long, xbean.RoleFriend> friends = xtable.Rolefriendsinfo.selectFriends(myroleid);
        final int myLevel = xtable.Roleinfos.selectLevel(myroleid);

		// 获取所有在线的角色，随机从某个位置开始查找符合条件的可以交友的角色
		Role[] allRoles = gnet.link.Onlines.getInstance().roles();
//		xdb.Trace.info("All Online roles size=" + allRoles.length);

		for (int index : common.Utils.getMutiRandom(0, allRoles.length, FFriendModule.RANDOM_FRIEND_NUM)) {
			long id = allRoles[index].getRoleid();
			if (id == myroleid)
				continue;
			final int otherLevel = xtable.Roleinfos.selectLevel(id);
			if (friends != null && friends.containsKey(id))
				continue;
			int detal = java.lang.Math.abs(otherLevel - myLevel);
			if (detal > FFriendModule.RANDOM_FRIEND_LEVEL_GAP) {
				continue;
			}
			result.add(FFriend.makeProtocolRoleShowInfo(id, 0));
		}
		return result;
	}

	public static lx.gs.friend.msg.FriendInfo makeProtocolBasicFriendInfo(long roleid, xbean.RoleFriend roleFriend) {
		lx.gs.friend.msg.FriendInfo fresult = new lx.gs.friend.msg.FriendInfo();
		fresult.roleinfo = FFriend.makeProtocolRoleShowInfo(roleid, roleFriend.getTime());
		xtable.Rolefriendsinfo.getTable().select(roleid, info -> {
            fresult.charmdegree = info.getCharmdegree();
            return info;
        });
		fresult.frienddegree = roleFriend.getFrienddegress();
		fresult.relation = roleFriend.getRelation();
		return fresult;
	}

	public static lx.gs.friend.msg.RoleShowInfo makeProtocolRoleShowInfo(long roleid, long time) {
		lx.gs.friend.msg.RoleShowInfo fresult = new lx.gs.friend.msg.RoleShowInfo();
        fresult.roleid = roleid;
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            fresult.rolename = roleinfo.getName();
            fresult.level = roleinfo.getLevel();
            fresult.profession = roleinfo.getProfession();
            fresult.viplevel = roleinfo.getViplevel();
            fresult.gender = roleinfo.getGender();
            fresult.time = time;
            return roleinfo;
        });
        fresult.online = common.Utils.toByte(gnet.link.Onlines.getInstance().find(roleid) != null);
        fresult.attackpower = xtable.Roleattrs.selectRolecombatpower(roleid);
		return fresult;
	}

    public static lx.gs.friend.msg.EnemyShowInfo makeProtocolEnemyShowInfo(long roleid, xbean.Enemyinfo enemyinfo) {
        lx.gs.friend.msg.EnemyShowInfo fresult = new lx.gs.friend.msg.EnemyShowInfo();
        fresult.roleid = roleid;
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            fresult.rolename = roleinfo.getName();
            fresult.level = roleinfo.getLevel();
            fresult.profession = roleinfo.getProfession();
            fresult.viplevel = roleinfo.getViplevel();
            fresult.gender = roleinfo.getGender();
            fresult.time = enemyinfo.getLastbekilltime();
            return roleinfo;
        });
        fresult.online = common.Utils.toByte(gnet.link.Onlines.getInstance().find(roleid) != null);
        fresult.attackpower = xtable.Roleattrs.selectRolecombatpower(roleid);
        fresult.killtime = enemyinfo.getKillnum();
        fresult.bekilltime = enemyinfo.getBekillnum();
        return fresult;
    }

	public static java.util.HashMap<Integer, lx.gs.friend.msg.MMInfoList> makeProtocolMMInfo(
			xbean.RoleFriendsInfo role) {
		java.util.HashMap<Integer, lx.gs.friend.msg.MMInfoList> result = new java.util.HashMap<Integer, lx.gs.friend.msg.MMInfoList>();
		for (java.util.Map.Entry<Integer, xbean.RoleRelation> e : role.getRelationinfo().entrySet()) {
			lx.gs.friend.msg.MMInfoList info = new lx.gs.friend.msg.MMInfoList();
			for (long rid : e.getValue().getRolelist()) {
				info.mmlist.add(makeProtocolRoleShowInfo(rid, 0));
			}
			result.put(e.getKey(), info);
		}
		return result;
	}
	
	public static List<Long> getMMInfoList(long roleid){
		List<Long> result = new ArrayList<Long>();
		xbean.RoleFriendsInfo role = xtable.Rolefriendsinfo.get(roleid);
		for (java.util.Map.Entry<Integer, xbean.RoleRelation> e : role.getRelationinfo().entrySet()) {
			for (long rid : e.getValue().getRolelist()) {
				result.add(rid);
			}
		}
		return result;
	}

	/**
	 * 增加屏蔽人，持有的锁myroleid，toroleid，rolelock
	 * 
	 * @param myroleid
	 * @param toroleid
	 * @return
	 */
	public static long addBlackRole(long myroleid, long toroleid) {
	    Map<Long, Long> blackList = getRoleFriendsInfo(myroleid).getBlacklist();
        final long now = System.currentTimeMillis();
        blackList.put(toroleid, now);
		// 增加屏蔽的时候如果列表超过最大值，删除掉时间最老的仇人，并按照击杀时间排序
		if (blackList.size() > FFriendModule.MAX_BLACKLIST) {
            final long oldest = blackList.entrySet().stream().min((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get().getKey();
			blackList.remove(oldest);
		}
		return now;
	}

	/**
	 * 增加仇人，持有的锁myroleid，toroleid，rolelock
	 * 
	 * @param myroleid 被击杀的
	 * @param toroleid 杀人的
	 * @return
	 */
	public static boolean addEnemyRole(long myroleid, long toroleid) {
        Lockeys.lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myroleid, toroleid));
		xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myroleid);
		xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(toroleid);
        long now = System.currentTimeMillis();

        final Map<Long, xbean.Enemyinfo> myEnemys = myinfo.getEnemylist();
		// 增加仇人的时候如果列表超过最大值，删除掉时间最老的仇人，并按照击杀时间排序
		if (!myEnemys.containsKey(toroleid) && myEnemys.size() >= FFriendModule.MAX_ENEMYLIST) {
			myinfo.getEnemylist().remove(Collections.min(myEnemys.entrySet(), (e1, e2) ->
                    Long.compare(e1.getValue().getLastbekilltime(), e2.getValue().getLastbekilltime())).getKey());
		}
        xbean.Enemyinfo newEnemyInfo = myEnemys.get(toroleid);
        if(newEnemyInfo == null){
            newEnemyInfo = xbean.Pod.newEnemyinfo();
            myEnemys.put(toroleid, newEnemyInfo);
        }
        newEnemyInfo.setBekillnum(newEnemyInfo.getBekillnum() + 1);
        newEnemyInfo.setLastbekilltime(now);
        SAddEnemyNotify mynotify = new SAddEnemyNotify();
        mynotify.enemy = FFriend.makeProtocolEnemyShowInfo(toroleid, newEnemyInfo);
        xdb.Transaction.tsendWhileCommit(myroleid, mynotify);
        xbean.Enemyinfo toEnemyInfo = toinfo.getEnemylist().get(myroleid);
        if (toEnemyInfo != null) {
            toEnemyInfo.setKillnum(toEnemyInfo.getKillnum() + 1);
            SAddEnemyNotify toNotify = new SAddEnemyNotify();
            toNotify.enemy = FFriend.makeProtocolEnemyShowInfo(myroleid, toEnemyInfo);
            xdb.Transaction.tsendWhileCommit(toroleid, toNotify);
        }
        return true;
	}

	public static boolean multicastAllFriend(long roleid, xio.Protocol pro) {
		xbean.RoleFriendsInfo friendsInfo = getRoleFriendsInfo(roleid);
		if (friendsInfo == null)
			return false;
		Set<Long> roleids = friendsInfo.getFriends().keySet();
		gnet.link.Onlines.getInstance().multicast(roleids, pro);
		return true;
	}
	
	public static boolean multicastAllEnemy(long roleid, xio.Protocol pro) {
		xbean.RoleFriendsInfo friendsInfo = getRoleFriendsInfo(roleid);
		if (friendsInfo == null)
			return false;
		Set<Long> roleids = friendsInfo.getEnemylist().keySet();
		gnet.link.Onlines.getInstance().multicast(roleids, pro);
		return true;
	}
	
	public static boolean multicastAllMM(long roleid, xio.Protocol pro) {
		List<Long> roleids = getMMInfoList(roleid);
		gnet.link.Onlines.getInstance().multicast(roleids, pro);
		return true;
	}


    public static void setBanlvInfo(long proposeid, long beproposeid){
        xbean.RoleFriendsInfo proInfo = getRoleFriendsInfo(proposeid);
        xbean.RoleFriendsInfo beproInfo = getRoleFriendsInfo(beproposeid);
        long now = System.currentTimeMillis();
        if(xtable.Roleinfos.selectGender(proposeid) == GenderType.MALE){//脉脉分了男女伴侣
            xbean.RoleRelation proRe = xbean.Pod.newRoleRelation();
            proRe.getRolelist().add(beproposeid);
            proInfo.getRelationinfo().put(MaimaiRelationshipType.BanLvNv, proRe);
            proInfo.getFriends().get(beproposeid).setRelation(MaimaiRelationshipType.BanLvNv);
            sendBanlvChangeNotify(proposeid, MaimaiRelationshipType.BanLvNv, beproposeid, now);
            updateRelationAchievement(proposeid, proInfo.getRelationinfo(), MaimaiRelationshipType.BanLvNv);

            xbean.RoleRelation beproRe = xbean.Pod.newRoleRelation();
            beproRe.getRolelist().add(proposeid);
            beproInfo.getRelationinfo().put(MaimaiRelationshipType.BanLvNan, beproRe);
            beproInfo.getFriends().get(proposeid).setRelation(MaimaiRelationshipType.BanLvNan);
            updateRelationAchievement(beproposeid, beproInfo.getRelationinfo(), MaimaiRelationshipType.BanLvNan);

            sendBanlvChangeNotify(beproposeid, MaimaiRelationshipType.BanLvNan, proposeid, now);
        }else {
            xbean.RoleRelation proRe = xbean.Pod.newRoleRelation();
            proRe.getRolelist().add(beproposeid);
            proInfo.getRelationinfo().put(MaimaiRelationshipType.BanLvNan, proRe);
            proInfo.getFriends().get(beproposeid).setRelation(MaimaiRelationshipType.BanLvNan);
            updateRelationAchievement(proposeid, proInfo.getRelationinfo(), MaimaiRelationshipType.BanLvNan);
            sendBanlvChangeNotify(proposeid, MaimaiRelationshipType.BanLvNan, beproposeid, now);

            xbean.RoleRelation beproRe = xbean.Pod.newRoleRelation();
            beproRe.getRolelist().add(proposeid);
            beproInfo.getRelationinfo().put(MaimaiRelationshipType.BanLvNv, beproRe);
            beproInfo.getFriends().get(proposeid).setRelation(MaimaiRelationshipType.BanLvNv);
            updateRelationAchievement(beproposeid, beproInfo.getRelationinfo(), MaimaiRelationshipType.BanLvNv);
            sendBanlvChangeNotify(beproposeid, MaimaiRelationshipType.BanLvNv, proposeid, now);
        }
        deleteOtherAfterPropose(proposeid, beproposeid, beproInfo);
        deleteOtherAfterPropose(beproposeid, proposeid, proInfo);
    }

    public static void sendBanlvChangeNotify(long roleid, int type, long inforoleid, long time){
        SAcceptMMNotify notify = new SAcceptMMNotify();
        notify.mmtype = type;
        notify.mmroleinfo = makeProtocolRoleShowInfo(inforoleid, time);
        xdb.Transaction.tsendWhileCommit(roleid, notify);
    }

    /**
     * 结婚后如果还有别的脉脉关系，需要删除
     * @param todeleteroleid
     * @param deleteroleid
     * @param info
     */
    public static void deleteOtherAfterPropose(long todeleteroleid, long deleteroleid, xbean.RoleFriendsInfo info){
        for(Map.Entry<Integer, xbean.RoleRelation> e : info.getRelationinfo().entrySet()){
            if(e.getKey() == MaimaiRelationshipType.BanLvNan || e.getKey() == MaimaiRelationshipType.BanLvNv){
                continue;
            }
            if(e.getValue().getRolelist().remove(todeleteroleid)){//如果包含该关系,通知删除人删除了该关系
                SDeleteMMForProposeNotify nofity = new SDeleteMMForProposeNotify();
                nofity.mmtype = e.getKey();
                nofity.roleid = todeleteroleid;
                xdb.Transaction.tsendWhileCommit(deleteroleid, nofity);
                break;
            }
        }
    }

    public static void clearBanlvInfo(long proposeid, long beproposeid){ //清除伴侣信息
        xbean.RoleFriendsInfo proInfo = getRoleFriendsInfo(proposeid);
        xbean.RoleFriendsInfo beproInfo = getRoleFriendsInfo(beproposeid);
        proInfo.getRelationinfo().remove(MaimaiRelationshipType.BanLvNv);
        proInfo.getRelationinfo().remove(MaimaiRelationshipType.BanLvNan);
        proInfo.getFriends().get(beproposeid).setRelation(MaimaiRelationshipType.NULL);
        beproInfo.getRelationinfo().remove(MaimaiRelationshipType.BanLvNv);
        beproInfo.getRelationinfo().remove(MaimaiRelationshipType.BanLvNan);
        beproInfo.getFriends().get(proposeid).setRelation(MaimaiRelationshipType.NULL);
        SDeleteMMNotify nannotify = new SDeleteMMNotify();
        nannotify.mmtype = MaimaiRelationshipType.BanLvNan;
        SDeleteMMNotify nvnotify = new SDeleteMMNotify();
        nvnotify.mmtype = MaimaiRelationshipType.BanLvNv;
        Set<Long> roleids = new HashSet<>();
        roleids.add(proposeid);
        roleids.add(beproposeid);
        xdb.Transaction.tsendWhileCommit(roleids, nannotify);
        xdb.Transaction.tsendWhileCommit(roleids, nvnotify);
    }

    /**
     * 判断roleid1的黑名单列表中是否有roleid2
     * @param roleid1
     * @param roleid2
     */
    public static boolean isInBlackList(long roleid1, long roleid2){
        final xbean.RoleFriendsInfo info = xtable.Rolefriendsinfo.get(roleid1);
        return info == null || info.getBlacklist().containsKey(roleid2);
    }

}
