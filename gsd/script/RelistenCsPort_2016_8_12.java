import gm.server.CustomServiceServer;
import xdb.ThreadHelper;
import xdb.Trace;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by HuangQiang on 2016/8/12.
 */
public class RelistenCsPort_2016_8_12 implements Runnable {
        private static class AcceptThread extends ThreadHelper {
            private final int port;
            private final Acceptor acceptor;

            public AcceptThread(int var1, Acceptor var2) {
                super("GmAcceptor");
                this.port = var1;
                this.acceptor = var2;
            }

            public void run() {
                try {
                    ServerSocket var1 = new ServerSocket();
                    Throwable var2 = null;

                    try {
                        var1.bind(new InetSocketAddress(this.port));

                        while(true) {
                            Socket var3 = var1.accept();
                            this.acceptor.accept(var3);
                        }
                    } catch (Throwable var11) {
                        var2 = var11;
                        throw var11;
                    } finally {
                        if(var1 != null) {
                            if(var2 != null) {
                                try {
                                    var1.close();
                                } catch (Throwable var10) {
                                    var2.addSuppressed(var10);
                                }
                            } else {
                                var1.close();
                            }
                        }

                    }
                } catch (Exception var13) {
                    Trace.error("Gm Acceptor error", var13);
                }
            }
        }

    interface Acceptor {
        void accept(Socket var1);
    }
    
    
    @Override
    public void run() {
        (new AcceptThread(33551, (var0) -> {
            (new CustomServiceServer(var0)).start();
        })).start();
    }
}
