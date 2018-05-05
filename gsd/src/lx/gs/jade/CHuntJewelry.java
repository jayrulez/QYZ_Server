
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;
import xdb.Xdb;

import java.util.Random;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CHuntJewelry__ extends xio.Protocol { }

/** 猎取宝珠
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CHuntJewelry extends lx.gs.jade.__CHuntJewelry__ {
	@Override
	protected void process() {
		new AProcedure<CHuntJewelry>(this){

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);
				cfg.equip.JadeEnhance conf = cfg.CfgMgr.jadeenhance;
				
				SHuntJewelry result = new SHuntJewelry();
				int realnum = 0;
				java.util.List<Integer> jewelryidlist = new java.util.ArrayList<>();
				java.util.List<xbean.Jewelry> jewelrylist = new java.util.ArrayList<>();
				int newgetlevel = jadeinfo.getJewelrygetlevel();
				ErrorCode err = null;
				while(realnum<param.num){
					err = FCondition.checkA(roleid, conf.searchcost, 1, By.Hunt_Jewelry, 0, 0);
					if(err!=ErrorCode.OK){
						break;
					}
					cfg.equip.JewelryGet getConf = cfg.CfgMgr.jewelryget.get(newgetlevel);
					int quality = FJade.getRandomQuality(getConf.ratelist);
					jewelryidlist.clear();
					for(java.util.Map.Entry<Integer, cfg.equip.Jewelry> e: cfg.CfgMgr.jewelry.entrySet()){
						if(e.getValue().quality == quality){
							jewelryidlist.add(e.getKey());
						}
					}
					Random rand = Xdb.random();
					int index = rand.nextInt(jewelryidlist.size());
					int jewelryid = jewelryidlist.get(index);
					
					xbean.Jewelry newjewelry = FJade.generateJewelry(roleid, jewelryid);
					jewelrylist.add(newjewelry);
					newgetlevel += FJade.getRandomTrans(getConf.uprate, getConf.downrate);
					jadeinfo.setJewelrygetlevel(newgetlevel);
					realnum++;
				}
				
				if(realnum==0){
					return error(err);
				}else{
					result.jewelrygetlevel = newgetlevel;
					result.num = realnum;
					for(xbean.Jewelry j: jewelrylist){
						result.jewelrylist.add(FJade.makeProtocolJewelry(j));
					}
				}
				response(result);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583949;

	public int getType() {
		return 6583949;
	}

	public int num; // 猎取次数

	public CHuntJewelry() {
	}

	public CHuntJewelry(int _num_) {
		this.num = _num_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(num);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		num = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CHuntJewelry) {
			CHuntJewelry _o_ = (CHuntJewelry)_o1_;
			if (num != _o_.num) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += num;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CHuntJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

