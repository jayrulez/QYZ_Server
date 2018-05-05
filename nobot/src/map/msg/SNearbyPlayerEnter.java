
package map.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Runnable;
import common.TaskQueue;
import robot.Ctx;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNearbyPlayerEnter__ extends xio.Protocol { }

/** 进出玩家视野
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNearbyPlayerEnter extends __SNearbyPlayerEnter__ {
	@Override
	protected void process() {
		final Ctx ctx = (Ctx)getContext();
        if(ctx.roleid == playerid) {
//            final map.msg.Vector3 pos = fightercommon.position;
//            ctx.linger.add(pos);
////            ctx.linger.add(new map.msg.Vector3(306, 0, 100));
////            ctx.linger.add(new map.msg.Vector3(331, 0, 85));
////            ctx.linger.add(new map.msg.Vector3(356, 0, 76));
////            ctx.linger.add(new map.msg.Vector3(380, 0, 88));
////            ctx.linger.add(new map.msg.Vector3(413, 0, 102));
////            ctx.linger.add(new map.msg.Vector3(422, 0, 89));
//            for(int i = 0 ; i < 5 ; i++) {
//                ctx.linger.add(new map.msg.Vector3(pos.x + common.Utils.random01f() * 20, pos.y, pos.z + common.Utils.random01f() * 20));
//            }
//
//            final Runnable walk = new Runnable() {
//                @Override
//                public void run() {
//                    if(ctx.roleid > 0) {
//                        final int n = ctx.linger.size();
//                        new CMove(ctx.linger.get(ctx.index % n), ctx.linger.get((ctx.index + 1) % n)).send(getConnection());
//                        ctx.index++;
//                        if(ctx.index >= n - 1) {
//                            Collections.reverse(ctx.linger);
//                            ctx.index = 0;
//                        }
//                        TaskQueue.getScheduleExecutor().schedule(this, 7 + common.Utils.randomRange(0, 8), TimeUnit.SECONDS);
//                    }
//                }
//            };

//            TaskQueue.getScheduleExecutor().schedule(walk, 7 + common.Utils.randomRange(0, 8), TimeUnit.SECONDS);
            TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
                if(ctx.roleid > 0) {
                    final map.msg.Vector3 p = fightercommon.position;
                    new CMove(p, new Vector3(p.x + 0.001f, p.y, p.z + 0.001f)).send(getConnection());
                }
            }, 500, 800, TimeUnit.MILLISECONDS);
            final List<Integer> skillids = CfgMgr.careerskilllist.get(ctx.roleDetail.profession).skilllist;
            TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
                final CSkillPerform s = new CSkillPerform();
                s.skillid = common.Utils.randomChoose(skillids);
                s.targetid = ctx.targetid;
                s.direction = new map.msg.Vector3(1, 0, 0);
                new CSkillPerform().send(getConnection());
            }, 3, 3, TimeUnit.SECONDS);

//            TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
//                if(ctx.roleid > 0) {
//                    new CTestNetwork().send(getConnection());
//                }
//            }, 1, 1, TimeUnit.SECONDS);
        } else {
            ctx.targetid = playerid;
        }
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684842;

	public int getType() {
		return 6684842;
	}

	public long playerid; // 进入视野的物体(玩家或怪物或道具等等)
	public int serverid;
	public int gender;
	public int profession;
	public java.lang.String name;
	public int level;
	public int viplevel;
	public java.lang.String familyname;
	public int dressid;
	public int rideid; // 坐骑id
	public int ridestatus; // 见cfg.MountType. 0表示无,1 骑行 2飞行
	public int titleid;
	public int fabaoid;
	public java.util.ArrayList<map.msg.EquipBrief> equips;
	public java.util.ArrayList<map.msg.EffectInfo> effects;
	public int pkstate;
	public map.msg.FighterCommon fightercommon;

	public SNearbyPlayerEnter() {
		name = "";
		familyname = "";
		equips = new java.util.ArrayList<map.msg.EquipBrief>();
		effects = new java.util.ArrayList<map.msg.EffectInfo>();
		fightercommon = new map.msg.FighterCommon();
	}

	public SNearbyPlayerEnter(long _playerid_, int _serverid_, int _gender_, int _profession_, java.lang.String _name_, int _level_, int _viplevel_, java.lang.String _familyname_, int _dressid_, int _rideid_, int _ridestatus_, int _titleid_, int _fabaoid_, java.util.ArrayList<map.msg.EquipBrief> _equips_, java.util.ArrayList<map.msg.EffectInfo> _effects_, int _pkstate_, map.msg.FighterCommon _fightercommon_) {
		this.playerid = _playerid_;
		this.serverid = _serverid_;
		this.gender = _gender_;
		this.profession = _profession_;
		this.name = _name_;
		this.level = _level_;
		this.viplevel = _viplevel_;
		this.familyname = _familyname_;
		this.dressid = _dressid_;
		this.rideid = _rideid_;
		this.ridestatus = _ridestatus_;
		this.titleid = _titleid_;
		this.fabaoid = _fabaoid_;
		this.equips = _equips_;
		this.effects = _effects_;
		this.pkstate = _pkstate_;
		this.fightercommon = _fightercommon_;
	}

	public final boolean _validator_() {
		for (map.msg.EquipBrief _v_ : equips)
			if (!_v_._validator_()) return false;
		for (map.msg.EffectInfo _v_ : effects)
			if (!_v_._validator_()) return false;
		if (!fightercommon._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(playerid);
		_os_.marshal(serverid);
		_os_.marshal(gender);
		_os_.marshal(profession);
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(level);
		_os_.marshal(viplevel);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(dressid);
		_os_.marshal(rideid);
		_os_.marshal(ridestatus);
		_os_.marshal(titleid);
		_os_.marshal(fabaoid);
		_os_.compact_uint32(equips.size());
		for (map.msg.EquipBrief _v_ : equips) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(effects.size());
		for (map.msg.EffectInfo _v_ : effects) {
			_os_.marshal(_v_);
		}
		_os_.marshal(pkstate);
		_os_.marshal(fightercommon);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		playerid = _os_.unmarshal_long();
		serverid = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		name = _os_.unmarshal_String("UTF-16LE");
		level = _os_.unmarshal_int();
		viplevel = _os_.unmarshal_int();
		familyname = _os_.unmarshal_String("UTF-16LE");
		dressid = _os_.unmarshal_int();
		rideid = _os_.unmarshal_int();
		ridestatus = _os_.unmarshal_int();
		titleid = _os_.unmarshal_int();
		fabaoid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.EquipBrief _v_ = new map.msg.EquipBrief();
			_v_.unmarshal(_os_);
			equips.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.EffectInfo _v_ = new map.msg.EffectInfo();
			_v_.unmarshal(_os_);
			effects.add(_v_);
		}
		pkstate = _os_.unmarshal_int();
		fightercommon.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNearbyPlayerEnter) {
			SNearbyPlayerEnter _o_ = (SNearbyPlayerEnter)_o1_;
			if (playerid != _o_.playerid) return false;
			if (serverid != _o_.serverid) return false;
			if (gender != _o_.gender) return false;
			if (profession != _o_.profession) return false;
			if (!name.equals(_o_.name)) return false;
			if (level != _o_.level) return false;
			if (viplevel != _o_.viplevel) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (dressid != _o_.dressid) return false;
			if (rideid != _o_.rideid) return false;
			if (ridestatus != _o_.ridestatus) return false;
			if (titleid != _o_.titleid) return false;
			if (fabaoid != _o_.fabaoid) return false;
			if (!equips.equals(_o_.equips)) return false;
			if (!effects.equals(_o_.effects)) return false;
			if (pkstate != _o_.pkstate) return false;
			if (!fightercommon.equals(_o_.fightercommon)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)playerid;
		_h_ += serverid;
		_h_ += gender;
		_h_ += profession;
		_h_ += name.hashCode();
		_h_ += level;
		_h_ += viplevel;
		_h_ += familyname.hashCode();
		_h_ += dressid;
		_h_ += rideid;
		_h_ += ridestatus;
		_h_ += titleid;
		_h_ += fabaoid;
		_h_ += equips.hashCode();
		_h_ += effects.hashCode();
		_h_ += pkstate;
		_h_ += fightercommon.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(playerid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(profession).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(level).append(",");
		_sb_.append(viplevel).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(dressid).append(",");
		_sb_.append(rideid).append(",");
		_sb_.append(ridestatus).append(",");
		_sb_.append(titleid).append(",");
		_sb_.append(fabaoid).append(",");
		_sb_.append(equips).append(",");
		_sb_.append(effects).append(",");
		_sb_.append(pkstate).append(",");
		_sb_.append(fightercommon).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

