package xio;

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.ObjectName;

/**
 * Socket 连接
 * 
 * 网络事件处理，和读写，管理 buffer
 * 通过接口 Filter 进行数据加解密，压缩，搭建高级协议层。
 * 
 * @author lichenghua
 *
 */
public class Xio implements Handle, java.io.Closeable, XioMBean {
	public static final int OP_READ = SelectionKey.OP_READ;
	public static final int OP_WRITE = SelectionKey.OP_WRITE;

	private boolean open = true; // 用来保证只关闭一次。

	// 线程安全： 下面成员变量都是final，不用做任何保护。
	private final SelectionKey key;
	private final Creator creator;
	private final SocketAddress peer; // 关闭以后看不到peer。开始就保存一个。
	private final Input input;
	private final Output output;
	private final ObjectName objname;

	public Xio(Creator creator, SelectorThread selector, SocketChannel sc, int ops, Input input, Output output) {
		this.creator = creator;
		final Socket so = sc.socket();
		this.peer = so.getRemoteSocketAddress();
		this.input = input;
		this.output = output;
		this.key = selector.register(sc, ops, this);

		ObjectName objname = null;
		try {
			objname = Engine.mbeans().register(this,
					"xio:type=" + creator.getManager().getMBeanName()
					+ ",name=" + so.getInetAddress() + "-" + so.getPort()
					+ "@" + creator.getManager().incrementAndGetNewXioCount());
		} catch (Throwable e) {
			xdb.Trace.warn("MBean.register xio. creator=" + this.getCreatorInfo(), e);
		}
		this.objname = objname;
	}

	public final Creator getCreator() {
		return creator;
	}

	public InetSocketAddress getPeer() {
		return (InetSocketAddress)peer;
	}

	public final Input getInput() {
		return input;
	}

	public final Output getOutput() {
		return output;
	}

	// 需要 public ？
	private final SocketChannel getSocketChannel() {
		return (SocketChannel)key.channel();
	}

	private synchronized boolean getAndClearOpen() {
		// 懒得确认是否可以直接使用 key.channel().isOpen()，
		// 这样也可避免耦合过紧。
		boolean isOpen = this.open;
		this.open = false;
		return isOpen;
	}

	@Override
	public void close() {
		close(null);
	}
	
