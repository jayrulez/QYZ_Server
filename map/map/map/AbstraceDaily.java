package map.map;

import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import common.ErrorCode;
import map.agent.Player;
import map.msg.Bonus;
import map.msg.MEndDailyEctype;
import map.msg.SEndDailyEctype;
import map.msg.SEnterDailyEctype;
import pathfinding.Vector3;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HuangQiang on 2016/8/9.
 */
public abstract class AbstraceDaily extends OnePlayerEctype {
    protected final HashMap<Integer, Integer> totalBonus = new HashMap<>();
    protected int waveIndex;
    protected long beginTime;
    
    private final long doubleBonusTime;
    private final cfg.cmd.action.Bonus passBonus;

    public AbstraceDaily(Builder b, long doubleBonusTime, cfg.cmd.action.Bonus passBonus) {
        super(b);
        this.doubleBonusTime = doubleBonusTime;
        this.passBonus = passBonus;
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    public final void createDropItems(List<Bonus> bonuss, Vector3 position, List<Player> owners) {
        super.createDropItems(bonuss, position, owners);
        for(map.msg.Bonus bonus : bonuss)
            common.Utils.addIntValue(totalBonus, bonus.items);
    }

    @Override
    protected final void onAsistKillMonster(Player member, int baseExp, int currency) {
        super.onAsistKillMonster(member, baseExp, currency);
        if(baseExp > 0)
            common.Utils.addValue(totalBonus, CurrencyType.JingYan, baseExp);
        if(currency > 0)
            common.Utils.addValue(totalBonus, CurrencyType.XuNiBi, currency);
    }

    @Override
    public final void sendPlayerEnter(Player player) {
        this.player = player;
        final SEnterDailyEctype re = new SEnterDailyEctype();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.waveindex = waveIndex;
        re.remaintime = remainTime;
        player.sendNotRoleMsg(re);
    }

    @Override
    protected void onFail(ErrorCode err) {
        final SEndDailyEctype re = new SEndDailyEctype();
        re.errcode = err.getErrorId();
        re.totalbonus = new map.msg.Bonus(EItemBindType.BOUND, totalBonus);

        if(err.ok()) {
            final map.msg.Bonus b1 = new map.msg.Bonus();
            common.Bonus.genBonusByProfession(profession, passBonus, 1, common.Bonus.BindType.BIND, b1);
            re.bonuss.add(b1);
            if(beginTime + doubleBonusTime * 1000 > endTime) {
                final map.msg.Bonus b2 = new map.msg.Bonus();
                common.Bonus.genBonusByProfession(profession, passBonus, 1, common.Bonus.BindType.BIND, b2);
                re.bonuss.add(b2);
            }
            Player.sendXdb(roleid, new MEndDailyEctype(ectypeid, (int)(getCostTime()/1000), re.bonuss));
        }
        sendContextMsg(re);
    }
}
