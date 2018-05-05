
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SKillGodAnimal__ extends xio.Protocol { }

/** 发放击杀神兽奖励
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SKillGodAnimal extends __SKillGodAnimal__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712517;

	public int getType() {
		return 6712517;
	}

	public int bossid;
	public int isover; // 是否结束了
	public map.msg.Bonus bonus;

	public SKillGodAnimal() {
		bonus = new map.msg.Bonus();
	}

	public SKillGodAnimal(int _bossid_, int _isover_, map.msg.Bonus _bonus_) {
		this.bossid = _bossid_;
		this.isover = _isover_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bossid);
		_os_.marshal(isover);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bossid = _os_.unmarshal_int();
		isover = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SKillGodAnimal) {
			SKillGodAnimal _o_ = (SKillGodAnimal)_o1_;
			if (bossid != _o_.bossid) return false;
			if (isover != _o_.isover) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bossid;
		_h_ += isover;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bossid).append(",");
		_sb_.append(isover).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

