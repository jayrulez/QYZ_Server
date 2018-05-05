package xio;

public interface XioMBean {
	/**
	 * @return readable interestOps. 关注的网络事件。
	 */
	public String getInterestOps();

	/**
	 * @return 连接创建者的信息
	 */
	public String getCreatorInfo();

	/**
	 * @return 远端连接地址信息。
	 */
	public String getPeerInfo();

	public int getInputBufferSize();
	public int getInputBufferCapacity();

	public int getOutputBufferSize();
	public int getOutputBufferCapacity();

	/**
	 * @return 已经分配的 buffer 内存，当 capacity 配置小于一兆时，可能大于capacity。
	 */
	public int getOutputBufferAllocation();
}
