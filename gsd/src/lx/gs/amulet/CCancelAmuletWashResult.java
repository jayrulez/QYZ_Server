
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelAmuletWashResult__ extends xio.Protocol { }

/** 取消洗练结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelAmuletWashResult extends lx.gs.amulet.__CCancelAmuletWashResult__ {
	@Override
	protected void process() {
		new AProcedure<CCancelAmuletWashResult>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleAmuletInfo info = FAmulet.get(roleid);
				ErrorCode errorCode = FAmulet.checkOperation(info, pageid);
				if (errorCode.err()) {
					return error(errorCode);
				}

				FAmulet.cancelWashResult(info.getPagemap().get(pageid));

				response(new SCancelAmuletWashResult(pageid));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571205;

	public int getType() {
		return 6571205;
	}

	public int pageid; // 洗练页面

	public CCancelAmuletWashResult() {
	}

	public CCancelAmuletWashResult(int _pageid_) {
		this.pageid = _pageid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pageid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pageid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCancelAmuletWashResult) {
			CCancelAmuletWashResult _o_ = (CCancelAmuletWashResult)_o1_;
			if (pageid != _o_.pageid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pageid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCancelAmuletWashResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pageid - _o_.pageid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

