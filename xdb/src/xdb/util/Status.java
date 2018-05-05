package xdb.util;

import java.io.Console;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 
 *
 */
public class Status {
	/*
	 * 操作状态
	 * 允许 read 
	 * xdb.running.read
	 * xdb.checkpoint.write
	 * xdb.backup.full.read
	 * xdb.backup.full.write
	 * xdb.backup.incr.read
	 * xdb.backup.incr.write
	 * xdb.restore.read
	 * xdb.restore.write
	 * 
	 * 数据状态
	 * 增量备份 checkpoint 列表：不知道 db.h 是否可以浏览保存点？
	 * 列表为空表示这是个全备份。
	 */

	public static void main(String args[]) throws Exception {
		if (args.length > 0) {
			// Get a file channel for the file
			File file = new File("filename");
			RandomAccessFile rwfile = new RandomAccessFile(file, "rw");
			FileChannel channel = rwfile.getChannel();

			// Use the file channel to create a lock on the file.
			// This method blocks until it can retrieve the lock.
			String str = args[0];
			Console console = System.console();
			for (int i = 0; i < 2; ++i) {
				System.out.println("press enter to lock!");
				console.readLine();
				FileLock lock = channel.lock();
				System.out.println("lock successed!");
				ByteBuffer buffer = ByteBuffer.allocate(4096);
				channel.position(0);
				int rc = channel.read(buffer);
				System.out.println("--- rc=" + rc);
				if (rc > 0) {
					System.out.println(new String(buffer.array(), 0, rc));
				}
				System.out.println("---");
				buffer.clear();
				System.out.println(str);
				System.out.println("---");
				buffer.put(str.getBytes());
				channel.truncate(0);
				buffer.flip();
				channel.write(buffer);

				System.out.println("press enter to release lock!");
				console.readLine();
				// Release the lock
				lock.release();
				str = str + str;
			}

			// Close the file
			channel.close();
			rwfile.close();
		} else {
			// Get a file channel for the file
			File file = new File("filename");
			RandomAccessFile rwfile = new RandomAccessFile(file, "rw");
			FileChannel channel = rwfile.getChannel();

			// Use the file channel to create a lock on the file.
			// This method blocks until it can retrieve the lock.
			FileLock lock = channel.tryLock();
			if (null != lock) {
				System.out.println("tryLock successed!");
				ByteBuffer buffer = ByteBuffer.allocate(4096);
				int rc = channel.read(buffer);
				System.out.println("--- content rc=" + rc);
				if (rc > 0) {
					System.out.println(new String(buffer.array(), 0, rc));
				}
				System.out.println("---");
				// Release the lock
				lock.release();
			} else
				System.out.println("tryLock failed!");

			// Close the file
			channel.close();
			rwfile.close();
		}
	}

	/**
	 * 
	 * @param operateName
	 */
	protected boolean tryReadLock(String operateName) {
		return false;
	}

	/**
	 * 
	 * @param operateName
	 */
	protected void readUnlock(String operateName) {
		
	}

	public void setXdbRunning() {
		
	}

	public void setBackup() {
		
	}

	public void setRestore() {
		
	}
}

class Backup {
	public void list() {
		
	}
	
	public void backup() {
		
	}
	
	public void restore() {
		
	}
}
