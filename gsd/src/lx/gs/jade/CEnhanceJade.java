
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnhanceJade__ extends xio.Protocol { }

/** 玉佩培养
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnhanceJade extends lx.gs.jade.__CEnhanceJade__ {
	@Override
	protected void process() {
		new AProcedure<CEnhanceJade>(this){

			@Override
			protected boolean doProcess() throws Exception {
				cfg.equip.JadeEnhance conf = cfg.CfgMgr.jadeenhance;
				cfg.equip.JadeEnhanceData enhancedata = conf.enhancedata_enhancetypeid.get(param.enhancetypeid);
				xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
				int realnum = 0;
				ErrorCode err = null;
				int addbonus = rjade.getBonus();
				while(realnum < param.num) {
					if(!new xdb.Procedure() {
						@Override
						protected boolean process() {
							return FCondition.checkByReflection(roleid, enhancedata, By.Enhance_Jade).ok()
									&& FCondition.checkA(roleid, conf.enhanceitemid, 1, By.Enhance_Jade, 0, 0).ok();
						}
					}.call())
						break;
					int bonus = FJade.calRandomBonus(enhancedata);
					addbonus = FJade.addJadeBonus(roleid, bonus);
					realnum++;
				}
				
				if(realnum==0){
					return error(err);
				} else {
					SEnhanceJade result = new SEnhanceJade();
					result.enhancetypeid = param.enhancetypeid;
					result.addbonus = addbonus;
					result.num = realnum;
					response(result);
				}	
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581077;

	public int getType() {
		return 6581077;
	}

	public int enhancetypeid;
	public int num;

	public CEnhanceJade() {
	}

	public CEnhanceJade(int _enhancetypeid_, int _num_) {
		this.enhancetypeid = _enhancetypeid_;
		this.num = _num_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(enhancetypeid);
		_os_.marshal(num);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		enhancetypeid = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnhanceJade) {
			CEnhanceJade _o_ = (CEnhanceJade)_o1_;
			if (enhancetypeid != _o_.enhancetypeid) return false;
			if (num != _o_.num) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += enhancetypeid;
		_h_ += num;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(enhancetypeid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnhanceJade _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = enhancetypeid - _o_.enhancetypeid;
		if (0 != _c_) return _c_;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

