
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SWorldBossKillNotice__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SWorldBossKillNotice extends __SWorldBossKillNotice__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578932;

	public int getType() {
		return 6578932;
	}

	public java.lang.String killername;
	public java.lang.String msg;

	public SWorldBossKillNotice() {
		killername = "";
		msg = "";
	}

	public SWorldBossKillNotice(java.lang.String _killername_, java.lang.String _msg_) {
		this.killername = _killername_;
		this.msg = _msg_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(killername, "UTF-16LE");
		_os_.marshal(msg, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		killername = _os_.unmarshal_String("UTF-16LE");
		msg = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SWorldBossKillNotice) {
			SWorldBossKillNotice _o_ = (SWorldBossKillNotice)_o1_;
			if (!killername.equals(_o_.killername)) return false;
			if (!msg.equals(_o_.msg)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += killername.hashCode();
		_h_ += msg.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(killername.length()).append(",");
		_sb_.append("T").append(msg.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

