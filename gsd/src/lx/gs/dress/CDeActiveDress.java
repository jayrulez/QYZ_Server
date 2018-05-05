
package lx.gs.dress;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import xbean.RoleDress;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDeActiveDress__ extends xio.Protocol { }

/** 卸载时装
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDeActiveDress extends lx.gs.dress.__CDeActiveDress__ {
	@Override
	protected void process() {
		new AProcedure<CDeActiveDress>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final RoleDress info = FDress.get(roleid);
				int oldId = info.getActivedress();
				if(!info.getDresses().containsKey(oldId)){
					return error(ErrorCode.DRESS_NOT_GET);
				}
				info.setActivedress(0);
				response(new SDeActiveDress());
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581317;

	public int getType() {
		return 6581317;
	}


	public CDeActiveDress() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDeActiveDress) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CDeActiveDress _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

