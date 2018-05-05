package lx.gs.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.achievement.CounterType;
import cfg.active.EventNum;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.currency.CurrencyType;
import cfg.ectype.*;
import cfg.equip.RideType;
import cfg.fight.AgentType;
import cfg.fight.CampType;
import cfg.fight.PKState;
import cfg.item.EItemBindType;
import cfg.monster.Monster;
import cfg.monster.MonsterType;
import cfg.role.GenderType;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import common.Rpc;
import common.TaskQueue;
import common.Utils;
import gnet.MapClient;
import gnet.ServiceClient;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.AProcedure;
import gs.RefObject;
import lx.gs.SError;
import lx.gs.achievement.FAchievement;
import lx.gs.activity.huiwu.HuiWu;
import lx.gs.activity.worldboss.*;
import lx.gs.activity.worldmonster.msg.SKillExpMonsterNums;
import lx.gs.amulet.FAmulet;
import lx.gs.arena.FArena;
import lx.gs.bonus.BonusStrategy;
import lx.gs.bonus.FBonus;
import lx.gs.chat.FChat;
import lx.gs.cmd.FCondition;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.dress.FDress;
import lx.gs.equip.FEquip;
import lx.gs.error.FError;
import lx.gs.event.ClimbTowerUpEvent;
import lx.gs.event.EventModule;
import lx.gs.event.TeamFightWinEvent;
import lx.gs.event.TeamSpeedWinEvent;
import lx.gs.family.FFamily;
import lx.gs.family.msg.SPartyEndNotify;
import lx.gs.friend.FFriend;
import lx.gs.item.FItem;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.mail.FMail;
import lx.gs.map.guardtower.GuardTowerModule;
import lx.gs.map.msg.*;
import lx.gs.map.teamspeed.TeamSpeedModule;
import lx.gs.mount.FRide;
import lx.gs.pet.FPet;
import lx.gs.role.FRole;
import lx.gs.role.FRoleAttr;
import lx.gs.role.RoleModule;
import lx.gs.role.msg.PetExp;
import lx.gs.role.msg.RoleShowInfo4;
import lx.gs.role.msg.SKillMonster;
import lx.gs.role.title.FTitle;
import lx.gs.skill.FSkill;
import lx.gs.talisman.FTalisman;
import lx.gs.task.FTask;
import lx.gs.task.condition.EventIds;
import lx.gs.team.FTeam;
import lx.matcher.GAddMultiMatch;
import lx.matcher.GCancelMultiMatch;
import lx.matcher.GCancelTeamFightMatch;
import map.MapUtils;
import map.msg.*;
import map.msg.Bonus;
import xbean.HeroesGroupInfo;
import xbean.*;
import xdb.Lockeys;
import xdb.Procedure;
import xdb.Trace;
import xdb.Transaction;
import xio.Protocol;
import xtable.Roleinfos;

import java.lang.System;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static lx.gs.logger.By.Ectype;
import static lx.gs.map.MapModule.players;
import static lx.gs.map.MapModule.worldsByLines;

public final class FMap {

