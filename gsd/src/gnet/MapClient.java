package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import common.ErrorCode;
import common.Rpc;
import common.TaskQueue;
import gnet.link.Onlines;
import gs.Config;
import lx.gs.SError;
import lx.gs.map.FMap;
import map.map.Handler;
import map.map.MapMgr;
import map.map.ProtocolDispatcher;
import map.msg.*;
import xdb.Lockeys;
import xdb.Trace;
import xio.DynamicMultiConnector;
import xio.Protocol;
import xio.Xio;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/8/17.
 */
public class MapClient extends xio.Manager implements ProtocolDispatcher {
    private static MapClient instance;
    public static MapClient getInstance() {
        return instance;
    }

    public MapClient()
    {
        instance = this;
        MapMgr.dispatcher = this;
    }

    private final static int localServerid = gs.Utils.getServerId();
    private final static Map<Integer, Xio> servers = new ConcurrentHashMap<>();

    public static void init() {
        MapMgr.Ins.setLocalServerid(localServerid);
        Rpc.init(localServerid, new Rpc.Sender() {
            @Override
            public void sendRemote(int serverid, long ctxid, Protocol argument) {
                MapClient.send(serverid, Rpc.make(ctxid, argument));
            }

            @Override
            public void sendLocal(long ctxid, Protocol argument) {
                MapClient.processLocalRpc(ctxid, argument);
            }
        });
    }

    public void setGsds(List<GsdInfo> gsds) {
        Trace.info("setGsds. {}", gsds);
        // serverid 小的gsd 连接 serverid大的gsd
        ((DynamicMultiConnector)getCreator("MapClient")).setConnections(gsds.stream().filter(m -> m.serverid > localServerid).collect(Collectors.toMap(m -> "mapd:" + m.serverid, m -> new DynamicMultiConnector.Address(m.ip, m.port))));
    }

    @Override
    public void removeXio(Xio xio, Throwable throwable) {
        servers.values().remove(xio);
        Trace.error("MapClient.removeXio. xio:{}" + xio, throwable);
    }

