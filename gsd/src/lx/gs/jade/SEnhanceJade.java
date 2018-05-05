
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnhanceJade__ extends xio.Protocol { }

/** 玉佩培养
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnhanceJade extends lx.gs.jade.__SEnhanceJade__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583989;

	public int getType() {
		return 6583989;
	}

	public int enhancetypeid;
	public int num;
	public int addbonus;

	public SEnhanceJade() {
	}

	public SEnhanceJade(int _enhancetypeid_, int _num_, int _addbonus_) {
		this.enhancetypeid = _enhancetypeid_;
		this.num = _num_;
		this.addbonus = _addbonus_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(enhancetypeid);
		_os_.marshal(num);
		_os_.marshal(addbonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		enhancetypeid = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		addbonus = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnhanceJade) {
			SEnhanceJade _o_ = (SEnhanceJade)_o1_;
			if (enhancetypeid != _o_.enhancetypeid) return false;
			if (num != _o_.num) return false;
			if (addbonus != _o_.addbonus) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += enhancetypeid;
		_h_ += num;
		_h_ += addbonus;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(enhancetypeid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(addbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEnhanceJade _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = enhancetypeid - _o_.enhancetypeid;
		if (0 != _c_) return _c_;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		_c_ = addbonus - _o_.addbonus;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

