package xdb;

/**
 * xbean实例都是独立的数据对象。所有的实例都是引用。
 * 任何xbean最多只能属于一个xbean。xbean之间不允许出现重复的引用。
 * 一个简单的违例：
 * <pre>
 * class A {}
 * class B { List<A> getListA(); }
 * A a = xbean.Pod.newA();
 * B b = xbean.Pod.newB();
 * List<A> lista = b.getListA();
 * lista.add(a);
 * lista.add(a); // 重复加入，抛出 XManagedError
 * </pre>
 * 
 * @see Procedure
 * 
 */
public class XManagedError extends XError {
	static final long serialVersionUID = 7269011645942640931L;
	XManagedError() {
	}
	XManagedError(String message) {
		super(message);
	}
}
