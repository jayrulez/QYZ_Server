
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreatExpMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreatExpMonster extends __XCreatExpMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6709106;

	public int getType() {
		return 6709106;
	}

	public int lvlindex;
	public int positionindex;

	public XCreatExpMonster() {
	}

	public XCreatExpMonster(int _lvlindex_, int _positionindex_) {
		this.lvlindex = _lvlindex_;
		this.positionindex = _positionindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(lvlindex);
		_os_.marshal(positionindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		lvlindex = _os_.unmarshal_int();
		positionindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreatExpMonster) {
			XCreatExpMonster _o_ = (XCreatExpMonster)_o1_;
			if (lvlindex != _o_.lvlindex) return false;
			if (positionindex != _o_.positionindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += lvlindex;
		_h_ += positionindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lvlindex).append(",");
		_sb_.append(positionindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XCreatExpMonster _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = lvlindex - _o_.lvlindex;
		if (0 != _c_) return _c_;
		_c_ = positionindex - _o_.positionindex;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

