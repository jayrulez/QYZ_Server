
package lx.gs.map.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import map.msg.XChangePKState;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSetPKState__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSetPKState extends __CSetPKState__ {
	@Override
	protected void process() {
		new AProcedure<CSetPKState>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final Map<Integer, Integer> pkStates = FMap.get(roleid).getPkstates();
                final ErrorCode err = FCondition.checkByReflection(roleid, CfgMgr.roleconfig.pkinfo, By.Pk);
                if(err.err())
                    return error(err);
                pkStates.put(worldid, pkstate);
                FMap.dispatchMessageInProcedure(roleid, new XChangePKState(worldid, pkstate));
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568130;

	public int getType() {
		return 6568130;
	}

	public int worldid;
	public int pkstate;

	public CSetPKState() {
	}

	public CSetPKState(int _worldid_, int _pkstate_) {
		this.worldid = _worldid_;
		this.pkstate = _pkstate_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldid);
		_os_.marshal(pkstate);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldid = _os_.unmarshal_int();
		pkstate = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSetPKState) {
			CSetPKState _o_ = (CSetPKState)_o1_;
			if (worldid != _o_.worldid) return false;
			if (pkstate != _o_.pkstate) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldid;
		_h_ += pkstate;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldid).append(",");
		_sb_.append(pkstate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSetPKState _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = worldid - _o_.worldid;
		if (0 != _c_) return _c_;
		_c_ = pkstate - _o_.pkstate;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

