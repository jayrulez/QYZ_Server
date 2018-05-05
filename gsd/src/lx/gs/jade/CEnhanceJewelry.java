
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;

import java.util.Collections;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnhanceJewelry__ extends xio.Protocol { }

/** 宝珠升级
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnhanceJewelry extends lx.gs.jade.__CEnhanceJewelry__ {
	@Override
	protected void process() {
		new AProcedure<CEnhanceJewelry>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);

				xbean.Jewelry jewelry = null;
				if (param.index != 0) {
					jewelry = jadeinfo.getJewelrybag().get(param.index - 1);
				}
				if (param.position != 0) {
					jewelry = jadeinfo.getJewelry().get(param.position);
				}

				if (jewelry == null) {
					if (param.index != 0) {
						return error(ErrorCode.ERROR_JEWELRY_INDEX);
					}
					if (param.position != 0) {
						return error(ErrorCode.ERROR_JADE_POSITION);
					}
				}

				
				int addexp = 0;
				for (int index : param.doglist) {
					if(index == param.index){
						return error(ErrorCode.ERROR_JEWELRY_INDEX);
					}
					xbean.Jewelry dogjewelry = jadeinfo.getJewelrybag().get(index - 1);
					addexp += dogjewelry.getExp();
					addexp += cfg.CfgMgr.jewelry.get(dogjewelry.getId()).quatilyexp;
				}

				int realexp = jewelry.getExp() + addexp;
				int tempexp = realexp;
				int level = 1;
				for(int i=1; ; i++){
					if(!cfg.CfgMgr.jewelrylvlup.containsKey(i)){
						return error(ErrorCode.PARAM_ERROR);
					}
					if(tempexp < cfg.CfgMgr.jewelrylvlup.get(i).requireexp){
						level = i;
						break;
					}
					tempexp -= cfg.CfgMgr.jewelrylvlup.get(i).requireexp;
				}

				xbean.RoleInfo roleinfo = xtable.Roleinfos.get(roleid);
				cfg.equip.JewelryLvlLimit limitConf = cfg.CfgMgr.jewelrylvllimit.get(roleinfo.getLevel());
				int maxlevel = limitConf.jewelrylvl.get(cfg.CfgMgr.jewelry.get(jewelry.getId()).quality);

				if (level > maxlevel) {
					return error(ErrorCode.ERROR_JEWELRY_INDEX);
				}
				Collections.sort(param.doglist);
				int i = 0;
				int newindex = param.index;
				for (int index : param.doglist) {
					if(index < param.index){
						newindex-=1;
					}
					jadeinfo.getJewelrybag().remove(index-1-i);
					i++;
				}

				jewelry.setExp(realexp);
				jewelry.setLevel(level);

				SEnhanceJewelry result = new SEnhanceJewelry();
				result.jewelry = FJade.makeProtocolJewelry(jewelry);
				result.position = param.position;
				result.index = newindex;
				for (xbean.Jewelry j : jadeinfo.getJewelrybag()) {
					result.jewelrybag.add(FJade.makeProtocolJewelry(j));
				}
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564739;

	public int getType() {
		return 6564739;
	}

	public int position; // 要升级的装配上的宝珠位置
	public int index; // 要升级的背包里的宝珠索引
	public java.util.ArrayList<Integer> doglist;

	public CEnhanceJewelry() {
		doglist = new java.util.ArrayList<Integer>();
	}

	public CEnhanceJewelry(int _position_, int _index_, java.util.ArrayList<Integer> _doglist_) {
		this.position = _position_;
		this.index = _index_;
		this.doglist = _doglist_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(index);
		_os_.compact_uint32(doglist.size());
		for (Integer _v_ : doglist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position = _os_.unmarshal_int();
		index = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			doglist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnhanceJewelry) {
			CEnhanceJewelry _o_ = (CEnhanceJewelry)_o1_;
			if (position != _o_.position) return false;
			if (index != _o_.index) return false;
			if (!doglist.equals(_o_.doglist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position;
		_h_ += index;
		_h_ += doglist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(index).append(",");
		_sb_.append(doglist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
