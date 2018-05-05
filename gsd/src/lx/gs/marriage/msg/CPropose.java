
package lx.gs.marriage.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gnet.link.Onlines;
import gs.AProcedure;
import lx.gs.friend.FFriend;
import lx.gs.marriage.FMarriage;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPropose__ extends xio.Protocol { }

/** 开始求婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPropose extends __CPropose__ {
	@Override
	protected void process() {
		new AProcedure<CPropose>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(Onlines.getInstance().find(beproposedroleid) == null){//如果对方不在线
					return error(ErrorCode.ROLE_NOT_ONLINE);
				}

				//如果没有彩礼
				final int cailiType = FMarriage.cailiType(roleid);
				if(cailiType == 0){
					return error(ErrorCode.NOT_HAVE_CAILI);
				}
				if(cailiType == FMarriage.NORMAL_CAILI && proposeoath.length() > 0){
					return error(ErrorCode.ONLY_LUXURY_WIRTE_OATH);
				}
				if(proposeoath.length() > CfgMgr.marrigeconfig.marrigetextlength){//超过字数限制
					return error(ErrorCode.TEXT_OVERLENT);
				}
				xbean.RoleMarriage marryInfo = FMarriage.getMarriageInfo(roleid);
				marryInfo.setCurproposeid(beproposedroleid);
				marryInfo.setStartproposetime(System.currentTimeMillis());
				SBeproposed notify = new SBeproposed();
				notify.proposeroleid = roleid;
                notify.proposerolename = xtable.Roleinfos.selectName(roleid);
				notify.proposetype = cailiType == FMarriage.NORMAL_CAILI?  0 : 1;
				notify.proposeoath = proposeoath;
				tsendWhileCommit(beproposedroleid, notify);//通知被求婚者
                response(notify);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581170;

	public int getType() {
		return 6581170;
	}

	public long beproposedroleid; // 被求婚的id
	public java.lang.String proposeoath; // 求婚宣言，只有豪华彩礼才能自定义誓词

	public CPropose() {
		proposeoath = "";
	}

	public CPropose(long _beproposedroleid_, java.lang.String _proposeoath_) {
		this.beproposedroleid = _beproposedroleid_;
		this.proposeoath = _proposeoath_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beproposedroleid);
		_os_.marshal(proposeoath, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beproposedroleid = _os_.unmarshal_long();
		proposeoath = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPropose) {
			CPropose _o_ = (CPropose)_o1_;
			if (beproposedroleid != _o_.beproposedroleid) return false;
			if (!proposeoath.equals(_o_.proposeoath)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)beproposedroleid;
		_h_ += proposeoath.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beproposedroleid).append(",");
		_sb_.append("T").append(proposeoath.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

