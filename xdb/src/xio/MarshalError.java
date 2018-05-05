package xio;

public class MarshalError extends Error {
	static final long serialVersionUID = -5940715854092786215L;

	public MarshalError() {
	}

	public MarshalError(String message, Throwable cause) {
		super(message, cause);
	}

	public MarshalError(String message) {
		super(message);
	}
}
