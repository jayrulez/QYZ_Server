package xdb.logs;

/**
 * 注册过程辅助类。
 * 
 * 目前只开放了一级。实际上可以不用数组。
 * 
 * @author lichenghua
 *
 */
public class VarNames {
	private String [] path;
	private int pos;

	public VarNames(String ... path) {
		this.path = path;
		this.pos = 0;
	}

	public String currentFullVarName() {
		if (path.length == 0)
			return ".";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pos; ++i) {
			sb.append('.').append(path[i]);
		}
		return sb.toString();
	}

	public String next() {
		return path[pos++];
	}

	public boolean hasNext() {
		return pos < path.length;
	}

	public void assertLast() {
		if (hasNext())
			throw new IllegalStateException();
	}

	public void reset() {
		this.pos = 0;
	}
}
