package com.mxw.doraemon.netty.async.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpClientHandlerBytes extends SimpleChannelInboundHandler<byte[]> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

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