	public static xbean.RoleMap get(long roleid) {
		xbean.RoleMap info = xtable.Rolemaps.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleMap();
			xtable.Rolemaps.insert(roleid, info);
		}
		return info;
	}


	public static RoleEctype getEctype(long roleid) {
		xbean.RoleEctype info = xtable.Roleectypes.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleEctype();
			xtable.Roleectypes.insert(roleid, info);

            RoleEctype finalInfo = info;
            CfgMgr.herosets.ectypemsg_id.values().forEach(pet -> {
                HeroesGroupInfo groupInfo = Pod.newHeroesGroupInfo();
                groupInfo.setEctypeid(randomHeroEctypeId(pet));
                finalInfo.getHerogroups().put(pet.id, groupInfo);
            });
		}
		return info;
	}

    public static int randomHeroEctypeId(HeroEctypeMsg heroEctypeMsg){
        List<Integer> rateList = heroEctypeMsg.ectyperandom.stream().map(r -> r.weight).collect(Collectors.toList());
        final int randomIndex = Utils.getRandomIndex(rateList);
        return heroEctypeMsg.ectyperandom.get(randomIndex).id;
    }

	public static boolean isInWorldOrFamily(long roleid) {
		final RoleContext pi = players.get(roleid);
		return pi != null && pi.isInWorldOrFamily();
	}

    public static boolean isInWorld(long roleid) {
        final RoleContext pi = players.get(roleid);
        return pi != null && pi.isInWorld();
    }

    public static boolean isInFamily(long roleid){
        final RoleContext pi = players.get(roleid);
        return pi != null && pi.isInFamily();
    }

	static long makeWorldMapid(int worldid, int lineid) {
		return Utils.makeId(gs.Utils.getServerId(), MapModule.createWorldAtServerid, worldid, lineid);
	}

	static map.msg.Vector3 x2m(xbean.Vector3 position) {
		return new map.msg.Vector3(position.getX(), position.getY(), position.getZ());
	}

	static void m2x(xbean.Vector3 x, map.msg.Vector3 m) {
		x.setX(m.x);
		x.setY(m.y);
		x.setZ(m.z);
	}

	public static map.msg.Vector3 createOrient(double angle) {
		final double radian = Math.toRadians(angle);
		final double sin = Math.sin(radian);
		final double cos = Math.cos(radian);
		return new map.msg.Vector3((float)sin, 0, (float)cos);
	}

    public static int getArenaEctypeid() {
        return MapModule.ARENA_ECTYPE_ID;
    }

	public static RoleContext getRoleMapInfo(long roleid) {
		return players.get(roleid);
	}

	public static void addLockOper(long roleid, RoleContext.Work work, RoleContext.Action onDone, RoleContext.Action onTimeout) {
		RoleContext pi = getRoleMapInfo(roleid);
		if(pi == null) {
			Trace.error("FMap.addLockOper roleid:{} not exist RoleContext", roleid);
			return;
		}
		pi.addOper(work, onDone, onTimeout);
	}

	public static void addLockOper(long roleid, RoleContext.Work work) {
	    addLockOper(roleid, work, null, op -> {
	        Trace.error("FMap.LockOper timeout. roleid:{}", roleid);
            //MapModule.players.remove(roleid);
        });
    }


	public static void loginEnterMap(long roleid) {
	    addLockOper(roleid, (op, param) -> op.reenterCurMap(), null, op -> {
	        TaskQueue.getNormalExecutor().execute(() -> {
	            addLockOper(roleid, (op2, param) -> op2.fallback());
            });
        });
	}

	public static void logoutLeaveMap(long roleid) {
	    addLockOper(roleid, (op, param) -> op.reserveCurMap());
	}

    public static long getAlternativeWorldMapid(int worldid, long excludeMapid) {
        final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(worldid);
        final cfg.map.WorldMap wcfg = CfgMgr.worldmap.get(worldid);
        for(MapModule.MapInfo mi : lines.values()) {
            final int playerNum = mi.playerNum;
            if(mi.mapid != excludeMapid && playerNum < wcfg.suggestplayernum) {
                return mi.mapid;
            }
        }
        return 0;
    }

    public static void leaveCurrentMap(long roleid) {
        addLockOper(roleid, (op, param) -> op.leaveThenEnterPrevMap());
    }

    public static ErrorCode enterWorld(long roleid, int worldid, int lineid) {
        final cfg.map.WorldMap wcfg = CfgMgr.worldmap.get(worldid);
        if(xtable.Roleinfos.selectLevel(roleid) < wcfg.openlevel)
            return ErrorCode.LEVEL_NOT_ENOUGH_CAN_NOT_ENTER_WORLD;
        if(lineid != 0) {
            MapModule.MapInfo mapInfo = MapModule.worldsByLines.get(worldid).get(lineid);
            if (mapInfo == null)
                return ErrorCode.MAP_NOT_EXIST;
            else if (mapInfo.playerNum >= wcfg.maxlineplayernum)
                return ErrorCode.LINE_FULL;
        }
        addLockOper(roleid, (op, param) -> {
            final RoleContext info = op.getRoleMapInfo();
            final long mapid = info.getCurMapid();

            if(common.Utils.isEctype(mapid)) {
                FError.sendNotProcedureError(roleid, ErrorCode.CANNOT_ENTER_WORLD_IN_ECTYPE);
                return RoleContext.Result.END;
            }
            final long newMapid = makeWorldMapid(worldid, lineid);
            if(common.Utils.isWorld(mapid) && common.Utils.getWorldidById(mapid) == worldid) {
                final int curlineid = common.Utils.getLineididById(mapid);
                if(curlineid == 0 || lineid == 0 || curlineid == lineid){
                    FError.sendNotProcedureError(roleid, ErrorCode.ENTER_CUR_MAP);
                    return RoleContext.Result.END;
                }
            }
            return op.leaveThenEnterNewMap(newMapid, null, null);
        });
        return ErrorCode.OK;
    }

	public static ErrorCode transferWorld(long roleid, int worldid, map.msg.Vector3 position, map.msg.Vector3 orient) {
        final cfg.map.WorldMap wcfg = CfgMgr.worldmap.get(worldid);
        if(xtable.Roleinfos.selectLevel(roleid) < wcfg.openlevel)
            return ErrorCode.LEVEL_NOT_ENOUGH_CAN_NOT_ENTER_WORLD;
        addLockOper(roleid, (op, param) -> {
            final RoleContext info = op.getRoleMapInfo();
            final long mapid = info.getCurMapid();
            if(common.Utils.isEctype(mapid)) {
                FError.sendNotProcedureError(roleid, ErrorCode.CANNOT_ENTER_WORLD_IN_ECTYPE);
                return RoleContext.Result.END;
            }
            if(common.Utils.isWorld(mapid) && common.Utils.getWorldidById(mapid) == worldid) {
                dispatchMessage(roleid, new map.msg.XTransfer(position, orient));
                return RoleContext.Result.END;
            }
            return op.leaveThenEnterNewMap(makeWorldMapid(worldid, 0), MapUtils.m2p(position), MapUtils.m2p(orient));
        });
        return ErrorCode.OK;
	}

    public interface CreateMapCallback<T extends xio.Protocol> {
        void onSucc(T builder, long mapid);

        default void onFail(T builder, int retcode) {
            Trace.error("CreateMap fail. builder:{}{} retcode:{}", builder.getClass().getName(), builder, retcode);
        }

        default void onTimeout(T builder) {
            Trace.error("CreateMap timeout. builder:{}{}", builder.getClass().getName(), builder);
        }
    }

    public static <T extends xio.Protocol> void createMapNotInProcedure(int serverid, T builder, CreateMapCallback<T> callback) {
        Rpc.send(serverid, builder, new Rpc.Callback<Protocol>() {
            @Override
            public void onResult(Protocol argument, Protocol result) {
                final MCreateMap msg = (MCreateMap) result;
                if(msg.retcode == 0) {
                   callback.onSucc(builder, msg.mapid);
                } else {
                    callback.onFail(builder, msg.retcode);
                }
            }

            @Override
            public void onTimeout(Protocol argument) {
                callback.onTimeout(builder);
            }
        });
    }

    public static <T extends xio.Protocol> void createMapInProcedure(int serverid, T builder, CreateMapCallback<T> callback) {
        TaskQueue.getNormalExecutor().execute(() -> {
            createMapNotInProcedure(serverid, builder, callback);
        });
    }

    public static void createWorldMapNotInProcedure(int worldid, int lineid) {
        final int localServerid = gs.Utils.getServerId();
        final int createAtServerid = localServerid;
        final XCreateWorldMap msg = new XCreateWorldMap(localServerid, worldid, lineid);
        final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(worldid);
        final RefObject<Integer> remainTryTime = new RefObject<>(3);
        createMapNotInProcedure(createAtServerid, msg, new CreateMapCallback<XCreateWorldMap>() {
            @Override
            public void onSucc(XCreateWorldMap builder, long mapid) {
                final MapModule.MapInfo mi = new MapModule.MapInfo(worldid, lineid);
                mi.mapid = mapid;
                mi.state = MapModule.MapState.NORMAL;
                mi.playerNum = 0;
                lines.put(lineid, mi);
               // Trace.info("CreateWorldMap succ. worldid:{} lineid:{} mapid:{}", worldid, lineid, mapid);
            }

            @Override
            public void onTimeout(XCreateWorldMap builder) {
                Trace.info("CreateWorldMap timeout. worldid:{} lineid:{} mapid:{}", worldid, lineid);
                if(--remainTryTime.value > 0)
                    createMapNotInProcedure(createAtServerid, builder, this);
            }
        });
    }

    private static int getNewLineId(int worldid, Map<Integer, MapModule.MapInfo> lines) {
        int lineid;
        for(lineid = CfgMgr.worldmap.get(worldid).initlinenum + 1 ; lines.containsKey(lineid) ; lineid++);
        return lineid;
    }

    public static void createWorldMapAndEnterNotInProcedure(int worldid, long roleid) {
        final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(worldid);
        final int lineid = getNewLineId(worldid, lines);
        Trace.info("FMap.createWorldMapAndEnterNotInProcedure worldid:{} lineid:{} roleid:{}", worldid, lineid, roleid);
        final int localServerid = gs.Utils.getServerId();
        final int createAtServerid = localServerid;
        final XCreateWorldMap msg = new XCreateWorldMap(localServerid, worldid, lineid);
        final RefObject<Integer> remainTryTime = new RefObject<>(3);
        createMapNotInProcedure(createAtServerid, msg, new CreateMapCallback<XCreateWorldMap>() {
            @Override
            public void onSucc(XCreateWorldMap builder, long mapid) {
                final MapModule.MapInfo mi = new MapModule.MapInfo(worldid, lineid);
                mi.mapid = mapid;
                mi.state = MapModule.MapState.NORMAL;
                mi.playerNum = 0;
                lines.put(lineid, mi);
                FMap.enterMapNotInProcedure(roleid, mapid);
                // Trace.info("CreateWorldMap succ. worldid:{} lineid:{} mapid:{}", worldid, lineid, mapid);
            }
//
//            @Override
//            public void onFail(XCreateWorldMap builder, int retcode) {
//                Trace.info("CreateWorldMap fail. worldid:{} lineid:{} retcode:{}", worldid, lineid, retcode);
//            }
//
            @Override
            public void onTimeout(XCreateWorldMap builder) {
                Trace.info("CreateWorldMap timeout. worldid:{} lineid:{} mapid:{}", worldid, lineid);
                if(--remainTryTime.value > 0)
                    createMapNotInProcedure(createAtServerid, builder, this);
            }
        });
    }


    public static void openEctypeInProcedure(long roleid, int serverid, xio.Protocol builder) {
        checkCancelMatch(roleid);
        TaskQueue.getNormalExecutor().execute(() -> {
            openEctypeNotInProcedure(roleid, serverid, builder);
        });
    }

    public static void openEctypeAtLocalInProcedure(long roleid, xio.Protocol builder) {
        TaskQueue.getNormalExecutor().execute(() -> {
            openEctypeNotInProcedure(roleid, gs.Utils.getServerId(), builder);
        });
    }

    public static void openEctypeNotInProcedure(long roleid, int serverid, xio.Protocol builder) {
        createMapNotInProcedure(serverid, builder, new CreateMapCallback<Protocol>() {
            @Override
            public void onSucc(Protocol builder, long mapid) {
                addLockOper(roleid, (op, param) -> {
                    final RoleContext info = op.getRoleMapInfo();
                    if(info.isInEctype()) {
                        FError.sendNotProcedureError(roleid, ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
                        return RoleContext.Result.END;
                    }
                    return op.leaveThenEnterNewMap(mapid, null, null);
                });
            }
        });
    }

    public static void openEctypeInProcedure(Collection<Long> roleids, int serverid, xio.Protocol builder) {
        roleids.forEach(FMap::checkCancelMatch);
        TaskQueue.getNormalExecutor().execute(() -> {
            openEctypeNotInProcedure(roleids, serverid, builder);
        });
    }

    public static void openEctypeNotInProcedure(Collection<Long> roleids, int serverid, xio.Protocol builder) {
        createMapNotInProcedure(serverid, builder, new CreateMapCallback<Protocol>() {
            @Override
            public void onSucc(Protocol builder, long mapid) {
                for (long roleid : roleids) {
                    addLockOper(roleid, (op, param) -> {
                        final RoleContext info = op.getRoleMapInfo();
                        if(info.isInEctype()) {
                            FError.sendNotProcedureError(roleid, ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
                            return RoleContext.Result.END;
                        }
                        return op.leaveThenEnterNewMap(mapid, null, null);
                    });
                }
            }
        });
    }

    public static void openOnePlayerEctypeInProcedure(long roleid, int ectypeid) {
        openOnePlayerEctypeInProcedure(roleid, ectypeid, false, 0);
    }

    public static void openOnePlayerEctypeInProcedure(long roleid, int ectypeid, boolean isFromTask, int param) {
        checkCancelMatch(roleid);
        // 策划要求进单人副本，退出队伍
        FTeam.quitTeam(roleid);
        final int profession = xtable.Roleinfos.selectProfession(roleid);
        TaskQueue.getNormalExecutor().execute(() -> {
            final XCreateEcypeOnePlayer msg = new XCreateEcypeOnePlayer();
            msg.roleid = roleid;
            msg.profession = profession;
            msg.serverid = gs.Utils.getServerId();
            msg.istaskectype = common.Utils.toByte(isFromTask);
            msg.ectypeid = ectypeid;
            msg.param1 = param;
            msg.param2 = 0;

            final int createServerid = msg.serverid;
            openEctypeNotInProcedure(roleid, createServerid, msg);
        });
    }

    public static void enterMapInProcedure(long roleid, long mapid) {
        TaskQueue.getNormalExecutor().execute(() -> enterMapNotInProcedure(roleid, mapid));
    }

    public static void enterMapNotInProcedure(long roleid, long mapid) {
        addLockOper(roleid, (op, param) -> {
            if (op.getRoleMapInfo().isInEctype()) {
                FError.sendNotProcedureError(roleid, ErrorCode.CANNOT_ENTER_ECTYPE_IN_ECTYPE);
                return RoleContext.Result.END;
            }
            return op.leaveThenEnterNewMap(mapid, null, null);
        });
    }

    /**
     * 匹配前先检查条件
     * @param roleids
     * @param ectypeid
     * @param conf
     * @return
     */
    public static ErrorCode checkEnrollMultiCondition(Collection<Long> roleids, int ectypeid, cfg.ectype.TeamStoryEctype conf) {
        for (long roleid : roleids) {
            xbean.RoleEctype ectypeInfo = FMap.getEctype(roleid);
            if(conf.preectypeid != -1) {
                if (!ectypeInfo.getMultistory().containsKey(conf.preectypeid)){
                    return ErrorCode.PRE_MULTI_STORY_NOT_COMPLETE;
                }
            }
            xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
            //策划要求进入次数不做限制，改为增加限制奖励次数,而且达到次数后，不在扣体力
            int usedTimes = FLimit.getLimitTimes(roleid, ConfigId.MULTI_STORY_ECTYPE, ectypeid, LimitType.DAY);
            if(usedTimes < conf.daylimit.num && roleInfo.getCurrencys().get(CurrencyType.TiLi) < conf.costtili.amount){
//                return ErrorCode.SOME_MEMBER_EXCEED_LIMIT;
                return ErrorCode.SOME_MEMBER_TILI_NOT_ENOUTH;
            }

            if(roleInfo.getLevel() < conf.openlevel.level){
                return ErrorCode.SOME_MEMBER_LEVEL_TOO_LOW;
            }
            if(!isInWorldOrFamily(roleid)){
                return ErrorCode.SOME_MEMBER_IS_IN_ECTYPE;
            }

        }
        return ErrorCode.OK;
    }

    /**
     * 检查队长的条件，策划要求队长跟队员提示不一样，蛋疼
     * @param roleid
     * @param ectypeid
     * @param conf
     * @return
     */
    public static ErrorCode checkTeamLeader(long roleid, int ectypeid, cfg.ectype.TeamStoryEctype conf) {
        xbean.RoleEctype ectypeInfo = FMap.getEctype(roleid);
        if (conf.preectypeid != -1) {
            if (!ectypeInfo.getMultistory().containsKey(conf.preectypeid)) {
                return ErrorCode.PRE_MULTI_STORY_NOT_COMPLETE;
            }
        }
        xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
        int usedTimes = FLimit.getLimitTimes(roleid, ConfigId.MULTI_STORY_ECTYPE, ectypeid, LimitType.DAY);
        if(usedTimes < conf.daylimit.num && roleInfo.getCurrencys().get(CurrencyType.TiLi) < conf.costtili.amount){
//                return ErrorCode.SOME_MEMBER_EXCEED_LIMIT;
            return ErrorCode.TILI_NOT_ENOUGH;
        }
        if (roleInfo.getLevel() < conf.openlevel.level) {
            return ErrorCode.NOT_ENOUGH_LEVEL;
        }
        if (!isInWorldOrFamily(roleid)) {
            return ErrorCode.HAS_IN_ECTTYPE;
        }

        return ErrorCode.OK;
    }

    /**
     * 准备开启多人挑战剧情副本
     * @param roleids
     * @param ectypeid
     * @return
     */
    public static XCreateEctypeMultiStory prepareMultiStoryNotInProcedure(Collection<Long> roleids, int ectypeid) {
        final XCreateEctypeMultiStory msg = new XCreateEctypeMultiStory();
        msg.serverid = gs.Utils.getServerId();
        msg.istaskectype = common.Utils.toByte(false);
        msg.ectypeid = ectypeid;
        cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(ectypeid);
        boolean prepareSuccess = true;
        boolean isSuccess = true;
        long failRoleid = 0;
        for (long i : roleids) {
            xbean.RoleInfo roleInfo = xtable.Roleinfos.get(i);
            int lvl = roleInfo.getProfession();
            msg.roles.put(i, lvl);
            int star = xtable.Roleectypes.selectMultistory(i).getOrDefault(ectypeid, 0);
            msg.rolesstar.put(i, star);
            //匹配成功扣体力,扣限制次数
            int usedTimes = FLimit.getLimitTimes(i, ConfigId.MULTI_STORY_ECTYPE, ectypeid, LimitType.DAY);
            msg.roleusedtimes.put(i, usedTimes);
            if (usedTimes < conf.daylimit.num && !FRole.checkCostCurrency(i, roleInfo, CurrencyType.TiLi, conf.costtili.amount, By.Ectype)) {
//                xdb.Trace.info("roleid = {} tili or limit time not enough", i);
                isSuccess = false;
                failRoleid = i;
                prepareSuccess = false;
                break;
            }
        }
        if (!isSuccess) {
            SPrepareMultiStoryEctypeFailNotify failNotify = new SPrepareMultiStoryEctypeFailNotify();
            failNotify.reason = xtable.Roleinfos.selectName(failRoleid) + "准备失败";
            gnet.link.Onlines.getInstance().send(new HashSet<>(roleids), failNotify);//通知匹配失败的三个玩家
        }

        if (!prepareSuccess) {//已经在匹配开始的时候检查过了，所以其实这里失败的可能性很小
            return null;
        }
        return msg;
    }

    public static void openMultiStoryEctype(Collection<Long> roleids, int ectypeid){//开启多人剧情挑战副本
        new Procedure() {
            @Override
            protected boolean process() {
                XCreateEctypeMultiStory msg = prepareMultiStoryNotInProcedure(roleids, ectypeid);
                if(msg == null) //准备失败，直接返回
                    return false;
                roleids.forEach(i -> succMatch(i));
                SEnrollMultiStoryEctypeSuccessNotify notify = new SEnrollMultiStoryEctypeSuccessNotify();
                notify.lefttime = TeamStoryEctype.MATCH_SUCCESS_WAITING_TIME;//
                makeEnrollBriefInfo(roleids,notify.roleinfos);
                gnet.link.Onlines.getInstance().send(new HashSet<Long>(roleids), notify);//通知玩家已经匹配成功，马上开始游戏
                TaskQueue.getScheduleExecutor().schedule(() -> {//延迟5s开始
                    final int creatServerid = msg.serverid;
                    openEctypeNotInProcedure(roleids, creatServerid, msg);
                }, TeamStoryEctype.MATCH_SUCCESS_WAITING_TIME, TimeUnit.SECONDS);
                return true;
            }
        }.execute();

    }

    public static void makeEnrollBriefInfo(Collection<Long> roleids, List<EnrollBriefInfo> infos) {
        for (long i : roleids) {
            xbean.RoleInfo roleInfo = xtable.Roleinfos.select(i);
            EnrollBriefInfo eb = new EnrollBriefInfo();
            eb.roleid = i;
            eb.gender = roleInfo.getGender();
            eb.profession = roleInfo.getProfession();
            eb.name = roleInfo.getName();
            infos.add(eb);
        }
    }

    public static void makeMatchMultiInfo(Collection<Long> roleids, GAddMultiMatch msg, int ectypeid){
        for (long i : roleids) {
            xbean.RoleInfo roleInfo = xtable.Roleinfos.select(i);
            MatchMultiStroyInfo eb = new MatchMultiStroyInfo();
            eb.roleid = i;
            eb.gender = roleInfo.getGender();
            eb.profession = roleInfo.getProfession();
            eb.name = roleInfo.getName();
            eb.star = xtable.Roleectypes.selectMultistory(i).getOrDefault(ectypeid, 0);
            eb.level = roleInfo.getLevel();
            eb.usedtimes = FLimit.getLimitTimes(i, ConfigId.MULTI_STORY_ECTYPE, ectypeid, LimitType.DAY);
            msg.members.add(eb);
        }
    }

    /*
    家族驻地相关
     */

    //创建家族驻地,并进入其中
    public static void creatAndEnterFamilyMap(long roleid, xbean.Family familyInfo, int isToOpen) {
        XCreateFamilyStation msg = makeFamilyStationPro(familyInfo);
        TaskQueue.getNormalExecutor().execute(() ->
                createMapNotInProcedure(msg.serverid, msg, new CreateMapCallback<XCreateFamilyStation>() {
                    @Override
                    public void onSucc(XCreateFamilyStation builder, long mapid) {
                        FFamily.FamilyId2MapId.put(familyInfo.getFamilyid(), mapid);
                        if(isToOpen != 0){//如果进入副本是去开启家族聚宴，那么要将玩家传送到npc处
                            sendPlayerToOpenFamilyParty(mapid, roleid, isToOpen);
                        }
//                        if(familyInfo.getBeatanimalactivity().getStarttime() > 0){//创建家族的时候，就开启家族神兽活动,防止重启服务器后不刷神兽
//                            sendGodAnimalInfoToMap(mapid, familyInfo);
//                        }
                        enterMapInProcedure(roleid, mapid);
                    }
                }));
    }

    //创建家族驻地,并开启家族聚宴
    public static void creatAndOpenParty(xbean.Family familyInfo) {
        XCreateFamilyStation msg = makeFamilyStationPro(familyInfo);
        TaskQueue.getNormalExecutor().execute(() ->
                createMapNotInProcedure(msg.serverid, msg, new CreateMapCallback<XCreateFamilyStation>() {
                    @Override
                    public void onSucc(XCreateFamilyStation builder, long mapid) {
                        FFamily.FamilyId2MapId.put(familyInfo.getFamilyid(), mapid);
                        sendSystemFamAutoOpenParty(mapid);
                    }
                }));
    }

    //创建家族驻地，并重置神兽挑战开启时间
    public static void creatAndSetGodAnimal(long roleid, xbean.Family familyInfo) {
        XCreateFamilyStation msg = makeFamilyStationPro(familyInfo);
        TaskQueue.getNormalExecutor().execute(() ->
                createMapNotInProcedure(msg.serverid, msg, new CreateMapCallback<XCreateFamilyStation>() {
                    @Override
                    public void onSucc(XCreateFamilyStation builder, long mapid) {
                        FFamily.FamilyId2MapId.put(familyInfo.getFamilyid(), mapid);
                        sendGodAnimalInfoToMap(mapid, familyInfo);
                    }
                }));
    }


    public static XCreateFamilyStation makeFamilyStationPro(xbean.Family familyInfo){
        XCreateFamilyStation msg = new XCreateFamilyStation();
        msg.familyid = familyInfo.getFamilyid();
        msg.serverid = gs.Utils.getServerId();
        msg.members.addAll(familyInfo.getMembers().keySet());
        familyInfo.getActivity().getGodanimalinfo().values().forEach(i-> msg.godanimallvl.put(i.getAnimalid(), i.getAnimallevel()));
        return msg;
    }

    public static void sendGodAnimalInfoToMap(long mapid, xbean.Family familyInfo){
        SLaunchFamilyGodAnimal msg = new SLaunchFamilyGodAnimal();
        msg.starttime = familyInfo.getBeatanimalactivity().getStarttime();
        msg.endtime = CfgMgr.bossconfig.battletime * 1000 + msg.starttime;
        msg.familymapid = mapid;
        MapClient.sendToMap(mapid, msg);
    }

    public static void sendGodAnimalLvlup(long mapid, int bossid, int lvl){
        SGodAnimalLvlup msg = new SGodAnimalLvlup();
        msg.bossid = bossid;
        msg.bosslvl = lvl;
        MapClient.sendToMap(mapid, msg);
    }

    public static void sendPlayerToOpenFamilyParty(long mapid, long roleid, int eventid){
        XToOpenFamilyParty msg = new XToOpenFamilyParty();
        msg.roleid = roleid;
        msg.eventid = eventid;
        MapClient.sendToMap(mapid, msg);
    }

    public static void sendSystemFamAutoOpenParty(long mapid){
        SAutoStartParty msg = new SAutoStartParty();
        msg.remaintime = CfgMgr.familyparty.duration * 1000;
        MapClient.sendToMap(mapid, msg);
    }

    public static void sendToKickFamilyMem(long familyid, long mapid, long roleid, int reason){
        XLeaveOrKickFamily msg = new XLeaveOrKickFamily();
        msg.familyid = familyid;
        msg.roleid = roleid;
        msg.reason = reason;
        MapClient.sendToMap(mapid, msg);
    }

	public static int getDailyEctypeidByRoleLevel(long roleid, int ectypeType) {
        final int level = xtable.Roleinfos.selectLevel(roleid);
        int ectypeid = 0;
        switch (ectypeType) {
            case EctypeType.EXP: {
                for(cfg.ectype.ExpEctype dcfg : CfgMgr.expectype.values()) {
                    if(dcfg.openlevel.level > level)
                        return ectypeid;
                    ectypeid = dcfg.id;
                }
                break;
            }
            case EctypeType.CURRENCY: {
                for(cfg.ectype.CurrencyEctype dcfg : CfgMgr.currencyectype.values()) {
                    if(dcfg.openlevel.level > level)
                        return ectypeid;
                    ectypeid = dcfg.id;
                }
                break;
            }
            default: {
                for(cfg.ectype.DailyEctype dcfg : MapModule.dailyEctypes.get(ectypeType)) {
                    if(dcfg.openlevel.level > level)
                        return ectypeid;
                    ectypeid = dcfg.id;
                }
            }
        }
		return ectypeid;
	}
	
	public static long getRoleid(Protocol p) {
		final Role role = gnet.link.Onlines.getInstance().find(p);
		return role != null ? role.getRoleid() : -1;
	}
	
	public static void dispatchClientMessage(Protocol proto) {
		dispatchMessage(getRoleid(proto), proto);
	}

	public static void dispatchClientMessage(Protocol from, Protocol proto) {
		dispatchMessage(getRoleid(from), proto);
	}

	public static void dispatchPetMessageInProcedure(long roleid, int petModelId, xio.Protocol msg) {
		xdb.Transaction.texecuteWhileCommit(() -> {
            final RoleContext pi = getRoleMapInfo(roleid);
            if (pi != null) {
                final RolePetProtocol proto = new RolePetProtocol();
                proto.petkey = petModelId;
                proto.proto.ptype = msg.getType();
                proto.proto.pdata = new OctetsStream().marshal(msg);
                //Trace.debug("FMap.dispatchPetMessageInProcedure roleid:{} petid:{} msg:{}{}", roleid, petid, msg.getClass().getName(), msg);
                //MapClient.sendByMapid(pi.getCurMapid(), proto);
                dispatchMessage(roleid, proto);
            }
        });
	}


	public static void dispatchPetMessageInProcedure(long roleid, int petModelId, xio.Protocol msg, xio.Protocol msg2) {
		dispatchPetMessageInProcedure(roleid, petModelId, msg);
		dispatchPetMessageInProcedure(roleid, petModelId, msg2);
	}

	public static void dispatchMessage(long roleid, xio.Protocol msg) {
		final RoleContext pi = players.get(roleid);
		if(pi != null) {
			final long mapid = pi.getCurMapid();
			//Trace.info("FMap.dispatchMessage roleid:{} mapid:{} msg:{}{}", roleid, mapid, msg.getClass().getName(), msg);
			MapClient.sendRoleMap(common.Utils.getHolderserveridById(mapid), roleid, msg);
		} else {
			//Trace.warn("FMap.dispatchMessage roleid:{} msg:{}{} can't find RoleContext", roleid, msg.getClass().getName(), msg);
		}
	}

	public static void dispatchMessageInProcedure(long roleid, xio.Protocol msg) {
		xdb.Transaction.texecuteWhileCommit(() -> {
			dispatchMessage(roleid, msg);
		});
	}


	public static void initPlayerBuilder(PlayerBuilder pb, long roleid, boolean onlyActivePet, RoleMapContext last, RoleMapContext cur, RoleMapInfo2 worldOrFamily) {
		final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
		final int profession = info.getProfession();
		final int gender = info.getGender();
		final cfg.role.Profession pcfg = CfgMgr.profession.get(profession);
		final cfg.character.Model mcfg = CfgMgr.model.get(gender == GenderType.MALE ? pcfg.modelname : pcfg.modelname2);
		{
			AgentBuilder b = pb.basic.basic;
			b.agentid = roleid;
			b.atype = AgentType.PLAYER;
            b.subtype = profession;
			b.bodyheight = mcfg.bodyheight;
			b.bodyradius = mcfg.bodyradius;
            b.position = cur.info1.position;
            b.orient = cur.info1.orient;
		}

		final long mapid = cur.info1.mapid;
        if(mapid == 0 || common.Utils.isEctype(mapid)) {
            if(cur.isnew != 0)
		        cur.info2 = new map.msg.RoleMapInfo2();
        } else {
            cur.info2 = worldOrFamily;
        }

		{
			FighterBuilder b = pb.basic;
			b.camp = CampType.PLAYER;
		}

		{
			PlayerBuilder b = pb;
			b.serverid = info.getServerid();
			b.gender = gender;
			b.profession = profession;

			b.name = info.getName();
			b.level = info.getLevel();
			b.viplevel = info.getViplevel();
            final xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
            b.iscantakeparty = roleFamily.getHasjoinpartytoday();
            b.taskmonsters.addAll(FTask.getTaskMonsters(FTask.getTask(roleid)));
			final xbean.Family family = FFamily.selectFamilyByRoleId(roleid);
			if(family != null) {
				b.familyname = family.getFamilyname();
			}

			final xbean.RoleDress dress = FDress.get(roleid);
			b.dressid = dress.getActivedress();

            b.rideid = FRide.getRoleRide(roleid).getActiveride();
            if(cur.isnew != 0) {
                b.ridestatus = cur.info1.ridestatus;
            } else {
                b.ridestatus = RideType.NONE;
            }

            if(common.Utils.isWorld(mapid)) {
                b.pkstate = get(roleid).getPkstates().getOrDefault(common.Utils.getWorldidById(mapid), PKState.PEACE);
            } else {
                b.pkstate = PKState.PEACE;
            }

			final xbean.RoleTitle title = FTitle.getRoleTitle(roleid);
			b.titleid = title.getActivekey();

			final xbean.Talisman fabao = FTalisman.getEquipedTalisman(roleid);
			if(fabao != null) {
				b.fabaoid = fabao.getModelid();
				b.fabaoskills.putAll(fabao.getSkills());
                b.fabaobuffs.addAll(FTalisman.getAwakeBuff(fabao.getModelid(), fabao.getAwakelevel()));
			}

			final xbean.RoleSkill skill = FSkill.get(roleid);
			b.roleskills.put(CfgMgr.careerskilllist.get(profession).normalskillid, 1);
			skill.getSkills().entrySet().forEach(e -> b.roleskills.put(e.getKey(), e.getValue().getLevel()));

			b.amulets = FAmulet.getPrfsSkillAddInfo(roleid);

            FEquip.getEquipBrief(roleid, b.equips);

			final xbean.RoleAttr roleAttr = FRoleAttr.get(roleid);
			b.combatpower = roleAttr.getTotalcombatpower();
			b.attrs.addAll(roleAttr.getRawattrs());

			b.team = FTeam.getProtocolMapTeamByRoleId(roleid);

            b.ready = 0;

            b.cur = cur;
            if(last != null)
                b.last.add(last);
		}

		final xbean.RolePet rolePet = FPet.get(roleid);
		for(int modelId : rolePet.getFightpets()) {
			if(!onlyActivePet || modelId == rolePet.getActivepetmodelid()){
                pb.pets.add(createPetBuilder(roleid, rolePet.getPetmap().get(modelId)));
            }
		}
	}

    public static PlayerBuilder createFakePlayerBuilder(long roleid, int camp) {
        final PlayerBuilder b = new PlayerBuilder();
        final RoleMapContext ctx = new RoleMapContext();
        ctx.isnew = 1;
        //ctx.info1.position = ctx.info1.orient = new map.;
        FMap.initPlayerBuilder(b, roleid, false, null, ctx, new RoleMapInfo2());
        final AgentBuilder ab = b.basic.basic;
        ab.atype = AgentType.FAKE_PLAYER;
        b.basic.camp = camp;
        b.ready = 1;
        b.pets.forEach(p -> p.serverai = 1);
        return b;
    }

	public static PetBuilder createPetBuilder(long roleid, xbean.Pet pet) {
        final int petKey = pet.getModelid();
		final PetBuilder pb = new PetBuilder();
		pb.ownerid = roleid;
		pb.level = pet.getLevel();
		pb.starlevel = pet.getStarlevel();
		pb.awakelevel = pet.getAwakelevel();
		pb.skinid = pet.getActiveskinid();
		pb.skills.putAll(pet.getSkills());

        pb.hp = Const.NULL;
        pb.mp = Const.NULL;
        FPet.genPetAttrsAndBuffs(pet, pb.attrs, pb.buffs);
		{
			FighterBuilder b = pb.basic;
			b.camp = CampType.PLAYER;
		}

        {
            AgentBuilder b = pb.basic.basic;
            final cfg.pet.PetBasicStatus pcfg = CfgMgr.petbasicstatus.get(petKey);
            final cfg.character.Model mcfg = CfgMgr.model.get(pcfg.modelname);
            b.agentid = ((long)petKey << 32) + roleid;//Agent.allocAid();
            b.atype = AgentType.PET;
            b.subtype = petKey;
            b.bodyheight = mcfg.bodyheight;
            b.bodyradius = mcfg.bodyradius;
        }
        //xdb.Trace.info("createPetBuilder. roleid:{} pet:{}", roleid, pb);
        return pb;
    }

    static boolean isOneActivePet(int type) {
        switch (type) {
            case EctypeType.WORLD:
            case EctypeType.FAMILY:
            case EctypeType.MULTI_STORY:
            case EctypeType.TEAMFIGHT:
            case EctypeType.GUARDTOWER:
            case EctypeType.ATK_CITY:
                return true;
            default:
                return false;
        }
    }

	public static void process(MEnterMap msg) {
        if(msg.retcode == 0 || msg.retcode != ErrorCode.MAP_NOT_EXIST.getErrorId()) {
            updateMapRoleNum(msg.cur.info1.mapid, msg.rolenum);
        }
		final RoleContext pi = getRoleMapInfo(msg.roleid);
		if(pi != null) {
			pi.resume(msg.ctxid, msg);
		}
	}

    public static void updateMapRoleNum(long mapid, int num) {
        if(common.Utils.isWorld(mapid)) {
            final int worldid = common.Utils.getWorldidById(mapid);
            final Map<Integer, MapModule.MapInfo> lines = worldsByLines.get(worldid);
            if(lines != null) {
                final int lineid = common.Utils.getLineididById(mapid);
                final MapModule.MapInfo info = lines.get(lineid);
                if(info != null) {
                    info.playerNum = num;
                }
            }
        }
    }

	public static void process(MLeaveMap msg) {
        if(msg.retcode == 0) {
            updateMapRoleNum(msg.cur.info1.mapid, msg.rolenum);
        }
		final long roleid = msg.roleid;
		final RoleContext pi = getRoleMapInfo(roleid);
		if(pi != null) {
			if(msg.retcode == 0) {
				Onlines.getInstance().send(roleid, new SLeaveMap());
			}
			if(msg.ctxid != 0) {
				pi.resume(msg.ctxid, msg);
			} else if(msg.retcode == 0) {
                addLockOper(roleid, (op, param) -> op.doneLeaveThenEnterPrevMap(msg));
            }
		}
	}

	public static void process(long roleid, MTransferWorld msg) {
	    transferWorld(roleid, msg.worldid, msg.position, msg.orient);
	}

	public static boolean process(long roleid, MUseItem msg) {
        ErrorCode errorCode = FItem.useItemByPos(roleid, msg.pos, msg.usenumber);
        if(errorCode.err()){
            Transaction.tsend(roleid, new SError(errorCode.getErrorId()));
            return false;
        }
        return true;
	}

    public static boolean process(long xxx, MKillTaskMonster msg){
        Lockeys.lock(xtable.Roletask.getTable(), msg.roles);
        for(long rid : msg.roles){
            FTask.onEvent(rid, EventIds.MONSTER, msg.monsterid);
        }
        return true;
    }

	public static boolean process(long roleid, MPlayerKillMonster msg) {
		xdb.Trace.debug("MPlayerKillMonster. roleid:{} msg:{}", roleid, msg);
        msg.monsters.forEach((monsterid, num) -> {

            FAchievement.addCounter(roleid, CounterType.KILL_MONSTER, num);
            FDailyActivity.addActiveScores(roleid, EventNum.KILLMONSTER, num);
            final cfg.monster.Monster mcfg = CfgMgr.monster.get(monsterid);
            if (mcfg.monstertype == MonsterType.BOSS)
                FAchievement.addCounter(roleid, CounterType.KILL_BOSS, num);
        });

        final xbean.RoleInfo roleinfo = xtable.Roleinfos.get(roleid);

        final SKillMonster re = new SKillMonster();
        final xbean.DailyMonsterExp dme = FLimit.getDaily(roleid).getMonsterexp();
        if (!msg.baseexp.isEmpty()) {
            final int level = roleinfo.getLevel();
            final long todayMaxBonusExp = CfgMgr.exptable.get(level).bonusexp;
            long todayTotalMonsterExp = dme.getTodaytotaladdmonsterexp();
            final long totalAddExp;
            if (msg.inworld != 0) {
                if (todayTotalMonsterExp < todayMaxBonusExp) {
                    for (int i = 0, n = msg.baseexp.size(); i < n; i++)
                        msg.baseexp.set(i, (int) (msg.baseexp.get(i) * msg.expbonusrate));
                }
                totalAddExp = common.Utils.sumIntToLong(msg.baseexp);
                todayTotalMonsterExp += totalAddExp;
                dme.setTodaytotaladdmonsterexp(todayTotalMonsterExp);
            } else {
                totalAddExp = common.Utils.sumIntToLong(msg.baseexp);
            }
            FRole.addBatchExp(roleid, roleinfo, totalAddExp, msg.baseexp, By.Kill_Monster);

            // 只有世界地图里打怪才计入杀怪经验
            for (int modelId : msg.pets) {
                final xbean.Pet pet = FPet.getPetByModelId(roleid, modelId);
                if (pet != null) {
                    FPet.addExp(roleid, pet, totalAddExp, level);
                    re.petexps.add(new PetExp(pet.getModelid(), pet.getLevel(), pet.getExp()));
                }
            }
        }
        re.todaytotaladdmonsterexp = dme.getTodaytotaladdmonsterexp();
        for (int addXnb : msg.currency) {
            FRole.addCurrency(roleid, roleinfo, Monster.DROP_CURRENCY_TYPE, addXnb, By.Kill_Monster);
        }

        BonusStrategy strategy = msg.inworld == Const.FALSE ? BonusStrategy.TIPS_MAIL : BonusStrategy.TIPS_DISCARD;
        for(Bonus b : msg.bonuss) {
            FBonus.addBonus(roleid, b, strategy, By.Kill_Monster);
        }
        RoleModule.INSTANCE.getThenClearCurrencyAddRecords(roleid, re);
        xdb.Transaction.tsendWhileCommit(roleid, re);
        return true;
	}

    public static boolean process(long roleid, MAddBonus msg) {
        Trace.debug("FMap.MAddBonus roleid:{} msg:{}", roleid, msg);
        return FBonus.addBonus(roleid, msg.bonus, By.From_Map);
    }

    public static boolean process(long roleid, MCollect msg){
        int mineid = msg.mineid;
        xdb.Trace.debug("onCollectMine. roleid:{} mineid:{}", roleid, mineid);
        FTask.onEvent(roleid, EventIds.COLLECT, mineid, 1);
        return true;
    }

	public static xbean.ChapterInfo getChapter(long roleid, int chapterid) {
		final Map<Integer, xbean.ChapterInfo> storys = getEctype(roleid).getChapters();
		xbean.ChapterInfo s = storys.get(chapterid);
		if(s == null) {
			s = xbean.Pod.newChapterInfo();
			storys.put(chapterid, s);
		}
		return s;
	}

	public static int getLastStar(xbean.ChapterInfo chapter, int sectionid) {
		if(chapter != null) {
			final List<Integer> curSections = chapter.getSectionstars();
			return curSections.size() >= sectionid ? curSections.get(sectionid - 1) : 0;
		} else {
			return 0;
		}
	}

	private static void onFinishEctype(long roleid, int ectypeid) {
		Trace.debug("onFinishEctype. roleid:{} ectypeie:{}", roleid, ectypeid);
		FTask.onEvent(roleid, EventIds.ECTYPE, ectypeid);
	}

	public static boolean process(long roleid, MEndStoryEctype msg) {
		final cfg.ectype.StoryEctype scfg = CfgMgr.storyectype.get(msg.ectypeid);
		final int chapterid = scfg.chapter;
		final int sectionid = scfg.section;
		final int star = msg.star;
		final List<Integer> sectionStars = getChapter(roleid, chapterid).getSectionstars();
		// 如果之前的章节未完成.强行填充0星
		for(int i = sectionStars.size(), n = scfg.section - 1 ; i < n ; i++) {
			xdb.Trace.warn("FMap.MEndStoryEctype roleid:{} chapterid:{} not finish prev sectionid:{}. fill 0 star",
					roleid, chapterid, i + 1);
			sectionStars.add(0);
		}

		if(scfg.section == sectionStars.size() + 1) {
			sectionStars.add(star);
			xdb.Transaction.tsendWhileCommit(roleid, new SChangeSection(chapterid, sectionid, star));
		} else if(star > sectionStars.get(sectionid - 1)) {
				sectionStars.set(sectionid - 1, star);
				xdb.Transaction.tsendWhileCommit(roleid, new SChangeSection(chapterid, sectionid, star));
		}

		FBonus.addBonusAlwaysSucc(roleid, msg.bonus, Ectype);
		onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addActiveScores(roleid, EventNum.STORYPOINT);
        FAchievement.addCounter(roleid, CounterType.PASS_STORY_ECTYPE, 1);
		return true;
	}

    public static boolean process(long roleid, MEndPlainStoryEctype msg) {
        onFinishEctype(roleid, msg.ectypeid);
        FAchievement.addCounter(roleid, CounterType.PASS_STORY_ECTYPE, 1);
        FDailyActivity.addActiveScores(roleid, EventNum.STORYPOINT);
        return true;
    }

    //xdb中处理副本结束的消息,不管成功还是失败都会收到结束消息,结束后扣次数
    public static boolean process(long roleid, MEndMultiStoryEctype msg){
        xbean.RoleEctype roleEctype = getEctype(roleid);
        endMatchEctype(roleid, roleEctype);
        if(msg.result == 0){//失败直接返回
            return true;
        }
        int oldStar = roleEctype.getMultistory().getOrDefault(msg.ectypeid, 0);
        if(oldStar < msg.star){
            roleEctype.getMultistory().put(msg.ectypeid, msg.star);
        }
        cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(msg.ectypeid);
        FCondition.checkA(roleid, conf.daylimit, 1, By.Ectype, ConfigId.MULTI_STORY_ECTYPE, msg.ectypeid);
        FBonus.addBonusAlwaysSucc(roleid, msg.bonus, Ectype);
        onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addActiveScores(roleid, EventNum.MANYPOINT);
        return true;
    }

    public static boolean process(long roleid, MKillGodAnimal msg){
        List<SingleRoleBonus> allBonus = new ArrayList<>(msg.membersbonus);
        allBonus.addAll(msg.lasthitbonus);
        allBonus.addAll(msg.luckybonus);
        Map<Long, Bonus> bonusMap = new HashMap<>();
        allBonus.forEach(e -> {
            if(!bonusMap.containsKey(e.roleid)){
                bonusMap.put(e.roleid, new Bonus(EItemBindType.BOUND, new HashMap<>()));
            }
            common.Bonus.merge(bonusMap.get(e.roleid), e.rolebonus);
        });
        bonusMap.forEach((aLong, bonus) -> FBonus.addBonusAlwaysSucc(aLong, bonus, By.Kill_Monster));

        SKillGodAnimalNotify notify = new SKillGodAnimalNotify();
        notify.membersbonus.addAll(msg.membersbonus);
        notify.lasthitbonus.addAll(msg.lasthitbonus);
        notify.luckybonus.addAll(msg.luckybonus);
        FChat.sendSystemMessage(notify);
        return true;
    }

    public static boolean process(long roleid, MEndFamilyParty msg){
        Lockeys.lock(xtable.Locks.ROLELOCK, msg.joinroles);
        SPartyEndNotify endNotify = new SPartyEndNotify();
        for(long rid : msg.joinroles){

            FDailyActivity.addActiveScores(rid, EventNum.PARTY);
            gnet.link.Onlines.getInstance().send(rid, endNotify);
        }
        return true;
    }

    public static boolean process(long roleid, MEatFamilyParty msg){
        FFamily.getRoleFamilyInfo(roleid).setHasjoinpartytoday(1);
        return true;
    }

	public static xbean.EctypeSingle getEctypeSingle() {
		xbean.EctypeSingle info = xtable.Ectypesingle.get(0);
		if(info == null) {
			info = xbean.Pod.newEctypeSingle();
			xtable.Ectypesingle.insert(0, info);
		}
		return info;
	}

    public static boolean process(long roleid, MEndTeamSpeed msg) {
        FBonus.addBonusAlwaysSucc(roleid, msg.bonus, By.Team_Speed_Reward);
        if(msg.iswin == Const.TRUE){
            EventModule.INSTANCE.broadcastEvent(new TeamSpeedWinEvent(roleid));
            FAchievement.addCounter(roleid, CounterType.PVP_ZHENGBA_WIN, 1);
        }
        onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addFindBackTimes(roleid, EventNum.HONGMENG);
        return true;
    }

	public static boolean process(long roleid, MEndDailyEctype msg) {
		final int ectypeid = msg.ectypeid;
		final int costtime = msg.costtime;
		final Map<Integer, xbean.DailyEctypeRecord> records = getEctypeSingle().getDailyectypebestrecords();
		xbean.DailyEctypeRecord record = records.get(ectypeid);
		if(record == null) {
			xdb.Trace.info("FEctype.updateDailyBestRecord  create rank record ectypeid:{} roleid:{} costtime:{}", ectypeid, roleid, costtime);
			record = xbean.Pod.newDailyEctypeRecord();
			record.setRoleid(roleid);
			record.setValue(costtime);
			records.put(ectypeid, record);
		} else if(costtime < record.getValue()) {
			record.setRoleid(roleid);
			record.setValue(costtime);
			xdb.Trace.info("FEctype.updateDailyBestRecord  update rank record ectypeid:{} roleid:{} costtime:{}", ectypeid, roleid, costtime);
		}
		final Map<Integer, xbean.DailyInfo> dailys = getEctype(roleid).getDailys();
		xbean.DailyInfo di = dailys.get(ectypeid);
		if(di == null) {
			di = xbean.Pod.newDailyInfo();
			di.setValue(costtime);
			dailys.put(ectypeid, di);
			xdb.Trace.info("FEctype.updateDailyBestRecord  create my record ectypeid:{} roleid:{} costtime:{}", ectypeid, roleid, costtime);
		} else if(costtime < di.getValue()) {
			di.setValue(costtime);
			xdb.Trace.info("FEctype.updateDailyBestRecord  my update record ectypeid:{} roleid:{} costtime:{}", ectypeid, roleid, costtime);
		}

        Bonus bonus = new Bonus(EItemBindType.BOUND, new HashMap<>());
        msg.bonuss.forEach(b -> common.Bonus.merge(bonus, b));
        FBonus.addBonusAlwaysSucc(roleid, bonus, Ectype);
		onFinishEctype(roleid, msg.ectypeid);

        final int type = CfgMgr.ectypebasic.get(ectypeid).type;
        switch (type) {
            case EctypeType.CURRENCY:
                FAchievement.addCounter(roleid, CounterType.PASS_CURRENCY_ECTYPE, 1);
                FDailyActivity.addActiveScores(roleid, EventNum.COINPOINT);
                FDailyActivity.addFindBackTimes(roleid, EventNum.COINPOINT);
                break;
            case EctypeType.EXP:
                FAchievement.addCounter(roleid, CounterType.PASS_EXP_ECTYPE, 1);
                FDailyActivity.addActiveScores(roleid, EventNum.EXPPOINT);
                FDailyActivity.addFindBackTimes(roleid, EventNum.EXPPOINT);
            break;
            case EctypeType.ZAOHUA:
                FAchievement.addCounter(roleid, CounterType.PASS_ZAOHUA_ECTYPE, 1);
                break;
            case EctypeType.PETECTYPE:
                FAchievement.addCounter(roleid, CounterType.PASS_PET_ECTYPE, 1);
                break;
            case EctypeType.FABAOECTYPE:
                FAchievement.addCounter(roleid, CounterType.PASS_TALISMAN_ECTYPE, 1);
                break;
            case EctypeType.HUFU:
                FDailyActivity.addFindBackTimes(roleid, EventNum.HUFUPOINT);
                break;
            case EctypeType.YUPEI:
//                FDailyActivity.addFindBackTimes(roleid, EventNum.YUPEIPOINT);
                break;
            default:
                throw new RuntimeException("FMap.process MEndDailyEctype:" + msg + ", unknown ectypetype:" + type);
        }
		return true;
	}

	public static lx .gs.map.msg.ClimbTowerInfo convert(xbean.ClimbTowerInfo info) {
		final lx.gs.map.msg.ClimbTowerInfo c = new lx.gs.map.msg.ClimbTowerInfo();
		c.maxfloorid = info.getMaxfloorid();
		c.costtime = info.getCosttime();
		return c;
	}

	public static lx.gs.map.msg.ChapterInfo convert(xbean.ChapterInfo info) {
		final lx.gs.map.msg.ChapterInfo c = new lx.gs.map.msg.ChapterInfo();
		c.sectionstars.addAll(info.getSectionstars());
		c.obtainrewardindexs.addAll(info.getObtainrewardindexs());
		return c;
	}

    public static lx.gs.map.msg.TeamFightInfo convert(xbean.TeamFightInfo info) {
        final lx.gs.map.msg.TeamFightInfo c = new lx.gs.map.msg.TeamFightInfo();
        c.weekscore = info.getWeekscore();
        c.todaywinnum = info.getTodaywinnum();
        c.todayfightnum = info.getTodayfightnum();
        c.obtaintodaywinreward = common.Utils.toByte(info.getObtaintodaywinreward());
        c.obtainscorerewards.addAll(info.getObtainscorerewards());
        return c;
    }

    static void refreshEctypeInfo(xbean.RoleEctype info) {
        final long now = System.currentTimeMillis();
        final xbean.TeamFightInfo team = info.getTeamfight();
        final long lastUpdateTime = team.getLastupdatetime() - 5000;
        if(!common.Time.isSameDay(now, lastUpdateTime)) {
            team.setObtaintodaywinreward(false);
            team.setTodayfightnum(0);
            team.setTodaywinnum(0);
        }
        if(!common.Time.isSameWeek(now, lastUpdateTime)) {
            team.setWeekscore(0);
            team.getObtainscorerewards().clear();
        }
        team.setLastupdatetime(now);
    }

	static void refreshAndSendEctypeInfo(long roleid) {
		final xbean.RoleEctype info = getEctype(roleid);
        refreshEctypeInfo(info);
		final lx.gs.map.msg.SEctypeInfo re = new lx.gs.map.msg.SEctypeInfo();
		for(Map.Entry<Integer, xbean.ClimbTowerInfo> e : info.getClimbtowers().entrySet()) {
			re.climbtowers.put(e.getKey(), convert(e.getValue()));
		}
		for(Map.Entry<Integer, xbean.ChapterInfo> e : info.getChapters().entrySet()) {
			re.chapters.put(e.getKey(), convert(e.getValue()));
		}
        for(Map.Entry<Integer, Integer> e : info.getMultistory().entrySet()){
            re.multistory.put(e.getKey(), new MultiStoryInfo(e.getValue()));
        }
        re.teamfight = convert(info.getTeamfight());
        if(info.getMatchtype() != MatchType.TEAM_SPEED
                && info.getMatchtype() > 0 && System.currentTimeMillis() >= info.getNextmatchtime()) {
            info.setMatchtype(0);
            info.setNextmatchtime(0);
        }
        re.matchtype = info.getMatchtype();
        re.nextmatchtime = info.getNextmatchtime();
		xdb.Transaction.tsendWhileCommit(roleid, re);
	}

	public static boolean process(long roleid, MEndClimbTowerEctype msg) {
        final map.msg.Bonus allbonus = new map.msg.Bonus();
        common.Utils.addIntValue(allbonus.items, msg.normalbonus.items);
        common.Utils.addIntValue(allbonus.items, msg.firstbonus.items);
		if (msg.retcode == ErrorCode.ECTYPE_PLAYER_LOGOUT.getErrorId()) {
			FMail.addMail(roleid, ClimbTowerEctype.OFFLINE_BONUS_MAILID, allbonus);
		} else {
			FBonus.addBonusAlwaysSucc(roleid, allbonus, By.ClimbTower);
		}

		final xbean.RoleEctype info = getEctype(roleid);
		final Map<Integer, xbean.ClimbTowerInfo> towerInfos = info.getClimbtowers();
		xbean.ClimbTowerInfo towerInfo = towerInfos.get(msg.ectypeid);

		boolean update = true;
		if(towerInfo == null) {
			towerInfo = xbean.Pod.newClimbTowerInfo();
			towerInfo.setMaxfloorid(msg.newfloorid);
			towerInfo.setCosttime(msg.lastfloorcosttime);
            towerInfos.put(msg.ectypeid, towerInfo);
		} else {
			final int oldFloorid = towerInfo.getMaxfloorid();
			if(msg.newfloorid > oldFloorid){
				towerInfo.setMaxfloorid(msg.newfloorid);
				towerInfo.setCosttime(msg.lastfloorcosttime);
			} else if(msg.newfloorid == oldFloorid && msg.lastfloorcosttime < towerInfo.getCosttime()) {
				towerInfo.setCosttime(msg.lastfloorcosttime);
			} else {
				update = false;
			}
		}

		if(update) {
			xdb.Transaction.tsend(roleid, new SChangeClimbTower(msg.ectypeid, convert(towerInfo)));
		}
        EventModule.INSTANCE.broadcastEvent(new ClimbTowerUpEvent(roleid, msg.newfloorid, msg.lastfloorcosttime));
        onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addActiveScores(roleid, EventNum.CLIMBTOWER);
		return true;
	}

	public static boolean process(long roleid, MEndPersonalBossEctype msg) {//成功才会收到
		final int ectypeid = msg.ectypeid;
		if (FCondition.checkByReflection(roleid, CfgMgr.personalboss.get(ectypeid), 1, By.Open_Ectype, ConfigId.PERSONAL_BOSS_ECTYPE, ectypeid).err()) {
			xdb.Trace.error("PersonalBoss fail! roleid:{} ectypeid:{}", roleid, ectypeid);
			return false;
		}
		onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addActiveScores(roleid, EventNum.SPECTRUM);
		return true;
	}

    public static boolean process(long roleid, MEndArenaEctype msg) {
        return FArena.endChallenge(roleid, msg.opponentid, msg.retcode);
    }

    public static boolean process(long roleid, MAddEnemy msg) {
        return FFriend.addEnemyRole(roleid, msg.enemyid);
    }

    public static boolean process(long roleid, MEndHeroes msg) {

        // 次数是不同组公用的，cmdId统一传0
        ErrorCode errorCode = FCondition.checkByReflection(roleid, CfgMgr.herosets, 1, By.Open_Ectype, ConfigId.HEROES_ECTYPE, 0);
        if (errorCode.err()) {
            return false;
        }

        // 重置更换伙伴次数和默认伙伴副本
        final Map<Integer, HeroesGroupInfo> herogroups = FMap.getEctype(roleid).getHerogroups();
        final HeroesGroupInfo info = herogroups.get(msg.groupid);
        info.setEctypeid(FMap.randomHeroEctypeId(CfgMgr.herosets.ectypemsg_id.get(msg.groupid)));

        final SHeroGroupSyncInfo change = new SHeroGroupSyncInfo();
        change.groupid = msg.groupid;
        change.groupinfo = new lx.gs.map.msg.HeroesGroupInfo(info.getRefreshtime(), info.getEctypeid());
        Transaction.tsendWhileCommit(roleid, change);

        // heroes副本挑战成功地图服务器才会向xdb发送该协议，所以不需要判断ErrorCode
        final int ectypeid = msg.ectypeid;
        final RoleMemInfo roleMemInfo = FRole.getRoleMemInfo(roleid);
        HeroesGroupMemInfo meminfo = Pod.newHeroesGroupMemInfo();
        meminfo.setEctypeid(ectypeid);
        meminfo.setRandomtime(0);
        roleMemInfo.getHerogroupmeminfos().put(msg.groupid, meminfo);
        // 服务器自动刷新第一次奖励
        final SEndHeroes re = new SEndHeroes();
        ErrorCode err = refreshHeroesAward(roleid, msg.groupid, ectypeid, re.awardinfo);
        if (!err.ok()) {
            xdb.Transaction.tsendWhileCommit(roleid, new SError(err.getErrorId()));
        }
        re.retcode = msg.retcode;
        re.groupid = msg.groupid;
        re.ectypeid = ectypeid;
        xdb.Transaction.tsendWhileCommit(roleid, re);
        onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addActiveScores(roleid, EventNum.HEROBOOK);
        return true;
    }

    public static ErrorCode refreshHeroesAward(long roleid, int groupid, int ectypeid, HeroesAwardInfo awardinfo) {
        final RoleMemInfo roleMemInfo = FRole.getRoleMemInfo(roleid);
        if (!roleMemInfo.getHerogroupmeminfos().containsKey(groupid)) {
            return ErrorCode.HERO_ECTYPE_NO_AWARD_WITH_GROUPID;
        }
        final HeroesGroupMemInfo info = roleMemInfo.getHerogroupmeminfos().get(groupid);
        if (info.getEctypeid() != ectypeid) {
            return ErrorCode.HERO_ECTYPE_NO_AWARD_WITH_ECTYPEID;
        }
        final HeroEctype heroEctype = CfgMgr.heroectype.get(ectypeid);
        int randomtime = info.getRandomtime();
        if (randomtime >= heroEctype.heroectypebonus.size()) {
            return ErrorCode.HERO_ECTYPE_REWARD_REFRESH_MAX_TIME;
        }
        final HeroEctypeBonus heroEctypeBonus = heroEctype.heroectypebonus.get(randomtime);
        // 扣除刷新费用
        if (heroEctypeBonus.cost.amount > 0) {
            ErrorCode err = FCondition.checkByReflection(roleid, heroEctypeBonus, 1, By.Hero_Refresh_Award);
            if (err.err()) {
                return err;
            }
        }
        // 生成随机奖励
        Bonus bonus = new Bonus();
        FBonus.genBonusByProfession(Roleinfos.selectProfession(roleid), heroEctypeBonus.bonus, 1, bonus);

        randomtime++;
        awardinfo.leftrefreshcount = Math.max(0, heroEctype.heroectypebonus.size() - randomtime);
        if(awardinfo.leftrefreshcount > 0){
            awardinfo.refreshcost = heroEctype.heroectypebonus.get(randomtime).cost.amount;
        }
        awardinfo.bonus = bonus;
        //次数及结果状态保存
        info.getBonus().clear();
        RandomBonus newBonus = xbean.Pod.newRandomBonus();
        newBonus.setBindtype(bonus.bindtype);
        newBonus.getItems().putAll(bonus.items);
        info.getBonus().add(newBonus);
        info.setRandomtime(randomtime);

        return ErrorCode.OK;
    }

    public static boolean isInMatch(long roleid) {
        return isInMatch(getEctype(roleid));
    }

    public static boolean isInMatch(xbean.RoleEctype info) {
        return info.getMatchtype() > 0;
    }

    public static boolean isForbidMatch(xbean.RoleEctype info) {
        return info.getNextmatchtime() > System.currentTimeMillis();
    }

    private static xio.Protocol createSChangeMatch(xbean.RoleEctype info) {
        return new SChangeMatch(info.getMatchtype(), info.getNextmatchtime());
    }

    public static void beginMatch(long roleid, xbean.RoleEctype info, int matchType) {
        info.setMatchtype(matchType);
        info.setNextmatchtime(System.currentTimeMillis() + CfgMgr.teamfight.quitmatchforbidtime * 1000L);
        xdb.Transaction.tsendWhileCommit(roleid, createSChangeMatch(info));
    }

    public static void succMatch(long roleid) {
        succMatch(roleid, getEctype(roleid));
    }

    public static void succMatch(long roleid, xbean.RoleEctype info) {
        info.setMatchtype(0);
        // not reset nextmatchtime
        info.setMultiectypid(0);
        xdb.Transaction.tsendWhileCommit(roleid, createSChangeMatch(info));
    }

    public static void cancelMatch(long roleid) {
        cancelMatch(roleid, getEctype(roleid));
    }

    public static void cancelMatch(long roleid, xbean.RoleEctype info) {
        info.setMatchtype(0);
        info.setNextmatchtime(0);
        info.setMultiectypid(0);
        xdb.Transaction.tsendWhileCommit(roleid, createSChangeMatch(info));
    }

    public static void endMatchEctype(long roleid, xbean.RoleEctype info) {
        info.setMatchtype(0);
        info.setNextmatchtime(0);
        info.setMultiectypid(0);
        xdb.Transaction.tsendWhileCommit(roleid, createSChangeMatch(info));
    }

    public static void checkCancelMatch(long roleid) {
        checkCancelMatch(roleid, getEctype(roleid));
    }

    public static void checkCancelMatch(long roleid, xbean.RoleEctype info) {
        final int matchType = info.getMatchtype();
        if(matchType > 0) {
            switch (matchType) {
                case MatchType.GUARD_TOWER: {
                    GuardTowerModule.INSTANCE.removeTeamByRoleid(roleid);
                    break;
                }
                case MatchType.MULTI_STORY: {
                    GCancelMultiMatch msg = new GCancelMultiMatch();
                    msg.roleid = roleid;
                    ServiceClient.send(msg);
//                    MapModule.multiStoryManager.removeTeamByRoleid(roleid);
                    break;
                }
                case MatchType.TEAM_FIGHT: {
                    ServiceClient.send(new GCancelTeamFightMatch(roleid));
//                    TeamFightModule.INSTANCE.removeTeamByRoleid(roleid);
                    break;
                }
                case MatchType.TEAM_SPEED: {
                    TeamSpeedModule.INSTANCE.cancelApply(roleid);
                    break;
                }
                default: {
                    Trace.error("unknown match type:{}", matchType);
                }
            }
        }
    }

    /**
     * 由于多人剧情在匹配成功后扣体力，所以在消耗体力的时候，要检查是否还有足够多的体力
     * @param roleid
     * @param leftTili
     */
    public static void checkMultiStoryTili(long roleid, long leftTili){
        xbean.RoleEctype ectype = getEctype(roleid);
        if(ectype.getMatchtype() == MatchType.MULTI_STORY && ectype.getMultiectypid() != 0){
            cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(ectype.getMultiectypid());
            if(leftTili < conf.costtili.amount){
                gnet.link.Onlines.getInstance().send(roleid, new SError(ErrorCode.TILI_NOT_ENOUGH_AND_CANCEL_MATCH.getErrorId()));
                GCancelMultiMatch msg = new GCancelMultiMatch();
                msg.roleid = roleid;
                ServiceClient.send(msg);
            }
        }
    }

    public static lx.gs.map.msg.MatchTeamInfo genTeamInfo(List<Long> members) {
        final lx.gs.map.msg.MatchTeamInfo result = new lx.gs.map.msg.MatchTeamInfo();
        for(long roleid : members) {
            result.members.add(FRole.genRoleShowInfo(roleid, new RoleShowInfo4()));
        }
        return result;
    }

    public static lx.gs.map.msg.MatchTeamInfo genTeamInfo2(List<match.Team> teams) {
        final lx.gs.map.msg.MatchTeamInfo result = new lx.gs.map.msg.MatchTeamInfo();
        for(match.Team team : teams) {
            for(match.Member member : team.members) {
                result.members.add(FRole.genRoleShowInfo(member.roleid, new RoleShowInfo4()));
            }
        }
        return result;
    }

    public static boolean process(long roleid, MEndTeamFight msg) {//天下会武
        final xbean.RoleEctype info = getEctype(roleid);
        if(msg.quit == 0) {
            endMatchEctype(roleid, info);

            final xbean.TeamFightInfo teamFightInfo = info.getTeamfight();
            if (msg.result > 0) {
                teamFightInfo.setTodaywinnum(teamFightInfo.getTodaywinnum() + 1);
                teamFightInfo.setWeekscore(teamFightInfo.getWeekscore() + CfgMgr.teamfight.winscore);
                EventModule.INSTANCE.broadcastEvent(new TeamFightWinEvent(roleid));
                FAchievement.addCounter(roleid, CounterType.PVP_HUIWU_WIN, 1);
                FDailyActivity.addActiveScores(roleid, EventNum.WINHUIWU);
            } else if (msg.result == 0) {
                teamFightInfo.setWeekscore(teamFightInfo.getWeekscore() + CfgMgr.teamfight.drawscore);
            } else if (msg.result < 0) {
                teamFightInfo.setWeekscore(teamFightInfo.getWeekscore() + CfgMgr.teamfight.losescore);
            }
            teamFightInfo.setTodayfightnum(teamFightInfo.getTodayfightnum() + 1);

            if(msg.evaluates.contains(EvaluateCondition.MAX_KILL)) {
                FAchievement.addCounter(roleid, CounterType.PVP_HUIWU_POJUN, 1);
            }
            if(msg.evaluates.contains(EvaluateCondition.MAX_DAMAGE_KILL_CONTINUE_KILL)) {
                FAchievement.addCounter(roleid, CounterType.PVP_HUIWU_DAMANHGUAN, 1);
            }
            if(msg.evaluates.contains(EvaluateCondition.MIN_DAMAGE_MAX_KILL)) {
                FAchievement.addCounter(roleid, CounterType.PVP_HUIWU_JIANLOUWANG, 1);
            }
            if(msg.evaluates.contains(EvaluateCondition.MAX_DAMAGE_MIN_KILL)) {
                FAchievement.addCounter(roleid, CounterType.PVP_HUIWU_LAOMO, 1);
            }
            onFinishEctype(roleid, CfgMgr.teamfight.id);
            FBonus.addBonusAlwaysSucc(roleid, msg.bonus, By.Team_Fight_Result);
            xdb.Transaction.tsendWhileCommit(roleid, new SChangeTeamFight(convert(teamFightInfo)));
        } else {
            Trace.error("MEndTeamFight roleid:{} quit", roleid);
        }
        return true;
    }

    public static boolean process(long roleid, MEndGuardTower msg) {//血战青云
        // 非在线玩家不清除匹配状态
        if (Onlines.getInstance().find(roleid) != null) {
            final xbean.RoleEctype info = getEctype(roleid);
            endMatchEctype(roleid, info);
        }
        if (msg.errcode == ErrorCode.OK.getErrorId()) {
            // TODO 成就
        }
        onFinishEctype(roleid, msg.ectypeid);
        FDailyActivity.addFindBackTimes(roleid, EventNum.XUEZHAN);
        return true;
    }

    public static boolean process(long roleid, MEndHuiWu msg) {//七脉会武
        return HuiWu.Instance.setBattleResult(msg.profession, msg.roundindex, msg.battleindex, msg.result > 0);
    }

    public static boolean process(long roleid, MGetPlayerLocation msg) {
        final SGetPlayerLocation re = new SGetPlayerLocation();
        re.roleid = roleid;
        re.maptype = msg.maptype;
        re.worldid = msg.worldid;
        re.lineid = msg.lineid;
        re.position = msg.position;
        xdb.Transaction.tsendWhileCommit(msg.queryroleid, re);
        return true;
    }

    public static boolean process(long roleid, MKillWorldBoss msg) {
        lx.gs.activity.worldboss.WorldBoss.onBossKill(msg.lineid, msg.killer, xtable.Roleinfos.selectName(msg.killer), msg.bossid);
        return true;
    }

    public static boolean process(long roleid, MKillExpMonster msg){
        for(long l : msg.roleids){
            xbean.DailyResetData dailyResetData = FLimit.getDaily(l);
            dailyResetData.setKillexpmonsters(dailyResetData.getKillexpmonsters() + 1);
            SKillExpMonsterNums notify = new SKillExpMonsterNums();
            notify.nums = dailyResetData.getKillexpmonsters();
            notify.receivedbonus.addAll(dailyResetData.getRecexpmonbonus());
            xdb.Transaction.tsendWhileCommit(l, notify);
        }
        return true;
    }

    public static boolean process(long roleid, MCheckWorldRevive msg) {
        final cfg.role.Revive revive = CfgMgr.revive;
        if(!(FCondition.checkA(roleid, revive.viprevivetimes, 1, By.Revive, ConfigId.REVIVE, 0).ok()
                || FCondition.checkA(roleid, revive.reviveitem, 1, By.Revive, ConfigId.REVIVE, 0).ok()
                || FCondition.checkA(roleid, revive.reviveYuanBao, 1, By.Revive, ConfigId.REVIVE, 0).ok())) {
            xdb.Transaction.tsend(roleid, AProcedure.makeError(ErrorCode.CURRENCY_IS_NOT_ENOUGH));
            return false;
        }
        dispatchMessageInProcedure(roleid, new XCheckWorldRevive(msg.revivetype));
        return true;
    }

    public static boolean process(long roleid, MEndPrologue msg) {
        xbean.RoleMap info = get(roleid);
        if(msg.errcode == 0) {
            info.setFinishprologue(true);
            addLockOper(roleid, (op, param) -> {
                op.getRoleMapInfo().setFinishPrologue();
                Onlines.getInstance().send(roleid, new SEndPrologue());
                return RoleContext.Result.END;
            });
        } else {
            Onlines.getInstance().send(roleid, new SEndPrologue(msg.errcode));
        }
        return true;
    }

    /**
     * 进入副本或结束副本后通知，记录日志
     * @param roleid
     * @param msg
     * @return
     */
    public static boolean process(long roleid, MEnterEctypeLogger msg){
        FLogger.startInstanceInProcedure(roleid, FBonus.getRoleInfo(roleid), msg.ectypeid);
        return true;
    }

    public static boolean process(long roleid, MEnterArenaLogger msg){
        FLogger.startArenaInProcedure(roleid, FBonus.getRoleInfo(roleid), msg.ectypeid, msg.enemyroleid);
        return true;
    }

    public static boolean process(long roleid, MEndEctypeLogger msg){
        FLogger.endInstanceInProcedure(roleid, FBonus.getRoleInfo(roleid), msg.ectypeid, (int) msg.time, msg.result);
        return true;
    }

    public static boolean process(long roleid, MEndArenaLogger msg){
        FLogger.endArenaInProcedure(roleid, FBonus.getRoleInfo(roleid), msg.ectypeid, (int)msg.time, msg.result, msg.enemyroleid);
        return true;
    }

    public static boolean process(long roleid, MFindNearbyTeam msg) {
        final SFindNearbyTeam re = new SFindNearbyTeam();
        xdb.Lockeys.lock(xtable.Team.getTable(), re.teamlist);
        for(long teamid : msg.teamlist) {
            re.teamlist.add(FTeam.getRoleTeamInfo2(teamid));
        }
        Transaction.tsendWhileCommit(roleid, re);
        return true;
    }

    public static boolean process(long roleid, MEndFamilyTeam msg) {
        if(FCondition.checkA(roleid, CfgMgr.familyteam.rewardfinishnum, 1, By.Family_Team, ConfigId.FAMILY_TEAM_ECTYPE, 0).ok()) {
            FBonus.addBonusAlwaysSucc(roleid, msg.bonus, By.Family_Team);
            return true;
        } else {
            return false;
        }
    }

    public static boolean process(long xxx, MWorldPKKill msg) {
        xdb.Transaction.tsendWhileCommit(msg.attacker, new SKillOther(xtable.Roleinfos.selectName(msg.defencer)));
        SBekillByOther pro = new SBekillByOther(xtable.Roleinfos.selectName(msg.attacker), msg.defencer);
        FFriend.multicastAllMM(msg.defencer, pro);
        gnet.link.Onlines.getInstance().send(msg.attacker, pro);
        gnet.link.Onlines.getInstance().send(msg.defencer, pro);
        FFriend.addEnemyRole(msg.defencer, msg.attacker);
        return true;
    }

    public static boolean process(long xxx, MCloseWorldMap msg) {
        Trace.info("MCloseWorldMap. worlid:{} lineid:{}", msg.worldid, msg.lineid);
        MapModule.worldsByLines.get(msg.worldid).remove(msg.lineid);
        lx.gs.activity.worldboss.WorldBoss.onLineRemove(msg.worldid, msg.lineid);
        return true;
    }
}

