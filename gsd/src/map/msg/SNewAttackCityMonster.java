
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNewAttackCityMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNewAttackCityMonster extends __SNewAttackCityMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6704021;

	public int getType() {
		return 6704021;
	}

	public int sectionindex; // 所属大区
	public int monsterbatchindex; // 怪物波次

	public SNewAttackCityMonster() {
	}

	public SNewAttackCityMonster(int _sectionindex_, int _monsterbatchindex_) {
		this.sectionindex = _sectionindex_;
		this.monsterbatchindex = _monsterbatchindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(sectionindex);
		_os_.marshal(monsterbatchindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		sectionindex = _os_.unmarshal_int();
		monsterbatchindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNewAttackCityMonster) {
			SNewAttackCityMonster _o_ = (SNewAttackCityMonster)_o1_;
			if (sectionindex != _o_.sectionindex) return false;
			if (monsterbatchindex != _o_.monsterbatchindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += sectionindex;
		_h_ += monsterbatchindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sectionindex).append(",");
		_sb_.append(monsterbatchindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SNewAttackCityMonster _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = sectionindex - _o_.sectionindex;
		if (0 != _c_) return _c_;
		_c_ = monsterbatchindex - _o_.monsterbatchindex;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

