
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyLog__ extends xio.Protocol { }

/** 获取家族日志
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyLog extends __SGetFamilyLog__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586221;

	public int getType() {
		return 6586221;
	}

	public long familyid;
	public java.util.ArrayList<lx.gs.family.msg.FamilyLogReport> logs; // 家族日志信息

	public SGetFamilyLog() {
		logs = new java.util.ArrayList<lx.gs.family.msg.FamilyLogReport>();
	}

	public SGetFamilyLog(long _familyid_, java.util.ArrayList<lx.gs.family.msg.FamilyLogReport> _logs_) {
		this.familyid = _familyid_;
		this.logs = _logs_;
	}

	public final boolean _validator_() {
		for (lx.gs.family.msg.FamilyLogReport _v_ : logs)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyid);
		_os_.compact_uint32(logs.size());
		for (lx.gs.family.msg.FamilyLogReport _v_ : logs) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.family.msg.FamilyLogReport _v_ = new lx.gs.family.msg.FamilyLogReport();
			_v_.unmarshal(_os_);
			logs.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyLog) {
			SGetFamilyLog _o_ = (SGetFamilyLog)_o1_;
			if (familyid != _o_.familyid) return false;
			if (!logs.equals(_o_.logs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familyid;
		_h_ += logs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid).append(",");
		_sb_.append(logs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

