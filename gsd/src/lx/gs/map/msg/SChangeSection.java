
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeSection__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeSection extends __SChangeSection__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579610;

	public int getType() {
		return 6579610;
	}

	public int chapterid;
	public int sectionid;
	public int star;

	public SChangeSection() {
	}

	public SChangeSection(int _chapterid_, int _sectionid_, int _star_) {
		this.chapterid = _chapterid_;
		this.sectionid = _sectionid_;
		this.star = _star_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(chapterid);
		_os_.marshal(sectionid);
		_os_.marshal(star);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		chapterid = _os_.unmarshal_int();
		sectionid = _os_.unmarshal_int();
		star = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeSection) {
			SChangeSection _o_ = (SChangeSection)_o1_;
			if (chapterid != _o_.chapterid) return false;
			if (sectionid != _o_.sectionid) return false;
			if (star != _o_.star) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += chapterid;
		_h_ += sectionid;
		_h_ += star;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapterid).append(",");
		_sb_.append(sectionid).append(",");
		_sb_.append(star).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChangeSection _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = chapterid - _o_.chapterid;
		if (0 != _c_) return _c_;
		_c_ = sectionid - _o_.sectionid;
		if (0 != _c_) return _c_;
		_c_ = star - _o_.star;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

