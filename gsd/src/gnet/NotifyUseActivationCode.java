
package gnet;

import cfg.CfgMgr;
import cfg.Const;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.Bonus;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.role.msg.SUseCode;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __NotifyUseActivationCode__ extends xio.Protocol { }

/** auany - deliver - gs
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class NotifyUseActivationCode extends __NotifyUseActivationCode__ {
	@Override
	protected void process() {
		new xdb.Procedure() {
			@Override
			protected boolean process() {
                Trace.info("NotifyUseActiveCode. userid:{} roleid:{} code:{} codetype:{}", userid, roleid, code, codetype);
                if (err.code != gnet.ActivationCodeErr.ERR_SUCCESS) {
                   return error(err.code);
                }

                boolean isConfirm = false;
                try {
                    final cfg.activecode.ActiveCode acfg = CfgMgr.activecode.get(codetype);
                    if(acfg == null) {
                        return error(ActivationCodeErr.ERR_TYPE_NOT_MATCH);
                    }

                    final int level = xtable.Roleinfos.selectLevel(roleid);
                    if(acfg.levellimit.min != Const.NULL && level < acfg.levellimit.min) {
                        return error(ActivationCodeErr.ERR_LEVEL_TOO_LOWE);
                    }
                    if(acfg.levellimit.max != Const.NULL && level > acfg.levellimit.max) {
                        return error(ActivationCodeErr.ERR_LEVEL_TOO_HIGH);
                    }

                    xbean.RoleActiveCode info = xtable.Roleactivecodes.get(roleid);
                    if(info == null) {
                        info = xbean.Pod.newRoleActiveCode();
                        xtable.Roleactivecodes.insert(roleid, info);
                    }
                    xbean.ActiveCode codeInfo = info.getCodegroups().get(acfg.groupid);
                    if(codeInfo == null) {
                        codeInfo = xbean.Pod.newActiveCode();
                        info.getCodegroups().put(acfg.groupid, codeInfo);
                    }

                    final long now = System.currentTimeMillis();
                    if(!common.Time.isSameDay(codeInfo.getLastusetime(), now)) {
                        codeInfo.setTodayusecount(0);
                    }
                    final int newTodayCount = codeInfo.getTodayusecount() + 1;
                    final int newTotalCount = codeInfo.getTotalusecount() + 1;
                    if(acfg.totallimit != Const.NULL && newTotalCount > acfg.totallimit) {
                        return error(ActivationCodeErr.ERR_EXCEED_ALL_USENUM);
                    }
                    if(acfg.daylimit != Const.NULL && newTodayCount > acfg.daylimit) {
                        return error(ActivationCodeErr.ERR_EXCEED_DAY_USENUM);
                    }
                    codeInfo.setTodayusecount(newTodayCount);
                    codeInfo.setTotalusecount(newTotalCount);
                    codeInfo.setLastusetime(now);


                    final SUseCode re = new SUseCode();
                    re.retcode = ActivationCodeErr.ERR_SUCCESS;
                    re.code = code;
					xdb.Transaction.tsendWhileCommit(roleid, re);
                    if(FBonus.genAndAddBonus(roleid, acfg.bonus, Bonus.BindType.BIND, re.bonus, By.ActiveCode)) {
                        isConfirm = true;
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return error(ActivationCodeErr.ERR_INTERNAL);
                }
                finally {
                    //gsd --> Deliver --> AU 无论成功失败，通知AU激活码是否使用真正使用
                    new gnet.ConfirmUseActivationCode(code, common.Utils.toByte(isConfirm)).send(gnet.DeliverClient.getInstance());
                }
			}
		}.execute();
	}

    private boolean error(int errcode) {
        xdb.Transaction.tsend(roleid, new SUseCode(errcode, code, new map.msg.Bonus()));
        return false;
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 145;

	public int getType() {
		return 145;
	}

	public gnet.ActivationCodeErr err;
	public long userid;
	public long roleid;
	public java.lang.String code;
	public int codetype;

	public NotifyUseActivationCode() {
		err = new gnet.ActivationCodeErr();
		code = "";
	}

	public NotifyUseActivationCode(gnet.ActivationCodeErr _err_, long _userid_, long _roleid_, java.lang.String _code_, int _codetype_) {
		this.err = _err_;
		this.userid = _userid_;
		this.roleid = _roleid_;
		this.code = _code_;
		this.codetype = _codetype_;
	}

	public final boolean _validator_() {
		if (!err._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(userid);
		_os_.marshal(roleid);
		_os_.marshal(code, "UTF-16LE");
		_os_.marshal(codetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err.unmarshal(_os_);
		userid = _os_.unmarshal_long();
		roleid = _os_.unmarshal_long();
		code = _os_.unmarshal_String("UTF-16LE");
		codetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NotifyUseActivationCode) {
			NotifyUseActivationCode _o_ = (NotifyUseActivationCode)_o1_;
			if (!err.equals(_o_.err)) return false;
			if (userid != _o_.userid) return false;
			if (roleid != _o_.roleid) return false;
			if (!code.equals(_o_.code)) return false;
			if (codetype != _o_.codetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err.hashCode();
		_h_ += (int)userid;
		_h_ += (int)roleid;
		_h_ += code.hashCode();
		_h_ += codetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(codetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

