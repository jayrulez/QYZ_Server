package gs;

/**
 * 修改自twitter的snowflake
 * 从2016-01-01 00:00:00开始到2050-01-01 00:00:00期间有效
 * 支持分布式环境，以serverId作为区分
 * 时间戳40位，区服号12位，sequence 11位
 * 每毫秒可以产生2047个不同的数字
 * 线程安全
 *
 * @author Jin Shuai
 */
public final class GlobalIdGen {
    private final long serverId;
    private long sequence;

    /**
     * 2016-01-01 00:00:00 时间戳
     */
    private final long startMill = 1451577600000L;
    private long lastTimestamp = -1L;

    private final long serverIdBits = 12L;
    private final long sequenceBits = 11L;

    private final long serverIdShift = this.sequenceBits;
    private final long timestampLeftShift = this.sequenceBits + this.serverIdBits;

    private final long maxServerId = -1L ^ -1L << this.serverIdBits;
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

    public GlobalIdGen(long serverId) {
        // sanity check for serverId
        if (serverId > this.maxServerId || serverId < 0) {
            throw new IllegalArgumentException(String.format("service Id can't be greater than %d or less than 0", this.maxServerId));
        }
        this.serverId = serverId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        }

        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tillNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.startMill << this.timestampLeftShift | this.serverId << this.serverIdShift | this.sequence;
    }

    private long tillNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
