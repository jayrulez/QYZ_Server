
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.mail.FMail;
import lx.gs.marriage.FMarriage;
import lx.gs.notice.FNotice;
import xdb.Lockeys;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDivorceWithBook__ extends xio.Protocol { }

/** 使用休书与对象离婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDivorceWithBook extends __CDivorceWithBook__ {
	@Override
	protected void process() {
		new AProcedure<CDivorceWithBook>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, bedivorceroleid)); //都先进行加锁操作 
				xbean.RoleMarriage divorceRole = FMarriage.getMarriageInfo(roleid);
				xbean.RoleMarriage bedivorceRole = FMarriage.getMarriageInfo(bedivorceroleid);
				if(divorceRole.getCoupleroleid() != bedivorceroleid || bedivorceRole.getCoupleroleid() != roleid){//如果传的id和服务器存的伴侣id不匹配
					return error(ErrorCode.NOT_YOUR_WIFE_OR_HUSBAND);
				}
				if(bookcontent.length() > CfgMgr.marrigeconfig.divorcetextlength){
					return error(ErrorCode.TEXT_OVERLENT);
				}
				
				if(!FItem.spendItemBindFirst(roleid, FMarriage.XIUSHU, 1, By.Divorce)){//直接消耗休书，如果有，返回true
					return error(ErrorCode.NOT_HAVE_XIUSHU);
				}
				divorceRole.setCoupleroleid(0L);
				bedivorceRole.setCoupleroleid(0L);
                FFriend.clearBanlvInfo(roleid, bedivorceroleid);//清除脉脉里面的伴侣信息
				SBedivorcedNotify notifyDivorce = new SBedivorcedNotify();
				notifyDivorce.bookcontent = bookcontent;
                notifyDivorce.divorcetime = System.currentTimeMillis();
				notifyDivorce.divorceroleid = roleid;
				notifyDivorce.divorcerolename = xtable.Roleinfos.selectName(roleid);
				FNotice.sendNotice(bedivorceroleid, notifyDivorce);
                String divorceContent = notifyDivorce.divorcerolename + "对你说:" + bookcontent;
                FMail.addMail(bedivorceroleid, FMarriage.DIVORCE_MAIL_ID, divorceContent);
                SDivorceWithBook response = new SDivorceWithBook();
				response.bedivorceroleid = bedivorceroleid;
                response.bedivorcerolename = xtable.Roleinfos.selectName(bedivorceroleid);
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582102;

	public int getType() {
		return 6582102;
	}

	public long bedivorceroleid; // 被离婚对象的id
	public java.lang.String bookcontent; // 休书内容

	public CDivorceWithBook() {
		bookcontent = "";
	}

	public CDivorceWithBook(long _bedivorceroleid_, java.lang.String _bookcontent_) {
		this.bedivorceroleid = _bedivorceroleid_;
		this.bookcontent = _bookcontent_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bedivorceroleid);
		_os_.marshal(bookcontent, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bedivorceroleid = _os_.unmarshal_long();
		bookcontent = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDivorceWithBook) {
			CDivorceWithBook _o_ = (CDivorceWithBook)_o1_;
			if (bedivorceroleid != _o_.bedivorceroleid) return false;
			if (!bookcontent.equals(_o_.bookcontent)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bedivorceroleid;
		_h_ += bookcontent.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bedivorceroleid).append(",");
		_sb_.append("T").append(bookcontent.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

