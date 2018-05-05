package lx.gs.cmd;

import cfg.Const;
import cfg.cmd.action.*;
import common.ErrorCode;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCmd.Context;
import lx.gs.family.FFamily;
import lx.gs.logger.By;

import java.lang.reflect.Field;
import java.util.Collection;

import static cfg.Const.NULL;
import static common.ErrorCode.OK;

public final class FAction {
    public static ErrorCode processA(Context ctx, Action action) {
        final long roleid = ctx.roleid;
        if (action instanceof Bonus) {
            FBonus.genBonus(roleid, (Bonus) action, ctx.num, ctx.getOrCreateBonus());
            return OK;
        }

        switch (action.getTypeId()) {
            case MultiAction.TYPEID: {
                for (Action act : ((MultiAction) action).actions) {
                    final ErrorCode err = processA(ctx, act);
                    if (err.err())
                        return err;
                }
                return OK;
            }
//            case OpenEctype.TYPEID: {
//                final OpenEctype c = (OpenEctype) action;
//                return FEctype.openEctype(roleid, c.ectypeid, null);
//            }
            case AddFamilyMoneyBuild.TYPEID: {
                final AddFamilyMoneyBuild c = (AddFamilyMoneyBuild) action;
                return FFamily.addFamilyBuildAndMoneyByRole(roleid, c.money, c.buildv);
            }
//            case LevelBonus.TYPEID: {
//                final LevelBonus c = (LevelBonus)action;
//                final int level = xtable.Roleinfos.selectLevel(roleid);
//                if(level >= c.minlevel && (c.maxlevel == Const.NULL || level <= c.maxlevel)) {
//                    FBonus.genBonus(roleid, c.bonus, ctx.num, ctx.getOrCreateBonus());
//                }
//                return ErrorCode.OK;
//            }
            default: {
                throw new RuntimeException("unknown action:" + action);
            }
        }
    }

    public static FCmd.Context process(FCmd.Context ctx, Collection<Action> actions) {
        for (Action action : actions) {
            ctx.errcode = processA(ctx, action);
            if (ctx.errcode.err())
                return ctx;
        }
        bonus(ctx);
        return ctx;
    }

    /**
     * 检查cmd对象里所有Action类型顶级字段,并处理它们
     *
     * @param ctx
     * @param cmd
     * @return
     */
    public static FCmd.Context processByReflection(FCmd.Context ctx, Object cmd) {
        final Class<?> cls = cmd.getClass();
        for (Field f : cls.getFields()) {
            try {
                if (Action.class.isAssignableFrom(f.getType())) {
                    ctx.errcode = processA(ctx, (Action) f.get(cmd));
                    if (ctx.errcode.err())
                        return ctx;
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // impossible
                ctx.errcode = ErrorCode.INTERNAL_ERR;
                return ctx;
            }
        }
        bonus(ctx);
        return ctx;
    }

    public static FCmd.Context processByReflection(long roleid, Object cmd, int num, By addby, int configid, int cmdid) {
        final Context ctx = FCmd.createCtx(roleid, configid, cmdid, num, addby);
        return processByReflection(ctx, cmd);
    }

    public static FCmd.Context processByReflection(long roleid, Object cmd, int num, By addby) {
        return processByReflection(roleid, cmd, num, addby, NULL, NULL);
    }

    public static FCmd.Context processByReflection(long roleid, Object cmd, By addby) {
        return processByReflection(roleid, cmd, 1, addby, NULL, NULL);
    }

    private static void bonus(FCmd.Context ctx) {
        if (ctx.bonus != null) {
            if(!FBonus.addBonus(ctx.roleid, ctx.bonus, ctx.by)){
                ctx.errcode = ErrorCode.BAG_FULL;
            }
        }
    }
}
