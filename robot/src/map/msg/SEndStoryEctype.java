
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndStoryEctype extends __SEndStoryEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714994;

	public int getType() {
		return 6714994;
	}

	public int errcode;
	public int isfirst3star; // 是否首次三星
	public int star; // 评价星级
	public map.msg.Bonus bonus;

	public SEndStoryEctype() {
		bonus = new map.msg.Bonus();
	}

	public SEndStoryEctype(int _errcode_, int _isfirst3star_, int _star_, map.msg.Bonus _bonus_) {
		this.errcode = _errcode_;
		this.isfirst3star = _isfirst3star_;
		this.star = _star_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(isfirst3star);
		_os_.marshal(star);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		isfirst3star = _os_.unmarshal_int();
		star = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndStoryEctype) {
			SEndStoryEctype _o_ = (SEndStoryEctype)_o1_;
			if (errcode != _o_.errcode) return false;
			if (isfirst3star != _o_.isfirst3star) return false;
			if (star != _o_.star) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += isfirst3star;
		_h_ += star;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(isfirst3star).append(",");
		_sb_.append(star).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

