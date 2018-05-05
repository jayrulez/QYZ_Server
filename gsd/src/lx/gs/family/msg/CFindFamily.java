
package lx.gs.family.msg;

import lx.gs.family.PFindFamily;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFindFamily__ extends xio.Protocol { }

/** 查找家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFindFamily extends __CFindFamily__ {
	@Override
	protected void process() {
		new PFindFamily(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556181;

	public int getType() {
		return 6556181;
	}

	public java.lang.String familyname; // 查找家族，如果输入为空，返回所有的家族
	public int startindex; // 从第几个开始查找，每次找20个

	public CFindFamily() {
		familyname = "";
	}

	public CFindFamily(java.lang.String _familyname_, int _startindex_) {
		this.familyname = _familyname_;
		this.startindex = _startindex_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyname, "UTF-16LE");
		_os_.marshal(startindex);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyname = _os_.unmarshal_String("UTF-16LE");
		startindex = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFindFamily) {
			CFindFamily _o_ = (CFindFamily)_o1_;
			if (!familyname.equals(_o_.familyname)) return false;
			if (startindex != _o_.startindex) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += familyname.hashCode();
		_h_ += startindex;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(startindex).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

