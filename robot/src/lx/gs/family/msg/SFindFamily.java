
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindFamily__ extends xio.Protocol { }

/** 结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindFamily extends __SFindFamily__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571695;

	public int getType() {
		return 6571695;
	}

	public int startindex;
	public java.lang.String familyname;
	public java.util.ArrayList<lx.gs.family.msg.FamilyBasicInfo> families; // 家族列表

	public SFindFamily() {
		familyname = "";
		families = new java.util.ArrayList<lx.gs.family.msg.FamilyBasicInfo>();
	}

	public SFindFamily(int _startindex_, java.lang.String _familyname_, java.util.ArrayList<lx.gs.family.msg.FamilyBasicInfo> _families_) {
		this.startindex = _startindex_;
		this.familyname = _familyname_;
		this.families = _families_;
	}

	public final boolean _validator_() {
		for (lx.gs.family.msg.FamilyBasicInfo _v_ : families)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(startindex);
		_os_.marshal(familyname, "UTF-16LE");
		_os_.compact_uint32(families.size());
		for (lx.gs.family.msg.FamilyBasicInfo _v_ : families) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		startindex = _os_.unmarshal_int();
		familyname = _os_.unmarshal_String("UTF-16LE");
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.family.msg.FamilyBasicInfo _v_ = new lx.gs.family.msg.FamilyBasicInfo();
			_v_.unmarshal(_os_);
			families.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindFamily) {
			SFindFamily _o_ = (SFindFamily)_o1_;
			if (startindex != _o_.startindex) return false;
			if (!familyname.equals(_o_.familyname)) return false;
			if (!families.equals(_o_.families)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += startindex;
		_h_ += familyname.hashCode();
		_h_ += families.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(startindex).append(",");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(families).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

