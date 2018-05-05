
package lx.gs.activity.huiwu.msg;

import cfg.huiwu.Stage;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import common.ErrorCode;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGuess__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGuess extends __CGuess__ {
	@Override
	protected void process() {
		new AProcedure<CGuess>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.HuiWuCurTerm curTerm = FHuiWu.getCurTermInfo();
				if(curTerm.getStage() != Stage.END_PRESELECT2)
					return false;
                FHuiWu.checkAttend(roleid);
                if(curTerm.getGuess().put(roleid, target) != null)
                    return error(ErrorCode.HUIWU_ALREADY_GUESSED);
				final int profession = xtable.Roleinfos.selectProfession(target);
				final Map<Long, Integer> roleBeGuessNum = curTerm.getTerminfobyprofession().get(profession).getPreselection2roleidbeguessnums();
				final int newBeGuessNum = roleBeGuessNum.get(target) + 1;
				roleBeGuessNum.put(target, newBeGuessNum);
				response(new SGuess(profession, target, newBeGuessNum));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583526;

	public int getType() {
		return 6583526;
	}

	public int profession;
	public long target;

	public CGuess() {
	}

	public CGuess(int _profession_, long _target_) {
		this.profession = _profession_;
		this.target = _target_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(profession);
		_os_.marshal(target);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		profession = _os_.unmarshal_int();
		target = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGuess) {
			CGuess _o_ = (CGuess)_o1_;
			if (profession != _o_.profession) return false;
			if (target != _o_.target) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += profession;
		_h_ += (int)target;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(profession).append(",");
		_sb_.append(target).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGuess _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(target - _o_.target);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

