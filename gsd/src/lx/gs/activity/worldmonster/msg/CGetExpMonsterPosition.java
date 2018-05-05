
package lx.gs.activity.worldmonster.msg;

import cfg.CfgMgr;
import cfg.map.Vector2;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gs.AProcedure;
import lx.gs.activity.worldmonster.WorldMonster;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetExpMonsterPosition__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetExpMonsterPosition extends __CGetExpMonsterPosition__ {
	@Override
	protected void process() {
        new AProcedure<CGetExpMonsterPosition>(this){
            @Override
            protected boolean doProcess() throws Exception {
                int roleLevel = xtable.Roleinfos.selectLevel(roleid);
                SGetExpMonsterPosition response = new SGetExpMonsterPosition();
                for(WorldMonster.ExpMonsterToMapInfo info : WorldMonster.minLevelToMapid.values()){
                    if(roleLevel >= info.minLevel && roleLevel <= info.maxLevel){
                       cfg.ectype.ExpMonsterRef positions = CfgMgr.expmonster.monstermsg.get(info.levelindex).monsterref.get(info.positionindex);
                        int randIndex = common.Utils.randomRange(0, positions.refreshopint.size());
                        Vector2 xy = positions.refreshopint.get(randIndex).position;
                        response.mapid = info.mapid;
                        response.xposition = xy.x;
                        response.zposition = xy.y;
                        response(response);
                        break;
                    }
                }
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573867;

	public int getType() {
		return 6573867;
	}


	public CGetExpMonsterPosition() {
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
		if (_o1_ instanceof CGetExpMonsterPosition) {
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

	public int compareTo(CGetExpMonsterPosition _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

