package common;

import com.goldhuman.Common.Marshal.OctetsStream;
import map.msg.XRpc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by HuangQiang on 2016/5/5.
 */
public class Rpc<T extends xio.Protocol> {

    public interface Callback<T extends xio.Protocol> {
        void onResult(T argument, xio.Protocol result);
        void onTimeout(T argument);
    }

    public interface Sender {
        void sendRemote(int serverid, long ctxid, xio.Protocol argument);
        void sendLocal(long ctxid, xio.Protocol argument);
    }

    private final static AtomicLong NEXT_CTX_ID = new AtomicLong();
    private final static ConcurrentHashMap<Long, Rpc<?>> rpcs = new ConcurrentHashMap<>();

    private static int localServerid;
    private static Sender sender;
    public static void init(int localServerid2, Sender sender2) {
        localServerid = localServerid2;
        sender = sender2;
    }

    private final T argument;
    private final Callback<T> callback;

    private Rpc(T argument, Callback<T> callback) {
        this.argument = argument;
        this.callback = callback;
    }

    private void onResult(xio.Protocol result) {
        callback.onResult(argument, result);
    }

    private void onTimeout() {
        callback.onTimeout(argument);
    }


    public static <T extends xio.Protocol> void send(int serverid, T argument, Callback<T> callback) {
        if(serverid == localServerid) {
            sendLocal(serverid, argument, callback);
        } else {
            sendRemote(serverid, argument, callback);
        }
    }

    public static <T extends xio.Protocol> void sendRemote(int serverid, T argument, Callback<T> callback) {
        //Trace.info("Rpc.sendRemote serverid:{} argument:{} callback:{}", serverid, argument, callback);
        final long ctxid = NEXT_CTX_ID.incrementAndGet();
        rpcs.put(ctxid, new Rpc<T>(argument, callback));
        TaskQueue.getScheduleExecutor().schedule(() -> {
            final Rpc<?> r = rpcs.remove(ctxid);
            if(r != null)
                r.onTimeout();
        }, 15, TimeUnit.SECONDS);

        sender.sendRemote(serverid, ctxid, argument);
    }

    public static XRpc make(long ctxid, xio.Protocol argument) {
        return new XRpc(ctxid, argument.getType(), new OctetsStream().marshal(argument));
    }

    public static <T extends xio.Protocol> void sendLocal(int serverid, T argument, Callback<T> callback) {
        //Trace.info("Rpc.sendLocal serverid:{} argument:{} callback:{}", serverid, argument, callback);
        final long ctxid = NEXT_CTX_ID.incrementAndGet();
        rpcs.put(ctxid, new Rpc<T>(argument, callback));
        TaskQueue.getScheduleExecutor().schedule(() -> {
            final Rpc<?> r = rpcs.remove(ctxid);
            if(r != null)
                r.onTimeout();
        }, 15, TimeUnit.SECONDS);

        sender.sendLocal(ctxid, argument);
    }

    public static void recv(long ctxid, xio.Protocol result) {
        final Rpc<?> rpc = rpcs.remove(ctxid);
        if(rpc != null) {
            rpc.onResult(result);
        }
    }
}
