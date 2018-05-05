
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterStoryEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterStoryEctype extends __SEnterStoryEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684809;

	public int getType() {
		return 6684809;
	}

	public long id;
	public int ectypeid;
	public long remaintime;
	public int remainrevivecount; // 剩余复活次数
	public java.util.HashMap<Integer,Integer> enviroments;
	public java.util.ArrayList<map.msg.LayoutInfo> openlayouts;
	public java.util.ArrayList<Integer> activeactions;

	public SEnterStoryEctype() {
		enviroments = new java.util.HashMap<Integer,Integer>();
		openlayouts = new java.util.ArrayList<map.msg.LayoutInfo>();
		activeactions = new java.util.ArrayList<Integer>();
	}

	public SEnterStoryEctype(long _id_, int _ectypeid_, long _remaintime_, int _remainrevivecount_, java.util.HashMap<Integer,Integer> _enviroments_, java.util.ArrayList<map.msg.LayoutInfo> _openlayouts_, java.util.ArrayList<Integer> _activeactions_) {
		this.id = _id_;
		this.ectypeid = _ectypeid_;
		this.remaintime = _remaintime_;
		this.remainrevivecount = _remainrevivecount_;
		this.enviroments = _enviroments_;
		this.openlayouts = _openlayouts_;
		this.activeactions = _activeactions_;
	}

	public final boolean _validator_() {
		for (map.msg.LayoutInfo _v_ : openlayouts)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(ectypeid);
		_os_.marshal(remaintime);
		_os_.marshal(remainrevivecount);
		_os_.compact_uint32(enviroments.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : enviroments.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(openlayouts.size());
		for (map.msg.LayoutInfo _v_ : openlayouts) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(activeactions.size());
		for (Integer _v_ : activeactions) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		ectypeid = _os_.unmarshal_int();
		remaintime = _os_.unmarshal_long();
		remainrevivecount = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			enviroments.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.LayoutInfo _v_ = new map.msg.LayoutInfo();
			_v_.unmarshal(_os_);
			openlayouts.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			activeactions.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterStoryEctype) {
			SEnterStoryEctype _o_ = (SEnterStoryEctype)_o1_;
			if (id != _o_.id) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (remaintime != _o_.remaintime) return false;
			if (remainrevivecount != _o_.remainrevivecount) return false;
			if (!enviroments.equals(_o_.enviroments)) return false;
			if (!openlayouts.equals(_o_.openlayouts)) return false;
			if (!activeactions.equals(_o_.activeactions)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += ectypeid;
		_h_ += (int)remaintime;
		_h_ += remainrevivecount;
		_h_ += enviroments.hashCode();
		_h_ += openlayouts.hashCode();
		_h_ += activeactions.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(remaintime).append(",");
		_sb_.append(remainrevivecount).append(",");
		_sb_.append(enviroments).append(",");
		_sb_.append(openlayouts).append(",");
		_sb_.append(activeactions).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

