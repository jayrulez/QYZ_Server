
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SClaimIdolAward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SClaimIdolAward extends __SClaimIdolAward__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577004;

	public int getType() {
		return 6577004;
	}

	public long idolid; // 偶像的id
	public int awardid; // 奖励的id

	public SClaimIdolAward() {
	}

	public SClaimIdolAward(long _idolid_, int _awardid_) {
		this.idolid = _idolid_;
		this.awardid = _awardid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(idolid);
		_os_.marshal(awardid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		idolid = _os_.unmarshal_long();
		awardid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SClaimIdolAward) {
			SClaimIdolAward _o_ = (SClaimIdolAward)_o1_;
			if (idolid != _o_.idolid) return false;
			if (awardid != _o_.awardid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)idolid;
		_h_ += awardid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(idolid).append(",");
		_sb_.append(awardid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SClaimIdolAward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(idolid - _o_.idolid);
		if (0 != _c_) return _c_;
		_c_ = awardid - _o_.awardid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

