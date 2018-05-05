package lx.gs.cmd;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import common.ErrorCode;
import lx.gs.logger.By;

import java.util.Collection;
import java.util.HashMap;

import static cfg.Const.NULL;

public final class FCmd {
	
	public final static class Context {
		private Context() {
			
		}
		public long roleid;
		public int configid;
		public int cmdid;
		public By by;
		public int num;
		public ErrorCode errcode;
		public map.msg.Bonus bonus;
		
		public map.msg.Bonus getOrCreateBonus() {
			if(bonus == null) {
				bonus = new map.msg.Bonus();
				bonus.bindtype = EItemBindType.BOUND;
			}
			return bonus;
		}
	}
	
	public static Context createCtx(long roleid, int configid, int id) {
		return createCtx(roleid, configid, id, 1);
	}
	
	public static Context createCtx(long roleid, int configid, int id, int num) {
		final Context ctx = new Context();
		ctx.errcode = ErrorCode.OK;
		ctx.roleid = roleid;
		ctx.configid = configid;
		ctx.cmdid = id;
		ctx.num = num;
		ctx.by = By.ofCmd(configid, id);
		return ctx;
	}
	
	public static Context createCtx(long roleid, int configid, int id, By by) {
		return createCtx(roleid, configid, id, 1, by);
	}
	
	public static Context createCtx(long roleid, int configid, int id, int num, By by) {
		final Context ctx = new Context();
		ctx.errcode = ErrorCode.OK;
		ctx.roleid = roleid;
		ctx.configid = configid;
		ctx.cmdid = id;
		ctx.num = num;
		ctx.by = by;
		return ctx;
	}
	
	public static void checkAndProcess(Context ctx, Collection<cfg.cmd.condition.Condition> conditions, Collection<cfg.cmd.action.Action> actions) {
		ctx.errcode = FCondition.check(ctx.roleid, ctx.num, ctx.by, ctx.configid, ctx.cmdid, conditions);
		if(ctx.errcode.ok()) {
			FAction.process(ctx, actions);
		}
	}
	
	public static void checkAndProcessByReflection(Context ctx, Object cmd) {
		ctx.errcode = FCondition.checkByReflection(ctx.roleid, cmd, ctx.num, ctx.by, ctx.configid, ctx.cmdid);
		if(ctx.errcode.ok()) {
			FAction.processByReflection(ctx, cmd);
		}
	}
	
	public static Context invoke(long roleid, int configid, int cmdid) {
		return invoke(roleid, configid, cmdid, 1, By.ofCmd(configid, cmdid));
	}
	
	public static Context invoke(long roleid, int configid, int cmdid, int num) {
		return invoke(roleid, configid, cmdid, num, By.ofCmd(configid, cmdid));
	}
	
	public static Context invoke(long roleid, int configid, int cmdid, By by) {
		return invoke(roleid, configid, cmdid, 1, by);
	}

	public static Context invoke(long roleid, int configid, int cmdid, int num, By by) {
		xdb.Trace.info("FCmd.invoke roleid:{} configid:{} cmdid:{} num:{}", roleid, configid, cmdid, num);
		final Context ctx = createCtx(roleid, configid, cmdid, num, by);
		final CmdConfig config = cmdconfigs.get(configid);
		if(config != null) {
			if((config.multi && num > 0) || (!config.multi && num == 1)) {
				checkAndProcessByReflection(ctx, config.config.get(cmdid));
			} else {
				ctx.errcode = ErrorCode.INVALID_NUM;
			}
		} else {
			ctx.errcode = ErrorCode.INVALID_CONFIGID;
		}
		return ctx;
	}
	
	/*
	 * 参数顺序与上面的不同,纯粹了为避免误调了invoke(long,int,int,by)
	 */
	public static Context invoke(long roleid, By by, Object cmd, int num) {
		final Context ctx = createCtx(roleid, NULL, NULL, num, by);
		if(num > 0) {
			checkAndProcessByReflection(ctx, cmd);
		} else {
			ctx.errcode = ErrorCode.INVALID_CONFIGID;
		}
		return ctx;
	}
	
	public static Context invoke(long roleid, By by, Object cmd) {
		return invoke(roleid, by, cmd, 1);
	}
	
	public static final class CmdConfig {
		private final int configid;
		private final HashMap<Integer, ?> config;
		private final boolean multi;
		public CmdConfig(int configid, HashMap<Integer, ?> config, boolean multi) {
			this.configid = configid;
			this.config = config;
			this.multi = multi;
		}
	}
	
	private static final HashMap<Integer,CmdConfig> cmdconfigs = new HashMap<>();
	public static void register(CmdConfig... configs) {
		for(CmdConfig config : configs) {
			cmdconfigs.put(config.configid, config);
		}
	}
	
	public static void init() {
		register(
			 new CmdConfig(cfg.cmd.ConfigId.MALL, CfgMgr.mall, true)
//			,new CmdConfig(cfg.cmd.ConfigId.LOTTERY, CfgMgr.gradeexchange, true)
		);
	}
}
