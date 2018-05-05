
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gnet.link.Onlines;
import gs.AProcedure;
import lx.gs.event.EventModule;
import lx.gs.event.ReceiveFlowerEvent;
import lx.gs.friend.FFriend;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.role.title.FTitle;
import xdb.Lockeys;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSendFlower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSendFlower extends __CSendFlower__ {
	@Override
	protected void process() {
		new AProcedure<CSendFlower>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if (param.sendtype == cfg.item.FlowerType.PLAYER) {
                    lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, param.reveiverid));
					xbean.RoleFriendsInfo info = FFriend.getRoleFriendsInfo(roleid);
					xbean.RoleFriendsInfo toinfo = FFriend.getRoleFriendsInfo(param.reveiverid);

					int totalcharm = 0;
					int totalfrienddegree = 0;
					for (FlowerInfo flowerinfo : param.flowers) {
						cfg.item.ItemFlower flowerconf = (cfg.item.ItemFlower) cfg.CfgMgr.itembasic
								.get(flowerinfo.flowerid);
						if (flowerconf.flowertype != cfg.item.FlowerType.PLAYER) {
							return error(ErrorCode.PARAM_ERROR);
						}
						boolean flag = FItem.spendItemBindFirst(roleid, flowerinfo.flowerid, flowerinfo.flowernum, By.Send_Flower);
						if (!flag)
							return error(ErrorCode.FLOWER_NOT_ENOUGH);
						totalcharm += flowerconf.charmdegree * flowerinfo.flowernum;
						totalfrienddegree += flowerconf.frienddegree * flowerinfo.flowernum;
					}

					xbean.RoleFriend roleFriend = info.getFriends().get(param.reveiverid);
					roleFriend.setFrienddegress(roleFriend.getFrienddegress() + totalfrienddegree);
                    toinfo.getFriends().get(roleid).setFrienddegress(roleFriend.getFrienddegress());
					toinfo.setCharmdegree(toinfo.getCharmdegree() + totalcharm);

					SFriendDegreeNotify degreenotify = new SFriendDegreeNotify();
					degreenotify.notifytype = cfg.item.FlowerType.PLAYER;
					degreenotify.frienddegree = roleFriend.getFrienddegress();
					degreenotify.roleid = param.reveiverid;

					tsend(roleid, degreenotify);
					SFriendDegreeNotify degreenotify2me = new SFriendDegreeNotify();
					degreenotify2me.notifytype = cfg.item.FlowerType.PLAYER;
					degreenotify2me.frienddegree = roleFriend.getFrienddegress();
					degreenotify2me.roleid = roleid;
					tsend(param.reveiverid, degreenotify);

					SRoleCharmNotify charmnotify = new SRoleCharmNotify();
					charmnotify.notifytype = cfg.item.FlowerType.PLAYER;
					charmnotify.charm = toinfo.getCharmdegree();
					charmnotify.roleid = param.reveiverid;

					FFriend.multicastAllFriend(param.reveiverid, charmnotify);
					tsend(param.reveiverid, charmnotify);
					EventModule.INSTANCE.broadcastEvent(new ReceiveFlowerEvent(param.reveiverid, toinfo.getCharmdegree()));
				} else if (param.sendtype == cfg.item.FlowerType.NPC) {
					xbean.RoleFriendsInfo info = FFriend.getRoleFriendsInfo(roleid);

					int totalcharm = 0;
					int totalfrienddegree = 0;
					for (FlowerInfo flowerinfo : param.flowers) {
						cfg.item.ItemFlower flowerconf = (cfg.item.ItemFlower) cfg.CfgMgr.itembasic
								.get(flowerinfo.flowerid);
						if (flowerconf.flowertype != cfg.item.FlowerType.NPC) {
							return error(ErrorCode.PARAM_ERROR);
						}
						boolean flag = FItem.spendItemBindFirst(roleid, flowerinfo.flowerid, flowerinfo.flowernum, By.Send_Flower);
						if (!flag)
							return error(ErrorCode.FLOWER_NOT_ENOUGH);
						totalcharm += flowerconf.charmdegree * flowerinfo.flowernum;
						totalfrienddegree += flowerconf.frienddegree * flowerinfo.flowernum;
					}
					
					Integer old = info.getIdolfrienddegree().get(param.reveiverid);
					if(old == null){
						info.getIdolfrienddegree().put(param.reveiverid, totalfrienddegree);
					}else{
						info.getIdolfrienddegree().put(param.reveiverid, old+totalfrienddegree);
					}

					SFriendDegreeNotify degreenotify = new SFriendDegreeNotify();
					degreenotify.notifytype = cfg.item.FlowerType.NPC;
					degreenotify.frienddegree = info.getIdolfrienddegree().get(param.reveiverid);
					degreenotify.roleid = param.reveiverid;
					tsend(roleid, degreenotify);
					
					xbean.IdolCharmInfo charminfo = FFriend.getIdolCharmInfo(param.reveiverid);
					if(charminfo.getGuardid() != roleid){
						if(charminfo.getGuarddegree() < degreenotify.frienddegree){
                            long oldGuardId = charminfo.getGuardid();
							cfg.friend.Idol conf = cfg.CfgMgr.idol.get(param.reveiverid);
							if(degreenotify.frienddegree > conf.guardthreshold){
								xbean.RoleInfo myroleinfo = xtable.Roleinfos.get(roleid);
								long oldguard = charminfo.getGuardid();
								charminfo.setGuardid(roleid);
								charminfo.setGuarddegree(degreenotify.frienddegree);
								long time = System.currentTimeMillis();
								charminfo.setGuardtime(time);
                                FTitle.unlockTitle(roleid, conf.guardtitleid);
                                if(oldGuardId != 0) {
                                    FTitle.delTitle(oldGuardId, conf.guardtitleid);
                                }
								SIdolGuardNotify guardnotify = new SIdolGuardNotify();
								guardnotify.guardid = roleid;
								guardnotify.oldguardid = oldguard;
								guardnotify.guardname = myroleinfo.getName();
								guardnotify.guardtime = time;
								guardnotify.idolid = param.reveiverid;
								Onlines.getInstance().broadcast(guardnotify);
							}
						}
					}
					
					int real = charminfo.getCharm() + totalcharm;
					charminfo.setCharm(real);

					//int newcharm = FFriend.addIdolCharm(param.reveiverid, totalcharm);
					SRoleCharmNotify charmnotify = new SRoleCharmNotify();
					charmnotify.notifytype = cfg.item.FlowerType.NPC;
					charmnotify.charm = real;
					charmnotify.roleid = param.reveiverid;

					Onlines.getInstance().broadcast(charmnotify);
				} else {
                    return false;
                }

				SSendFlower result = new SSendFlower();
				result.sendtype = param.sendtype;
				result.reveiverid = param.reveiverid;
				result.flowers = param.flowers;
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586055;

	public int getType() {
		return 6586055;
	}

	public int sendtype; // 发送类型，给普通好友还是给偶像，通过enum值设定
	public long reveiverid; // 接受者的角色id
	public java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> flowers;

	public CSendFlower() {
		flowers = new java.util.ArrayList<lx.gs.friend.msg.FlowerInfo>();
	}

	public CSendFlower(int _sendtype_, long _reveiverid_, java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> _flowers_) {
		this.sendtype = _sendtype_;
		this.reveiverid = _reveiverid_;
		this.flowers = _flowers_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(sendtype);
		_os_.marshal(reveiverid);
		_os_.compact_uint32(flowers.size());
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		sendtype = _os_.unmarshal_int();
		reveiverid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.FlowerInfo _v_ = new lx.gs.friend.msg.FlowerInfo();
			_v_.unmarshal(_os_);
			flowers.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSendFlower) {
			CSendFlower _o_ = (CSendFlower)_o1_;
			if (sendtype != _o_.sendtype) return false;
			if (reveiverid != _o_.reveiverid) return false;
			if (!flowers.equals(_o_.flowers)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += sendtype;
		_h_ += (int)reveiverid;
		_h_ += flowers.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sendtype).append(",");
		_sb_.append(reveiverid).append(",");
		_sb_.append(flowers).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
