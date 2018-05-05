package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import xdb.Trace;
import xio.Protocol;

import java.util.*;

/**
 * @author Jin Shuai
 */
public class LoginTraceMgr {

    private static class ProtoInfo {
        private final String name;
        private int count;
        private long maxSize;
        private long totalSize;
        public ProtoInfo(String name) {
            this.name = name;
        }
    }

    private static class RoleLoginTrace {
        private long roleId;
        private long nickName;
        private long loginTime;
        private long logoutTime;
        private Map<String, ProtoInfo> upProtos = new HashMap<>();
        private Map<String, ProtoInfo> downProtos = new HashMap<>();
        private int procedureCount;

        public RoleLoginTrace(long roleId) {
            this.roleId = roleId;
            this.loginTime = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            long time = (logoutTime-loginTime) / 1000;
            if(time == 0) time = 1;

            final long onlineTime = time;
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n")
                    .append(String.format("%-20s %d%n","roleId :", roleId))
                    .append(String.format("%-20s %d  minutes, %d seconds %n", "online time :", onlineTime / 60, onlineTime % 60))
                    .append(String.format("%-20s %d %n","procedure count :", procedureCount))
                    .append(String.format("%-20s %d %n", "up proto :",upProtos.values().stream().mapToInt(value -> value.count).sum()))
                    .append(String.format("%-20s %f kb %n", "up proto size :", upProtos.values().stream().mapToLong(value -> value.totalSize).sum() / 1024.0))
                    .append(String.format("%-20s %-20d %n", "up proto per sec :", upProtos.values().stream().mapToInt(value -> value.count).sum() / onlineTime))
                    .append(String.format("%-20s %d %n", "down proto :",downProtos.values().stream().mapToInt(value -> value.count).sum()))
                    .append(String.format("%-20s %f kb %n", "down proto size :", downProtos.values().stream().mapToLong(value -> value.totalSize).sum() / 1024.0))
                    .append(String.format("%-20s %-20d %n", "down proto per sec :", downProtos.values().stream().mapToInt(value -> value.count).sum() / onlineTime))
//                    .append("online time : ").append(onlineTime / 60 + " minutes, " + onlineTime % 60 + " seconds").append("\r\n")
//                    .append("procedure count : ").append(procedureCount).append("\r\n")
//                    .append("up proto : ").append(upProtos.values().stream().mapToInt(value -> value.count).sum()).append("\r\n")
//                    .append("up proto size : ").append(upProtos.values().stream().mapToLong(value -> value.totalSize).sum() * 8f / 1024).append("\r\n")
//                    .append("up proto per sec : ").append(upProtos.values().stream().mapToInt(value -> value.count).sum() / onlineTime).append("\r\n")
//                    .append("down proto : ").append(downProtos.values().stream().mapToInt(value -> value.count).sum()).append("\r\n")
//                    .append("down proto size : ").append(downProtos.values().stream().mapToLong(value -> value.totalSize).sum()).append("\r\n")
//                    .append("down proto per sec : ").append(downProtos.values().stream().mapToInt(value -> value.count).sum() / onlineTime).append("\r\n")
            ;
            /*
            toOrderList(upProtos).forEach(protoInfo -> sb
                    .append(String.format("up   proto : %-30s", protoInfo.name))
                    .append(String.format(", count : %-8d", protoInfo.count))
                    .append(String.format(", total size :%-10d", protoInfo.totalSize))
                    .append(String.format(", max size : %-5d", protoInfo.maxSize))
                    .append(String.format(", average count/s: %.2f", (double)protoInfo.count / onlineTime))
                    .append("\r\n"));
            toOrderList(downProtos).forEach(protoInfo -> sb
                    .append(String.format("down proto : %-30s", protoInfo.name))
                    .append(String.format(", count : %-8d", protoInfo.count))
                    .append(String.format(", total size :%-10d", protoInfo.totalSize))
                    .append(String.format(", max size : %-5d", protoInfo.maxSize))
                    .append(String.format(", average count/s: %.2f", (double)protoInfo.count / onlineTime))
                    .append("\r\n"));
            */
            final Map<String, ProtoInfo> totalProtos = new HashMap<>(upProtos);
            totalProtos.putAll(downProtos);
            toOrderList(totalProtos).forEach(protoInfo -> sb
                    .append(String.format("proto : %-30s", protoInfo.name))
                    .append(String.format(", count : %-8d", protoInfo.count))
                    .append(String.format(", total size :%-10d", protoInfo.totalSize))
                    .append(String.format(", max size : %-5d", protoInfo.maxSize))
                    .append(String.format(", average count/s: %.2f", (double)protoInfo.count / onlineTime))
                    .append("\r\n"));
            return sb.toString();
        }

        private List<ProtoInfo> toOrderList(Map<String, ProtoInfo> infos) {
            final List<ProtoInfo> orderInfos = new ArrayList<>(infos.values());
            Collections.sort(orderInfos, (o1, o2) -> o2.count - o1.count);
            return orderInfos;
        }
    }



    private static Map<Long, RoleLoginTrace> traceMap = new HashMap<>();
    public static volatile boolean isPrint = true;

    public static void addProcedureCount(long roleId) {
//        try{
//            getRoleTrace(roleId).procedureCount++;
//        }catch (Throwable throwable){
//        }
    }

    public static void onLogin(long roleId) {
//        try{
//            getRoleTrace(roleId);  //为了设置登录时间
//        }catch (Throwable throwable){
//        }
    }

    public static void onLogout(long roleId) {
//        try{
//            getRoleTrace(roleId).logoutTime = System.currentTimeMillis();
//            RoleLoginTrace trace = traceMap.remove(roleId);
//            if(isPrint){
//                Trace.info(trace);
//            }
//        }catch (Throwable throwable){
//        }
    }

    public static void traceUp(Protocol protocol) {
//        try{
//            RoleLoginTrace trace = getRoleTrace(protocol);
//            if(trace == null) return;
//            trace(protocol, trace.upProtos);
//        }catch (Throwable throwable){
//        }
    }

    public static void traceDown(long roleid, Protocol protocol) {
//        try{
//            RoleLoginTrace trace = getRoleTrace(roleid);
//            trace(protocol, trace.downProtos);
//        }catch (Throwable throwable){
//        }
    }

    private static void trace(Protocol protocol, Map<String, ProtoInfo> protoInfoMap) {
//        final String protoName = protocol.getClass().getName();
//        if (!protoInfoMap.containsKey(protoName)) {
//            protoInfoMap.putIfAbsent(protoName, new ProtoInfo(protoName));
//        }
//        ProtoInfo info = protoInfoMap.get(protocol.getClass().getName());
//        info.count++;
//        int size =  protocol.marshal(new OctetsStream()).size();
//        if(size > info.maxSize) info.maxSize = size;
//        info.totalSize += size;
    }


//    private static RoleLoginTrace getRoleTrace(Protocol protocol) {
//        gnet.link.Role role = gnet.link.Onlines.getInstance().find(protocol);
//        return role != null ? getRoleTrace(role.getRoleid()) : null;
//    }
//
//    private static RoleLoginTrace getRoleTrace(long roleId) {
//        if (!traceMap.containsKey(roleId)) {
//            traceMap.putIfAbsent(roleId, new RoleLoginTrace(roleId));
//        }
//        return traceMap.get(roleId);
//    }
}
