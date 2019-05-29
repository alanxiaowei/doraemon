package com.mxw.doraemon.netty.async.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TcpClientHandler extends ChannelInboundHandlerAdapter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof ByteBuf){
			ByteBuf buf = (ByteBuf)msg;
			byte[] dst = new byte[buf.capacity()];
			buf.readBytes(dst);
			logger.info("client接收到服务器返回的消息:"+new String(dst));
			ReferenceCountUtil.release(msg);
		}else{
			logger.warn("error object");
		}
		
	}

   
}