
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTransferWorldUseItem__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTransferWorldUseItem extends __CTransferWorldUseItem__ {
	@Override
	protected void process() {
		new AProcedure<CTransferWorldUseItem>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				ErrorCode err = FCondition.checkByReflection(roleid, CfgMgr.transport, 1, By.World_Transport, ConfigId.TRANSPORT, 0);
				if(err.err())
					return error(err);
				err = FMap.transferWorld(roleid, worldid, position, FMap.createOrient(0));
				if(err.err())
					return error(err);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585812;

	public int getType() {
		return 6585812;
	}

	public int worldid;
	public map.msg.Vector3 position;

	public CTransferWorldUseItem() {
		position = new map.msg.Vector3();
	}

	public CTransferWorldUseItem(int _worldid_, map.msg.Vector3 _position_) {
		this.worldid = _worldid_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldid);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldid = _os_.unmarshal_int();
		position.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CTransferWorldUseItem) {
			CTransferWorldUseItem _o_ = (CTransferWorldUseItem)_o1_;
			if (worldid != _o_.worldid) return false;
			if (!position.equals(_o_.position)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldid;
		_h_ += position.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

