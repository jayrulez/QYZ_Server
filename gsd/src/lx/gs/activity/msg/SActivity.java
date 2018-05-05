
package lx.gs.activity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SActivity__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SActivity extends __SActivity__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579108;

	public int getType() {
		return 6579108;
	}

	public java.util.HashMap<Integer,lx.gs.activity.msg.ActivityInfo> activityinfos;

	public SActivity() {
		activityinfos = new java.util.HashMap<Integer,lx.gs.activity.msg.ActivityInfo>();
	}

	public SActivity(java.util.HashMap<Integer,lx.gs.activity.msg.ActivityInfo> _activityinfos_) {
		this.activityinfos = _activityinfos_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.activity.msg.ActivityInfo> _e_ : activityinfos.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(activityinfos.size());
		for (java.util.Map.Entry<Integer, lx.gs.activity.msg.ActivityInfo> _e_ : activityinfos.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.activity.msg.ActivityInfo _v_ = new lx.gs.activity.msg.ActivityInfo();
			_v_.unmarshal(_os_);
			activityinfos.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SActivity) {
			SActivity _o_ = (SActivity)_o1_;
			if (!activityinfos.equals(_o_.activityinfos)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += activityinfos.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(activityinfos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

