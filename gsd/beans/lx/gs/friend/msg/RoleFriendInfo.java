
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleFriendInfo implements Marshal {
	public java.util.HashMap<Long,Integer> idolfrienddegree; // 跟偶像的友好度，key为偶像的id
	public java.util.HashMap<Long,lx.gs.friend.msg.IdolAwardClaim> idolawardclaiminfo; // 偶像奖励的领取情况，key为偶像id，value为领取情况
	public java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList> relations;
	public int allowfriendgetmm; // 是否允许好友查看脉脉
	public int allowstrangergetmm; // 是否允许陌生人查看脉脉

	public RoleFriendInfo() {
		idolfrienddegree = new java.util.HashMap<Long,Integer>();
		idolawardclaiminfo = new java.util.HashMap<Long,lx.gs.friend.msg.IdolAwardClaim>();
		relations = new java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList>();
	}

	public RoleFriendInfo(java.util.HashMap<Long,Integer> _idolfrienddegree_, java.util.HashMap<Long,lx.gs.friend.msg.IdolAwardClaim> _idolawardclaiminfo_, java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList> _relations_, int _allowfriendgetmm_, int _allowstrangergetmm_) {
		this.idolfrienddegree = _idolfrienddegree_;
		this.idolawardclaiminfo = _idolawardclaiminfo_;
		this.relations = _relations_;
		this.allowfriendgetmm = _allowfriendgetmm_;
		this.allowstrangergetmm = _allowstrangergetmm_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.IdolAwardClaim> _e_ : idolawardclaiminfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Integer, lx.gs.friend.msg.MMInfoList> _e_ : relations.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(idolfrienddegree.size());
		for (java.util.Map.Entry<Long, Integer> _e_ : idolfrienddegree.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(idolawardclaiminfo.size());
		for (java.util.Map.Entry<Long, lx.gs.friend.msg.IdolAwardClaim> _e_ : idolawardclaiminfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(relations.size());
		for (java.util.Map.Entry<Integer, lx.gs.friend.msg.MMInfoList> _e_ : relations.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(allowfriendgetmm);
		_os_.marshal(allowstrangergetmm);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			int _v_;
			_v_ = _os_.unmarshal_int();
			idolfrienddegree.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.friend.msg.IdolAwardClaim _v_ = new lx.gs.friend.msg.IdolAwardClaim();
			_v_.unmarshal(_os_);
			idolawardclaiminfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.friend.msg.MMInfoList _v_ = new lx.gs.friend.msg.MMInfoList();
			_v_.unmarshal(_os_);
			relations.put(_k_, _v_);
		}
		allowfriendgetmm = _os_.unmarshal_int();
		allowstrangergetmm = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleFriendInfo) {
			RoleFriendInfo _o_ = (RoleFriendInfo)_o1_;
			if (!idolfrienddegree.equals(_o_.idolfrienddegree)) return false;
			if (!idolawardclaiminfo.equals(_o_.idolawardclaiminfo)) return false;
			if (!relations.equals(_o_.relations)) return false;
			if (allowfriendgetmm != _o_.allowfriendgetmm) return false;
			if (allowstrangergetmm != _o_.allowstrangergetmm) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += idolfrienddegree.hashCode();
		_h_ += idolawardclaiminfo.hashCode();
		_h_ += relations.hashCode();
		_h_ += allowfriendgetmm;
		_h_ += allowstrangergetmm;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(idolfrienddegree).append(",");
		_sb_.append(idolawardclaiminfo).append(",");
		_sb_.append(relations).append(",");
		_sb_.append(allowfriendgetmm).append(",");
		_sb_.append(allowstrangergetmm).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

