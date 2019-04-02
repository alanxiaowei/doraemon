package com.alanma.doraemon.utils.websocket;

import io.socket.emitter.Emitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Socket;

public class SocketIOExample {
	private Socket socket;

	void init() {
		Socket.Options opts = new Socket.Options();
		opts.hostname = "wss://onli-quotation.btcdo.com/v1/market/?EIO=3&transport=websocket";
		socket = new Socket(opts);
		socket.on(Socket.EVENT_OPEN, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				socket.on(Socket.EVENT_UPGRADE, new Emitter.Listener() {
					@Override
					public void call(Object... args) {
						socket.emit("subscribe", "{symbol: 'BDB_ETH'}");
						socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
							@Override
							public void call(Object... args) {
								System.out.println("~~~~~~~~:" + args[0]);
							}
						});
					}
				});
			}
		});
		socket.open();
	}

}