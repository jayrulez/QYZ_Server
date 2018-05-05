
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFamilyInfoChangeNotify__ extends xio.Protocol { }

/** 家族信息更新通知，通知全族人员
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFamilyInfoChangeNotify extends __SFamilyInfoChangeNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572828;

	public int getType() {
		return 6572828;
	}

	public java.lang.String declaration;
	public java.lang.String publicinfo;
	public long money;
	public int curlvlbuilddegree;
	public int flevel;

	public SFamilyInfoChangeNotify() {
		declaration = "";
		publicinfo = "";
	}

	public SFamilyInfoChangeNotify(java.lang.String _declaration_, java.lang.String _publicinfo_, long _money_, int _curlvlbuilddegree_, int _flevel_) {
		this.declaration = _declaration_;
		this.publicinfo = _publicinfo_;
		this.money = _money_;
		this.curlvlbuilddegree = _curlvlbuilddegree_;
		this.flevel = _flevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(declaration, "UTF-16LE");
		_os_.marshal(publicinfo, "UTF-16LE");
		_os_.marshal(money);
		_os_.marshal(curlvlbuilddegree);
		_os_.marshal(flevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		declaration = _os_.unmarshal_String("UTF-16LE");
		publicinfo = _os_.unmarshal_String("UTF-16LE");
		money = _os_.unmarshal_long();
		curlvlbuilddegree = _os_.unmarshal_int();
		flevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFamilyInfoChangeNotify) {
			SFamilyInfoChangeNotify _o_ = (SFamilyInfoChangeNotify)_o1_;
			if (!declaration.equals(_o_.declaration)) return false;
			if (!publicinfo.equals(_o_.publicinfo)) return false;
			if (money != _o_.money) return false;
			if (curlvlbuilddegree != _o_.curlvlbuilddegree) return false;
			if (flevel != _o_.flevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += declaration.hashCode();
		_h_ += publicinfo.hashCode();
		_h_ += (int)money;
		_h_ += curlvlbuilddegree;
		_h_ += flevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(declaration.length()).append(",");
		_sb_.append("T").append(publicinfo.length()).append(",");
		_sb_.append(money).append(",");
		_sb_.append(curlvlbuilddegree).append(",");
		_sb_.append(flevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

