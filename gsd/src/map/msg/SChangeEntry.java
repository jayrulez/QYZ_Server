
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeEntry__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeEntry extends __SChangeEntry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684816;

	public int getType() {
		return 6684816;
	}

	public int layoutid;
	public int entryid;
	public int open;

	public SChangeEntry() {
	}

	public SChangeEntry(int _layoutid_, int _entryid_, int _open_) {
		this.layoutid = _layoutid_;
		this.entryid = _entryid_;
		this.open = _open_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(layoutid);
		_os_.marshal(entryid);
		_os_.marshal(open);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		layoutid = _os_.unmarshal_int();
		entryid = _os_.unmarshal_int();
		open = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeEntry) {
			SChangeEntry _o_ = (SChangeEntry)_o1_;
			if (layoutid != _o_.layoutid) return false;
			if (entryid != _o_.entryid) return false;
			if (open != _o_.open) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += layoutid;
		_h_ += entryid;
		_h_ += open;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(layoutid).append(",");
		_sb_.append(entryid).append(",");
		_sb_.append(open).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChangeEntry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = layoutid - _o_.layoutid;
		if (0 != _c_) return _c_;
		_c_ = entryid - _o_.entryid;
		if (0 != _c_) return _c_;
		_c_ = open - _o_.open;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

