package map.map;

import cfg.CfgMgr;
import common.ErrorCode;
import map.agent.Player;
import map.msg.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HuangQiang on 2016/8/31.
 */
public class FamilyTeam extends MultiPlayerEctype {
    public static FamilyTeam create(XCreateFamilyTeam msg) {
        final Builder b = new Builder();
        initCommon(b, CfgMgr.familyteam.ectypeid, false, msg.serverid);

        b.countDownDurationTime = 0;
        b.useBroadcastPolicy = false;
        for(FamilyTeamMember m : msg.members) {
            b.roles.add(m.roleid);
        }
        b.level = msg.level;
        b.members = msg.members;
        return new FamilyTeam(b);
    }

    public static class Builder extends MultiPlayerEctype.Builder {
        int level;
        ArrayList<FamilyTeamMember> members;
    }

    public FamilyTeam(Builder b) {
        super(b);

        this.tcfg = CfgMgr.familyteam;
        this.mcfg = getByLevel(tcfg.levelmonsterinfos, b.level);
        this.members = b.members;

        this.waveindex = 0;
    }

    private cfg.ectype.FamilyTeamLevelMonserInfo getByLevel(List<cfg.ectype.FamilyTeamLevelMonserInfo> infos, int level) {
        cfg.ectype.FamilyTeamLevelMonserInfo prevInfo = infos.get(0);
        for(cfg.ectype.FamilyTeamLevelMonserInfo info : infos) {
            if(info.level >= level)
                return prevInfo;
            prevInfo = info;
        }
        return prevInfo;
    }

    private final cfg.ectype.FamilyTeam tcfg;
    private final cfg.ectype.FamilyTeamLevelMonserInfo mcfg;
    private final ArrayList<FamilyTeamMember> members;
    private int waveindex; // 从1开始

    @Override
    protected void onBattleBegin() {
        final List<BatchMonsterInfo> batches = new ArrayList<>();
        for(cfg.ectype.FamilyTeamMonsterWave b : mcfg.monsterwaves) {
            final BatchMonsterInfo n = new BatchMonsterInfo();
            n.delaySeconds = tcfg.waveinterval;
            n.ignoreRegionid = 0;
            n.regionid = b.regionid;
            n.monsters = Collections.singletonMap(b.monster, b.num);
            n.onBatchBegin = () -> {
                ++waveindex;
                sendContextMsg(new SNewMonsterWave(waveindex));
            };
            batches.add(n);
        }
        createMultiBatchMonsters(batches, this::success);
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        getRoles().stream().filter(aLong -> aLong != player.getRoleid())
                .forEach(aLong -> sendToEachother(player, getPlayer(aLong)));
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    protected void onFail(ErrorCode err) {
        if(err.ok()) {
            for(FamilyTeamMember member : members) {
                final Player player = players.get(member.roleid);
                if(player == null) continue;
                final SEndFamilyTeam msg = new SEndFamilyTeam();
                if(member.hasbonus != 0) {
                    common.Bonus.genBonusByProfession(player.getProfession(), tcfg.levelbonus_level.get(player.getLevel()).bonus, msg.bonus);
                }
                player.sendNotRoleMsg(msg);
                player.sendXdb(new MEndFamilyTeam(0, msg.bonus));
            }
        } else {
            sendContextMsg(new SEndFamilyTeam(err.getErrorId(), new Bonus()));
        }
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterFamilyTeam msg = new SEnterFamilyTeam();
        msg.ectypeid = tcfg.ectypeid;
        msg.mapid = getMapid();
        msg.monsterwaveindex = waveindex;
        player.sendNotRoleMsg(msg);
    }
}
