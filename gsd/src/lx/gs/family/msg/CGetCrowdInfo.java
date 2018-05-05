
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.family.FFamily;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetCrowdInfo__ extends xio.Protocol { }

/** 获取众筹信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetCrowdInfo extends __CGetCrowdInfo__ {
	@Override
	protected void process() {
//		new AProcedure<CGetCrowdInfo>(this) {
//
//			@Override
//			protected boolean doProcess() throws Exception {
//				if(familyname == null || familyname.trim().length() == 0){//输出所有的信息
//					FFamily.sendCrowdInfo(roleid,FFamily.getCrowdFamilyNames().values());
//				}else {//否则按输入的name进行查找
//					String name = familyname.trim();
//					List<Long> findIds = FFamily.findFamidsByname(name, FFamily.getCrowdFamilyNames());
//					FFamily.sendCrowdInfo(roleid, findIds);
//				}
//				return true;
//			}
//
//		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568798;

	public int getType() {
		return 6568798;
	}

	public java.lang.String familyname; // 如果为空，那么输出所有的众筹家族信息

	public CGetCrowdInfo() {
		familyname = "";
	}

	public CGetCrowdInfo(java.lang.String _familyname_) {
		this.familyname = _familyname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetCrowdInfo) {
			CGetCrowdInfo _o_ = (CGetCrowdInfo)_o1_;
			if (!familyname.equals(_o_.familyname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += familyname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