	public void close(Throwable ex) {
		// 小心锁的问题，参见 Manager.close 等在 synchronized(xios) 调用 xio.close() 的地方。
		try {
			if (getAndClearOpen()) {
				key.channel().close();
				creator.onClose(this, ex);
				Engine.mbeans().unregister(objname);
			}
		} catch (Throwable e) {
			// skip
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Xio").append(peer).append('-').append(creator).toString();
	}

	// net event setup
	public final void interestOps(int ops) {
		key.interestOps(ops);
		key.selector().wakeup();
	}

	public final void interestOps(int remove, int add) {
		int cur = key.interestOps();
		int ops = (cur & ~remove) | add;
		if (cur != ops)
			this.interestOps(ops);
	}

	// maybe override
/*	public boolean write(ByteBuffer b) {
		try {
			if (!key.isValid())
				return false;
			return output.write(b, this);
		} catch (IOError e) {
			if (xdb.Trace.isDebugEnabled())
				xdb.Trace.debug(this, e); // IOError 错误日志只在debug时记录。
		} catch (Throwable e) {
			xdb.Trace.error(this, e);
		}
		close();
		return false;
	}
*/
	private Lock outputQueueLock = new ReentrantLock();
	private List<ByteBuffer> outputQueue = new ArrayList<ByteBuffer>();
	
	/**
	 * 相对于以前的 write 操作，buffer不能被复用：加入到输出队列以后，由  Xio 管理。
	 * @param buffer
	 */
	public boolean add(ByteBuffer buffer) {
		if (key.isValid()) {
			outputQueueLock.lock();
			try {
				outputQueue.add(buffer);
				this.interestOps(0, OP_WRITE);
			} finally {
				outputQueueLock.unlock();
			}
			return true;
		}
		return false;
	}

	/////////////////////////////////////////////////////////////////
	// net event process
	@Override
	public void doHandle(SelectionKey key) throws Throwable {
		if (key.isReadable()) {
			if (-1 == input.doRead(this)) {
				close();
				return;
			}
		}

		if (key.isWritable()) {
			// 1. 交换得到当前输出队列（在输出队列锁内）。
			// 此方式在输出非常繁忙，队列经常有很多输出协议时，效率较高。
			// 在输出不忙碌时，new ArrayList 会造成一定浪费。需要改进。
			List<ByteBuffer> outputQ = null;
			outputQueueLock.lock();
			try {
				if (0 != outputQueue.size()) {
					outputQ = outputQueue;
					outputQueue = new ArrayList<ByteBuffer>();
				}
			} finally {
				outputQueueLock.unlock();
			}

			// 2. 写到输出缓存中（在输出队列锁外）。
			if (null != outputQ) {
				for (ByteBuffer buffer : outputQ) {
					// 数据流处理，（可能）包括加密压缩等等。
					// （可能）直接写入到 SocketChannel 中，see output.doNextFilter。
					if (false == output.write(buffer, this)) {
						// output 满的错误处理。由于协议类型信息在这里已经丢失，无法记录详细日志。
						xdb.Trace.error("output buffer overflow. " + this);
						break;
					}
				}
			}

			// 3. 刷新输出缓存（在输出队列锁外）。
			int remaining = output.doWrite(this);

			// 4. 更新网络事件（在输出队列锁内）。
			if (0 == remaining) {
				outputQueueLock.lock();
				try {
					if (outputQueue.isEmpty())
						this.interestOps(OP_WRITE, 0);
				} finally {
					outputQueueLock.unlock();
				}
			}
		}
	}

	@Override
	public void doException(SelectionKey key,  Throwable e) throws Throwable {
		// channel已经被close了，必须从jmx中unregister，否则会内存泄露
		close(e);
	}

	/**
	 * 按索引递增顺序调用list中的filter
	 */
	public static final class Input extends Filter.List implements Filter.Iterator {
		private int index;
		private ByteBuffer buffer;

		@Override
		public void doFilterNextOf(Filter f, ByteBuffer b, Xio x) {
			if (raw().get(index) != f)
				throw new IllegalStateException();
			raw().get(++ index).doFilter(this, b, x);
		}

		// public 出来,用于测试. under lock
		public void doInput(ByteBuffer b, Xio x) {
			index = 0;
			raw().get(index).doFilter(this, b, x); // 必须有输入过滤器，否则抛出异常，关闭连接。
		}

		public int getBufferSize() {
			synchronized (this) {
				return null == buffer ? 0 : buffer.position();
			}
		}

		public int getBufferCapacity() {
			synchronized (this) {
				return null == buffer ? 0 : buffer.limit();
			}
		}

		private int doRead(Xio x) throws java.io.IOException {
			synchronized (this) {
				if (null == buffer)
					buffer = ByteBuffer.allocate(x.creator.getInputBufferSize());
				int rc = x.getSocketChannel().read(buffer);
				if (rc > 0) {
					buffer.flip();
					doInput(buffer, x);
					buffer.compact();
//					if (0 == buffer.position() && ! x.creator.isKeepInputBuffer()) {
//						buffer = null; // buffer 空了以后，释放所有内存
//					}
				}
				// 当输入缓存没有空间时，抛出异常，关闭连接。输入缓存必须大于最大协议包的大小。
				if (null != buffer && buffer.remaining() == 0)
					throw new IOError("OutofInputBuffer " + x.creator);
				return rc;
			}
		}
	}

	/**
	 * 按索引递减顺序调用list中的filter
	 */
	public static final class Output extends Filter.List implements Filter.Iterator {
		private int index;
		private OutputBuffer buffer; // 未优化前使用 ByteBuffer。
		private final int maxSize; // read only

		public int getBufferSize() {
			synchronized (this) {
				return null == buffer ? 0 : buffer.position();
			}
		}

		public int getBufferCapacity() {
			return maxSize; // final and read only
		}

		public int getBufferAllocation() {
			synchronized (this) {
				return null == buffer ? 0 : buffer.allocation();
			}
		}

		public Output() {
			maxSize = Helper.MAX_BUFFER_SIZE;
		}

		public Output(Creator creator) {
			maxSize = creator.getOutputBufferSize();
		}

		/**
		 * public for test
		 * <p> 使用 Filter 链处理数据，并写到输出缓存中。
		 * @param b
		 * @param x
		 * @return
		 */
		public boolean write(ByteBuffer b, Xio x) {
			synchronized (this) {
				int bufferSize = null == buffer ? 0 : buffer.position();
				if (bufferSize + b.remaining() > maxSize)
					return false;

				index = raw().size();
				doNextFilter(--index, b, x);
				return true;
			}
		}

		// under lock
		@Override
		public void doFilterNextOf(Filter f, ByteBuffer b, Xio x) {
			if (raw().get(index) != f)
				throw new IllegalStateException();
			doNextFilter(--index, b, x);
		}

		// under lock
		private void doNextFilter(int next, ByteBuffer b, Xio x) {
			if (next >= 0) {
				raw().get(next).doFilter(this, b, x);
				return;
			}

			// try Write Direct If buffer isEmpty
			/*
			if (null == buffer || buffer.position() == 0) {
				try {
					x.getSocketChannel().write(b);
				} catch (java.io.IOException e) {
					throw new IOError(e.getMessage());
				}
			}
			// */

			if (b.remaining() > 0) {
				// buffer = Helper.realloc(buffer, b.remaining());
				if (null == buffer) buffer = new OutputBuffer();
				buffer.put(b);
				//x.interestOps(0, Xio.OP_WRITE);
			}
		}

		/**
		 * 把缓存的数据刷新到网络中。
		 * @param x
		 * @return remaining
		 * @throws java.io.IOException
		 */
		private final int doWrite(Xio x) throws java.io.IOException {
			synchronized (this) {
				// 如果直接把数据写入到 SocketChannel，buffer 可能为 null。
				// 参考上面关于 "try Write Direct If buffer isEmpty" 下方被注释掉的代码。
				if (null == buffer)
					return 0;

				buffer.output(x.getSocketChannel());
				/*
				buffer.flip();
				x.getSocketChannel().write(buffer);
				buffer.compact();
				*/
				final int remaining = buffer.size();
				if ((0 == remaining) && (false == x.creator.isKeepOutputBuffer())) {
					buffer = null; // 空了以后，释放所有内存
				}
				return remaining;
			}
		}
	}

	/////////////////////////////////////////////////////////////////////
	// MBean implement

	@Override
	public String getInterestOps() {
		StringBuilder sb = new StringBuilder();
		int ops = this.key.interestOps();
		if (0 != (ops & SelectionKey.OP_ACCEPT)) sb.append("+Accept");
		if (0 != (ops & SelectionKey.OP_CONNECT)) sb.append("+Connect");
		if (0 != (ops & SelectionKey.OP_READ)) sb.append("+Read");
		if (0 != (ops & SelectionKey.OP_WRITE)) sb.append("+Write");
		return sb.toString();
	}

	@Override
	public String getCreatorInfo() {
		final StringBuilder sb = new StringBuilder();
		final Manager manager = creator.getManager();
		final XioConf conf = manager.getConf();
		sb.append("XioConf.").append(conf.getName());
		sb.append("/");
		sb.append(manager.getClass().getName()).append(".").append(manager.getName());
		sb.append("/");
		sb.append(creator.getClass().getName()).append(".").append(creator.getName());
		return sb.toString();
	}

	@Override
	public String getPeerInfo() {
		return peer.toString();
	}

	@Override
	public int getInputBufferSize() {
		return this.input.getBufferSize();
	}

	@Override
	public int getInputBufferCapacity() {
		return this.input.getBufferCapacity();
	}

	@Override
	public int getOutputBufferSize() {
		return this.output.getBufferSize();
	}

	@Override
	public int getOutputBufferCapacity() {
		return this.output.getBufferCapacity();
	}

	@Override
	public int getOutputBufferAllocation() {
		return this.output.getBufferAllocation();
	}
}
