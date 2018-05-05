
package lx.gs.achievement.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEvolveTitle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEvolveTitle extends __CEvolveTitle__ {
	@Override
	protected void process() {
		new AProcedure<CEvolveTitle>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode err = FAchievement.evolveTitle(roleid, titleid);
				if(err.err())
					return error(err);
				response(new SEvolveTitle(titleid));
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566528;

	public int getType() {
		return 6566528;
	}

	public int titleid;

	public CEvolveTitle() {
	}

	public CEvolveTitle(int _titleid_) {
		this.titleid = _titleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(titleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		titleid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEvolveTitle) {
			CEvolveTitle _o_ = (CEvolveTitle)_o1_;
			if (titleid != _o_.titleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += titleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEvolveTitle _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = titleid - _o_.titleid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

