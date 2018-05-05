package xdb;

/**
 * 
 * 线程被 interrupt 时抛出这个异常。用来报告不需要特别处理的中断。
 * <b>未用。</b>
 * 
 */
public class XInterrupted extends RuntimeException {
	static final long serialVersionUID = -7927226240291624476L;

	public XInterrupted() {
	}

	public XInterrupted(String message) {
		super(message);
	}

	public XInterrupted(Throwable e) {
		super(e);
	}

	public XInterrupted(String message, Throwable e) {
		super(message, e);
	}
}
