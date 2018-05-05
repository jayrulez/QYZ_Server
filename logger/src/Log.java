import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

///////////////////////////////////////////////////////////////////////////
public final class Log extends java.util.TimerTask {
    private final File home;
    private boolean console;
    private PrintStream ps;

    public Log(File home, boolean console, int hourOfDay, int minute) {
        this.home = home;
        this.console = console;
        try {
            if (null != home) {
                File file = new File(home, "logger.log");
                // open with append
                ps = new PrintStream(new FileOutputStream(file, true), false, "GBK");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ps = null;
        }

        if (hourOfDay < 0 || minute < 0 || null == ps)
            return; // no rotate

        java.util.Calendar firstTime = java.util.Calendar.getInstance();
        firstTime.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
        firstTime.set(java.util.Calendar.MINUTE, minute);
        firstTime.set(java.util.Calendar.SECOND, 0);
        firstTime.set(java.util.Calendar.MILLISECOND, 0);
        if (firstTime.before(Calendar.getInstance())) // 如果第一次的时间比当前时间早，推到明天。
            firstTime.add(java.util.Calendar.DAY_OF_MONTH, 1); // tomorrow!

        //System.out.println("xdb.Trace.rotate schedule. firstTime=" + dateFormat.format(firstTime.getTime()));
        //new Exception().printStackTrace(System.out);
        Main.timer.schedule(this, firstTime.getTime(), 24 * 3600 * 1000); // every day
        //System.out.println("TimerTask.executionTime=" + dateFormat.format(scheduledExecutionTime()));
    }

    public boolean rotate() {
        //System.out.println("xdb.Trace.rotate :");
        //new Exception().printStackTrace(System.out);


        try {
            if (null == ps)
                return false;

            File dest = new File(this.home, "logger."
                    + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS")
                    .format(Calendar.getInstance().getTime()) + ".log");
            if (dest.exists())
                return false;

            File file = new File(this.home, "logger.log");
            ps.close(); // close before rename
            boolean renameOk = file.renameTo(dest);
            // open with truncate if renameOk
            ps = new PrintStream(new FileOutputStream(file, !renameOk), false, "GBK");
            return renameOk;
        } catch (Exception ex) {
            ex.printStackTrace();
            ps = null;
        }
        return false;
    }

    public void println(String str, Throwable e) {
        if (null != ps) {
            ps.println(str);
            if (null != e)
                e.printStackTrace(ps);
        }
        if (this.console) {
            System.out.println(str);
            if (null != e)
                e.printStackTrace(System.out);
        }
    }

    public void close() {
        if (null != ps) {
            ps.close();
            ps = null;
        }
        this.console = false;
        this.cancel();
    }

    @Override
    public void run() {
        this.rotate(); // skip error
    }
}