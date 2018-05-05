package xio;

import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.w3c.dom.Element;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 被动连接接受器。
 * 
 * @author lichenghua
 *
 */
public class Acceptor extends Creator implements Handle {
	// 属性都是初始化以后只读。不做线程保护。
	private int backlog;
	// 主线程读写，Engine线程读。
	private volatile SelectionKey server;

	@Override
	public void onClose(Xio xio, Throwable e) {
		super.getManager().removeXio(xio, e);
	}

	@Override
	public synchronized void close() {
		try {
			if (null != server) {
				server.channel().close();
				server = null; // 允许重新打开.
			}
		} catch (Throwable e) {
			// skip
		}
	}

	@Override
	public synchronized void open() {
		Engine.verify();
		try {
			if (null == server) {
				ServerSocketChannel ssc = ServerSocketChannel.open();
				ServerSocket ss = ssc.socket();
				ss.setReuseAddress(true);
				// xxx 只能设置到 ServerSocket 中，以后 Accept 的连接通过继承机制得到这个配置。
				ss.setReceiveBufferSize(super.getReceiveBufferSize());
				ss.bind(super.getAddress(), backlog);
				server = Engine.selector().register(ssc, SelectionKey.OP_ACCEPT, this);
			}
		} catch (Exception e) {
			throw new java.lang.IllegalStateException(e);
		}
	}

	////////////////////////////////////////////////////////////////
	// 事件处理
	@Override
	public void doHandle(SelectionKey key) throws Throwable {
		SocketChannel sc = null;
		try {
			ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
			sc = ssc.accept();
			if (null != sc) {
				Socket so = sc.socket();
				so.setSendBufferSize(super.getSendBufferSize());
				so.setTcpNoDelay(super.isTcpNoDelay());
				sc.configureBlocking(false);
				Engine.selector().execute(this, super.getManager(), sc);
				//super.getManager().addXio(newXio(sc));
			}
		} catch (Throwable e) {
			if (null != sc)
				sc.close();
			// skip all error
			// xdb.Trace.debug(this, e);
		}
	}

	@Override
	public void doException(SelectionKey key, Throwable e) throws Throwable {
		xdb.Trace.error(this.toString() + " " + key, e);
	}

	/////////////////////////////////////////////////////////////////////
	protected void parse(Manager manager, org.w3c.dom.Element self) {
		super.parse(manager, self);

		String localIp = self.getAttribute("localIp");
		int localPort = Integer.parseInt(self.getAttribute("localPort"));
		super.initNameAndAddress(self.getAttribute("name"), new InetSocketAddress(localIp, localPort));

		this.backlog = Integer.parseInt(self.getAttribute("backlog"));
	}

	public static Acceptor create(Manager manager, Element self) throws Exception {
		String clsName = self.getAttribute("class");
		Acceptor acceptor = clsName.isEmpty() ? new Acceptor() :
			(Acceptor)Class.forName(clsName).newInstance();
		acceptor.parse(manager, self);
		return acceptor;
	}
}
