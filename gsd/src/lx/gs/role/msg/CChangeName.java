
package lx.gs.role.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.login.FLogin;
import lx.gs.map.FMap;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChangeName__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChangeName extends __CChangeName__ {
	@Override
	protected void process() {
		new AProcedure<CChangeName>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
                String name = newname.trim();
                if (name.length() < CfgMgr.roleconfig.minnamelength) {
                    return error(ErrorCode.NAME_SHORTLEN);
                }
                if (name.length() > CfgMgr.roleconfig.maxnamelength) {
                    return  error(ErrorCode.NAME_OVERLEN);
                }
                if(FRole.containsWhiteSpaceChar(name)) {
                    return error(ErrorCode.NAME_INVALID);
                }
                if (FRole.isSenseWord(name)) {
                    return error(ErrorCode.NAME_SENSE);
                }
                final int serverid = gs.Utils.getServerId();
                final String fullName = FLogin.makeFullName(name, serverid);
                if(xtable.Rolename2ids.get(fullName) != null) {
                    return error(ErrorCode.NAME_DUPLICATED);
                }
                int changetimes = roleInfo.getChangenametime();
                int index = Math.min(changetimes, CfgMgr.roleconfig.renamecost.amout.size() - 1);
                if(!FRole.checkCostYuanBao(roleid, roleInfo, CfgMgr.roleconfig.renamecost.amout.get(index), By.ReName)){
                    return error(ErrorCode.YUANBAO_NOT_ENOUGH);
                }
                roleInfo.setChangenametime(changetimes + 1);
                String oldfullName = FLogin.makeFullName(roleInfo.getName(), serverid);
                xtable.Rolename2ids.delete(oldfullName);//delete old name to id and insert new
                xtable.Rolename2ids.insert(fullName, roleid);
                roleInfo.setName(name);
                FMap.dispatchMessageInProcedure(roleid, new map.msg.SChangeName(name));
                SChangeName response = new SChangeName();
                response.newname = name;
                response.changetimes = changetimes + 1;
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563907;

	public int getType() {
		return 6563907;
	}

	public java.lang.String newname;

	public CChangeName() {
		newname = "";
	}

	public CChangeName(java.lang.String _newname_) {
		this.newname = _newname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(newname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		newname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CChangeName) {
			CChangeName _o_ = (CChangeName)_o1_;
			if (!newname.equals(_o_.newname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += newname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(newname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

