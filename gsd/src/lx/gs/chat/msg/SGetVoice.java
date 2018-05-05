
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetVoice__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetVoice extends __SGetVoice__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554010;

	public int getType() {
		return 6554010;
	}

	public int voiceid;
	public com.goldhuman.Common.Octets voice;

	public SGetVoice() {
		voice = new com.goldhuman.Common.Octets();
	}

	public SGetVoice(int _voiceid_, com.goldhuman.Common.Octets _voice_) {
		this.voiceid = _voiceid_;
		this.voice = _voice_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(voiceid);
		_os_.marshal(voice);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		voiceid = _os_.unmarshal_int();
		voice = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetVoice) {
			SGetVoice _o_ = (SGetVoice)_o1_;
			if (voiceid != _o_.voiceid) return false;
			if (!voice.equals(_o_.voice)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += voiceid;
		_h_ += voice.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(voiceid).append(",");
		_sb_.append("B").append(voice.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

