
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.currency.CurrencyType;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.family.FFamily;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xdb.Lockeys;

import java.util.Set;
import java.util.Map.Entry;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelCrowdFamily__ extends xio.Protocol { }

/** 取消众筹家族,只有发起人才取消
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelCrowdFamily extends __CCancelCrowdFamily__ {
	@Override
	protected void process() {
//		new AProcedure<CCancelCrowdFamily>(this) {
//
//			@Override
//			protected boolean doProcess() throws Exception {
//				xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
//				if(roleFamily.getInitcrowdfamid() == 0){
//					return error(ErrorCode.NOT_INIT_CORWD_FAMILY);
//				}
//				xbean.CrowdFamily crowdInfo = FFamily.getCrowdFamByid(roleFamily.getInitcrowdfamid());
//				assert(crowdInfo != null);
//				FFamily.getCrowdFamilyNames().remove(FFamily.makeFullname(crowdInfo.getFamilyname()));
//				roleFamily.setInitcrowdfamid(0);
//				int returnYuanbao = Math.round(crowdInfo.getInityuanbao() *(1 - cfg.family.FamilyInfo.FUND_FAILED_COST));
//				FRole.addCurrency(roleid, CurrencyType.YuanBao, returnYuanbao, By.Crowd_Family_Return);
//
//				lx.gs.family.msg.SCrowFamFailNotity failNotify = new lx.gs.family.msg.SCrowFamFailNotity();
//				failNotify.faminfo = FFamily.makeProtocolCrowdFam(crowdInfo);
//				//退钱给参与该众筹的玩家
//				Set<Long> roles = crowdInfo.getInvolveroles().keySet();
//				lock(Lockeys.get(xtable.Locks.ROLELOCK, roles));
//				for(Entry<Long, Integer> e : crowdInfo.getInvolveroles().entrySet()){
//					FRole.addCurrency(e.getKey(), CurrencyType.YuanBao, e.getValue(), By.Crowd_Family_Return);
//				}
//				gnet.link.Onlines.getInstance().send(roles, failNotify);
//				xtable.Crowdfamily.remove(roleFamily.getInitcrowdfamid());//从表中移除该项
//				xdb.Trace.info("Cancel crowd family success, family id = {}, family name = {}", crowdInfo.getCrowdfamilyid(), crowdInfo.getFamilyname());
//				SCancelCrowdFamily response = new SCancelCrowdFamily();
//				response.returnyuanbao = returnYuanbao;
//				response(response);
//				return true;
//			}
//
//		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570365;

	public int getType() {
		return 6570365;
	}


	public CCancelCrowdFamily() {
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
		if (_o1_ instanceof CCancelCrowdFamily) {
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

	public int compareTo(CCancelCrowdFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

