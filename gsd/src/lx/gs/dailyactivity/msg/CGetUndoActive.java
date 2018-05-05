
package lx.gs.dailyactivity.msg;

import cfg.active.Activebonus;
import com.goldhuman.Common.Marshal.OctetsStream;

import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.dailyactivity.FDailyActivity;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetUndoActive__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetUndoActive extends __CGetUndoActive__ {
	@Override
	protected void process() {
		new AProcedure<CGetUndoActive>(this) {

			@Override
			protected boolean doProcess() throws Exception {
//                if(xtable.Roleinfos.selectLevel(roleid) < Activebonus.openlevel){
//                    return error(ErrorCode.NOT_ENOUGH_LEVEL);
//                }
				SGetUndoActive response = new SGetUndoActive();
				FDailyActivity.getUndoActiveInfo(roleid, response.undoactive);
                if(response.undoactive.undoactive.size() > 0) {
                    FDailyActivity.genTotalFindBackBonus(response.undoactive);
                }
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556435;

	public int getType() {
		return 6556435;
	}


	public CGetUndoActive() {
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
		if (_o1_ instanceof CGetUndoActive) {
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

	public int compareTo(CGetUndoActive _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

