package map.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.ectype.HeroEctype;
import common.ErrorCode;
import map.agent.Player;
import map.controller.MultiBatchMonsterController;
import map.msg.MEndHeroes;
import map.msg.SEndHeroes;
import map.msg.SEnterHeroes;

/**
 * Created by live106 on 2016/5/9.
 */
public class Heroes extends OnePlayerEctype {
    private final HeroEctype ecfg;
    private final int groupid;

    public Heroes(Builder b) {
        super(b);
        this.groupid = b.param1;
        this.ecfg = CfgMgr.heroectype.get(b.subid);
    }

    @Override
    public void init() {
        super.init();
        createMultiBatchMonsters2(ecfg.monsters, ecfg.monsterreftime, Const.NULL, null, null, null, this::success);
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    protected void onFail(ErrorCode err) {
        if (err.ok()) {
            final MEndHeroes msg = new MEndHeroes();
            msg.retcode = err.getErrorId();
            msg.groupid = this.groupid;
            msg.ectypeid = ectypeid;
            Player.sendXdb(roleid, msg);
        } else {
            final SEndHeroes re = new SEndHeroes();
            re.retcode = err.getErrorId();
            re.groupid = groupid;
            re.ectypeid = ectypeid;
            sendContextMsg(re);
        }
    }

    @Override
    public void sendPlayerEnter(Player player) {
        SEnterHeroes re = new SEnterHeroes();
        re.id = getMapid();
        re.groupid = groupid;
        re.ectypeid = ectypeid;
        player.sendNotRoleMsg(re);
    }

    @Override
    public void onReady() {
        super.onReady();
        startRemainTime();
    }
}
