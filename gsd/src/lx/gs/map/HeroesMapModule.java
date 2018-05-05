package lx.gs.map;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.item.EItemBindType;
import common.Time;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.HourChangeListener;
import lx.gs.bonus.FBonus;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import map.msg.Bonus;
import xbean.HeroesGroupMemInfo;
import xbean.RoleMemInfo;
import xdb.Procedure;
import xtable.Roleinfos;

import java.util.HashMap;

/**
 * Created by live106 on 2016/5/9.
 */
public enum HeroesMapModule implements gs.Module, gs.RoleLoginListener, HourChangeListener {
    INSTANCE;

    @Override
    public void hourChanged() {
        // 青云英雄录 策划说这个重置时间只考虑小时就可以
        int hour = Time.getTimeHour(System.currentTimeMillis());
        if (CfgMgr.herosets.resettime.contains(hour)) {
            for (Role role : Onlines.getInstance().roles()) {
                new Procedure(){
                    @Override
                    protected boolean process() throws Exception {
                        FLimit.clearLimit(role.getRoleid(), ConfigId.HEROES_ECTYPE, 0);
                        return true;
                    }
                }.call();
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        long now = System.currentTimeMillis();
        long lastLogout = Roleinfos.selectLastlogouttime(roleid);
        int lastHour = Time.getTimeHour(lastLogout);
        int nowHour = Time.getTimeHour(now);
        if(!Time.isSameDay(lastLogout, now)
                || CfgMgr.herosets.resettime.stream().anyMatch(integer -> lastHour < integer && integer <= nowHour)){
            FLimit.clearLimit(roleid, ConfigId.HEROES_ECTYPE, 0);
        }
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
        // 青云英雄录结算时随机奖励未领取时掉线处理
        new xdb.Procedure() {
            @Override
            protected boolean process() throws Exception {
                final RoleMemInfo roleMemInfo = FRole.getRoleMemInfo(roleid);
                roleMemInfo.getHerogroupmeminfos().values().forEach(v -> {
                    if (v.getBonus().size() > 0) {
                        addHeroesBonuses(v, roleid);
                    }
                });
                roleMemInfo.getHerogroupmeminfos().clear();
                return true;
            }
        }.call();
    }

    public static void addHeroesBonuses(HeroesGroupMemInfo v, long roleid) {
        Bonus bonus = new Bonus(EItemBindType.BOUND, new HashMap<>());
        v.getBonus().forEach(b -> {
            bonus.items.putAll(b.getItems());
        });
        FBonus.addBonusAlwaysSucc(roleid, bonus, By.Hero_Gain_Award);

    }
}
