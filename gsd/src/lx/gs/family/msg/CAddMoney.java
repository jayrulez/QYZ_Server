
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.event.EventModule;
import lx.gs.event.FamilyBuildEvent;
import lx.gs.event.FamilyCreateEvent;
import lx.gs.family.FFamily;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xdb.Lockeys;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAddMoney__ extends xio.Protocol { }

/** 出资或者增资
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAddMoney extends __CAddMoney__ {
	@Override
	protected void process() {
//		new AProcedure<CAddMoney>(this) {
//
//			@Override
//			protected boolean doProcess() throws Exception {
//				xbean.RoleFamily info = FFamily.getRoleFamilyInfo(roleid);
//				//出资前先检查
//				if(info.getCurrentfid() != 0){
//					return error(ErrorCode.FAMILY_ALREADY_IN_ONE);
//				}
//				if(info.getInitcrowdfamid() != 0 && info.getInitcrowdfamid() != crowfamilyid){//如果发起过众筹，则只能投资自己的项目
//					return error(ErrorCode.ALREADY_IN_CROWD);
//				}
//				xbean.CrowdFamily cfInfo = FFamily.getCrowdFamByid(crowfamilyid);
//				if(cfInfo == null){
//					return error(ErrorCode.FAMILY_NOT_EXISTED);
//				}
//                if(!FRole.checkCostYuanBao(roleid, amount, By.Crowd_Family)){
//                    return error(ErrorCode.YUANBAO_NOT_ENOUGH);
//                }
//				Map<Long, Integer> allCrowd = cfInfo.getInvolveroles();
//				final int needYuanbao = cfg.family.FamilyInfo.CREATE_REQUIRE_YUANBAO;
//				int addYuanbao = amount + cfInfo.getCrowdyuanbao();
//				if(addYuanbao > needYuanbao){
//					return error(ErrorCode.YUANBAO_EXCEED);
//				}else if (addYuanbao < needYuanbao) {//不够的话 继续增加信息
//					cfInfo.setCrowdyuanbao(addYuanbao);
//					if(roleid == cfInfo.getInitroleid()){//如果是发起人增资
//						cfInfo.setInityuanbao(cfInfo.getInityuanbao() + amount);
//					}else { //如果是其他玩家投资
//						int exist = allCrowd.getOrDefault(roleid, 0);
//						allCrowd.put(roleid, exist + amount);
//					}
//					xdb.Trace.info("Crowd family add money success, current money = {} ", addYuanbao);
//				}else {//如果刚好众筹成功
//
//					if(roleid == cfInfo.getInitroleid()){
//						cfInfo.setInityuanbao(cfInfo.getInityuanbao() + amount);
//					}else {
//						int exist = allCrowd.getOrDefault(roleid, 0);
//						allCrowd.put(roleid, exist + amount);
//					}
//					long chiefId = cfInfo.getInitroleid();
//					Set<Long> involves = new HashSet<>();
//					involves.addAll(allCrowd.keySet());
//					involves.add(chiefId);
//					lock(Lockeys.get(xtable.Locks.ROLELOCK, involves));
//
//					long curtime = System.currentTimeMillis();
//					xbean.Family newf = xbean.Pod.newFamily();
//					long newfid = xtable.Family.insert(newf);
//					newf.setFamilyid(newfid);
//					newf.setFamilyname(cfInfo.getFamilyname());
//					newf.setUpdatetime(curtime);
//					newf.setCreatetime(curtime);
//					newf.setFlevel(1);
//					newf.setCurlvlbuilddegree(0);
//					newf.setTotalbuilddegree(0);
//					newf.setMalllevel(1);
//					newf.setDeclaration(cfg.family.FamilyInfo.DEFAULT_DECLARATION);
//					newf.setPublicinfo(cfg.family.FamilyInfo.DEFAULT_PUBLICINFO);
//					FFamily.getFamilyNames().put(FFamily.makeFullname(newf.getFamilyname()), newfid);//添加到家族名称-id表中
//					FFamily.getCrowdFamilyNames().remove(FFamily.makeFullname(cfInfo.getFamilyname()));//从众筹列表名称表中移除
//
//					xbean.FamilyMember member = xbean.Pod.newFamilyMember();
//					member.setFamilyjob(cfg.family.FamilyJobEnum.CHIEF);
//					member.setJointime(curtime);
//					member.setRoleid(chiefId);//众筹成功，那么众筹发起人成为族长
//					newf.getMembers().put(chiefId, member);
//					newf.setMembernum(allCrowd.size() + 1);
//					newf.setChiefid(chiefId);
//					xbean.RoleFamily chiefRoleFamily = FFamily.getRoleFamilyInfo(chiefId);
//					chiefRoleFamily.setCurrentfid(newfid);
//					chiefRoleFamily.setInitcrowdfamid(0);//众筹成功了
//					//家族的一些初始化
//					FFamily.initFamilyJobInfo(newf);
//					FFamily.addJobStaff(newf, cfg.family.FamilyJobEnum.CHIEF, chiefId);
//					FFamily.initFamilyAnimal(newf);
//					FFamily.addFamilyLog(newfid, roleid,lx.gs.family.msg.FamilyLogReport.TYPE_CREATE_FAMILY,0);
//
//					//所有参加众筹的玩家都成为家族成员 ，并且玩家要退出其余所有的众筹，并退钱
//					SCrowdFamChangeNotify changeNotify = new SCrowdFamChangeNotify();
//					for(long l : involves){
//                        if(l == chiefId)
//                            continue;
//						xbean.FamilyMember tempMem = FFamily.createMemeberinfo(l);
//						newf.getMembers().put(l, tempMem);
//						xbean.RoleFamily tempInfo = FFamily.getRoleFamilyInfo(l);
//						tempInfo.setCurrentfid(newfid); //设置当前所在家族id
//						Set<Long> crowdFams = tempInfo.getInvolvecrowdfam();
//						crowdFams.remove(crowfamilyid);//移除角色家族信息中当前众筹成功的famid
//						lock(Lockeys.get(xtable.Locks.CROWDFAMILYLOCK, crowdFams));
//						FFamily.clearInvovleInfo(roleid, crowdFams, changeNotify);
//						FFamily.onJoinFamily(newf, tempMem);//通知其他模块
//					}
////					if(changeNotify.changeinfo.size() > 0){//如果发生了变化
////						FChat.sendSystemMessage(changeNotify);
////					}
//					//通知所有参与该众筹的玩家众筹成功,并发送家族信息
//					SCrowdSuccessNotify notify = new SCrowdSuccessNotify();
//					notify.family = FFamily.makeProtocolFamilyBasicInfo(newf);
//					notify.crowdfamid = crowfamilyid;
//					FFamily.multicastAllFamily(newf, notify);
//					//众筹成功后，从众筹列表中移除该信息
//					xtable.Crowdfamily.remove(crowfamilyid);
//                    EventModule.INSTANCE.broadcastEvent(new FamilyCreateEvent(roleid, newf.getFamilyid()));
//					xdb.Trace.info("Crowd family success, family id = {}, family name={}", newfid, newf.getFamilyname());
//				}
//
//				SAddMoney response = new SAddMoney();
//				response.amount = amount;
//				response.crowfamilyid = crowfamilyid;
//				response(response);
//				return true;
//			}
//
//		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569122;

	public int getType() {
		return 6569122;
	}

	public long crowfamilyid;
	public int amount; // 增加的金额

	public CAddMoney() {
	}

	public CAddMoney(long _crowfamilyid_, int _amount_) {
		this.crowfamilyid = _crowfamilyid_;
		this.amount = _amount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(crowfamilyid);
		_os_.marshal(amount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		crowfamilyid = _os_.unmarshal_long();
		amount = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAddMoney) {
			CAddMoney _o_ = (CAddMoney)_o1_;
			if (crowfamilyid != _o_.crowfamilyid) return false;
			if (amount != _o_.amount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)crowfamilyid;
		_h_ += amount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(crowfamilyid).append(",");
		_sb_.append(amount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAddMoney _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(crowfamilyid - _o_.crowfamilyid);
		if (0 != _c_) return _c_;
		_c_ = amount - _o_.amount;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

