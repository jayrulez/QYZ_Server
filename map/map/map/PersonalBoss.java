package map.map;

import cfg.CfgMgr;
import cfg.Const;
import common.ErrorCode;
import map.agent.Player;
import map.msg.MEndPersonalBossEctype;
import map.msg.SEndPersonalBossEctype;
import map.msg.SEnterPersonalBossEctype;
import map.msg.SNewMonsterWave;

/**
 * Created by huangqiang on 2016/1/26.
 */
public class PersonalBoss extends OnePlayerEctype {

    public PersonalBoss(Builder b) {
        super(b);
        this.personalBossCfg = CfgMgr.personalboss.get(b.subid);
    }


    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterPersonalBossEctype re = new SEnterPersonalBossEctype();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.remaintime = remainTime;
        player.sendNotRoleMsg(re);
        this.player = player;
    }


    private final cfg.ectype.PersonalBoss personalBossCfg;
    private int monsterWaveIndex;



    public void init() {
        super.init();
        monsterWaveIndex = 0;
    }


    protected void onFail(ErrorCode err) {
        final SEndPersonalBossEctype re = new SEndPersonalBossEctype();
        re.errcode = result.getErrorId();
        if(err.ok()) {
            common.Bonus.genBonusByProfession(profession, personalBossCfg.bonus, 1, re.bonus);
            Player.sendXdb(roleid, new MEndPersonalBossEctype(ectypeid, re.bonus));
        }
        sendContextMsg(re);
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    public void onReady() {
        super.onReady();
        startRemainTime();

        createMultiBatchMonsters2(personalBossCfg.monsters, personalBossCfg.monsterreftime, Const.NULL, () -> {
            broadcastNotContextMsg(new SNewMonsterWave(monsterWaveIndex++));
        }, null, null, this::success);
    }
}
