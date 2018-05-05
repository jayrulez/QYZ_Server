import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class Main {


    static final Timer timer = new Timer();

    private static final byte[] buffer = new byte[4096];
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String args[]) throws Exception {
        String logdir = ".";
        if (args.length > 0)
            logdir = args[0];

        int port = 10010;
        if (args.length > 1)
            port = Integer.parseInt(args[1]);


        DatagramSocket socket = new DatagramSocket(port);
        Log log = new Log(new File(logdir), false, 7, 0);

        log.println(dateFormat.format(Calendar.getInstance().getTime()) +" logger started", null);

        while (true){
            try {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                socket.receive(dp);

                String s = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
                if (s.endsWith("\n"))
                    s = s.substring(0, s.length()-1);
                if (s.endsWith("\r"))
                    s = s.substring(0, s.length()-1);

                StringBuilder sb = new StringBuilder();
                sb.append(dateFormat.format(Calendar.getInstance().getTime()));
                sb.append(' ').append(dp.getSocketAddress()).append(' ');
                sb.append(' ').append(s);
                log.println(sb.toString(), null);
            }catch(Exception e){
                log.println("logger exception", e);
            }

        }
    }


    private static void test() throws Exception{
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress("124.193.167.190", 10010));
        byte[] bytes = "test from logger".getBytes();
        socket.send(new DatagramPacket(bytes, bytes.length));

        socket.close();
    }

}
