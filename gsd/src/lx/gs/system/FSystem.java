package lx.gs.system;

import cfg.CfgMgr;
import cfg.arena.Robot;
import cfg.role.EProfessionType;
import cfg.role.GenderType;
import gs.Listeners;
import gs.Utils;
import lx.gs.arena.ArenaModule;
import lx.gs.arena.FArena;
import lx.gs.login.FLogin;
import lx.gs.role.FRoleAttr;
import lx.gs.role.NamePool;
import lx.gs.skill.FSkill;
import xbean.GMSenseword;
import xbean.Pod;
import xdb.Procedure;
import xdb.Trace;
import xtable.Gmsenseword;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/4/8.
 */
public class FSystem {

    public static xbean.System get() {
        xbean.System info = xtable.System.get(0);
        if(info == null) {
            info = xbean.Pod.newSystem();
            xtable.System.insert(0, info);
        }
        return info;
    }

    public final static class RobotBuilder {
        public String name;
        public int gender;
        public int profession;
        public int serverid;
        public int level;
        public int viplevel;
        public int combatpower;
        public boolean addrank;
    }

    public static long createOrUpdateRobot(RobotBuilder robot) {
        final int profession = robot.profession;
        final int gender = robot.gender;
        final String name = robot.name;
        final int serverid = robot.serverid;
        final String fullName = FLogin.makeFullName(name, serverid);


        xbean.RoleInfo roleinfo = xbean.Pod.newRoleInfo();
        roleinfo.setUserid(-1);
        roleinfo.setServerid(serverid);
        roleinfo.setProfession(profession);
        roleinfo.setName(name);
        roleinfo.setGender(gender);
        roleinfo.setLevel(robot.level);
        roleinfo.setViplevel(robot.viplevel);

        final long now = System.currentTimeMillis();
        roleinfo.setCreatetime(now);
        roleinfo.setLastlogintime(now);
        roleinfo.setTotalonlinetime(0);

        long roleid = xtable.Roleinfos.insert(roleinfo);
        xtable.Rolename2ids.insert(fullName, roleid);
        Listeners.INSTANCE.roleCreate(roleid);

        final xbean.RoleAttr roleAttr = xbean.Pod.newRoleAttr();
        roleAttr.setRoleid(roleid);
        xtable.Roleattrs.insert(roleid, roleAttr);
        Listeners.INSTANCE.updateRoleAttr(roleid);
        FRoleAttr.updateRoleCombatPower(roleAttr);
        roleAttr.setTotalcombatpower(robot.combatpower);

        final xbean.RoleArena arenaInfo = FArena.get(roleid);
        arenaInfo.setIsrobot(true);

        FSkill.checkNewAvaliableSkills(roleid, false);

        if(robot.addrank)
            FArena.getSwapRank().addId(roleid);
        return roleid;
    }

    public static long createRobot(cfg.arena.Robot cur, cfg.arena.Robot prev, int index, boolean addrank) {
        final RobotBuilder robot = new RobotBuilder();
        robot.profession = cur.profession;
        robot.gender = cur.gender;
        robot.name = NamePool.Ins.randomUniqueName(cur.gender);
        robot.serverid = Utils.getServerId();
        robot.level = cur.level;
        robot.viplevel = cur.viplevel;
        robot.addrank = addrank;
        if(prev != null && prev.rank - cur.rank < 0) {
            robot.combatpower = prev.combatpower + (index - prev.rank) * robot.combatpower / (cur.rank - prev.rank);
        } else {
            robot.combatpower = cur.combatpower;
        }
        robot.combatpower += common.Utils.randomRange(0, 100);
        return createOrUpdateRobot(robot);
    }

