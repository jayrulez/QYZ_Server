
package lx.gs.paomadeng.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPaomadengShow__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPaomadengShow extends __SPaomadengShow__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571363;

	public int getType() {
		return 6571363;
	}

	public final static int ANNEAL = 1; // 装备洗练
	public final static int PERFUSE = 2; // 装备灌注
	public final static int EVOLVE = 3; // 神器进阶
	public final static int PICKCARD = 4; // 抽卡
	public final static int TALISMAN_STARTLEVEL = 5; // 法宝升星
	public final static int PET_AWAKE = 6; // 伙伴觉醒
	public final static int ROLE_LEVELUP = 7; // 玩家升级
	public final static int IDOL_TITLE = 8; // 获得偶像称号

	public long roleid;
	public java.lang.String rolename; // 玩家名字
	public int operatetype; // 操作类型
	public int id; // 配置id
	public int lvl; // 操作后的等级，获得物品，为0

	public SPaomadengShow() {
		rolename = "";
	}

	public SPaomadengShow(long _roleid_, java.lang.String _rolename_, int _operatetype_, int _id_, int _lvl_) {
		this.roleid = _roleid_;
		this.rolename = _rolename_;
		this.operatetype = _operatetype_;
		this.id = _id_;
		this.lvl = _lvl_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(operatetype);
		_os_.marshal(id);
		_os_.marshal(lvl);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		operatetype = _os_.unmarshal_int();
		id = _os_.unmarshal_int();
		lvl = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPaomadengShow) {
			SPaomadengShow _o_ = (SPaomadengShow)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (operatetype != _o_.operatetype) return false;
			if (id != _o_.id) return false;
			if (lvl != _o_.lvl) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += rolename.hashCode();
		_h_ += operatetype;
		_h_ += id;
		_h_ += lvl;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(operatetype).append(",");
		_sb_.append(id).append(",");
		_sb_.append(lvl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

