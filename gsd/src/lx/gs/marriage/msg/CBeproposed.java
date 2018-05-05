
package lx.gs.marriage.msg;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.chat.FChat;
import lx.gs.friend.FFriend;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.marriage.FMarriage;
import xbean.RoleMarriage;
import xdb.Lockeys;
import xtable.Roleinfos;

import java.util.HashMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBeproposed__ extends xio.Protocol { }

/** 被求婚者的答复信息,该结果要通知给求婚方
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBeproposed extends __CBeproposed__ {
	@Override
	protected void process() {
		new AProcedure<CBeproposed>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, proposeroleid)); //都先进行加锁操作 
				RoleMarriage proposeRole = FMarriage.getMarriageInfo(proposeroleid);
				RoleMarriage beproposeRole = FMarriage.getMarriageInfo(roleid);
				if(proposeresult == 1){//如果同意求婚，那么设置伴侣id,并消耗彩礼
					//全服发送系统公告；结婚双方主界面显示结婚动画；结婚双方收到结婚大礼包；双方当前所有在线好友发送祝福提示；祝福后好友和夫妻双方都获得奖励
                    proposeRole.setCurproposeid(0L);
                    proposeRole.setStartproposetime(0L);
                    if(proposeRole.getCoupleroleid() != 0){
                        return error(ErrorCode.OTHER_HAS_MARRIED);
                    }
                    if(beproposeRole.getCoupleroleid() != 0){
                        return error(ErrorCode.HAS_MARRIED);
                    }
                    proposeRole.setCoupleroleid(roleid);
					beproposeRole.setCoupleroleid(proposeroleid);
                    FFriend.setBanlvInfo(proposeroleid, roleid);
					beproposeRole.setCurproposeid(0L);//被求婚者同意求婚后，清空他自己的正在求婚信息
					proposeRole.getWishfriendlist().clear();//清空上一次结婚后的祝福信息
					HashMap<Integer, Integer> marryGift = new HashMap<>();
					if(proposetype == 0){
						if(!FItem.spendItemBindFirst(proposeroleid, FMarriage.NORMAL_CAILI, 1, By.Cost_Caili)){
							return error(ErrorCode.NOT_HAVE_CAILI);
						}
						marryGift.put(CfgMgr.marrigeconfig.giftpackid.get(0), 1);
					}else if (proposetype == 1) {
						if(!FItem.spendItemBindFirst(proposeroleid, FMarriage.LUXURY_CAILI, 1, By.Cost_Caili)){
							return error(ErrorCode.NOT_HAVE_CAILI); 
						}
						marryGift.put(CfgMgr.marrigeconfig.giftpackid.get(1), 1);
					}
					if(!FBonus.addBonus(roleid, common.Bonus.BindType.BIND, marryGift, By.Marry)){
						return false;
					}
					FBonus.addBonusAlwaysSucc(proposeroleid, common.Bonus.BindType.BIND, marryGift, By.Marry);//给结婚双方发放大礼包
					SAllSomePeopleMarryNotify allNotify = new SAllSomePeopleMarryNotify();//全服公告
					allNotify.content = proposeoath;
					allNotify.proposename = Roleinfos.get(proposeroleid).getName();
                    allNotify.proposegender = Roleinfos.selectGender(proposeroleid);
					allNotify.beproposedname = Roleinfos.get(roleid).getName();
                    allNotify.beproposegender = Roleinfos.selectGender(roleid);
					FChat.sendSystemMessage(allNotify);
					SFriendMarryNotify friendNotify = new SFriendMarryNotify(); //通知双方好友
					friendNotify.proposeroleid = proposeroleid;
					friendNotify.proposename = allNotify.proposename;
					friendNotify.beproposeroleid = roleid;
					friendNotify.beproposedname = allNotify.beproposedname;
					friendNotify.proposetype = proposetype;
					FFriend.multicastAllFriend(proposeroleid, friendNotify);
					FFriend.multicastAllFriend(roleid, friendNotify);
				}
				SPropose notify = new SPropose();
                notify.proposeid = proposeroleid;
				notify.beproposedroleid = roleid;
				notify.proposetype = proposetype;
				notify.proposeresult = proposeresult;
				tsendWhileCommit(proposeroleid, notify);//将结果通知给求婚者
                response(notify);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586200;

	public int getType() {
		return 6586200;
	}

	public long proposeroleid; // 求婚者的id
	public int proposeresult; // 0表示拒绝求婚，1表示同意求婚
	public int proposetype; // 求婚类型，0表示普通，1表示豪华
	public java.lang.String proposeoath; // 由于没有存储求婚宣言，这里再上传一次

	public CBeproposed() {
		proposeoath = "";
	}

	public CBeproposed(long _proposeroleid_, int _proposeresult_, int _proposetype_, java.lang.String _proposeoath_) {
		this.proposeroleid = _proposeroleid_;
		this.proposeresult = _proposeresult_;
		this.proposetype = _proposetype_;
		this.proposeoath = _proposeoath_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(proposeresult);
		_os_.marshal(proposetype);
		_os_.marshal(proposeoath, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		proposeresult = _os_.unmarshal_int();
		proposetype = _os_.unmarshal_int();
		proposeoath = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBeproposed) {
			CBeproposed _o_ = (CBeproposed)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (proposeresult != _o_.proposeresult) return false;
			if (proposetype != _o_.proposetype) return false;
			if (!proposeoath.equals(_o_.proposeoath)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += proposeresult;
		_h_ += proposetype;
		_h_ += proposeoath.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append(proposeresult).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append("T").append(proposeoath.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

