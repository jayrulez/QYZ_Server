
package lx.gs.activity.huiwu.msg;

import cfg.huiwu.BattleState;
import cfg.huiwu.RoundStage;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import lx.gs.map.FMap;
import xbean.HuiWuRound;
import xdb.Trace;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnterBattleEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnterBattleEctype extends __CEnterBattleEctype__ {
	@Override
	protected void process() {
		new AProcedure<CEnterBattleEctype>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.HuiWuCurTerm info = FHuiWu.getCurTermInfo();
                if(info.getRoundstage() != RoundStage.ROUND_PREPARE)
                    return false;
                final int profession = xtable.Roleinfos.selectProfession(roleid);
                final Map<Integer, HuiWuRound> rounds = info.getTerminfobyprofession().get(profession).getRounds();
                final xbean.HuiWuBattle battle = rounds.get(rounds.size()).getBattles().stream()
                        .filter(b -> b.getState() == BattleState.NULL && (b.getRoleid1() == roleid || b.getRoleid2() == roleid))
                        .findFirst().get();
                if(battle.getMapid() == 0) {
                    Trace.error("CEnterBattleEctype. Battle ectype not create!");
                    return false;
                }
                FMap.enterMapInProcedure(roleid, battle.getMapid());
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586147;

	public int getType() {
		return 6586147;
	}


	public CEnterBattleEctype() {
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
		if (_o1_ instanceof CEnterBattleEctype) {
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

	public int compareTo(CEnterBattleEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

