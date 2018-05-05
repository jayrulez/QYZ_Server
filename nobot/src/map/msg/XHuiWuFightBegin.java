
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XHuiWuFightBegin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XHuiWuFightBegin extends __XHuiWuFightBegin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714607;

	public int getType() {
		return 6714607;
	}

	public map.msg.PlayerBuilder player1;
	public map.msg.PlayerBuilder player2;

	public XHuiWuFightBegin() {
		player1 = new map.msg.PlayerBuilder();
		player2 = new map.msg.PlayerBuilder();
	}

	public XHuiWuFightBegin(map.msg.PlayerBuilder _player1_, map.msg.PlayerBuilder _player2_) {
		this.player1 = _player1_;
		this.player2 = _player2_;
	}

	public final boolean _validator_() {
		if (!player1._validator_()) return false;
		if (!player2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(player1);
		_os_.marshal(player2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		player1.unmarshal(_os_);
		player2.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XHuiWuFightBegin) {
			XHuiWuFightBegin _o_ = (XHuiWuFightBegin)_o1_;
			if (!player1.equals(_o_.player1)) return false;
			if (!player2.equals(_o_.player2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += player1.hashCode();
		_h_ += player2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(player1).append(",");
		_sb_.append(player2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

