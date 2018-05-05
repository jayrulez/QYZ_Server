package xgen;

/**
 * foreign ∏Ò ΩªØ
 * = [[key:tableName];][[value:]tableName]
 */
public class Foreign extends xdb.util.ForeignConf {
	private final String message;

	@Override
	public void throwIf(boolean condition, String more) {
		if (condition)
			throw new IllegalArgumentException("invalid foreign! " + message + " " + more);
	}

	public void warn(String more) {
		Main.warn(message + " " + more, 'f');
	}

	public Foreign(Foreign foreign, String value) {
		super(null, value);
		this.message = foreign.message;
	}

	public Foreign(String conf, String context) {
		super(conf);
		this.message = "FOREIGN conf=" + Main.quote(conf) + " name=" + context;
	}
}
