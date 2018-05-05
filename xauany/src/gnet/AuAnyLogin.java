
package gnet;

import xauany.PlatManager;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AuAnyLogin__ extends xio.Rpc<gnet.AuAnyLoginArg, gnet.AuAnyLoginRes> { }
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AuAnyLogin extends __AuAnyLogin__ {
	@Override
	protected void onServer() {
		PlatManager.onAuAnyLogin(getConnection(), getArgument(), getResult());
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
		return 140;
	}

	public AuAnyLogin() {
		super.setArgument(new gnet.AuAnyLoginArg());
		super.setResult(new gnet.AuAnyLoginRes());
	}

	public AuAnyLogin(gnet.AuAnyLoginArg argument) {
		super.setArgument(argument);
		super.setResult(new gnet.AuAnyLoginRes());
	}

	public int getTimeout() {
		return 1000 * 20;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}
}

