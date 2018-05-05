
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCancelCrowdFamily__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCancelCrowdFamily extends __SCancelCrowdFamily__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567556;

	public int getType() {
		return 6567556;
	}

	public int returnyuanbao; // 撤资退还的资金

	public SCancelCrowdFamily() {
	}

	public SCancelCrowdFamily(int _returnyuanbao_) {
		this.returnyuanbao = _returnyuanbao_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(returnyuanbao);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		returnyuanbao = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCancelCrowdFamily) {
			SCancelCrowdFamily _o_ = (SCancelCrowdFamily)_o1_;
			if (returnyuanbao != _o_.returnyuanbao) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += returnyuanbao;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(returnyuanbao).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SCancelCrowdFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = returnyuanbao - _o_.returnyuanbao;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

