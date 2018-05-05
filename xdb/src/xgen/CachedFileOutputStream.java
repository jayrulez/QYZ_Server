package xgen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

// 此类为了效率考虑只能单线程同时访问
public class CachedFileOutputStream extends ByteArrayOutputStream
{
	private static HashSet<String>					filename_set	= new HashSet<String>(4096);
	private static HashSet<CachedFileOutputStream>	fileopen_set	= new HashSet<CachedFileOutputStream>(4096);
	private static byte[]							buffer			= new byte[64 * 1024];
	private File									file;

	public CachedFileOutputStream(File file)
	{
		super(64 * 1024); // 初始64KB缓存空间, 适用于大多数生成的代码
		filename_set.add(file.getAbsolutePath().toLowerCase());
		this.file = file;
		fileopen_set.add(this);
	}

	private boolean compareFile() throws IOException
	{
		final int file_len = (int)this.file.length();
		if(file_len != this.count)
			return false;
		if(file_len > buffer.length)
			buffer = new byte[Math.max(buffer.length * 2, file_len)];

		FileInputStream fs = new FileInputStream(this.file);
		fs.read(buffer);
		fs.close();

		for(int i = 0; i < file_len; ++i)
			if(this.buf[i] != buffer[i])
				return false;
		return true;
	}

	private void writeFile() throws IOException
	{
		FileOutputStream fs = new FileOutputStream(this.file);
		fs.write(this.buf, 0, this.count);
		fs.close();
	}

	public void close() throws IOException
	{
		if(fileopen_set.contains(this))
		{
			if(!this.file.exists())
				this.writeFile();
			else if(!this.compareFile())
			{
				System.out.println("xgen: modify file: " + this.file);
				this.writeFile();
			}
			this.file = null;
			fileopen_set.remove(this);
		}
	}

	private static void closeAll()
	{
		for(Object file : fileopen_set.toArray())
		{
			try
			{
				((CachedFileOutputStream)file).close();
			}
			catch(IOException e)
			{
			}
		}
	}

	public static boolean removeOtherFiles(File file) // 返回文件或目录是否已经不存在
	{
		closeAll();

		if(!file.exists())
			return true;

		if(file.isDirectory())
		{
			boolean empty_dir = true;
			for(File f : file.listFiles())
				empty_dir &= removeOtherFiles(f);
			if(!empty_dir)
				return false;
		}
		else if(filename_set.contains(file.getAbsolutePath().toLowerCase()))
			return false;

		System.out.println("xgen: delete file: " + file);
		return file.delete();
	}
}
