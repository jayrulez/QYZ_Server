
package lx.gs.dress;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import xbean.RoleDress;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CActiveDress__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CActiveDress extends lx.gs.dress.__CActiveDress__ {
	@Override
	protected void process() {
		new AProcedure<CActiveDress>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final RoleDress info = FDress.get(roleid);
				if(!info.getDresses().containsKey(dresskey)) {
					return error(ErrorCode.DRESS_NOT_GET);
				}
				info.setActivedress(dresskey);
				response(new SActiveDress(dresskey));
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555772;

	public int getType() {
		return 6555772;
	}

	public int dresskey; // 要使用的dress的key

	public CActiveDress() {
	}

	public CActiveDress(int _dresskey_) {
		this.dresskey = _dresskey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dresskey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dresskey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CActiveDress) {
			CActiveDress _o_ = (CActiveDress)_o1_;
			if (dresskey != _o_.dresskey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dresskey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresskey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CActiveDress _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dresskey - _o_.dresskey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

