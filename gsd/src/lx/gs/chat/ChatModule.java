package lx.gs.chat;

import cfg.CfgMgr;
import com.goldhuman.Common.Octets;
import common.LRULinkedHashMap;
import gs.RoleCreateListener;
import lx.gs.bonus.FBonus;
import lx.gs.chat.msg.SChatMsg;
import xbean.Pod;
import xdb.Procedure;
import xtable.Rolechat;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum ChatModule implements gs.Module, gs.RoleLoginListener, gs.RoleDayOverListener, RoleCreateListener {
	INSTANCE;

    final static AtomicInteger NEXT_VOICE_ID = new AtomicInteger(0);
    final static LRULinkedHashMap<Integer, Octets> cacheVoices = LRULinkedHashMap.newInstance(200);

	@Override
	public void start() {
	}




    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        SChatMsg chatStatus = new SChatMsg();
        xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
        chatStatus.lasttalktime = roleInfo.getLastworldtalktime();
        chatStatus.silentendtime = roleInfo.getSilentendtime();
        chatStatus.leftreporttime = roleInfo.getLeftreporttime();
        xdb.Transaction.tsendWhileCommit(roleid, chatStatus);
        FChat.syncChatFace(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }


    @Override
    public void onDayOver(long roleid) {
        new Procedure() {
            @Override
            protected boolean process() {
                xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
                roleInfo.setLeftreporttime(CfgMgr.roleconfig.everydayreportnum);//reset left report time
                return true;
            }
        }.execute();
    }

    @Override
    public void onRoleCreateInProcedure(long roleid) {
        Rolechat.add(roleid, Pod.newRoleChat());
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {
    }
}
