
package lx.gs.family.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import common.Time;
import gs.AProcedure;
import lx.gs.family.FFamily;
import lx.gs.map.FMap;
import map.msg.SStartFamilyParty;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenFamilyParty__ extends xio.Protocol { }

/** 开启家族仙府聚会
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenFamilyParty extends __COpenFamilyParty__ {
	@Override
	protected void process() {
        new AProcedure<COpenFamilyParty>(this){
            @Override
            protected boolean doProcess() throws Exception {
                if(!FMap.isInFamily(roleid)){
                    return error(ErrorCode.MUST_IN_FAMILY_STATION);
                }
                xbean.Family familyInfo = FFamily.getFamilyByRoleId(roleid);
                if(!FFamily.isFamilyLeader(roleid, familyInfo)){
                    return error(ErrorCode.ONLY_CHIEF_VICECHIEF_CAN_ACTION);
                }
                cfg.family.FamilyParty conf = CfgMgr.familyparty;
                long now = System.currentTimeMillis();
                if(common.Time.isSameDay(familyInfo.getLastpartyopentime(), now)){
                    return error(ErrorCode.HAS_OPEN_FAMILY_PARTY);
                }
//                //TODO  检查条件放宽，测试用
//                if(now - familyInfo.getLastpartyopentime() <= conf.duration * 1000){
//                    return error(ErrorCode.THE_PARTY_IS_DOING);
//                }
                long todayPass = Time.getDayMilliseconds(now);
                if((todayPass >= Time.getTodayMillisecondByHourAndMin(conf.starttime.get(0), conf.starttime.get(1)) &&
                todayPass <= Time.getTodayMillisecondByHourAndMin(conf.endtime.get(0), conf.endtime.get(1))) ||
                        (todayPass >= Time.getTodayMillisecondByHourAndMin(conf.starttime2.get(0), conf.starttime2.get(1)) &&
                                todayPass <= Time.getTodayMillisecondByHourAndMin(conf.endtime2.get(0), conf.endtime2.get(1)))){
                    familyInfo.setLastpartyopentime(now);
                }else{
                    return error(ErrorCode.NOT_THE_OPEN_TIME);
                }
                SStartFamilyParty msg = new SStartFamilyParty();
                msg.remaintime = CfgMgr.familyparty.duration * 1000;
                FMap.dispatchMessageInProcedure(roleid, msg);//开启家族聚宴,只能在家族副本内开启
                SOpenFamilyParty response = new SOpenFamilyParty();
                SFamilyPartyOpenNotify notify = new SFamilyPartyOpenNotify();
                notify.openid = roleid;
                FFamily.multicastAllFamily(familyInfo, notify);
                familyInfo.setLastpartycalltime(System.currentTimeMillis());
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576573;

	public int getType() {
		return 6576573;
	}


	public COpenFamilyParty() {
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
		if (_o1_ instanceof COpenFamilyParty) {
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

	public int compareTo(COpenFamilyParty _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