    @Override
    public void addXio(Xio xio) {
        final Config conf = Config.getInstance();
        new AnnounceGsd(conf.getServerid(), conf.getMapServerIp(), conf.getMapServerPort()).send(xio);
        Trace.info("MapClient.addXo xio:{}", xio);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Xio get() {
        return null;
    }


    public void addServer(int serverid, xio.Xio io) {
        final Xio oldXio = servers.put(serverid, io);
        if(oldXio != null) {
            Trace.error("MapServer. duplicate serverid:{}! remove old.", serverid);
            oldXio.close();
        }
        Trace.info("MapClient.addServer serverid:{} xio:{}", serverid, io);
    }

    @Override
    public void execute(Protocol msg) {
       dispatch(msg);
    }

    private static void dispatch(Protocol msg) {
        try {
            Trace.debug("Server.recv msg:[{}]{}", msg.getClass().getName(), msg);
            switch (msg.getType()) {
                case ForwardClient.PROTOCOL_TYPE: {
                    final ForwardClient p = (ForwardClient)msg;
                    Onlines.getInstance().sendForward(p.roleid, p.proto.ptype, p.proto.pdata);
                    break;
                }
                case ForwardMultiClients.PROTOCOL_TYPE: {
                    final ForwardMultiClients p = (ForwardMultiClients)msg;
                    Onlines.getInstance().multicastForward(p.roleids, p.proto.ptype, p.proto.pdata, false);
                    break;
                }
                case RoleXdbProtocol.PROTOCOL_TYPE: {
                    final RoleXdbProtocol p = (RoleXdbProtocol)msg;
                    processRoleXdbProtocol(p.roleid, decode(p.proto));
                    break;
                }
                case MEnterMap.PROTOCOL_TYPE: FMap.process((MEnterMap)msg); break;
                case MLeaveMap.PROTOCOL_TYPE: FMap.process((MLeaveMap)msg); break;
                case MRpc.PROTOCOL_TYPE: {
                    final MRpc p = (MRpc)msg;
                    Rpc.recv(p.ctxid, common.Utils.decode(Onlines.getInstance(), p.ptype, p.result));
                    break;
                }
                case AnnounceGsd.PROTOCOL_TYPE:
                    final AnnounceGsd p = (AnnounceGsd)msg;
                    Trace.info("MapServer. AnnounceGsd. serverid:{}", p.serverid);
                    instance.addServer(p.serverid, msg.getConnection());
                    break;
                default:
                    Handler.dispatch(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processLocalRpc(long ctxid, xio.Protocol msg) {
        TaskQueue.getNormalExecutor().execute(() -> {
            try {
                final xio.Protocol result = (xio.Protocol) Handler.class.getMethod("call", msg.getClass()).invoke(null, msg);
                Trace.debug("Server.process XLocalRpc argument:{}{} result:{}{}", msg.getClass().getName(), msg, result.getClass().getName(), result);
                Rpc.recv(ctxid, result);
            } catch (Exception e) {
                xdb.Trace.error("Server.process XLocalRpc argument type:" + msg.getClass().getName() + ", data:" + msg, e);
            }
        });
    }

    public static void processRoleXdbProtocol(long roleid, xio.Protocol msg) {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                try {
                    switch (msg.getType()) {
                        case MPlayerKillMonster.PROTOCOL_TYPE: return FMap.process(roleid, (MPlayerKillMonster)msg);
                        case MAddBonus.PROTOCOL_TYPE: return FMap.process(roleid, (MAddBonus)msg);
                        case MUseItem.PROTOCOL_TYPE: return FMap.process(roleid, (MUseItem)msg);
                        default:
                            final Object result = FMap.class.getMethod("process", long.class, msg.getClass()).invoke(null, roleid, msg);
                            return result != null ? (Boolean)result : true;
                    }
                } catch (Exception e) {
                    Trace.error("Server.LocalRoleXdbProtocol roleid:" + roleid + ",proto:<" + msg.getClass().getName() + ">" + msg, e);
                    return false;
                }
            }
        }.execute();
    }

    private static boolean sendRemoteByServerid(int serverid, xio.Protocol msg) {
        Trace.debug("MapClient send. serverid:{} msg:{}{}", serverid, msg.getClass().getName(), msg);
        final xio.Xio io = instance.servers.get(serverid);
        return io != null && msg.send(io);
    }

    public static void send(int serverid, Protocol msg) {
        if(serverid == localServerid) {
            TaskQueue.getNormalExecutor().execute(() -> dispatch(msg));
        } else {
            sendRemoteByServerid(serverid, msg);
        }
    }

    public static void sendToMap(long mapid, Protocol msg) {
        final int serverid = common.Utils.getHolderserveridById(mapid);
        if(serverid == localServerid) {
            Handler.processMapProtocol(mapid, msg);
        } else {
            sendRemoteByServerid(serverid, new MapProtocol(mapid, encode(msg)));
        }
    }

    public static void sendRoleMap(int serverid, long roleid, Protocol msg) {
        if(serverid == localServerid) {
            Handler.processRoleMapProtocol(roleid, msg);
        } else {
            Trace.debug("sendRoleMap. serverid:{} roleid:{} msg:{}{}", serverid, roleid, msg.getClass().getName(), msg);
            sendRemoteByServerid(serverid, new RoleMapProtocol(roleid, encode(msg)));
        }
    }

    @Override
    public void sendClient(long roleid, Protocol msg) {
        final int serverid = common.Utils.getServeridByRoleid(roleid);
        if(serverid == localServerid) {
            Onlines.getInstance().send(roleid, msg);
        } else {
            Trace.debug("sendForwardClient. serverid:{} roleid:{} msg:{}{}", serverid, roleid, msg.getClass().getName(), msg);
            sendRemoteByServerid(serverid, new ForwardClient(roleid, encode(msg)));
        }
    }

    @Override
    public void multicastClient(Set<Long> roleids, Protocol msg) {
        Map<Integer, Set<Long>> roleidsByServerids = roleids.stream().collect(Collectors.groupingBy(
                roleid -> common.Utils.getServeridByRoleid(roleid),
                Collectors.toSet()));
        for(Map.Entry<Integer, Set<Long>> e : roleidsByServerids.entrySet()) {
            final int serverid = e.getKey();
            if(serverid == localServerid) {
                Onlines.getInstance().multicast(e.getValue(), msg);
            } else {
                Trace.debug("sendForwardMultiClient. serverid:{} roleid:{} msg:{}{}", serverid, e.getValue(), msg.getClass().getName(), msg);
                sendRemoteByServerid(serverid, new ForwardMultiClients((HashSet<Long>)e.getValue(), encode(msg)));
            }
        }
    }

    @Override
    public Protocol createSError(ErrorCode err) {
        return new SError(err.getErrorId());
    }

    @Override
    public void sendServer(int serverid, Protocol msg) {
        send(serverid, msg);
    }

    @Override
    public void sendXdbRole(long roleid, Protocol msg) {
        final int serverid = common.Utils.getServeridByRoleid(roleid);
        if(serverid == localServerid || roleid == 0) {
            processRoleXdbProtocol(roleid, msg);
        } else {
            sendServerByRoleid(roleid, new RoleXdbProtocol(roleid, encode(msg)));
        }
    }

    @Override
    public void sendXdbServer(int serverid, Protocol msg) {
        if(serverid == localServerid) {
            processRoleXdbProtocol(0, msg);
        } else {
            sendRemoteByServerid(serverid, new RoleXdbProtocol(0, encode(msg)));
        }
    }

    @Override
    public Protocol decode(int ptype, Octets pdata) {
        return common.Utils.decode(Onlines.getInstance(), ptype, pdata);
    }

    private static xio.Protocol decode(gnet.ProtocolData data) {
        return common.Utils.decode(Onlines.getInstance(), data.ptype, data.pdata);
    }

    private static gnet.ProtocolData encode(xio.Protocol msg) {
        return new gnet.ProtocolData(msg.getType(), new OctetsStream().marshal(msg));
    }

}
