package lx.gs.login;

import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.RefObject;
import common.Time;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.STips;
import lx.gs.map.FMap;
import lx.gs.role.FForbid;
import lx.gs.tips.FTips;
import xdb.Procedure;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRoleLogin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRoleLogin extends __CRoleLogin__ {
	@Override
	protected void process() {
		lx.gs.login.SRoleLogin result = new lx.gs.login.SRoleLogin();

		if (Onlines.getInstance().isFull()) {
			// cacheCapacity 都没有配置，默认是10240，所以这里配成10000，就不配置到文件中了。
			result.err = lx.gs.login.SRoleLogin.TOOMANY_ONLINES_ROLE;
			gnet.link.Onlines.getInstance().sendResponse(this, result);
			return;
		}
		java.lang.management.OperatingSystemMXBean osmxbean = java.lang.management.ManagementFactory.getOperatingSystemMXBean();
		if (osmxbean.getSystemLoadAverage() >= osmxbean.getAvailableProcessors() * 0.8) {
			result.err = lx.gs.login.SRoleLogin.SERVER_LOADAVG_BUSY;
			gnet.link.Onlines.getInstance().sendResponse(this, result);
			return;
		}

		gnet.link.Dispatch context = ((gnet.link.Dispatch) this.getContext());
		long userid = context.userid;
		xbean.User user = xtable.Users.select(userid);
		if (null == user) {
			result.err = lx.gs.login.SRoleLogin.USER_NOT_FOUND;
			gnet.link.Onlines.getInstance().sendResponse(this, result);
			return;
		}
		if (!user.getRoleids().contains(roleid)) {
			result.err = lx.gs.login.SRoleLogin.ROLEID_NOT_IN_USER;
			gnet.link.Onlines.getInstance().sendResponse(this, result);
			return;
		}

		RefObject<Boolean> canLogin = new RefObject<>(true);
		new Procedure(){
			@Override
			protected boolean process() throws Exception {
				xbean.ForbidItem loginForbid = FForbid.getLoginItem(roleid);
				if(loginForbid != null){
					String date = Time.toFormatStr(loginForbid.getForbidrealsetime());
					STips sTips = FTips.create(LocationType.ALERT, TipsCode.LOGIN_FORBIDEN, date, loginForbid.getDesc());
					gnet.link.Onlines.getInstance().sendResponse(CRoleLogin.this, sTips);
					canLogin.value = false;
				}
				//必须返回true，FForbid.getLoginItem(roleid)这一步如果过期会修改xdb清理掉数据
				return true;
			}
		}.call();
		if(!canLogin.value){
			return;
		}

        final long lastLoginRoleid = xtable.Users.selectLastloginrole(userid);
        final Role lastRole = gnet.link.Onlines.getInstance().find(lastLoginRoleid);
        if(lastLoginRoleid != roleid) {
            if(lastRole != null)
                lastRole.doCompleteLogout();
            final Role oldRole = gnet.link.Onlines.getInstance().find(roleid);
            if(oldRole != null) {
                oldRole.doReplaceLogout();
            }
        } else {
            if(lastRole != null)
                lastRole.doReplaceLogout();
        }

		final Role role = gnet.link.Onlines.getInstance().insert(this, roleid);



		if (gnet.link.Onlines.isTraceProtocolAtInfoLevel())
            xdb.Trace.info("CRoleLoginStart linksid={}, userid={}, roleid={}", context.linksid, userid, roleid);
		try {
            role.onLogin(this);
			new PRoleLogin(userid, roleid, this).call();

			FMap.loginEnterMap(roleid);
		} catch (Exception ex) {
			xdb.Trace.error("role login error", ex);
		}
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553607;

	public int getType() {
		return 6553607;
	}

	public long roleid;

	public CRoleLogin() {
	}

	public CRoleLogin(long _roleid_) {
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRoleLogin) {
			CRoleLogin _o_ = (CRoleLogin)_o1_;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRoleLogin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

