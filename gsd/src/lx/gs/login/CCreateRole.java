
package lx.gs.login;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCreateRole__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCreateRole extends __CCreateRole__ {
	@Override
	protected void process() {
		long userid = ((gnet.link.Dispatch) this.getContext()).userid;		
		lx.gs.login.PCreateRole p = new lx.gs.login.PCreateRole(userid, this);
		try {
			p.submit().get();
		} catch (Exception e) {
			xdb.Trace.error("convert role error", e);
		}

		gnet.link.Onlines.getInstance().sendResponse(this, p.getResult());
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553603;

	public int getType() {
		return 6553603;
	}

	public java.lang.String name;
	public int profession;
	public int gender;

	public CCreateRole() {
		name = "";
	}

	public CCreateRole(java.lang.String _name_, int _profession_, int _gender_) {
		this.name = _name_;
		this.profession = _profession_;
		this.gender = _gender_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		_os_.marshal(profession);
		_os_.marshal(gender);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		profession = _os_.unmarshal_int();
		gender = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCreateRole) {
			CCreateRole _o_ = (CCreateRole)_o1_;
			if (!name.equals(_o_.name)) return false;
			if (profession != _o_.profession) return false;
			if (gender != _o_.gender) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		_h_ += profession;
		_h_ += gender;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(gender).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

