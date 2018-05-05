
package gnet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __VerifyLoginActivationCode__ extends xio.Rpc<gnet.VerifyLoginActivationCodeArg, gnet.VerifyLoginActivationCodeRes> { }
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class VerifyLoginActivationCode extends __VerifyLoginActivationCode__ {
	@Override
	protected void onServer() {
        getResult().err.code = ActivationCodeErr.ERR_INTERNAL;
		new xauany.code.PVerifyLoginActivationCode(getArgument(), getResult()).call();
	}

	@Override
	protected void onClient() {
		// response handle
	}

	@Override
	protected void onTimeout(int code) {
		// client only. 当使用 submit 方式调用 rpc 时，不会产生这个回调。
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public int getType() {
		return 143;
	}

	public VerifyLoginActivationCode() {
		super.setArgument(new gnet.VerifyLoginActivationCodeArg());
		super.setResult(new gnet.VerifyLoginActivationCodeRes());
	}

	public VerifyLoginActivationCode(gnet.VerifyLoginActivationCodeArg argument) {
		super.setArgument(argument);
		super.setResult(new gnet.VerifyLoginActivationCodeRes());
	}

	public int getTimeout() {
		return 1000 * 20;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}
}

