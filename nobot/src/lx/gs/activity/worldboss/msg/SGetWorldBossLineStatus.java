
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetWorldBossLineStatus__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetWorldBossLineStatus extends __SGetWorldBossLineStatus__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575695;

	public int getType() {
		return 6575695;
	}

	public int bossid;
	public java.util.HashMap<Integer,Integer> lines; // key : lineid   value-> cfg.ectype.WorldBossStateType

	public SGetWorldBossLineStatus() {
		lines = new java.util.HashMap<Integer,Integer>();
	}

	public SGetWorldBossLineStatus(int _bossid_, java.util.HashMap<Integer,Integer> _lines_) {
		this.bossid = _bossid_;
		this.lines = _lines_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bossid);
		_os_.compact_uint32(lines.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : lines.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bossid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			lines.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetWorldBossLineStatus) {
			SGetWorldBossLineStatus _o_ = (SGetWorldBossLineStatus)_o1_;
			if (bossid != _o_.bossid) return false;
			if (!lines.equals(_o_.lines)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bossid;
		_h_ += lines.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bossid).append(",");
		_sb_.append(lines).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

