
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndStoryEctype extends __MEndStoryEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6701902;

	public int getType() {
		return 6701902;
	}

	public int ectypeid;
	public int star;
	public map.msg.Bonus bonus;

	public MEndStoryEctype() {
		bonus = new map.msg.Bonus();
	}

	public MEndStoryEctype(int _ectypeid_, int _star_, map.msg.Bonus _bonus_) {
		this.ectypeid = _ectypeid_;
		this.star = _star_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(star);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		star = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndStoryEctype) {
			MEndStoryEctype _o_ = (MEndStoryEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (star != _o_.star) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += star;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(star).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

