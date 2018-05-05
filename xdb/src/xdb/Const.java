package xdb;

/**
 * 定义 xdb 内使用的常量。
 */
public final class Const {
	/**
	 * 字符串系列化时的编码。
	 * 
	 * 在网络传输和数据库存储时都需要对字符串进行编码。
	 * xgen 生成的 XBean 和 XTable 使用这个常量。
	 * rpcgen 由于生成的协议可能不依赖XDB，并不使用这个常量。
	 */
	public final static String IO_CHARSET = "UTF-16LE";
}
