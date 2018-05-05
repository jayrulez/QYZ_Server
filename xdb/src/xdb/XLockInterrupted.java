package xdb;

/**
 * 锁请求被 Interrupt 时抛出这个异常。
 */
public class XLockInterrupted extends XError {
	static final long serialVersionUID = -7927226240291624476L;

	public XLockInterrupted() {
	}

	public XLockInterrupted(String message) {
		super(message);
	}

	public XLockInterrupted(Throwable e) {
		super(e);
	}

	public XLockInterrupted(String message, Throwable e) {
		super(message, e);
	}
}
