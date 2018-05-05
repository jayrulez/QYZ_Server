
package lx.gs.chat.msg;

import cfg.chat.Const;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.chat.FChat;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xbean.RoleChat;

import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyChatFace__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyChatFace extends __CBuyChatFace__ {
	@Override
	protected void process() {
		new AProcedure<CBuyChatFace>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				RoleChat roleChat = FChat.get(roleid);
				Set<String> face  = roleChat.getChatface();
				if(face.contains(name.toUpperCase())){
					return error(ErrorCode.PARAM_ERROR);
				}

				if(!FRole.checkCostYuanBao(roleid, Const.FACE_PRICE, By.Chapter_Reward)){
					return error(ErrorCode.YUANBAO_NOT_ENOUGH);
				}
				face.add(name.toUpperCase());
				FChat.syncChatFace(roleid);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575092;

	public int getType() {
		return 6575092;
	}

	public java.lang.String name;

	public CBuyChatFace() {
		name = "";
	}

	public CBuyChatFace(java.lang.String _name_) {
		this.name = _name_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(name, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		name = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyChatFace) {
			CBuyChatFace _o_ = (CBuyChatFace)_o1_;
			if (!name.equals(_o_.name)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += name.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

