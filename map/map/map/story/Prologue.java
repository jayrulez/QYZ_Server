package map.map.story;

import cfg.CfgMgr;
import cfg.Const;
import cfg.fight.AttrId;
import cfg.map.Reason;
import common.ErrorCode;
import map.MapUtils;
import map.agent.Player;
import map.map.OnePlayerEctype;
import map.msg.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/6/12.
 */
public class Prologue extends AbstractStory {

    public Prologue(OnePlayerEctype.Builder b) {
        super(b);
        this.prologueCfg = CfgMgr.prologue;
        this.roleid = b.roleid;
        this.player = null;
    }

    private final long roleid;
    private final cfg.ectype.Prologue prologueCfg;

    protected Player player;

    @Override
    public boolean admit(long roleid) {
        return notEnd() && roleid == this.roleid;
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterPrologue re = new SEnterPrologue();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.remaintime = remainTime;
        re.remainrevivecount = basiccfg.reviveinfo.maxcount;
        re.enviroments.putAll(this.enviroments);
        re.openlayouts.addAll(layouts.values().stream().map(this::genLayoutInfo).collect(Collectors.toList()));
        re.activeactions.addAll(actions.keySet());
        player.sendNotRoleMsg(re);
    }

    public void init() {
        super.init();
        setSuspend();
    }

    @Override
    protected void onPrecreatePlayer(PlayerBuilder b) {
        b.roleskills.clear();
        final cfg.skill.CareerSkillList ccfg = CfgMgr.careerskilllist.get(b.profession);
        ccfg.skilllist.forEach(s -> b.roleskills.put(s, 1));
        b.roleskills.put(ccfg.normalskillid, 1);
        b.level = prologueCfg.level;
        final cfg.ectype.BeginnerEquip equip = prologueCfg.professionequips_profession.get(b.profession);
        b.equips.clear();
        b.equips.add(new EquipBrief(equip.weaponid, equip.weaponanneallevel, equip.perfuselevel));
        b.equips.add(new EquipBrief(equip.clothid, equip.notweaponanneallevel, equip.perfuselevel));
        b.equips.add(new EquipBrief(equip.hatid, equip.notweaponanneallevel, equip.perfuselevel));
        b.equips.add(new EquipBrief(equip.shoesid, equip.notweaponanneallevel, equip.perfuselevel));
        final cfg.fight.Attr attr = CfgMgr.prologue.statusinfo;
        final float[] attrs = common.AttrUtils.newRawAttrs();
        common.AttrUtils.convert(attr, attrs);
        common.AttrUtils.fill(b.attrs, attrs);
        b.fabaoid = equip.talismanid;
        super.onPrecreatePlayer(b);
    }

    @Override
    protected void onFail(ErrorCode err) {
        if(err == ErrorCode.ECTYPE_PLAYER_LEAVE)
            err= ErrorCode.OK;
        Player.sendXdb(roleid, new MEndPrologue(err.getErrorId()));
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        this.player = player;
    }

    @Override
    public void onPlayerLeave(Player player) {
        super.onPlayerLeave(player);
        this.player = null;
        if(notEnd() && player.getReason() != Reason.LOGOUT) {
            addDeferTask(() -> checkEnd(ErrorCode.ECTYPE_PLAYER_LEAVE));
        }
    }
}
