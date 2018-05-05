
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSign__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSign extends __SSign__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556110;

	public int getType() {
		return 6556110;
	}

	public int signtype;
	public int signdate;
	public map.msg.Bonus signgift;

	public SSign() {
		signgift = new map.msg.Bonus();
	}

	public SSign(int _signtype_, int _signdate_, map.msg.Bonus _signgift_) {
		this.signtype = _signtype_;
		this.signdate = _signdate_;
		this.signgift = _signgift_;
	}

	public final boolean _validator_() {
		if (!signgift._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(signtype);
		_os_.marshal(signdate);
		_os_.marshal(signgift);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		signtype = _os_.unmarshal_int();
		signdate = _os_.unmarshal_int();
		signgift.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSign) {
			SSign _o_ = (SSign)_o1_;
			if (signtype != _o_.signtype) return false;
			if (signdate != _o_.signdate) return false;
			if (!signgift.equals(_o_.signgift)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += signtype;
		_h_ += signdate;
		_h_ += signgift.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(signtype).append(",");
		_sb_.append(signdate).append(",");
		_sb_.append(signgift).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

