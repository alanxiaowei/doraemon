package com.alanma.doraemon.utils.file.giantfile.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 对文件建立 java.nio.channels.FileChannel ，每次调用 read() 方法时会先将文件数据读取到分配的长度为
 * arraySize 的 java.nio.ByteBuffer 中，再从中将已经读取到的文件数据转化到 array
 * 中。这种利用了NIO中的通道的方法，比传统的字节流读取文件是要快一些。
 */
public class ChannelFileReader {
	private FileInputStream fileIn;
	private ByteBuffer byteBuf;
	private long fileLength;
	private int arraySize;
	private byte[] array;

	public ChannelFileReader(String fileName, int arraySize) throws IOException {
		this.fileIn = new FileInputStream(fileName);
		this.fileLength = fileIn.getChannel().size();
		this.arraySize = arraySize;
		this.byteBuf = ByteBuffer.allocate(arraySize);
	}

	public int read() throws IOException {
		FileChannel fileChannel = fileIn.getChannel();
		int bytes = fileChannel.read(byteBuf);// 读取到ByteBuffer中
		if (bytes != -1) {
			array = new byte[bytes];// 字节数组长度为已读取长度
			byteBuf.flip();
			byteBuf.get(array);// 从ByteBuffer中得到字节数组
			byteBuf.clear();
			return bytes;
		}
		return -1;
	}

	public void close() throws IOException {
		fileIn.close();
		array = null;
	}

	public byte[] getArray() {
		return array;
	}

	public long getFileLength() {
		return fileLength;
	}

	public static void main(String[] args) throws IOException {
		ChannelFileReader reader = new ChannelFileReader("/home/zfh/movie.mkv", 65536);
		long start = System.nanoTime();
		while (reader.read() != -1)
			;
		long end = System.nanoTime();
		reader.close();
		System.out.println("ChannelFileReader: " + (end - start));
	}
}