    public static void initRobots() {
        if(!new Procedure() {
            @Override
            protected boolean process() {
                final xbean.System info = get();
                return info.getInitrobot();
            }
        }.call()) {
            final List<Robot> robots = new ArrayList<>(CfgMgr.robot.values());
            for(int i = 0 ; i < robots.size() ; i++) {
                final Robot last = i > 0 ? robots.get(i - 1) : null;
                final Robot cur = robots.get(i);
                new Procedure() {
                    @Override
                    protected boolean process() {
                        final xbean.System info = get();
                        Trace.info("init robots begin ...");
                        info.setInitrobot(true);
                        if(cur.rank <= ArenaModule.RANK_SIZE) {
                            for(int j = last != null ? last.rank + 1 : 1 ; j <= cur.rank ; j++) {
                                final long robotid = createRobot(cur, last, j, true);
                                Trace.info("create [{}] robot:{}", j, robotid);
                            }
                        } else {
                            if(last.rank < ArenaModule.RANK_SIZE) {
                                for(int j = last.rank + 1 ; j <= ArenaModule.RANK_SIZE ; j++) {
                                    final long robotid = createRobot(last, last, j, true);
                                    Trace.info("create [{}] robot:{}", j, robotid);
                                }
                            }
                            {
                                final long robotid = createRobot(cur, last, cur.rank, false);
                                info.getOutrankrobots().add(robotid);
                                Trace.info("create [{}] robot:{}", cur.rank, robotid);
                            }
                        }
                        return true;
                    }
                }.call();
            }

//            new Procedure() {
//                @Override
//                protected boolean process() {
//                    final List<Robot> robots = new ArrayList<>(CfgMgr.robot.values());
//                    for (int i = 0; i < robots.size(); i++) {
//                        final Robot last = i > 0 ? robots.get(i - 1) : null;
//                        final Robot cur = robots.get(i);
//                        final xbean.System info = get();
//                        Trace.info("init robots begin ...");
//                        info.setInitrobot(true);
//                        if (cur.rank <= ArenaModule.RANK_SIZE) {
//                            for (int j = last != null ? last.rank + 1 : 1; j <= cur.rank; j++) {
//                                final long robotid = createRobot(cur, j, true);
//                                Trace.info("create [{}] robot:{}", j, robotid);
//                            }
//                        } else {
//                            if (last.rank < ArenaModule.RANK_SIZE) {
//                                for (int j = last.rank + 1; j <= ArenaModule.RANK_SIZE; j++) {
//                                    final long robotid = createRobot(last, j, true);
//                                    Trace.info("create [{}] robot:{}", j, robotid);
//                                }
//                            }
//                            {
//                                final long robotid = createRobot(cur, cur.rank, false);
//                                info.getOutrankrobots().add(robotid);
//                                Trace.info("create [{}] robot:{}", cur.rank, robotid);
//                            }
//                        }
//                    }
//                    return true;
//                }
//
//            }.call();
        }
    }

    public static Set<String> getSenseWords(){
        Set<String> ret = new HashSet<>();
        ret.addAll(CfgMgr.senseword.words);
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                GMSenseword gmSenseword = getGMSenseWords();
                ret.addAll(gmSenseword.getAddwords());
                ret.removeAll(gmSenseword.getRemovewords());
                return true;
            }
        }.call();
        return ret;
    }

    public static String getFuzzyWords(){
        Set<String> ret = new HashSet<>();
        ret.addAll(CfgMgr.senseword2.words);
        return ret.stream().map(s -> makeRegx(s)).collect(Collectors.joining("|"));
    }

    public static String makeRegx(String s){
        String regx = ".*";
        for(int i = 0 ; i < s.length(); i++){
            regx += s.charAt(i) + ".*";
        }
        return regx;
    }

    public static GMSenseword getGMSenseWords(){
        GMSenseword gmSenseword = Gmsenseword.get(0);
        if(gmSenseword == null){
            gmSenseword = Pod.newGMSenseword();
            Gmsenseword.insert(0, gmSenseword);
        }
        return gmSenseword;
    }
    public static long createFakeFamilyChief(){
        final RobotBuilder robot = new RobotBuilder();
        robot.profession = EProfessionType.QINGYUNMEN;
        robot.gender = common.Utils.randomRange(0,2);
        robot.name = NamePool.Ins.randomUniqueName(robot.gender);
        robot.serverid = Utils.getServerId();
        robot.level = common.Utils.randomRange(20, 26);
        robot.viplevel = common.Utils.randomRange(2,5);
        robot.addrank = false;
        robot.combatpower = 10000 + common.Utils.randomRange(0, 1000);
        return createOrUpdateRobot(robot);
    }

}
