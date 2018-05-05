package xdb;

public interface CheckpointMBean {

	public int getCountCheckpoint();
	public long getCountMarshalN();
	public long getCountMarshal0();
	public long getCountFlush();
	public long getCountSnapshot();

	// checkpoint 相关时间统计，单位纳秒
	public long getTotalTimeMarshalN();
	public long getTotalTimeSnapshot();
	public long getTotalTimeFlush();
	public long getTotalTimeCheckpoint();

	// 时间
	public String getTimeOfNextFlush();
	public String getTimeOfNextCheckpoint();
	public String getTimeOfNextFullBackup();
	public String getTimeOfNextIncBackup();

	// method
	/**
	 * 马上进行一次全备份。
	 *
	 * 如果在 backupDelay 期间，则不会马上开始。必须等待到Delay结束，backupDelay 优先级比这个命令高。
	 * 如果正在进行全备份，则不会启动新的全备份。如果需要等待全备份结束，则等待当前正在进行的全备份。
	 *    多个fullBackup请求，可能只会启动一次全备份。
	 *
	 * @param waitTimeout ==0 等待备份结束；>0，最多等待这么多时间；<0不等待；
	 * @throws InterruptedException
	 */
	public void fullBackup(long waitTimeout) throws InterruptedException;

	/**
	 * 马上执行checkpoint操作。 
	 * 同时发起多个请求，只会执行一次操作，操作完成以后，全部等待线程都会被唤醒。
	 * 如果正在进行备份，操作不会马上开始，而是在备份结束后才开始。
	 * 
	 * @param waitTimeout waitTimeout ==0 等待操作结束；>0，最多等待这么多时间；<0不等待；
	 * @throws InterruptedException
	 */
	public void checkpoint(long waitTimeout) throws InterruptedException;

	/**
	 * 是否允许备份
	 * @return
	 */
	public boolean isAllowBackup();

	/**
	 * 设置备份开关。
	 * @param allow
	 */
	public void setAllowBackup(boolean allow);

	public int getPeriodCheckpoint();

	public void setPeriodCheckpoint(int period);
}
