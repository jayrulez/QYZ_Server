
package lx.gs.cmd.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCmd;
import lx.gs.item.FItem;
import lx.gs.logger.FLogger;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCommand__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCommand extends __CCommand__ {
	@Override
	protected void process() {
		new AProcedure<CCommand>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(num <= 0) {
					num = 1;
				}
				final FCmd.Context ctx = FCmd.invoke(roleid, moduleid, cmdid, num);
                if(ctx.errcode.err()){
                    return error(ctx.errcode);
                }
				final SCommand re = new SCommand();
				re.errcode = ctx.errcode.getErrorId();
				re.moduleid = moduleid;
				re.cmdid = cmdid;
				re.num = num;
                if (ctx.bonus != null) {
                    re.bonus = ctx.bonus;
                }
                tsend(roleid, re);
                cfg.mall.Mall conf = CfgMgr.mall.get(cmdid);// oneitem not only in itembasic
                FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), conf.itemid.itemid, num, conf.cost.currencytype, conf.cost.amount * num);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573551;

	public int getType() {
		return 6573551;
	}

	public int moduleid;
	public int cmdid;
	public int num;

	public CCommand() {
	}

	public CCommand(int _moduleid_, int _cmdid_, int _num_) {
		this.moduleid = _moduleid_;
		this.cmdid = _cmdid_;
		this.num = _num_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(moduleid);
		_os_.marshal(cmdid);
		_os_.marshal(num);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		moduleid = _os_.unmarshal_int();
		cmdid = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCommand) {
			CCommand _o_ = (CCommand)_o1_;
			if (moduleid != _o_.moduleid) return false;
			if (cmdid != _o_.cmdid) return false;
			if (num != _o_.num) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += moduleid;
		_h_ += cmdid;
		_h_ += num;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(moduleid).append(",");
		_sb_.append(cmdid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCommand _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = moduleid - _o_.moduleid;
		if (0 != _c_) return _c_;
		_c_ = cmdid - _o_.cmdid;
		if (0 != _c_) return _c_;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

