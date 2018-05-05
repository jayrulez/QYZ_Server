package xio;

/**
 * 不管理连接的实现。
 *
 * 不对连接进行任何管理，适合用于纯被动的服务器。
 */
public class ManagerNone extends Manager {
	@Override
	protected void addXio(Xio xio) {
	}

	@Override
	public Xio get() {
		return null;
	}

	@Override
	protected void removeXio(Xio xio, Throwable e) {
	}

	@Override
	public int size() {
		return 0;
	}
}
