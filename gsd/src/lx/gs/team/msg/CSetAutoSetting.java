
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.team.FTeam;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSetAutoSetting__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSetAutoSetting extends __CSetAutoSetting__ {
	@Override
	protected void process() {
		new AProcedure<CSetAutoSetting>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTeamInfo info = FTeam.getRoleTeamInfo(roleid);
				if(opttype == CSetAutoSetting.AUTO_ACCEPT_INVITE){
					info.setAutoacceptinvite(cfgvalue);
				}else if(opttype == CSetAutoSetting.AUTO_ACCEPT_REQUEST){
					info.setAutoacceptrequest(cfgvalue);
				}else{
					error(ErrorCode.PARAM_ERROR);
				}
				response(new SSetAutoSetting(opttype, cfgvalue));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557847;

	public int getType() {
		return 6557847;
	}

	public final static int AUTO_ACCEPT_INVITE = 1;
	public final static int AUTO_ACCEPT_REQUEST = 2;

	public int opttype; // 操作类型，参考以上枚举定义
	public int cfgvalue; // 是否自动接收邀请

	public CSetAutoSetting() {
	}

	public CSetAutoSetting(int _opttype_, int _cfgvalue_) {
		this.opttype = _opttype_;
		this.cfgvalue = _cfgvalue_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(opttype);
		_os_.marshal(cfgvalue);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		opttype = _os_.unmarshal_int();
		cfgvalue = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSetAutoSetting) {
			CSetAutoSetting _o_ = (CSetAutoSetting)_o1_;
			if (opttype != _o_.opttype) return false;
			if (cfgvalue != _o_.cfgvalue) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += opttype;
		_h_ += cfgvalue;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(opttype).append(",");
		_sb_.append(cfgvalue).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSetAutoSetting _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = opttype - _o_.opttype;
		if (0 != _c_) return _c_;
		_c_ = cfgvalue - _o_.cfgvalue;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

