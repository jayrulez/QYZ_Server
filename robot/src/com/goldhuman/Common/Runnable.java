
package com.goldhuman.Common;

public abstract class Runnable implements java.lang.Runnable
{
	private int priority;
	public Runnable() { priority = 0; }
	public Runnable(int priority) { this.priority = priority; }
	public int GetPriority() { return priority; }
}
