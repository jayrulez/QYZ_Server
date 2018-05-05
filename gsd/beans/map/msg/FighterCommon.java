
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FighterCommon implements Marshal {
	public int isdead;
	public int isrevive;
	public int isborn;
	public int camp;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;
	public map.msg.Vector3 targetposition;
	public java.util.HashMap<Integer,Integer> skills;
	public java.util.HashMap<Integer,Float> attrs;

	public FighterCommon() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
		targetposition = new map.msg.Vector3();
		skills = new java.util.HashMap<Integer,Integer>();
		attrs = new java.util.HashMap<Integer,Float>();
	}

	public FighterCommon(int _isdead_, int _isrevive_, int _isborn_, int _camp_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_, map.msg.Vector3 _targetposition_, java.util.HashMap<Integer,Integer> _skills_, java.util.HashMap<Integer,Float> _attrs_) {
		this.isdead = _isdead_;
		this.isrevive = _isrevive_;
		this.isborn = _isborn_;
		this.camp = _camp_;
		this.position = _position_;
		this.orient = _orient_;
		this.targetposition = _targetposition_;
		this.skills = _skills_;
		this.attrs = _attrs_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		if (!targetposition._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(isdead);
		_os_.marshal(isrevive);
		_os_.marshal(isborn);
		_os_.marshal(camp);
		_os_.marshal(position);
		_os_.marshal(orient);
		_os_.marshal(targetposition);
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(attrs.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : attrs.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		isdead = _os_.unmarshal_int();
		isrevive = _os_.unmarshal_int();
		isborn = _os_.unmarshal_int();
		camp = _os_.unmarshal_int();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		targetposition.unmarshal(_os_);
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			skills.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			attrs.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FighterCommon) {
			FighterCommon _o_ = (FighterCommon)_o1_;
			if (isdead != _o_.isdead) return false;
			if (isrevive != _o_.isrevive) return false;
			if (isborn != _o_.isborn) return false;
			if (camp != _o_.camp) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (!targetposition.equals(_o_.targetposition)) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (!attrs.equals(_o_.attrs)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += isdead;
		_h_ += isrevive;
		_h_ += isborn;
		_h_ += camp;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += targetposition.hashCode();
		_h_ += skills.hashCode();
		_h_ += attrs.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(isdead).append(",");
		_sb_.append(isrevive).append(",");
		_sb_.append(isborn).append(",");
		_sb_.append(camp).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(targetposition).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(attrs).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

