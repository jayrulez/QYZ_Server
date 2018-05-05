package xio;

import java.nio.ByteBuffer;

public class Filter {
	private final String name;

	public Filter(String name) {
		this.name = name;
	}

	public final String getName() {
		return this.name;
	}

	/**
	 * 重载这个方法实现过滤功能。
	 * <p>如果实现不是最后过滤器，那么最后调用it.doFilterNextOf(this, b, x);继续处理。
	 * <p><b>实现这个方法的关键是对于ByteBuffer的理解。</b>
	 * @param it 过滤器队列指示器。用来把数据传递给下一个过滤器。
	 * @param b 输入数据，此时b为read模式。
	 * @param x 当前网络连接。
	 */
	protected void doFilter(Iterator it, ByteBuffer b, Xio x) {
		it.doFilterNextOf(this, b, x);
	}

	@Override
	public String toString() {
		return name;
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 列表中的Filter的名字必须唯一。
	 * 加入删除的时候，名字必须存在。
	 * 加入时，Filter必须是没有被加到其他FilterList中的。也就是isChained必须是false。
	 * 所有访问都 synchronized (this)
	 * 子类 Xio.Input Xio.Output 也 synchronized (this)
	 */
	public static class List {
		private final java.util.ArrayList<Filter> filters = new java.util.ArrayList<Filter>();

		private final int indexOf(String name) {
			for (int index = 0; index < filters.size(); ++index)
				if (filters.get(index).getName().equals(name))
					return index;
			return -2; // NOT -1, see addAfter
		}

		private final void add(int index, Filter f) {
			if (index < 0)
				throw new ArrayIndexOutOfBoundsException();
			if (indexOf(f.getName()) >= 0) // 名字存在
				throw new IllegalStateException();
			filters.add(index, f);
		}

		public void addAfter(String name, Filter f) {
			synchronized (this) { add(indexOf(name) + 1, f); }
		}

		public void addBefore(String name, Filter f) {
			synchronized (this) { add(indexOf(name), f); }
		}

		public void addFirst(Filter f) {
			synchronized (this) { add(0, f); }
		}

		public void addLast(Filter f) {
			synchronized (this) { add(filters.size(), f); }
		}

		public void clear() {
			synchronized (this) { filters.clear(); }
		}

		public Filter get(String name) {
			synchronized (this) { 
				int index = indexOf(name);
				if (index < 0)
					return null;
				return filters.get(index);
			}
		}

		public Filter remove(String name) {
			synchronized (this) { return filters.remove(indexOf(name)); }
		}

		public String toString() {
			synchronized (this) { return filters.toString(); }
		}

		// package private
		final java.util.List<Filter> raw() {
			return filters;
		}
	}

	public static interface Iterator {
		public void doFilterNextOf(Filter f, ByteBuffer b, Xio x);
	}
}

