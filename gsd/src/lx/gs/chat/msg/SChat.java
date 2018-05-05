
package lx.gs.chat.msg;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

abstract class __SChat__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChat extends __SChat__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554003;

	public int getType() {
		return 6554003;
	}

	public int channel; // 定义见cfg.chat.ChannelType
	public com.goldhuman.Common.Octets content; // 根据channel不同,content格式也不同

	public SChat() {
		content = new com.goldhuman.Common.Octets();
	}

	public SChat(int _channel_, com.goldhuman.Common.Octets _content_) {
		this.channel = _channel_;
		this.content = _content_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(channel);
		_os_.marshal(content);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		channel = _os_.unmarshal_int();
		content = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChat) {
			SChat _o_ = (SChat)_o1_;
			if (channel != _o_.channel) return false;
            return content.equals(_o_.content);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += channel;
		_h_ += content.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(channel).append(",");
		_sb_.append("B").append(content.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

