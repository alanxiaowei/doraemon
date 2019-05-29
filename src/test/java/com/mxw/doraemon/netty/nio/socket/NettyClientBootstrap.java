package com.mxw.doraemon.netty.nio.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket短连接客户端<br>
 * 建立连接发送消息成功后，关闭客户端
 * 
 * @author AlanMa
 *
 */
public class NettyClientBootstrap {

	private static final Logger log = LoggerFactory.getLogger(NettyClientBootstrap.class);
	private int port;
	private String host;
	private SocketChannel socketChannel;

	public NettyClientBootstrap(int port, String host) throws InterruptedException {
		this.port = port;
		this.host = host;
		start();
	}

	public void start() throws InterruptedException {
		System.out.println("========entered start()==========");
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);
		bootstrap.group(eventLoopGroup);
		bootstrap.remoteAddress(host, port);
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline().addLast(new ByteArrayDecoder());
				socketChannel.pipeline().addLast(new ByteArrayEncoder());
				socketChannel.pipeline().addLast(new NettyClientHandler());
				// socketChannel.pipeline().addLast("idleStateHandler",
				// new IdleStateHandler(2000, 2000, 2000,
				// TimeUnit.MILLISECONDS));
			}
		});
		ChannelFuture future = bootstrap.connect(host, port).sync();
		// ChannelFuture future = bootstrap.connect(host,
		// port).awaitUninterruptibly();
		System.out.println("========future.isSuccess():" + future.isSuccess());
		if (future.isSuccess()) {
			socketChannel = (SocketChannel) future.channel();
			InetSocketAddress localAddress = socketChannel.localAddress();
			System.out.println(
					"【localAddress】：" + localAddress.getAddress().getHostAddress() + ":" + localAddress.getPort());
			log.info("本地{}:{}-->{}:{} 连接成功",
					new Object[] { localAddress.getAddress().getHostAddress(), localAddress.getPort(), host, port });
		}
	}

	public void sendMessage(byte[] msg) throws InterruptedException {
		try {
			log.info("[sendMessage]:" + new String(msg, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		socketChannel.writeAndFlush(msg);
	}

	public static void main(String[] args) {

		try {
			long t0 = System.nanoTime();
			ExecutorService executors = Executors.newFixedThreadPool(30);
			for (int i = 0; i < 1; i++) {
				final int ii = i;
				executors.execute(new Runnable() {

					@Override
					public void run() {
						try {
							NettyClientBootstrap bootstrap = new NettyClientBootstrap(8999, "127.0.0.1");
							byte[] value = (ii + ",你好").getBytes();
							bootstrap.sendMessage(value);
						} catch (Exception e) {
							log.error("【send message to HangZhou Clearing Center failed！！！】", e);
							e.printStackTrace();
						}
					}
				});

			}
			long t1 = System.nanoTime();
			System.out.println("=========================【处理时间】：" + (t1 - t0) / 1000000.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
