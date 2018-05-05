package map.map;

import cfg.CfgMgr;
import cfg.ectype.EctypeType;
import cfg.map.Reason;
import common.ErrorCode;
import map.agent.Player;
import map.map.story.PlainStory;
import map.map.story.Prologue;
import map.map.story.Story;
import map.msg.XCreateEcypeOnePlayer;

/**
 * Created by huangqiang on 2016/4/21.
 */
public abstract class OnePlayerEctype extends Ectype {

    public static Ectype create(XCreateEcypeOnePlayer builder) {
        final Builder b = new Builder();
        initCommon(b, builder.ectypeid, builder.istaskectype != 0, builder.serverid);
        b.roleid = builder.roleid;
        b.profession = builder.profession;
        b.param1 = builder.param1;
        b.param2 = builder.param2;
        b.failOnLeave = true;
        b.useBroadcastPolicy = false;

        b.innerSightRadius = CfgMgr.roleconfig.oneplayermapinnersightradius;
        b.outerSightRadius = CfgMgr.roleconfig.oneplayermapoutersightradius;

        switch (b.basiccfg.type) {
            case EctypeType.CURRENCY:
                return new CurrencyEctype(b);
            case EctypeType.EXP:
                return new ExpEctype(b);
            case EctypeType.ZAOHUA:
            case EctypeType.PETECTYPE:
            case EctypeType.FABAOECTYPE:
            case EctypeType.YUPEI:
            case EctypeType.HUFU:
                return new Daily(b);

            case EctypeType.CLIMB_TOWER:
                return new ClimbTower(b);
            case EctypeType.PERSONAL_BOSS:
                return new PersonalBoss(b);
            case EctypeType.STORY:
                return new Story(b);
            case EctypeType.HEROES:
                return new Heroes(b);
            case EctypeType.PROLOGUE:
                b.maxDefencerBodyRadius = cfg.ectype.Prologue.MAX_BOSS_BODY_RADIUS;
                return new Prologue(b);
            case EctypeType.PLAIN_STORY:
                return new PlainStory(b);
            default:
                throw new RuntimeException("unknown OnePlayerEctype type:" + b.basiccfg.type);
        }
    }

    public static class Builder extends Ectype.Builder {
        public long roleid;
        public int profession;
        public boolean failOnLeave;

        public int param1;
        public int param2;
    }

    protected final long roleid;
    protected final int profession;
    protected final boolean failOnLeave;


    protected Player player;
    private boolean ready;

    public OnePlayerEctype(Builder b) {
        super(b);

        this.roleid = b.roleid;
        this.profession = b.profession;
        this.failOnLeave = b.failOnLeave;

        this.player = null;
        this.ready = false;

        setSuspend();
    }

    @Override
    public boolean admit(long roleid) {
        return notEnd() && roleid == this.roleid;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        this.player = player;
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
    }

    @Override
    public void onPlayerLeave(Player player) {
        super.onPlayerLeave(player);
        this.player = null;
        if(notEnd() && failOnLeave && player.getReason() != Reason.LOGOUT) {
            addDeferTask(() -> checkEnd(ErrorCode.ECTYPE_PLAYER_LEAVE));
        }
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {
        checkEnd(ErrorCode.ECTYPE_MAX_DEAD_COUNT);

        // 像 Arena 这样的副本 直接Override了这个动作,
        // 如果在此函数里添加了其他动作,记得检查Arena
    }

    @Override
    public void onPlayerReady(Player player) {
        super.onPlayerReady(player);
        if(notEnd() && !this.ready) {
            this.ready = true;
            clearSuspend();
            onReady();
        }
    }

    protected void onReady() {

    }
}
