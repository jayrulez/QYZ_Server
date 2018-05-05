
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.family.FFamily;
import lx.gs.family.FFamilyModule;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xdb.Lockeys;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCrowdFamily__ extends xio.Protocol { }

/** 众筹家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCrowdFamily extends __CCrowdFamily__ {
	@Override
	protected void process() {
//		new AProcedure<CCrowdFamily>(this) {
//
//			@Override
//			protected boolean doProcess() throws Exception {
//				//家族模块是否开放
//				if(!FFamily.isFamilyUnLocked(roleid)) return error(ErrorCode.FAMILY_MODULE_LOCKED);
//				//check名字是否可用
//				if (familyname.length() < cfg.family.FamilyInfo.NAME_MIN_LENGTH) {
//					return error(ErrorCode.NAME_SHORTLEN);
//				}
//				if (familyname.length() > cfg.family.FamilyInfo.NAME_MAX_LENGTH) {
//					return error(ErrorCode.NAME_OVERLEN);
//				}
//                xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
//                if(FFamily.isInQuitTimeLimit(info, cfg.family.FamilyInfo.DISALLOW_ACTION_HOUR_AFTER_QUIT))
//                    return error(ErrorCode.FAMILY_ACTION_DISALLOWED_AFATER_QUIT);
//				// 敏感词和唯一名检测
//				if (FRole.isSenseWord(familyname)) {
//					return error(ErrorCode.NAME_SENSE);
//				}
//				String fullName = FFamily.makeFullname(familyname);
//				if(FFamily.getFamilyIdByName(fullName) != 0 || FFamily.getCrowdFamilyIdByName(fullName) != 0) {
//					return error(ErrorCode.FAMILY_HAS_EXIST);
//				}
//
//				final int cost = cfg.family.FamilyInfo.CREATE_REQUIRE_YUANBAO;
//				final int mincost = cfg.family.FamilyInfo.MIN_CROWD_FUND_YUANBAO;
//				assert(inityuanbao < cost);
//				if(inityuanbao < mincost){
//					return error(ErrorCode.MIN_CROWD_FUND_YUANBAO_NOT_ENOUGH);
//				}
//				//众筹前先检查
//				if(info.getCurrentfid() != 0){
//					return error(ErrorCode.FAMILY_ALREADY_IN_ONE);
//				}
//				if(info.getInitcrowdfamid() != 0){
//					return error(ErrorCode.ALREADY_IN_CROWD);
//				}
//				if(!FRole.checkCostYuanBao(roleid, xtable.Roleinfos.get(roleid), mincost, By.Crowd_Family)){
//					return error(ErrorCode.YUANBAO_NOT_ENOUGH);
//				}
//				//清空加入家族的申请信息
//				java.util.Set<Long> request = info.getRequestedfamily().keySet();
//				lock(Lockeys.get(xtable.Locks.FAMILYLOCK, request));
//				request.forEach(l->FFamily.getFamilyByFamilyId(l).getRequestinglist().remove(roleid));
//				info.getRequestedfamily().clear();
//
//
//				//清空参与的众筹信息,退还投入的资金
//				java.util.Set<Long> involve = info.getInvolvecrowdfam();
//				lock(Lockeys.get(xtable.Locks.CROWDFAMILYLOCK, involve));
//				SCrowdFamChangeNotify changeNotify = new SCrowdFamChangeNotify();
//				FFamily.clearInvovleInfo(roleid, involve, changeNotify);
////				if(changeNotify.changeinfo.size() > 0){//如果发生了变化,那么向所有玩家广播该信息
////					FChat.sendSystemMessage(changeNotify);
////				}
//				long now = System.currentTimeMillis();
//				long cleartime = now + FFamily.CROWD_LAST_TIME;
//				if(cleartime < FFamilyModule.NEAREST_CLEAR_TIME){
//					FFamilyModule.NEAREST_CLEAR_TIME = cleartime;
//				}
//				xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
//				//创建一个新的众筹家族信息
//				xbean.CrowdFamily newCf = xbean.Pod.newCrowdFamily();
//				newCf.setInitroleid(roleid);
//				newCf.setInityuanbao(inityuanbao);
//				newCf.setCrowdstarttime(now);
//				newCf.setCrowdyuanbao(inityuanbao);
//				newCf.setFamilyname(familyname);
//				newCf.setInitrolelvl(roleInfo.getLevel());
//				newCf.setIntirolename(roleInfo.getName());
//				long newCfid = xtable.Crowdfamily.insert(newCf);
//                FFamily.CrowFamily2Time.put(newCfid, now);
//				newCf.setCrowdfamilyid(newCfid);
//				info.setInitcrowdfamid(newCfid);
//				FFamily.getCrowdFamilyNames().put(fullName, newCfid);//添加到众筹家族 name-id的表中
//				SCrowdFamily response = new SCrowdFamily();
//				response.crowfamilyinfo = FFamily.makeProtocolCrowdFam(newCf);
//				response(response);
//				xdb.Trace.info("Start crowd fund family, crowd id = {}, fund money = {}, name={}", newCfid, inityuanbao,familyname);
//				return true;
//			}
//
//		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582568;

	public int getType() {
		return 6582568;
	}

	public java.lang.String familyname;
	public int inityuanbao; // 众筹开始投入的元宝

	public CCrowdFamily() {
		familyname = "";
	}

	public CCrowdFamily(java.lang.String _familyname_, int _inityuanbao_) {
		this.familyname = _familyname_;
		this.inityuanbao = _inityuanbao_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(inityuanbao);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyname = _os_.unmarshal_String("UTF-16LE");
		inityuanbao = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCrowdFamily) {
			CCrowdFamily _o_ = (CCrowdFamily)_o1_;
			if (!familyname.equals(_o_.familyname)) return false;
			if (inityuanbao != _o_.inityuanbao) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += familyname.hashCode();
		_h_ += inityuanbao;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(inityuanbao).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

