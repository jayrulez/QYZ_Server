package xio;

import java.nio.channels.*;
import java.net.InetSocketAddress;

/**
 * 创建者。读取和管理基本配置。
 */
public abstract class Creator {
	// 属性都是初始化以后只读。不做线程保护。
	private Manager manager;
	private int inputBufferSize;
	private int outputBufferSize;
	private boolean keepOutputBuffer;
	private boolean keepInputBuffer;

	private boolean tcpNoDelay; // setup after accept
	private int receiveBufferSize; // 配置在ServerSocket上，accept出来的连接通过继承获得。
	private int sendBufferSize; // setup after accept

	private String name = "";          // ugly. 在子类中初始化。
	private InetSocketAddress address; // ugly. 在子类中初始化。

	/**
	 * 解析配置，并且记住所属的 manager。
	 * 子类重载解析自己的扩展并且调用这个方法解析基本配置。
	 * @param manager
	 * @param self
	 */
	protected void parse(Manager manager, org.w3c.dom.Element self) {
		this.manager = manager;

		this.inputBufferSize = Helper.roudup(Integer.parseInt(self.getAttribute("inputBufferSize")));
		this.keepInputBuffer = !self.getAttribute("keepInputBuffer").equals("false");
		this.outputBufferSize = Helper.roudup(Integer.parseInt(self.getAttribute("outputBufferSize")));
		this.keepOutputBuffer = !self.getAttribute("keepOutputBuffer").equals("false"); // default true

		this.sendBufferSize = Integer.parseInt(self.getAttribute("sendBufferSize"));
		this.receiveBufferSize = Integer.parseInt(self.getAttribute("receiveBufferSize"));
		this.tcpNoDelay = self.getAttribute("tcpNoDelay").equals("true");
	}

	protected void parse(Manager manager, int inputBufferSize, int outputBufferSize, int sendBufferSize, int receiveBufferSize, boolean tcpNoDelay) {
	    this.manager = manager;
        this.keepInputBuffer = true;
        this.keepOutputBuffer = true;
        this.inputBufferSize = inputBufferSize;
        this.outputBufferSize = outputBufferSize;
        this.sendBufferSize = sendBufferSize;
        this.receiveBufferSize = receiveBufferSize;
        this.tcpNoDelay = tcpNoDelay;
    }

	public final int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	public final int getSendBufferSize() {
		return sendBufferSize;
	}

	public final boolean isTcpNoDelay() {
		return tcpNoDelay;
	}

	public final int getOutputBufferSize() {
		return outputBufferSize;
	}


	public final int getInputBufferSize() {
		return inputBufferSize;
	}

	/**
	 * 是否保持InputBuffer。
	 * false=释放内存，如果buffer为空; true=永不释放内存。
	 */
	public final boolean isKeepInputBuffer() {
		return keepInputBuffer;
	}

	/**
	 * 是否保持OutputBuffer。
	 * false=释放内存，如果buffer为空; true=永不释放内存。
	 */
	public final boolean isKeepOutputBuffer() {
		return this.keepOutputBuffer;
	}

	public final Manager getManager() {
		return manager;
	}

	//////////////////////////////////////////////////////////////////////
	// 以下接口目前都不开放给外部，当需要扩展自己的Creator实现再开放出去。
	// 因为open close的使用可能还需要调整，目前这两个方法的线程安全方面定义不很明确。
	/**
	 * 完成连接创建。这里委托Manager完成连接创建。参考 Manager.newXio。
	 */
	Xio newXio(SelectorThread selector, SocketChannel sc) {
		return manager.newXio(this, selector, sc);
	}
	/**
	 * 该连接创建器生成的连接关闭时回调这个接口进行处理。参考 Manager.removeXio。
	 * @param xio 关闭的连接。
	 * @param e 关闭的原因，可能为null。
	 */
	abstract void onClose(Xio xio, Throwable e);
	/**
	 * 打开连接创建器，Engine启动时调用。
	 */
	abstract void open();
	/**
	 * 关闭连接创建器，Engine关闭时调用。
	 */
	abstract void close();

	public final String getName() {
		return name;
	}

	/**
	 * remote or local。参考 Acceptor, Connector。丑陋的。
	 */
	public final InetSocketAddress getAddress() {
		return address;
	}

	/**
	 * 初始化名字和网络地址，只能由子类在装载配置时调用。
	 * @param address
	 */
	protected final void initNameAndAddress(String name, InetSocketAddress address) {
		this.name = name;
		this.address = address;
		if (this.name.isEmpty())
			this.name = this.address.toString();
	}

	@Override
	public String toString() {
		return this.getAddress() + "@" + this.getManager().getName(); // + local;
	}
}
