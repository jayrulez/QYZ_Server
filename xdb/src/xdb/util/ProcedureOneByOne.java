package xdb.util;

import java.util.LinkedList;
import xdb.Procedure;

/**
 * 按加入顺序，挨个执行存储过程。任何时候最多只有一个存储过程正在执行。
 * 
 * 在并发异步环境下，为了正确处理某些问题，需要设计复杂的协议。
 * OneByOne 避免并发，顺序化以后，设计协议变得容易。
 * 
 * 功能：
 *   1 容量限制。达到最到容量后，add 操作抛出 RuntimeException。
 *   2 流量控制，(未实现)，根据时间控制存储过程数量。
 *   3 统计，(未实现)，弄个总数统计？
 *   4 shutdown，(未实现)，a) 等待所有的都执行完了才返回 b)中断正在执行的，返回还没有执行的存储过程。
 *   
 * @author lichenghua
 *
 */
public class ProcedureOneByOne {
	private int maxsize;
	private LinkedList<xdb.Procedure> onebyone = new LinkedList<xdb.Procedure>();
	private xdb.Procedure.Done<xdb.Procedure> done = new xdb.Procedure.Done<xdb.Procedure>() {
		@Override
		public void doDone(Procedure p) {
			synchronized (ProcedureOneByOne.this) {
				Procedure f = onebyone.removeFirst();
				assert(f == p); // 这是OneByOne本意。
				if (onebyone.size() > 0)
					xdb.Procedure.execute(onebyone.peekFirst(), done);
			}
		}
	};

	public ProcedureOneByOne() {
		this.maxsize = 0; // unlimited
	}

	public ProcedureOneByOne(int maxsize) {
		this.maxsize = maxsize;
	}

	public void add(xdb.Procedure p) {
		synchronized (this) {
			if (maxsize > 0 && onebyone.size() > maxsize)
				throw new RuntimeException("out of capacity! maxsize=" + maxsize);
			onebyone.addLast(p);
			if (onebyone.size() == 1)
				xdb.Procedure.execute(onebyone.peekFirst(), done);
		}
	}

	//////////////////////////////////////////////////////////////
	// 暂时不要依赖下面的方法，有些功能还没确定。
	public xdb.Procedure peekDebugOnly() {
		synchronized (this) {
			return onebyone.peekFirst();
		}
	}

	public int sizeDebugOnly() {
		synchronized (this) {
			return onebyone.size();
		}
	}
}
