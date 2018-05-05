
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import gs.Utils;
import lx.gs.bag.FBag;
import lx.gs.bag.StorageBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBatchSell__ extends xio.Protocol { }

/** 批量卖出
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBatchSell extends __CBatchSell__ {
	@Override
	@SuppressWarnings("unchecked")
	protected void process() {
		new AProcedure<CBatchSell>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				StorageBag bag = FBag.getStorageBag(roleid, bagtype);
				if(bag == null || Utils.isNull(posset)){
					return error(ErrorCode.PARAM_ERROR);
				}
				ErrorCode errorCode = bag.batchSell(posset);
				if(errorCode.err()){
					return error(errorCode);
				}
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584149;

	public int getType() {
		return 6584149;
	}

	public int bagtype;
	public java.util.HashSet<Integer> posset; // 位置集合

	public CBatchSell() {
		posset = new java.util.HashSet<Integer>();
	}

	public CBatchSell(int _bagtype_, java.util.HashSet<Integer> _posset_) {
		this.bagtype = _bagtype_;
		this.posset = _posset_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.compact_uint32(posset.size());
		for (Integer _v_ : posset) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			posset.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBatchSell) {
			CBatchSell _o_ = (CBatchSell)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (!posset.equals(_o_.posset)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += posset.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(posset).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

