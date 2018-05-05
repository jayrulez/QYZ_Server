
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SJadeUnLockNotify__ extends xio.Protocol { }

/** 玉佩开启通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SJadeUnLockNotify extends lx.gs.jade.__SJadeUnLockNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566822;

	public int getType() {
		return 6566822;
	}

	public lx.gs.jade.Jade jade; // 玉佩等级
	public int holenum;

	public SJadeUnLockNotify() {
		jade = new lx.gs.jade.Jade();
	}

	public SJadeUnLockNotify(lx.gs.jade.Jade _jade_, int _holenum_) {
		this.jade = _jade_;
		this.holenum = _holenum_;
	}

	public final boolean _validator_() {
		if (!jade._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(jade);
		_os_.marshal(holenum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		jade.unmarshal(_os_);
		holenum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SJadeUnLockNotify) {
			SJadeUnLockNotify _o_ = (SJadeUnLockNotify)_o1_;
			if (!jade.equals(_o_.jade)) return false;
			if (holenum != _o_.holenum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += jade.hashCode();
		_h_ += holenum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jade).append(",");
		_sb_.append(holenum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SJadeUnLockNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = jade.compareTo(_o_.jade);
		if (0 != _c_) return _c_;
		_c_ = holenum - _o_.holenum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

