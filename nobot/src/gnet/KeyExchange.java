
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Octets;
import robot.Client;
import robot.Ctx;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __KeyExchange__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class KeyExchange extends __KeyExchange__ {
	@Override
	protected void process() {
	    final Ctx ctx = (Ctx)getContext();
		final Octets renonce = Client.genKeyExchangeNonceAndSetInOutSecurity(getConnection(), ctx.account, Client.token, nonce);
        new KeyExchange(renonce, (byte)1).send(getConnection());
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 104;

	public int getType() {
		return 104;
	}

	public com.goldhuman.Common.Octets nonce;
	public byte kick_olduser; // 客户端发给link使用这个字段

	public KeyExchange() {
		nonce = new com.goldhuman.Common.Octets();
	}

	public KeyExchange(com.goldhuman.Common.Octets _nonce_, byte _kick_olduser_) {
		this.nonce = _nonce_;
		this.kick_olduser = _kick_olduser_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(nonce);
		_os_.marshal(kick_olduser);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		nonce = _os_.unmarshal_Octets();
		kick_olduser = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof KeyExchange) {
			KeyExchange _o_ = (KeyExchange)_o1_;
			if (!nonce.equals(_o_.nonce)) return false;
			if (kick_olduser != _o_.kick_olduser) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += nonce.hashCode();
		_h_ += (int)kick_olduser;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("B").append(nonce.size()).append(",");
		_sb_.append(kick_olduser).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

