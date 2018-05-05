package map.map;

import cfg.map.Reason;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import common.Utils;
import map.agent.Player;
import map.map.story.MultiStoryEctype;
import map.msg.*;
import xdb.Trace;
import xio.Protocol;

/**
 * Created by huangqiang on 2016/4/21.
 */
public class Handler {
    public static void process(XEnterMap msg) {
        final long roleid = msg.player.basic.basic.agentid;
        final long mapid = msg.mapid;
        final GameMap oldMap = MapMgr.Ins.getRoleInGame(roleid);
        if(oldMap != null) {
            if(oldMap.getMapid() == mapid) {
                Trace.info("XEnterMap enter same gamemap. do nothing. roleid:{} mapid:{}", roleid, mapid);
                oldMap.externPlayerEnter(msg.ctxid, msg.player);
            } else {
                Trace.info("XEnterMap roleid:{} has been in other mapid:{}, leave and enter new mapid:{}", roleid, oldMap.getMapid(), mapid);
                oldMap.externPlayerLeaveCurThenEnterNew(0, roleid, Reason.ENTER_NEW_MAP, msg);
            }
        } else {
            Trace.info("XEnterMap roleid:{} enter new mapid:{}", roleid, mapid);
            final GameMap map = MapMgr.Ins.getMap(mapid);
            if(map != null) {
                map.externPlayerEnter(msg.ctxid, msg.player);
            } else {
                Trace.info("XEnterMap roleid:{} mapid:{} fail. err:{}", roleid, mapid, ErrorCode.MAP_NOT_EXIST);
                final MEnterMap re = new MEnterMap();
                re.ctxid = msg.ctxid;
                re.retcode = ErrorCode.MAP_NOT_EXIST.getErrorId();
                re.roleid = roleid;
                re.cur.info1.mapid = msg.mapid;
                re.rolenum = 0;
                MapMgr.dispatcher.sendServerByRoleid(roleid, re);
            }
        }
    }

    public static void process(XLeaveMap msg) {
        final long roleid = msg.roleid;
        final long mapid = msg.mapid;
        final int reason = msg.reason;
        Trace.info("XLeaveMap roleid:{} mapid:{} reason:{}", roleid, mapid, reason);
        final GameMap map = MapMgr.Ins.getRoleInGame(roleid);
        if(map != null) {
            if(map.getMapid() != mapid) {
                Trace.warn("Handler.XLeaveMap roleid:{} mapid:{} but current in gamemap:{}", roleid, mapid, map);
            }
            map.externPlayerLeave(msg.ctxid, roleid, msg.reason);
        } else {
            Trace.error("Handler.XLeaveMap roleid:{} mapid:{} not exist", roleid, mapid);
            final MLeaveMap re = new MLeaveMap();
            re.ctxid = msg.ctxid;
            re.retcode = ErrorCode.MAP_NOT_EXIST.getErrorId();
            re.roleid = roleid;
            re.reason = reason;
            re.rolenum = 0;//map.getPlayerNum();
            MapMgr.dispatcher.sendServerByRoleid(roleid, re);
        }
    }


    public static MCreateMap call(XCreateWorldMap msg) {
        final MCreateMap re = new MCreateMap();
        final long mapid = Utils.makeId(msg.serverid, MapMgr.Ins.getLocalServerid(), msg.worldid, msg.lineid);
        synchronized (MapMgr.Ins) {
            World map = (World)MapMgr.Ins.getMap(mapid);
            if(map == null) {
                map = World.create(msg.worldid, msg.lineid, msg.serverid);
                map.start();
            }
            re.mapid = map.getMapid();
            re.retcode = 0;
        }
        //Trace.info("XCreateWorld  serverid:{} worldid:{} lineid:{} mapid:{}", msg.serverid, msg.worldid, msg.lineid, map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateEcypeOnePlayer msg) {
        final Ectype map = OnePlayerEctype.create(msg);
        map.start();
        final MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateEcypeOnePlayer  serverid:{} ectypeid:{} roleid:{} mapid:{}", msg.serverid, msg.ectypeid, msg.roleid, map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateArenaEctype msg) {
        final Arena map = Arena.create(msg);
        map.start();
        final MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateArenaEcype  serverid:{} ectypeid:{} roleid:{} mapid:{}", msg.serverid, msg.ectypeid, msg.roleid, map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateGuardTowerEctype msg) {
        final GuardTower map = GuardTower.create(msg);
        map.start();
        MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateGuardTowerEctype  serverid:{} ectypeid:{} roleid:{} mapid:{}", msg.serverid, msg.ectypeid, msg.roles.keySet(), map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateEctypeMultiStory msg){//接收并处理从xdb发过来的创建多人剧情副本的消息
        final MultiStoryEctype map = MultiStoryEctype.create(msg);
        map.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateEctypeMultiStory  serverid:{} ectypeid:{} roleids:{} mapid:{}", msg.serverid, msg.ectypeid, msg.roles.keySet(), map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateFamilyStation msg){//接收并处理从xdb发过来的创建家族驻地的消息
        final FamilyStation map = FamilyStation.create(msg);
        map.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateFamilyStation  serverid:{} familyid:{} mapid:{}", msg.serverid, msg.familyid,  map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateTeamFight msg){
        final TeamFight map = TeamFight.create(msg);
        map.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateTeamFight mapid:{} msg:{}", map.getMapid(), msg);
        return re;
    }

