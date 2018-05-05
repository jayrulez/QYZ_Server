package xio;

public class IOError extends Error {
	static final long serialVersionUID = -4675579273322106473L;

	public IOError(Throwable cause) {
		super(cause);
	}

	public IOError(String message) {
		super(message);
	}

	public IOError(String message, Throwable cause) {
		super(message, cause);
	}
}
