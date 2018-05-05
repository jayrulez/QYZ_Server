package lx.gs.paomadeng;

import cfg.CfgMgr;
import cfg.item.EItemColor;
import lx.gs.chat.FChat;
import lx.gs.paomadeng.msg.SPaomadengShow;

/**
 * Created by xiong on 2016/7/5.
 */
public class FPaomadeng {

    public static void sendRolelvlup(long roleid, int lvl){
        if(CfgMgr.paomadeng.rolelevelup.contains(lvl)){
            send(roleid, xtable.Roleinfos.selectName(roleid), SPaomadengShow.ROLE_LEVELUP, 0, lvl);
        }
    }

    public static void sendEvolve(long roleid, int type, int moduleid){
        send(roleid, xtable.Roleinfos.selectName(roleid), type, moduleid, 0);
    }

    public static void sendPickCard(long roleid, int moduleid){
        cfg.pet.PetBasicStatus pbs = CfgMgr.petbasicstatus.get(moduleid);
        cfg.talisman.TalismanBasic tbs = CfgMgr.talismanbasic.get(moduleid);
        if((pbs != null && pbs.basiccolor == EItemColor.RED) || (tbs != null && tbs.quality == EItemColor.RED)){
            send(roleid, xtable.Roleinfos.selectName(roleid), SPaomadengShow.PICKCARD, moduleid, 0);
        }
    }

    public static void send(long roleid, String rolename, int type, int moduleid, int lvl) {
        SPaomadengShow notify = new SPaomadengShow();
        notify.roleid = roleid;
        notify.rolename = rolename;
        notify.operatetype = type;
        notify.id = moduleid;
        notify.lvl = lvl;
        xdb.Transaction.texecuteWhileCommit(() -> FChat.sendSystemMessage(notify));
    }

}
