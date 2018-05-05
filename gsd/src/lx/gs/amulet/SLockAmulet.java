
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLockAmulet__ extends xio.Protocol { }

/** 锁定护符属性
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLockAmulet extends lx.gs.amulet.__SLockAmulet__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566193;

	public int getType() {
		return 6566193;
	}

	public int pageid; // 护符页面
	public int amuletid; // 护符id

	public SLockAmulet() {
	}

	public SLockAmulet(int _pageid_, int _amuletid_) {
		this.pageid = _pageid_;
		this.amuletid = _amuletid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pageid);
		_os_.marshal(amuletid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pageid = _os_.unmarshal_int();
		amuletid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLockAmulet) {
			SLockAmulet _o_ = (SLockAmulet)_o1_;
			if (pageid != _o_.pageid) return false;
			if (amuletid != _o_.amuletid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pageid;
		_h_ += amuletid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageid).append(",");
		_sb_.append(amuletid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SLockAmulet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pageid - _o_.pageid;
		if (0 != _c_) return _c_;
		_c_ = amuletid - _o_.amuletid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

