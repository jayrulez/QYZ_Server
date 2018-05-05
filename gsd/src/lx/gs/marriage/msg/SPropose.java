
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPropose__ extends xio.Protocol { }

/** 求婚结果,通知求婚方
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPropose extends __SPropose__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585013;

	public int getType() {
		return 6585013;
	}

	public long proposeid; // 求婚者的id
	public long beproposedroleid; // 被求婚的id
	public int proposetype; // 求婚类型，0表示普通，1表示豪华
	public int proposeresult; // 0表示求婚失败，1表示求婚成功，对应之后不同的流程

	public SPropose() {
	}

	public SPropose(long _proposeid_, long _beproposedroleid_, int _proposetype_, int _proposeresult_) {
		this.proposeid = _proposeid_;
		this.beproposedroleid = _beproposedroleid_;
		this.proposetype = _proposetype_;
		this.proposeresult = _proposeresult_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeid);
		_os_.marshal(beproposedroleid);
		_os_.marshal(proposetype);
		_os_.marshal(proposeresult);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeid = _os_.unmarshal_long();
		beproposedroleid = _os_.unmarshal_long();
		proposetype = _os_.unmarshal_int();
		proposeresult = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPropose) {
			SPropose _o_ = (SPropose)_o1_;
			if (proposeid != _o_.proposeid) return false;
			if (beproposedroleid != _o_.beproposedroleid) return false;
			if (proposetype != _o_.proposetype) return false;
			if (proposeresult != _o_.proposeresult) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeid;
		_h_ += (int)beproposedroleid;
		_h_ += proposetype;
		_h_ += proposeresult;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeid).append(",");
		_sb_.append(beproposedroleid).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append(proposeresult).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPropose _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(proposeid - _o_.proposeid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(beproposedroleid - _o_.beproposedroleid);
		if (0 != _c_) return _c_;
		_c_ = proposetype - _o_.proposetype;
		if (0 != _c_) return _c_;
		_c_ = proposeresult - _o_.proposeresult;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

