
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAddEnemyNotify__ extends xio.Protocol { }

/** 增加仇人通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAddEnemyNotify extends __SAddEnemyNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576622;

	public int getType() {
		return 6576622;
	}

	public lx.gs.friend.msg.EnemyShowInfo enemy;

	public SAddEnemyNotify() {
		enemy = new lx.gs.friend.msg.EnemyShowInfo();
	}

	public SAddEnemyNotify(lx.gs.friend.msg.EnemyShowInfo _enemy_) {
		this.enemy = _enemy_;
	}

	public final boolean _validator_() {
		if (!enemy._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(enemy);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		enemy.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAddEnemyNotify) {
			SAddEnemyNotify _o_ = (SAddEnemyNotify)_o1_;
			if (!enemy.equals(_o_.enemy)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += enemy.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(enemy).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

