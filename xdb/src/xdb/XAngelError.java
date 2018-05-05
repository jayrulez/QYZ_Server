package xdb;

/**
 * 死锁，重新运行存储过程次数超过最大配置时抛出这个异常。
 * 
 * @author lichenghua
 *
 */
public class XAngelError extends XError {
	static final long serialVersionUID = 6156439040197208243L;
	XAngelError() {
	}
}
