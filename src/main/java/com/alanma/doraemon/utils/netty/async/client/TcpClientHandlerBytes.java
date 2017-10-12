package com.alanma.doraemon.utils.netty.async.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.alanma.doraemon.utils.netty.nio.socket.NettyClientBootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandlerBytes extends SimpleChannelInboundHandler<byte[]> {
	private static final Logger logger = Logger.getLogger(TcpClientHandlerBytes.class);

	// @Override
	// public void channelRead(ChannelHandlerContext ctx, Object msg) throws
	// Exception {
	// if (msg instanceof ByteBuf) {
	// ByteBuf buf = (ByteBuf) msg;
	// byte[] dst = new byte[buf.capacity()];
	// buf.readBytes(dst);
	// logger.info("client接收到服务器返回的消息:" + new String(dst));
	// ReferenceCountUtil.release(msg);
	// } else {
	// logger.warn("error object");
	// }
	//
	// }

	@Override
	protected synchronized void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
		System.out.println("enter NettyClientHandler.channelRead0 ~~~");
		String body = new String(msg, "UTF-8");
		System.out.println("【~~~SERVER接收到消息~~~】:" + body);
		shutdown(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private void shutdown(Channel socketChannel) {
		if (socketChannel != null) {
			socketChannel.close();
			socketChannel = null;
			System.out.println("===========本地[{}]TCP连接关闭==========");
		}
	}

}