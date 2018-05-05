package xdb;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * ReentrantLock 的包装。重载 lock, tryLock 的实现，做如下处理。
 * <ol>
 * <li>lock 操作改成使用 lockInterruptibly。这样 Angel 可以中断它。
 * <li>处理 InterruptedException。 便于 ProcedureFuture 重做任务。
 * <li>便于使用，不用处理或者声明 InterruptedException。
 * </ol>
 * 
 * <pre>
 * 1 synchronized 同步锁
 * synchronized(object) {
 *     // ... critical section
 * }
 * 替换成
 * lock.lock();
 * try {
 *     // ... critical section
 * } finally {
 *     lock.unlock();
 * }
 * 如果 critical section 内包含有 try 语句，不要试图和这里的 try...finally 合并。
 * 保留原来的
 * 
 * 2 condition(synchronized and Object.wait and Object.notify) 同步和条件
 * 使用 Condition 的 await 和 signal 来替代 Object 的 wait 和 notify。
 * 通过 LockInterruptible.newCondition 创建。参考 Condition, Lock。
 * </pre>
 */
public final class LockInterruptible extends ReentrantLock {
	private static final long serialVersionUID = -3127211387464574647L;

	public LockInterruptible() {
	}

	public LockInterruptible(boolean fair) {
		super(fair);
	}

	@Override
	public void lock() {
		try {
			super.lockInterruptibly();
		} catch (InterruptedException ex) {
			if (xdb.Worker.angelInterrupted())
				throw new xdb.XLockDead();
			throw new XLockInterrupted("lock");
		}
	}

	@Override
	public void lockInterruptibly() {
		try {
			super.lockInterruptibly();
		} catch (InterruptedException ex) {
			if (xdb.Worker.angelInterrupted())
				throw new xdb.XLockDead();
			throw new XLockInterrupted("lockInterruptibly");
		}
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) {
		try {
			return super.tryLock(time, unit);
		} catch (InterruptedException ex){
			// tryLock 肯定不会造成死锁．需要确认 findDeadlockedThreads 如何判断的。
			// 现在先不看作死锁。 see xdb.Lockey.tryLock。
			throw new XLockInterrupted("tryLock");
		}
	}
}
