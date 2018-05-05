
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetWorldBossList__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetWorldBossList extends __SGetWorldBossList__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563913;

	public int getType() {
		return 6563913;
	}

	public java.util.ArrayList<lx.gs.activity.worldboss.msg.WorldBossInfo> bosses;

	public SGetWorldBossList() {
		bosses = new java.util.ArrayList<lx.gs.activity.worldboss.msg.WorldBossInfo>();
	}

	public SGetWorldBossList(java.util.ArrayList<lx.gs.activity.worldboss.msg.WorldBossInfo> _bosses_) {
		this.bosses = _bosses_;
	}

	public final boolean _validator_() {
		for (lx.gs.activity.worldboss.msg.WorldBossInfo _v_ : bosses)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(bosses.size());
		for (lx.gs.activity.worldboss.msg.WorldBossInfo _v_ : bosses) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.activity.worldboss.msg.WorldBossInfo _v_ = new lx.gs.activity.worldboss.msg.WorldBossInfo();
			_v_.unmarshal(_os_);
			bosses.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetWorldBossList) {
			SGetWorldBossList _o_ = (SGetWorldBossList)_o1_;
			if (!bosses.equals(_o_.bosses)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bosses.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bosses).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

