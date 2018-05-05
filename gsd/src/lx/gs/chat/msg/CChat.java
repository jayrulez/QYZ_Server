
package lx.gs.chat.msg;
import gs.AProcedure;
import lx.gs.chat.FChat;
import common.ErrorCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChat__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChat extends __CChat__ {
	@Override
	protected void process() {
		new AProcedure<CChat>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode ret = FChat.send(roleid, CChat.this);
				if(ret.err())
					return error(ret);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554001;

	public int getType() {
		return 6554001;
	}

	public int channel;
	public long receiver;
	public java.lang.String text;
	public int bagtype;
	public int pos;
	public int invitechannel;
	public com.goldhuman.Common.Octets voice;
	public float voiceduration;

	public CChat() {
		text = "";
		voice = new com.goldhuman.Common.Octets();
	}

	public CChat(int _channel_, long _receiver_, java.lang.String _text_, int _bagtype_, int _pos_, int _invitechannel_, com.goldhuman.Common.Octets _voice_, float _voiceduration_) {
		this.channel = _channel_;
		this.receiver = _receiver_;
		this.text = _text_;
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.invitechannel = _invitechannel_;
		this.voice = _voice_;
		this.voiceduration = _voiceduration_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(channel);
		_os_.marshal(receiver);
		_os_.marshal(text, "UTF-16LE");
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(invitechannel);
		_os_.marshal(voice);
		_os_.marshal(voiceduration);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		channel = _os_.unmarshal_int();
		receiver = _os_.unmarshal_long();
		text = _os_.unmarshal_String("UTF-16LE");
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		invitechannel = _os_.unmarshal_int();
		voice = _os_.unmarshal_Octets();
		voiceduration = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CChat) {
			CChat _o_ = (CChat)_o1_;
			if (channel != _o_.channel) return false;
			if (receiver != _o_.receiver) return false;
			if (!text.equals(_o_.text)) return false;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (invitechannel != _o_.invitechannel) return false;
			if (!voice.equals(_o_.voice)) return false;
			if (voiceduration != _o_.voiceduration) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += channel;
		_h_ += (int)receiver;
		_h_ += text.hashCode();
		_h_ += bagtype;
		_h_ += pos;
		_h_ += invitechannel;
		_h_ += voice.hashCode();
		_h_ += Float.floatToIntBits(voiceduration);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(channel).append(",");
		_sb_.append(receiver).append(",");
		_sb_.append("T").append(text.length()).append(",");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(invitechannel).append(",");
		_sb_.append("B").append(voice.size()).append(",");
		_sb_.append(voiceduration).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

