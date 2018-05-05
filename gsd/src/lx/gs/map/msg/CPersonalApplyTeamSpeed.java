
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.map.teamspeed.TeamSpeedModule;
import lx.gs.team.FTeam;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPersonalApplyTeamSpeed__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPersonalApplyTeamSpeed extends __CPersonalApplyTeamSpeed__ {
	@Override
	protected void process() {
		new AProcedure<CPersonalApplyTeamSpeed>(this){
			@Override
			protected boolean doProcess() throws Exception {
				//先退出队伍，再单排
				if(FTeam.isInTeam(roleid)){
					if(!FTeam.quitTeam(roleid)) return false;
				}

				boolean result = TeamSpeedModule.INSTANCE.applyMatch(roleid, Arrays.asList(roleid));
				if(result) response(new SApplyTeamSpeedSucc());
				return result;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581081;

	public int getType() {
		return 6581081;
	}


	public CPersonalApplyTeamSpeed() {
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
		if (_o1_ instanceof CPersonalApplyTeamSpeed) {
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

	public int compareTo(CPersonalApplyTeamSpeed _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

