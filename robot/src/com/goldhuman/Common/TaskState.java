package com.goldhuman.Common;

public interface TaskState
{
	public static final int INIT = 0;
	public static final int RUNNING = 1;
	public static final int STOPPING = 2;
	public static final int STOPPED = 3;
	public static final int FAIL = 4;
	public static final int SUCCEED = 5;
	public static final int USERDEFINE = 6;
}
