
package lx.gs.family.msg;

import cfg.family.FamilyInfo;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.family.FFamily;
import lx.gs.family.FFamilyModule;
import lx.gs.leaderboard.LeaderBoardModule;
import lx.gs.role.FRole;
import xdb.Lockeys;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRequestJoinAllFamily__ extends xio.Protocol { }

/** 一键申请加入家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRequestJoinAllFamily extends __CRequestJoinAllFamily__ {
	@Override
	protected void process() {
		new AProcedure<CRequestJoinAllFamily>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
                if(!FFamily.isFamilyUnLocked(roleid)) return error(ErrorCode.FAMILY_MODULE_LOCKED);
                if(FFamily.isInQuitTimeLimit(info, cfg.family.FamilyInfo.DISALLOW_ACTION_HOUR_AFTER_QUIT))
                    return error(ErrorCode.FAMILY_ACTION_DISALLOWED_AFATER_QUIT);
                if(info.getCurrentfid() > 0)
                    return error(ErrorCode.FAMILY_ALREADY_IN_ONE);
                final Map<Long, Long> requestFamilys = info.getRequestedfamily();
                final int hasJoinNum = requestFamilys.size();
                if(hasJoinNum >= FFamilyModule.FAMILY_MAX_APPLAY_NUM){
                    return error(ErrorCode.APPLY_FAMILY_NUM_MAX);
                }
                int leftCanJoin = FFamilyModule.FAMILY_MAX_APPLAY_NUM - hasJoinNum;
                long curtime = System.currentTimeMillis();

                Set<Long> fids = new HashSet<>();
                if (leftCanJoin >= FFamilyModule.FAMILY_MAX_APPLAY_NUM) { //按 3+2的方案
                    Map<Integer, Long> latest = LeaderBoardModule.familyRankMap;
                    for (int i = 1, j = FFamilyModule.NORMAL_FAMILY_APPLY_NUM; i <= latest.size() && j > 0; i++) {
                        if (latest.containsKey(i)) {
                            long fid = latest.get(i);
                            if (requestFamilys.containsKey(fid)) {
                                continue;
                            }
                            xtable.Family.getTable().select(fid, family -> {
                                if (family.getIssystemfam() == 0 || family.getRequestinglist().size() <= FFamily.leftPositions(family))
                                    fids.add(fid);
                                return family;
                            });
                            j--;
                        }
                    }
                    Set<Long> systemFam = FFamily.randomChooseTwoSysFams(FFamilyModule.SYSTEM_FAMILY_APPLY_NUM);
                    systemFam.forEach(f -> {
                        if (!requestFamilys.containsKey(f)) {
                            fids.add(f);
                        }
                    });
                } else {
                    Set<Long> systemFam = FFamily.randomChooseTwoSysFams(leftCanJoin);
                    systemFam.forEach(f -> {
                        if (!requestFamilys.containsKey(f)) {
                            fids.add(f);
                        }
                    });
                }
                lock(Lockeys.get(xtable.Locks.FAMILYLOCK, fids));
                for(long f : fids) {
                    xbean.Family family = FFamily.getFamilyByFamilyId(f);
                    if(family == null)
                        continue;
                    info.getRequestedfamily().put(f, curtime);
                    family.getRequestinglist().put(roleid, curtime);
                    SRequestJoinFamilyNotify notify = new SRequestJoinFamilyNotify();
                    FRole.genRoleShowInfo(roleid, notify.roleinfo);
                    FFamily.sendNotifyToChiefViceChief(family, notify);//分别给每个家族发通知
                }
                SRequestJoinAllFamily response = new SRequestJoinAllFamily();
                response.familyids.addAll(fids);
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586198;

	public int getType() {
		return 6586198;
	}


	public CRequestJoinAllFamily() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRequestJoinAllFamily) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRequestJoinAllFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

