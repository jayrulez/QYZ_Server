
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SMove__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SMove extends __SMove__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584463;

	public int getType() {
		return 6584463;
	}

	public int srcbagtype;
	public int srcpos;
	public int destbagtype;
	public int destpos;

	public SMove() {
	}

	public SMove(int _srcbagtype_, int _srcpos_, int _destbagtype_, int _destpos_) {
		this.srcbagtype = _srcbagtype_;
		this.srcpos = _srcpos_;
		this.destbagtype = _destbagtype_;
		this.destpos = _destpos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(srcbagtype);
		_os_.marshal(srcpos);
		_os_.marshal(destbagtype);
		_os_.marshal(destpos);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		srcbagtype = _os_.unmarshal_int();
		srcpos = _os_.unmarshal_int();
		destbagtype = _os_.unmarshal_int();
		destpos = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SMove) {
			SMove _o_ = (SMove)_o1_;
			if (srcbagtype != _o_.srcbagtype) return false;
			if (srcpos != _o_.srcpos) return false;
			if (destbagtype != _o_.destbagtype) return false;
			if (destpos != _o_.destpos) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += srcbagtype;
		_h_ += srcpos;
		_h_ += destbagtype;
		_h_ += destpos;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(srcbagtype).append(",");
		_sb_.append(srcpos).append(",");
		_sb_.append(destbagtype).append(",");
		_sb_.append(destpos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SMove _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = srcbagtype - _o_.srcbagtype;
		if (0 != _c_) return _c_;
		_c_ = srcpos - _o_.srcpos;
		if (0 != _c_) return _c_;
		_c_ = destbagtype - _o_.destbagtype;
		if (0 != _c_) return _c_;
		_c_ = destpos - _o_.destpos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

