
package gnet.link;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AnnounceLinkId__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AnnounceLinkId extends __AnnounceLinkId__ {
	@Override
	protected void process() {
		gnet.link.Onlines.getInstance().process(this);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65547;

	public int getType() {
		return 65547;
	}

	public int linkid;

	public AnnounceLinkId() {
	}

	public AnnounceLinkId(int _linkid_) {
		this.linkid = _linkid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(linkid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		linkid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AnnounceLinkId) {
			AnnounceLinkId _o_ = (AnnounceLinkId)_o1_;
			if (linkid != _o_.linkid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += linkid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(linkid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AnnounceLinkId _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = linkid - _o_.linkid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

