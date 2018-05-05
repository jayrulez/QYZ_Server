
package lx.gs.mail.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Mail implements Marshal {
	public int id;
	public int mailid; // cfg.mail.Mail
	public long sendtime;
	public java.lang.String title;
	public java.lang.String content;
	public map.msg.Bonus accessory;
	public java.util.ArrayList<java.lang.String> params;
	public int read; // 0表示未读,1表示已读

	public Mail() {
		title = "";
		content = "";
		accessory = new map.msg.Bonus();
		params = new java.util.ArrayList<java.lang.String>();
	}

	public Mail(int _id_, int _mailid_, long _sendtime_, java.lang.String _title_, java.lang.String _content_, map.msg.Bonus _accessory_, java.util.ArrayList<java.lang.String> _params_, int _read_) {
		this.id = _id_;
		this.mailid = _mailid_;
		this.sendtime = _sendtime_;
		this.title = _title_;
		this.content = _content_;
		this.accessory = _accessory_;
		this.params = _params_;
		this.read = _read_;
	}

	public final boolean _validator_() {
		if (!accessory._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(mailid);
		_os_.marshal(sendtime);
		_os_.marshal(title, "UTF-16LE");
		_os_.marshal(content, "UTF-16LE");
		_os_.marshal(accessory);
		_os_.compact_uint32(params.size());
		for (java.lang.String _v_ : params) {
			_os_.marshal(_v_, "UTF-16LE");
		}
		_os_.marshal(read);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_int();
		mailid = _os_.unmarshal_int();
		sendtime = _os_.unmarshal_long();
		title = _os_.unmarshal_String("UTF-16LE");
		content = _os_.unmarshal_String("UTF-16LE");
		accessory.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			java.lang.String _v_;
			_v_ = _os_.unmarshal_String("UTF-16LE");
			params.add(_v_);
		}
		read = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Mail) {
			Mail _o_ = (Mail)_o1_;
			if (id != _o_.id) return false;
			if (mailid != _o_.mailid) return false;
			if (sendtime != _o_.sendtime) return false;
			if (!title.equals(_o_.title)) return false;
			if (!content.equals(_o_.content)) return false;
			if (!accessory.equals(_o_.accessory)) return false;
			if (!params.equals(_o_.params)) return false;
			if (read != _o_.read) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += id;
		_h_ += mailid;
		_h_ += (int)sendtime;
		_h_ += title.hashCode();
		_h_ += content.hashCode();
		_h_ += accessory.hashCode();
		_h_ += params.hashCode();
		_h_ += read;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(mailid).append(",");
		_sb_.append(sendtime).append(",");
		_sb_.append("T").append(title.length()).append(",");
		_sb_.append("T").append(content.length()).append(",");
		_sb_.append(accessory).append(",");
		_sb_.append(params).append(",");
		_sb_.append(read).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

