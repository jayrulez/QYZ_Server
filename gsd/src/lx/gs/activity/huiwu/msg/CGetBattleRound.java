
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import xbean.HuiWuBattle;
import xbean.HuiWuCurTerm;
import xbean.HuiWuProfessionTerm;
import xbean.HuiWuRound;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetBattleRound__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetBattleRound extends __CGetBattleRound__ {
	@Override
	protected void process() {
		new AProcedure<CGetBattleRound>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final HuiWuCurTerm curTerm = FHuiWu.getCurTermInfo();
				final HuiWuProfessionTerm professionTerm = curTerm.getTerminfobyprofession().get(profession);
				final HuiWuRound termRound = professionTerm.getRounds().get(round);

				final SGetBattleRound re = new SGetBattleRound();
				re.round = round;
				re.profession = profession;
                if(termRound == null) return false;
                for (HuiWuBattle battle : termRound.getBattles()) {
                    re.battles.add(new BattlePair(
                            FHuiWu.createBattleRole(battle.getRoleid1()),
                            FHuiWu.createBattleRole(battle.getRoleid2()),
                            battle.getState()));
                }
				response(re);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583081;

	public int getType() {
		return 6583081;
	}

	public int round;
	public int profession;

	public CGetBattleRound() {
	}

	public CGetBattleRound(int _round_, int _profession_) {
		this.round = _round_;
		this.profession = _profession_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(round);
		_os_.marshal(profession);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		round = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetBattleRound) {
			CGetBattleRound _o_ = (CGetBattleRound)_o1_;
			if (round != _o_.round) return false;
			if (profession != _o_.profession) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += round;
		_h_ += profession;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(round).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetBattleRound _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = round - _o_.round;
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

