package com.alanma.doraemon.utils.netty.async.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class TcpClientBytes {
	private static final Logger logger = Logger.getLogger(TcpClientBytes.class);
	public static String HOST = "127.0.0.1";
	public static int PORT = 8999;

	public static Bootstrap bootstrap = getBootstrap();

	/**
	 * 初始化Bootstrap
	 * 
	 * @return
	 */
	public static final Bootstrap getBootstrap() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				// pipeline.addLast("frameDecoder", new
				// LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				// pipeline.addLast("frameEncoder", new
				// LengthFieldPrepender(4));
				pipeline.addLast(new ByteArrayDecoder());
				pipeline.addLast(new ByteArrayEncoder());
				pipeline.addLast(new TcpClientHandlerBytes());
			}
		});
		// b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public static final Channel getChannel(String host, int port) {
		Channel channel = null;
		try {
			channel = bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			logger.error(String.format("连接Server(IP[%s],PORT[%s])失败", host, port), e);
			return null;
		}
		return channel;
	}

	public static void sendMsg(Channel channel, Object msg) throws Exception {
		if (channel != null) {
			channel.writeAndFlush(msg);
		} else {
			logger.warn("消息发送失败,连接尚未建立!");
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			long t0 = System.nanoTime();
			// byte[] value = null;
			// Channel channel = null;
			ExecutorService executors = Executors.newFixedThreadPool(30);
			for (int i = 0; i < 500; i++) {
				final int ii = i;
				executors.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Channel channel = getChannel(HOST, PORT);
							byte[] value = (ii + ",你好").getBytes();
							ByteBufAllocator alloc = channel.alloc();
							ByteBuf buf = alloc.buffer(value.length);
							buf.writeBytes(value);
							TcpClientBytes.sendMsg(channel, buf);
						} catch (Exception e) {
							logger.error("【send message to HangZhou Clearing Center failed！！！】", e);
						}
					}
				});

			}
			long t1 = System.nanoTime();
			System.out.println("=========================【处理时间】：" + (t1 - t0) / 1000000.0);
			Thread.sleep(5000);
			// System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}