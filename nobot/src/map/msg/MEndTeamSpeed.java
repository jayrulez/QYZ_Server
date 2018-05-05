
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndTeamSpeed__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndTeamSpeed extends __MEndTeamSpeed__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699137;

	public int getType() {
		return 6699137;
	}

	public int iswin;
	public int ectypeid;
	public map.msg.Bonus bonus;

	public MEndTeamSpeed() {
		bonus = new map.msg.Bonus();
	}

	public MEndTeamSpeed(int _iswin_, int _ectypeid_, map.msg.Bonus _bonus_) {
		this.iswin = _iswin_;
		this.ectypeid = _ectypeid_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(iswin);
		_os_.marshal(ectypeid);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		iswin = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndTeamSpeed) {
			MEndTeamSpeed _o_ = (MEndTeamSpeed)_o1_;
			if (iswin != _o_.iswin) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += iswin;
		_h_ += ectypeid;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(iswin).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

