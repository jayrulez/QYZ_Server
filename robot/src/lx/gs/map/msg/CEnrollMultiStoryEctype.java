
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnrollMultiStoryEctype__ extends xio.Protocol { }

/** 报名组队剧情副本
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnrollMultiStoryEctype extends __CEnrollMultiStoryEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571201;

	public int getType() {
		return 6571201;
	}

	public final static int SINGLE = 1;
	public final static int TEAM = 2;

	public int ectypeid;
	public int enrolltype;

	public CEnrollMultiStoryEctype() {
	}

	public CEnrollMultiStoryEctype(int _ectypeid_, int _enrolltype_) {
		this.ectypeid = _ectypeid_;
		this.enrolltype = _enrolltype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(enrolltype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		enrolltype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnrollMultiStoryEctype) {
			CEnrollMultiStoryEctype _o_ = (CEnrollMultiStoryEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (enrolltype != _o_.enrolltype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += enrolltype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(enrolltype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnrollMultiStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = enrolltype - _o_.enrolltype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

