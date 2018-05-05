package map.map;

import cfg.CfgMgr;
import common.RefObject;
import map.agent.Listener;
import map.agent.Monster;
import map.buff.Buff;
import map.msg.*;

import java.util.Map;

/**
 * Created by HuangQiang on 2016/7/18.
 */
public class ExpEctype extends AbstraceDaily {

    public ExpEctype(Builder b) {
        this(b, CfgMgr.expectype.get(b.subid));
    }

    private ExpEctype(Builder b, cfg.ectype.ExpEctype dcfg) {
        super(b, dcfg.doublebonustime, dcfg.ectyperandomdrop);
        this.ccfg = dcfg;
        this.cgWaveIndex = ccfg.buffstartindex; // 配置那儿从1计数,而程序从0计数,故buff前波次为subIndex(0, ccfg.buffstartindex)
    }

    private final cfg.ectype.ExpEctype ccfg;
    private final int cgWaveIndex;

    enum State {
        WAIT_OPEN_CG1,
        IN_MONSTER1,
        WAIT_OPEN_CG2,
        IN_MONSTER2,
    }

    private State state;

    public void init() {
        super.init();
        waveIndex = -1;
        beginTime = 0;

        schedule(() -> onActionEnd(cfg.ectype.ExpEctype.OPEN_CG), CfgMgr.ectypesingle.clientactiontimeout * 1000L);
        state = State.WAIT_OPEN_CG1;
    }

    public void onActionEnd(int actionid) {
        if(actionid == cfg.ectype.ExpEctype.OPEN_CG && state == State.WAIT_OPEN_CG1) {
            startMonster1();
            startRemainTime();
            this.beginTime = getNow();
        } else if(actionid == cfg.ectype.ExpEctype.CHANGE_CG && state == State.WAIT_OPEN_CG2) {
            startMonster2();
        }
    }

    private void installBuffs() {
        if(player != null && player.isActive() && !player.isDead()) {
            for (int buffid : ccfg.buffs) {
                Buff.installNotSkillHitPointBuff(player, buffid);
            }
        }
    }


    private void startMonster1() {
        state = State.IN_MONSTER1;
        nextWave();
    }

    private void startMonster2() {
        state = State.IN_MONSTER2;
        nextWave();
    }

    private void nextWave() {
        if(waveIndex == cgWaveIndex - 1 && state == State.IN_MONSTER1) {
            state = State.WAIT_OPEN_CG2;
            broadcastNotContextMsg(new SActionBegin(cfg.ectype.ExpEctype.CHANGE_CG));
            installBuffs();
            schedule(() -> onActionEnd(cfg.ectype.ExpEctype.CHANGE_CG), CfgMgr.ectypesingle.clientactiontimeout * 1000L);
            return;
        }
        if(++waveIndex >= ccfg.monsters.size()) {
            success();
            return;
        }
        broadcastNotContextMsg(new SNewMonsterWave(waveIndex));
        schedule(() -> {
            RefObject<Integer> count = new RefObject<>(0);
            for (cfg.ectype.CurrencyMonsterRef cm : ccfg.monsters.get(waveIndex).monsterref) {
                final PatrolInfo pi = convert(cm.patrol);
                for (Map.Entry<Integer, Integer> e : cm.monsters.entrySet()) {
                    final int monseterid = e.getKey();
                    final int n = e.getValue();
                    for(int i = 0 ; i < n ; i++) {
                        final Monster monseter = createMonster(monseterid, cm.regionid, pi.patrolType, pi.patrolVertexs);
                        monseter.addListener(Listener.DEATH, (go, evtid, param) -> {
                            if (--count.value <= 0) {
                                nextWave();
                            }
                        });
                    }
                    count.value += n;
                }
            }
        }, ccfg.monsterreftime * 1000L);
    }
}