    public static MCreateMap call(XCreateHuiWu msg) {
        final map.map.HuiWu gamemap = map.map.HuiWu.create(msg);
        gamemap.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = gamemap.getMapid();
        //Trace.info("XCreateHuiWu  mapid:{} msg:{}", gamemap.getMapid(), msg);
        return re;
    }

    public static MCreateMap call(XCreateTeamSpeed msg){
        final TeamSpeedEctype map = TeamSpeedEctype.create(msg);
        map.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = map.getMapid();
        //Trace.info("XCreateTeamSpeed  serverid:{} ectypeid:{} team1:{} team2:{} mapid:{}", msg.serverid, msg.ectypeid, msg.team1, msg.team2, map.getMapid());
        return re;
    }

    public static MCreateMap call(XCreateAttackCity msg) {
        final map.map.AttackCity gamemap = map.map.AttackCity.create(msg);
        gamemap.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = gamemap.getMapid();
        //Trace.info("XCreateAttackCity  mapid:{} msg:{}", gamemap.getMapid(), msg);
        return re;
    }

    public static MCreateMap call(XCreateFamilyTeam msg) {
        final map.map.FamilyTeam gamemap = map.map.FamilyTeam.create(msg);
        gamemap.start();
        final  MCreateMap re = new MCreateMap();
        re.retcode = 0;
        re.mapid = gamemap.getMapid();
        return re;
    }

    private static xio.Protocol decode(gnet.ProtocolData data) {
        return MapMgr.dispatcher.decode(data.ptype, data.pdata);
    }

    public static void dispatch(xio.Protocol msg) {
        try {
            Trace.debug("Handler.dispatch msg:[{}]{}", msg.getClass().getName(), msg);
            switch (msg.getType()) {
                case RoleMapProtocol.PROTOCOL_TYPE: {
                    final RoleMapProtocol p = (RoleMapProtocol)msg;
                    processRoleMapProtocol(p.roleid, decode(p.proto));
                    break;
                }
                case MapProtocol.PROTOCOL_TYPE: {
                    final MapProtocol p = (MapProtocol)msg;
                    processMapProtocol(p.mapid, decode(p.proto));
                    break;
                }
                case XEnterMap.PROTOCOL_TYPE: process((XEnterMap)msg); break;
                case XLeaveMap.PROTOCOL_TYPE: process((XLeaveMap)msg); break;
                case XRpc.PROTOCOL_TYPE: processXRpc((XRpc)msg); break;
                default:
                    throw new RuntimeException("Server.dispatch unknown protocol:" + msg.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void processMapProtocol(long mapid, xio.Protocol msg) {
        final GameMap map = MapMgr.Ins.getMap(mapid);
        if(map != null) {
            map.addExternTask(() -> {
                try {
                    Trace.info("map.process msg:{} {}", msg.getClass().getName(), msg);
                    map.getClass().getMethod("process", msg.getClass()).invoke(map, msg);
                } catch (Exception e) {
                    Trace.error("map.process " + msg.getClass().getName(), e);
                }
            });
        }
    }

    public static void processRoleMapProtocol(long roleid, xio.Protocol msg) {
        Trace.debug("Server.recvRoleMap roleid:{} msg:{}{}", roleid, msg.getClass().getName(), msg);
        final GameMap map = MapMgr.Ins.getRoleInGame(roleid);
        if (map != null) {
            map.addExternTask(() -> {
                final Player player = map.getPlayer(roleid);
                if (player != null) {
                    try {
                        Trace.debug("Player.process msg:{} {}", msg.getClass().getName(), msg);
                        if(!player.isReady() && msg.getClass() != CReady.class) {
                            Trace.debug("player:{} not ready. ignore msg:{}{}", player, msg.getClass().getName(), msg);
                            return;
                        }
                        switch (msg.getType()) {
                            case XKeepAlive.PROTOCOL_TYPE: break;
                            case CMove.PROTOCOL_TYPE:  player.process((CMove)msg); break;
                            case CStop.PROTOCOL_TYPE: player.process((CStop)msg); break;
                            case CSkillPerform.PROTOCOL_TYPE: player.process((CSkillPerform)msg); break;
                            case COrient.PROTOCOL_TYPE: player.process((COrient)msg); break;
                            default: player.getClass().getMethod("process", msg.getClass()).invoke(player, msg);
                        }
                        player.refreshIdle();
                    } catch (Exception e) {
                        Trace.error("Player.process " + msg.getClass().getName(), e);
                    }
                } else {
                    Trace.warn("Server.recvRoleMap roleid:{} not in gamemap:{}. ignore", roleid, map);
                }
            });
        } else {
            Trace.debug("Server.recvRoleMap roleid:{} not in any gamemap. ignore", roleid);
        }
    }

    public static void processXRpc(XRpc msg) {
        try {
            xio.Protocol p = MapMgr.dispatcher.decode(msg.ptype, msg.argument);
            final xio.Protocol result = (xio.Protocol) Handler.class.getMethod("call", p.getClass()).invoke(null, p);
            final MRpc re = new MRpc();
            re.ctxid = msg.ctxid;
            re.ptype = result.getType();
            re.result = new OctetsStream().marshal(result);
            Trace.debug("Server.process XRpc argument:{}{} result:{}{}", p.getClass().getName(), p, result.getClass().getName(), result);
            re.send(msg.getConnection());
        } catch (Exception e) {
            xdb.Trace.error("Server.process XRpc argument type:" + msg.ptype + ", data:" + msg.argument, e);
        }
    }
}
