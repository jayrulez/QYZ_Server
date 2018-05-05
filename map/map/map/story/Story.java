
package map.map.story;

import cfg.CfgMgr;
import cfg.Const;
import cfg.ectype.StarCondition;
import cfg.item.EItemBindType;
import cfg.map.Reason;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.Player;
import map.map.MapMgr;
import map.map.OnePlayerEctype;
import map.msg.MEndStoryEctype;
import map.msg.MLeaveMap;
import map.msg.SEndStoryEctype;
import map.msg.SEnterStoryEctype;

import java.util.stream.Collectors;

public class Story extends AbstractStory {
	public Story(OnePlayerEctype.Builder b) {
		super(b);
		this.storyCfg = CfgMgr.storyectype.get(b.subid);
		this.lastStar = b.param1;
        this.roleid = b.roleid;
        this.profession = b.profession;

        this.player = null;
	}
	

    private final long roleid;
    private final int profession;
    private final int lastStar;
	private final cfg.ectype.StoryEctype storyCfg;

    protected Player player;
    private boolean ready;

    @Override
    public boolean admit(long roleid) {
        return notEnd() && roleid == this.roleid;
    }

	@Override
	public void sendPlayerEnter(Player player) {
		player.sendNotRoleMsg(genSEnterEctype(player));
	}

	public void init() {
		super.init();

        this.ready = false;
		setSuspend();
	}
	
	SEnterStoryEctype genSEnterEctype(Player player) {
		final SEnterStoryEctype re = new SEnterStoryEctype();
		re.id = getMapid();
		re.ectypeid = ectypeid;
		re.remaintime = remainTime;
		re.remainrevivecount = basiccfg.reviveinfo.maxcount;
		re.enviroments.putAll(this.enviroments);
		re.openlayouts.addAll(layouts.values().stream().map(this::genLayoutInfo).collect(Collectors.toList()));
		re.activeactions.addAll(actions.keySet());
		return re;
	}

	private int calcStar() {
		int star = 0;
		for(cfg.ectype.StarConditionInfo sc : storyCfg.starcondition) {
			switch (sc.condition) {
				case StarCondition.CLEAR: {
					star++;
					break;
				}
				case StarCondition.CLEAR_IN_SECONDS: {
					if(basiccfg.totaltime - (int)(remainTime / 1000) <= sc.value) {
						star++;
					}
					break;
				}
				case StarCondition.DEAD_TIMES_LOWER_THAN: {
					if(getDeadCount(roleid) <= sc.value) {
						star++;
					}
					break;
				}
				default: {
					throw new RuntimeException("unknown StarCondition:" + sc.condition);
				}

			}
		}
		return star;
	}


	@Override
	protected void onFail(ErrorCode err) {
		final SEndStoryEctype re = new SEndStoryEctype();
		if(err.ok()) {
			final int star = calcStar();
			re.star = star;

			common.Bonus.genBonusByProfession(profession, storyCfg.ectypedrop, 1, re.bonus);
			// 首次三星奖励
			if (star == 3 && lastStar < 3) {
				common.Bonus.genBonusByProfession(profession, storyCfg.starbonus, 1, re.bonus);
				re.isfirst3star = 1;
			}

			final MEndStoryEctype msg = new MEndStoryEctype();
			msg.ectypeid = getEctypeid();
			msg.star = star;
			msg.bonus = re.bonus;
			Player.sendXdb(roleid, msg);

		} else {
			re.errcode = err.getErrorId();
		}
		sendContextMsg(re);
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

    @Override
    public void onPlayerReady(Player player) {
        super.onPlayerReady(player);
        if(notEnd() && !this.ready) {
            this.ready = true;
            clearSuspend();
            startRemainTime();
        }
    }
}
