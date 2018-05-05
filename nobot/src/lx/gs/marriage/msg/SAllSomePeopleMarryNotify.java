
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAllSomePeopleMarryNotify__ extends xio.Protocol { }

/** 通知全体人有人结婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAllSomePeopleMarryNotify extends __SAllSomePeopleMarryNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584994;

	public int getType() {
		return 6584994;
	}

	public java.lang.String content; // 要显示的内容
	public java.lang.String proposename; // 求婚者的姓名
	public int proposegender; // 性别，见GenderType
	public java.lang.String beproposedname; // 被求婚者的姓名
	public int beproposegender;

	public SAllSomePeopleMarryNotify() {
		content = "";
		proposename = "";
		beproposedname = "";
	}

	public SAllSomePeopleMarryNotify(java.lang.String _content_, java.lang.String _proposename_, int _proposegender_, java.lang.String _beproposedname_, int _beproposegender_) {
		this.content = _content_;
		this.proposename = _proposename_;
		this.proposegender = _proposegender_;
		this.beproposedname = _beproposedname_;
		this.beproposegender = _beproposegender_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(content, "UTF-16LE");
		_os_.marshal(proposename, "UTF-16LE");
		_os_.marshal(proposegender);
		_os_.marshal(beproposedname, "UTF-16LE");
		_os_.marshal(beproposegender);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		content = _os_.unmarshal_String("UTF-16LE");
		proposename = _os_.unmarshal_String("UTF-16LE");
		proposegender = _os_.unmarshal_int();
		beproposedname = _os_.unmarshal_String("UTF-16LE");
		beproposegender = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAllSomePeopleMarryNotify) {
			SAllSomePeopleMarryNotify _o_ = (SAllSomePeopleMarryNotify)_o1_;
			if (!content.equals(_o_.content)) return false;
			if (!proposename.equals(_o_.proposename)) return false;
			if (proposegender != _o_.proposegender) return false;
			if (!beproposedname.equals(_o_.beproposedname)) return false;
			if (beproposegender != _o_.beproposegender) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += content.hashCode();
		_h_ += proposename.hashCode();
		_h_ += proposegender;
		_h_ += beproposedname.hashCode();
		_h_ += beproposegender;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(content.length()).append(",");
		_sb_.append("T").append(proposename.length()).append(",");
		_sb_.append(proposegender).append(",");
		_sb_.append("T").append(beproposedname.length()).append(",");
		_sb_.append(beproposegender).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

