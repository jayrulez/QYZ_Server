package map.agent;

import cfg.Const;
import cfg.map.Reason;
import cfg.mine.MineState;
import common.ErrorCode;
import map.MapUtils;
import map.buff.Buff;
import map.map.GameMap;
import map.msg.AgentBuilder;
import map.msg.MCollect;
import map.msg.SMineChange;
import map.msg.SNearbyMineEnter;
import xio.Protocol;

public final class AMine extends Agent {
	public static class Builder {
		public GameMap map;
		public AgentBuilder b;
		public cfg.mine.Mineral mcfg;
	}
	public AMine(Builder b) {
		super(b.map, b.b);
		this.mcfg = b.mcfg;
		this.state = cfg.mine.MineState.PROTECTED;
		this.digProctetedTime = System.currentTimeMillis() + (long)(mcfg.protecttime * 1000);
	}

	private final cfg.mine.Mineral mcfg;
	private int state;
	private long digger;
	private final long digProctetedTime;
	private long digCompleteTime;
	private long diggingExpireTime;
	private long diggedDisappearTime;

	@Override
	public String toString() {
		return String.format("Mine{aid:%s mineid:%s position:%s orient:%s}", getAid(), getMineid(), getPosition(), getOrient());
	}

    @Override
    public String getName() {
        return mcfg.name;
    }

    public final int getState() {
		return state;
	}
	
	public final void setState(int state) {
		this.state = state;
		
		final SMineChange re = new SMineChange();
		re.state = state;
		this.broadcastToNearby(re);
	}

	public final int getMineid() {
		return mcfg.id;
	}

	public ErrorCode beginDig(Player player) {
        if(mcfg.requiretaskid == Const.NULL) { //非任务矿进行状态检测
            if (state == cfg.mine.MineState.PROTECTED)
                return ErrorCode.MINE_IN_PROTECTED;
            if (state == cfg.mine.MineState.DIGGING)
                return ErrorCode.DIGGING_BY_OTHER;
            if (state != cfg.mine.MineState.NORMAL)
                return ErrorCode.CANNOT_DIG;
        }
		
		if(player.getPosition().getSubXZMagnitude(getPosition()) > mcfg.collectradius + 10)
			return ErrorCode.OUT_OFF_DIG_DISTANCE;
//		if(mcfg.requirebuff != 0 &&  !player.getBuffMgr().hasEffect(mcfg.requirebuff))
//			return ErrorCode.DIG_REQUIRE_BUFF;
		final long roleid = player.getRoleid();

        if(mcfg.requiretaskid == Const.NULL) {
            setState(cfg.mine.MineState.DIGGING);
        }
		digger = roleid;
		digCompleteTime = System.currentTimeMillis() + (long)(mcfg.costtime * 1000);
		diggingExpireTime = digCompleteTime + 10 * 1000;
		return ErrorCode.OK;
	}
	
	public ErrorCode endDig(Player player) {
        final long roleid = player.getRoleid();
        if(mcfg.requiretaskid == Const.NULL) {
            if (state != cfg.mine.MineState.DIGGING)
                return ErrorCode.CANNOT_DIG;
            if (roleid != digger)
                return ErrorCode.DIGGING_BY_OTHER;
        }
		final long now = System.currentTimeMillis();
//        xdb.Trace.info("AMine, digcompleteTime is = {}, now = {}", digCompleteTime, now);
//		if(Time.before(digCompleteTime, now)) //cancle time check
//			return ErrorCode.DIGGING_NOT_FINISH;

//		if(mcfg.gainbuff != 0) {
//			Buff.installNotSkillHitPointBuff(player, mcfg.gainbuff);
//		}
        if(mcfg.requiretaskid == Const.NULL) {//非任务矿才会消失,才会设置状态
            setState(cfg.mine.MineState.DIGGED);
        }
		diggedDisappearTime = now + (long)(mcfg.disappeartime * 1000);
        player.sendXdb(new MCollect(mcfg.id));//消息传递，通知xdb
		//Utils.onCollectMine(player.getRoleid(), mcfg.mapid);
		return ErrorCode.OK;
	}

	public ErrorCode cancelDig(Player player) {
		if(state != MineState.DIGGING)
			return ErrorCode.NOT_IN_DIGGING;
		final long roleid = player.getRoleid();
		if(roleid != digger)
			return ErrorCode.DIGGING_BY_OTHER;
		setState(MineState.NORMAL);
		digger = 0;
		return ErrorCode.OK;
	}

	@Override
	public void update(long now) {
		switch(state) {
		case cfg.mine.MineState.PROTECTED: {
			if(digProctetedTime < now)
				setState(cfg.mine.MineState.NORMAL);
			break;
		}
		case cfg.mine.MineState.DIGGING: {
			if(diggingExpireTime < now) {
				setState(cfg.mine.MineState.NORMAL);
				digger = 0;
			}
			break;
		}
		case cfg.mine.MineState.DIGGED: {
			if(mcfg.disappear && diggedDisappearTime < now) {
				// 消失时间到了后,从地图移除
				addDeferTask(() -> map.leave(this, Reason.COLLECTED));
			}
			break;
		}
		}
	}

	@Override
	public Protocol createSelfEnter() {
		final SNearbyMineEnter re = new SNearbyMineEnter();
		re.agentid = getAid();
		re.mineid = mcfg.id;
		re.digger = digger;
		re.position = MapUtils.p2m(position);
		re.orient = MapUtils.p2m(orient);
		re.state = state;
		return re;
	}

}
