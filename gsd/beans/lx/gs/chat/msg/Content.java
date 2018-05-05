
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Content implements Marshal {
	public long senderid;
	public java.lang.String sendername;
	public int senderprofession;
	public int sendergender;
	public long receiverid;
	public java.lang.String receivername;
	public java.lang.String text;
	public int bagtype;
	public com.goldhuman.Common.Octets item;
	public int voiceid;
	public float voiceduration;

	public Content() {
		sendername = "";
		receivername = "";
		text = "";
		item = new com.goldhuman.Common.Octets();
	}

	public Content(long _senderid_, java.lang.String _sendername_, int _senderprofession_, int _sendergender_, long _receiverid_, java.lang.String _receivername_, java.lang.String _text_, int _bagtype_, com.goldhuman.Common.Octets _item_, int _voiceid_, float _voiceduration_) {
		this.senderid = _senderid_;
		this.sendername = _sendername_;
		this.senderprofession = _senderprofession_;
		this.sendergender = _sendergender_;
		this.receiverid = _receiverid_;
		this.receivername = _receivername_;
		this.text = _text_;
		this.bagtype = _bagtype_;
		this.item = _item_;
		this.voiceid = _voiceid_;
		this.voiceduration = _voiceduration_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(senderid);
		_os_.marshal(sendername, "UTF-16LE");
		_os_.marshal(senderprofession);
		_os_.marshal(sendergender);
		_os_.marshal(receiverid);
		_os_.marshal(receivername, "UTF-16LE");
		_os_.marshal(text, "UTF-16LE");
		_os_.marshal(bagtype);
		_os_.marshal(item);
		_os_.marshal(voiceid);
		_os_.marshal(voiceduration);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		senderid = _os_.unmarshal_long();
		sendername = _os_.unmarshal_String("UTF-16LE");
		senderprofession = _os_.unmarshal_int();
		sendergender = _os_.unmarshal_int();
		receiverid = _os_.unmarshal_long();
		receivername = _os_.unmarshal_String("UTF-16LE");
		text = _os_.unmarshal_String("UTF-16LE");
		bagtype = _os_.unmarshal_int();
		item = _os_.unmarshal_Octets();
		voiceid = _os_.unmarshal_int();
		voiceduration = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Content) {
			Content _o_ = (Content)_o1_;
			if (senderid != _o_.senderid) return false;
			if (!sendername.equals(_o_.sendername)) return false;
			if (senderprofession != _o_.senderprofession) return false;
			if (sendergender != _o_.sendergender) return false;
			if (receiverid != _o_.receiverid) return false;
			if (!receivername.equals(_o_.receivername)) return false;
			if (!text.equals(_o_.text)) return false;
			if (bagtype != _o_.bagtype) return false;
			if (!item.equals(_o_.item)) return false;
			if (voiceid != _o_.voiceid) return false;
			if (voiceduration != _o_.voiceduration) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)senderid;
		_h_ += sendername.hashCode();
		_h_ += senderprofession;
		_h_ += sendergender;
		_h_ += (int)receiverid;
		_h_ += receivername.hashCode();
		_h_ += text.hashCode();
		_h_ += bagtype;
		_h_ += item.hashCode();
		_h_ += voiceid;
		_h_ += Float.floatToIntBits(voiceduration);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(senderid).append(",");
		_sb_.append("T").append(sendername.length()).append(",");
		_sb_.append(senderprofession).append(",");
		_sb_.append(sendergender).append(",");
		_sb_.append(receiverid).append(",");
		_sb_.append("T").append(receivername.length()).append(",");
		_sb_.append("T").append(text.length()).append(",");
		_sb_.append(bagtype).append(",");
		_sb_.append("B").append(item.size()).append(",");
		_sb_.append(voiceid).append(",");
		_sb_.append(voiceduration).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

