package xio.rpc;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

public final class LongInt implements Marshal {
	private long value;
	private int  code;

	@Override
	public OctetsStream marshal(final OctetsStream os) {
		return os.marshal(value).marshal(code);
	}

	@Override
	public OctetsStream unmarshal(final OctetsStream os) throws MarshalException {
		value = os.unmarshal_long();
		code = os.unmarshal_int();
		return os;
	}

	public LongInt() {
		
	}

	public LongInt(final long value, final int code) {
		this.value = value;
		this.code = code;
	}

	public long getValue() {
		return value;
	}

	public void setValue(final long value) {
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	@Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(value)+","+java.lang.String.valueOf(code);
	}
}
