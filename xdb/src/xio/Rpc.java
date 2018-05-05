package xio;

import org.w3c.dom.Element;

import xdb.Executor;
import xdb.Procedure;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.Marshal;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 远程调用的简单实现。
 * 
 * 这个对象本身设计上不是线程安全的，原则上不允许多线程同时使用同一个实例。
 * 内部采用不同的管理期达到多线程的安全性。
 *    1 发送请求：创建rpc，填写参数，发送请求都只在一个线程中执行。
 *    2 结果处理：此时有timeout线程，或者io线程，此时通过上下文来实现线程互斥：
 *              timeout,io在都通过线程安全的上下文管理器得到实例，只有一个线程能得到实例。
 */
public abstract class Rpc<A extends Marshal, R extends Marshal>
		extends Protocol implements java.io.Closeable, xdb.Procedure.Done<xdb.Procedure> {

	public static interface IOnClient<A extends Marshal, R extends Marshal> {
		public void onTimeout(Rpc<A, R> rpc, int code);
		public void onClient(Rpc<A, R> rpc);
	}

	private boolean isRequest;
	private int sid;
	private A argument;
	private R result;
	private IOnClient<A, R> iOnClient;

	// 处理上下文，submit调用方式创建，在调用时创建，的过程中，多线程调用
	private FutureX<R> future;

	public final int getSid() {
		return sid;
	}

	public final A getArgument() {
		return argument;
	}

	public final R getResult() {
		return result;
	}

	public IOnClient<A, R> getIOnClient() {
		return iOnClient;
	}

	public final Rpc<A, R> setIOnClient(IOnClient<A, R> iOnClient) {
		this.iOnClient = iOnClient;
		return this;
	}

	public final Rpc<A, R> setArgument(A argument) {
		this.argument = argument;
		return this;
	}

	public final Rpc<A, R> setResult(R result) {
		this.result = result;
		return this;
	}

	public final boolean isRequest() {
		return isRequest;
	}

	public final Rpc<A, R> setRequest(boolean isRequest) {
		this.isRequest = isRequest;
		return this;
	}

	@Override
	public void close() {
		if (null != future)
			future.setException(new IOError("AsynchronousClose"));
		else
			_onTimeout(-1); // 异步关闭时，使用onTimeout报告错误。
	}

	private final void _onTimeout(final int code) {
		if (null != this.iOnClient)
			this.iOnClient.onTimeout(this, code);
		else
			this.onTimeout(code);
	}

	private final void _onClient() {
		if (null != this.iOnClient)
			this.iOnClient.onClient(this);
		else
			this.onClient();
	}

	//////////////////////////////////////////////////////////////
	/**
	 * 异步处理接口，如果使用 submit 方式调用 rpc，不会产生这个回调。
	 * 当客户端使用异步方式调用RPC时，需要实现。
	 * -1 异步关闭(AsynchronousClose)
	 * -2 没有响应超时(timeout)
	 */ 
	protected void onTimeout(final int code) {
		// 当客户端使用异步方式调用RPC时，需要实现。
	}

	protected void onServer() {
		throw new UnsupportedOperationException("onServer of " + getClass().getName());
	}

	protected void onClient() {
		throw new UnsupportedOperationException("onClient of " + getClass().getName());
	}

	public Rpc() {
	}

	/**
	 * 异步调用或者同步调用默认的超时。
	 * 单位毫秒。
	 */
	public int getTimeout() {
		return 2000;
	}

	////////////////////////////////////////////
	// 调用接口
	private static class Timeout implements Runnable {
		private Manager manager;
		private int sid;

		Timeout(Manager manager, int sid) {
			this.manager = manager;
			this.sid = sid;
		}

		public void run() {
			java.io.Closeable c = manager.removeContext(sid);
			if (null != c && c instanceof Rpc<?, ?>) {
				@SuppressWarnings("rawtypes")
				Rpc rpc = (Rpc)c;
				rpc._onTimeout(-2);
			}
		}
	}

	@Override
	public boolean send(Xio to) {
		if (null == to)
			return false;
		isRequest = true;
		Manager manager = to.getCreator().getManager();
		sid = manager.addContext(this);
		boolean rc = super.checkSend(to, new OctetsStream().marshal(this));
		if (rc && null == future) // 异步调用方式的超时检查。非 submit 方式。
			Executor.getInstance().schedule(new Timeout(manager, sid), getTimeout(), TimeUnit.MILLISECONDS);
		return rc;
	}

	public final FutureX<R> submit(Xio to) {
		if (null != to) {
			future = new FutureX<R>(this.getTimeout());
			if (send(to))
				return future;
			future = null;
		}
		throw new IOError(this.toString() + to);
	}

	public final FutureX<R> submit(Manager manager) {
		return submit(manager.get());
	}

	//////////////////////////////////////////////////////////////////
	// 内部实现
	/**
	 * 用来传递结果给同步等待的线程。
	 * 通过继承获得保护接口的调用权利。
	 * 修改默认实现的超时设置。
	 * 
	 * 从不会真正去执行这个FutureTask的实例。
	 */
	public static class FutureX<R> extends FutureTask<R> {
		private static Runnable dummy = new Runnable() { public void run() { } };
		private int defaulttimeout;

		public FutureX(int timeout) {
			super(dummy, null);
			this.defaulttimeout = timeout;
		}

		@Override
		public R get() {
			return get(defaulttimeout, TimeUnit.MILLISECONDS);
		}

		@Override
		public R get(long timeout, TimeUnit unit) {
			try {
				return super.get(timeout, unit);
			} catch (Exception e) {
				throw new IOError(e);
			}
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			throw new UnsupportedOperationException();
		}

		@Override
		protected void set(R v) {
			super.set(v);
		}

		@Override
		protected void setException(Throwable t) {
			super.setException(t);
		}
	}

	/**
	 * 检查存储过程和rpc的参数类型可以匹配。
	 */
	public void checkProcedure(xdb.Procedure p) {
		@SuppressWarnings("unchecked")
		xio.rpc.Procedure<A, R> r = (xio.rpc.Procedure<A, R>)p; // check subclass
		r.setArgument(argument); // check argument type
		r.setResult(result); // check result type
	}

	public static class Stub extends Protocol.Stub {
		private Class<xdb.Procedure> onServerProcedure;

		public xdb.Procedure newProcedure() throws Exception {
			if (null != onServerProcedure)
				return onServerProcedure.newInstance();
			return null;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Stub(Element self) throws Exception {
			super(self);
			String onServer = self.getAttribute("onServer");
			if (!onServer.isEmpty()) {
				onServerProcedure = (Class<xdb.Procedure>)Class.forName(onServer);
				((Rpc)newInstance()).checkProcedure(onServerProcedure.newInstance());
			}
		}
	}

	@Override
	public final OctetsStream marshal(OctetsStream os) {
		if (isRequest)
			return os.marshal(sid | 0x80000000).marshal(argument);
		return os.marshal(sid).marshal(result);
	}

	@Override
	public final OctetsStream unmarshal(OctetsStream os) throws MarshalException {
		sid = os.unmarshal_int();
		isRequest = (sid & 0x80000000) != 0;
		if (isRequest) {
			sid = sid & 0x7fffffff; // clear request mask
			return argument.unmarshal(os);
		}
		return  result.unmarshal(os);
	}

	@Override
	public void dispatch(Protocol.Stub _stub) throws Exception {
		Stub stub = (Stub)_stub;
		if (isRequest) {
			xdb.Procedure p = stub.newProcedure();
			if (null != p) {
				@SuppressWarnings("unchecked")
				xio.rpc.Procedure<A, R> rp = (xio.rpc.Procedure<A, R>)p;
				// 传递参数(包括结果)引用给存储过程，并且启动
				rp.setArgument(argument);
				rp.setResult(result);
				rp.setConnection(getConnection());
				if (xdb.Trace.isDebugEnabled())
					xdb.Trace.debug("xio.Rpc.request " + this.str() + " Procedure=" + p.getClass().getName());
				xdb.Procedure.execute(p, this);
				return;
			}
			if (xdb.Trace.isDebugEnabled())
				xdb.Trace.debug("xio.Rpc.request execute " + this.str());
			getManager().execute(this);
			return;
		}
		// response
		Rpc<A, R> rpc = getConnection().getCreator().getManager().removeContext(sid, this);
		if (null == rpc) {
			xdb.Trace.info("xio.Rpc.response context lost! " + this.str());
			return;
		}
		rpc.setRequest(false).setResult(result);
		if (null != rpc.future) {
			if (xdb.Trace.isDebugEnabled())
				xdb.Trace.debug("xio.Rpc.response with submit " + rpc.str());
			rpc.future.set(rpc.result);
			return;
		}

		if (xdb.Trace.isDebugEnabled())
			xdb.Trace.debug("xio.Rpc.response execute " + this.str());
		getManager().execute(rpc);
	}

	@Override
	public final void doDone(Procedure p) {
		isRequest = false;
		checkSend(getConnection(), new OctetsStream().marshal(this));
	}

	@Override
	protected final void process() {
		if (isRequest) {
			onServer();
			isRequest = false;
			checkSend(getConnection(), new OctetsStream().marshal(this));
		} else {
			_onClient();
		}
	}

	@Override
	public String toString() {
		return "(" + argument + ", " + result + ")";
	}
}
