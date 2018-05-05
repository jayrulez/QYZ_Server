package map.map.story;

import cfg.CfgMgr;
import cfg.Const;
import cfg.map.Reason;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.Player;
import map.map.MapMgr;
import map.map.OnePlayerEctype;
import map.msg.MEndPlainStoryEctype;
import map.msg.MLeaveMap;
import map.msg.SEndPlainStoryEctype;
import map.msg.SEnterPlainStoryEctype;

import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/7/6.
 */
public class PlainStory extends AbstractStory {


    public PlainStory(OnePlayerEctype.Builder b) {
        super(b);
        this.roleid = b.roleid;

        this.player = null;
    }


    private final long roleid;

    protected Player player;

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
        setSuspend();
    }

    SEnterPlainStoryEctype genSEnterEctype(Player player) {
        final SEnterPlainStoryEctype re = new SEnterPlainStoryEctype();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.remaintime = remainTime;
        re.remainrevivecount = basiccfg.reviveinfo.maxcount;
        re.enviroments.putAll(this.enviroments);
        re.openlayouts.addAll(layouts.values().stream().map(this::genLayoutInfo).collect(Collectors.toList()));
        re.activeactions.addAll(actions.keySet());
        return re;
    }

    @Override
    protected void onFail(ErrorCode err) {
        final SEndPlainStoryEctype re = new SEndPlainStoryEctype();
        if(err.ok()) {
            final MEndPlainStoryEctype msg = new MEndPlainStoryEctype();
            msg.ectypeid = getEctypeid();
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
}
