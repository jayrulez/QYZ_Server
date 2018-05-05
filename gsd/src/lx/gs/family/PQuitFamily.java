package lx.gs.family;

import cfg.map.Reason;
import gs.AProcedure;
import lx.gs.event.EventModule;
import lx.gs.event.FamilyDisbandEvent;
import lx.gs.family.msg.CQuitFamily;
import lx.gs.family.msg.SQuitFamily;
import lx.gs.family.msg.SQuitFamilyNotify;
import lx.gs.map.FMap;
import xdb.Lockeys;
import xdb.Transaction;

import java.util.Map;

public class PQuitFamily extends AProcedure<CQuitFamily> {

	public PQuitFamily(CQuitFamily p) {
		super(p);
	}

	@Override
	protected boolean doProcess() throws Exception {
        xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
        final long familyid = info.getCurrentfid();
		xbean.Family family = FFamily.getFamilyByFamilyId(familyid);

		xbean.FamilyMember minfo = family.getMembers().get(roleid);
		long curtime = System.currentTimeMillis();
		boolean needNotify = true;
		if(family.getChiefid() == roleid && !FFamily.transferFamilyChief(family, 0)){
            //族长无法转让
            needNotify = false;
            xtable.Family.remove(familyid);
            xdb.Transaction.texecuteWhileCommit(() -> FFamily.FamilyId2MapId.remove(familyid));
            String fullName = FFamily.makeFullname(family.getFamilyname());
            FFamily.getFamilyNames().remove(fullName);
            for(long rid : family.getRequestinglist().keySet()) {
                Transaction.texecuteWhileCommit(new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(rid);
                        roleFamily.getRequestedfamily().remove(familyid);
                        return true;
                    }
                });
            }
            EventModule.INSTANCE.broadcastEvent(new FamilyDisbandEvent(roleid, familyid, fullName));
		} else {
            if(minfo.getFamilyjob() != cfg.family.FamilyJobEnum.MEMBER){
                FFamily.removeJobStaff(family, minfo.getFamilyjob(), roleid);
            }
        }
		info.setCurrentfid(0);
		info.setLastquittime(curtime);
		info.setTotalquitnum(info.getTotalquitnum()+1);
        family.getMembers().remove(roleid);
        //从仙府中退出去
        long mapid = FFamily.FamilyId2MapId.getOrDefault(familyid, 0L);
//        xdb.Trace.info("PQuitFamily leave family station, mapid is " + mapid);
        if (mapid != 0) {
            FMap.sendToKickFamilyMem(familyid, mapid, roleid, Reason.LEAVE_FAMILY);
        }

        if(needNotify){
			SQuitFamilyNotify notify = new SQuitFamilyNotify();
			notify.memberid = roleid;
			xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
			notify.membername = role.getName();
			FFamily.sendNotifyToChiefViceChief(family, notify);
			
//			FFamily.multicastAllFamily(family, notify);
			FFamily.addFamilyLog(family, roleid, lx.gs.family.msg.FamilyLogReport.TYPE_QUIT_FAMILY, 0);
		}
		FFamily.onLeaveFamily(roleid);
		
		response(new SQuitFamily());
		return true;
	}

}
