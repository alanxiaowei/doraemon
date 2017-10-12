package com.alanma.doraemon.utils.netty.nio.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Client端接收同步应答
 * 
 * @author AlanMa
 *
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<byte[]> {

	private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

	/**
	 *
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	protected synchronized void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
		logger.info("enter NettyClientHandler.channelRead0 ~~~");
		String charset = "UTF-8";// 字符集
		String body = new String(msg, charset);
		System.out.println("【~~~SERVER接收到消息~~~】:" + body);

		shutdown(ctx.channel());

	}

	private void shutdown(Channel socketChannel) {
		if (socketChannel != null) {
			socketChannel.close();
			socketChannel = null;
			logger.info("======本地[{}]TCP连接关闭======");
			System.out.println("======本地[{}]TCP连接关闭======");
		}else{
			System.out.println("======socketChannel is null======");
		}
	}

}
