package map.map;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.ectype.ItemRune;
import cfg.ectype.Rune;
import cfg.ectype.RuneInfo;
import cfg.item.EItemBindType;
import common.ErrorCode;
import map.MapUtils;
import map.agent.ARune;
import map.agent.Listener;
import map.agent.Monster;
import map.agent.Player;
import map.msg.*;
import map.skill.DamageParam;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/7/18.
 */
public class CurrencyEctype extends AbstraceDaily {

    private final cfg.ectype.CurrencyEctype ccfg;
    private int totalDamage;
    private int eatRuneCur;
    private boolean hasRefreshRune;
    private long lastSyncTime;
    private long syncInterval = 1000;
    private final List<Long> runes = new LinkedList<>();
    public CurrencyEctype(Builder b) {
        this(b, CfgMgr.currencyectype.get(b.subid));
    }

    private CurrencyEctype(Builder b, cfg.ectype.CurrencyEctype dcfg) {
        super(b, 0, dcfg.ectyperandomdrop);
        this.ccfg = dcfg;
    }

    public void init() {
        super.init();
//        waveIndex = -1;
        beginTime = 0;
        totalDamage = 0;
        hasRefreshRune = false;
//        schedule(() -> onActionEnd(cfg.ectype.CurrencyEctype.OPEN_CG), CfgMgr.ectypesingle.clientactiontimeout * 1000L);
    }

    @Override
    protected void onReady() {
        super.onReady();
        makeGoldCat();
        startRemainTime();
        lastSyncTime = System.currentTimeMillis();
    }

    //    public void onActionEnd(int actionid) {
//        if(actionid == cfg.ectype.CurrencyEctype.OPEN_CG && waveIndex == -1) {
//            startMonster();
//            startRemainTime();
//            this.beginTime = getNow();
//        }
//    }

//    private void nextWave() {
//        if(++waveIndex >= ccfg.monsters.size()) {
//            success();
//            return;
//        }
//        broadcastNotContextMsg(new SNewMonsterWave(waveIndex));
//        schedule(() -> {
//            RefObject<Integer> count = new RefObject<>(0);
//            for (cfg.ectype.CurrencyMonsterRef cm : ccfg.monsters.get(waveIndex).monsterref) {
//                final PatrolInfo pi = convert(cm.patrol);
//                for (Map.Entry<Integer, Integer> e : cm.monsters.entrySet()) {
//                    final int monseterid = e.getKey();
//                    final int n = e.getValue();
//                    for(int i = 0 ; i < n ; i++) {
//                        final Monster monseter = createMonster(monseterid, cm.regionid, pi.patrolType, pi.patrolVertexs);
//                        monseter.addListener(Listener.LEAVE, (go, evtid, param) -> {
//                            if (--count.value <= 0) {
//                                nextWave();
//                            }
//                        });
//                    }
//                    count.value += n;
//                }
//            }
//        }, ccfg.monsterreftime * 1000L);
//    }
//
//    private void startMonster() {
//        nextWave();
//    }

    private void makeGoldCat(){
        Monster monster = createMonster(ccfg.monster, MapUtils.c2p(ccfg.position), MapUtils.fixRotation(ccfg.orientangle));
        monster.addListener(Listener.BE_DAMAGE, (go, evtid, param) -> {
            final DamageParam dp = (DamageParam) param;
            if (totalDamage * ccfg.hurttocurrency < ccfg.maxgetcurrency) {
                player.onAsistKillMonster(0, (int)(dp.damage * ccfg.hurttocurrency));
                totalDamage += dp.damage;
            }
            if((int)(totalDamage * ccfg.hurttocurrency) >= ccfg.maxgetcurrency && !hasRefreshRune){
                hasRefreshRune = true;
                List<Integer> runeWeight = ccfg.runeinfo.stream().map(info -> info.weight).collect(Collectors.toList());
                for (cfg.map.Vector2 position : ccfg.positions) {
                    int runeIndex = common.Utils.getRandomIndex(runeWeight);
                    RuneInfo runeInfo = ccfg.runeinfo.get(runeIndex);
                    createRune(runeInfo.runeid, MapUtils.c2p(position), (rune) -> this.runes.add(rune.getAid()));
                }
                if(ccfg.spellbonustime * 1000L > remainTime){
                    remainTime = ccfg.spellbonustime * 1000L + 300;
                    timeoutTime = System.currentTimeMillis() + ccfg.spellbonustime * 1000L + 600;
                }
                schedule(()-> checkEnd(ErrorCode.OK), ccfg.spellbonustime * 1000L);
            }
        });
    }

    @Override
    protected void normalUpdate(long now) {
        super.normalUpdate(now);
        if(now > lastSyncTime + syncInterval){
            lastSyncTime = now;
            sendContextMsg(new SCurrencyGet((int)(totalDamage * ccfg.hurttocurrency)));
        }
    }

    @Override
    public boolean eatRune(Player player, ARune arune) {
        boolean result = super.eatRune(player, arune);
        if(result) {
            final Rune rune = CfgMgr.rune.get(arune.runeid);
            if (rune instanceof ItemRune) {
                ItemRune itemRune = (ItemRune) rune;
                if (itemRune.itemid == CurrencyType.XuNiBi) {
                    eatRuneCur += itemRune.num;
                }
            }
            runes.remove(arune.getAid());
            if (runes.isEmpty()) {
                checkEnd(ErrorCode.OK);
            }
        }
        return true;
    }

    @Override
    protected void onFail(ErrorCode err) { //always has the extra bonus
        final SEndDailyEctype re = new SEndDailyEctype();
        re.errcode = ErrorCode.OK.getErrorId();
        re.totalbonus.items.put(CurrencyType.XuNiBi, (int)(totalDamage * ccfg.hurttocurrency) + eatRuneCur);
        final map.msg.Bonus b1 = new map.msg.Bonus();
        common.Bonus.genBonusByProfession(profession, ccfg.ectyperandomdrop, 1, common.Bonus.BindType.BIND, b1);
        re.bonuss.add(b1);
        if(err.ok()) {
            Player.sendXdb(roleid, new MEndDailyEctype(ectypeid, (int) (getCostTime() / 1000), re.bonuss));
        }
        sendContextMsg(re);
    }
}
