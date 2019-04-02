package com.alanma.doraemon.utils.websocket;

public final class WebSocketEcho {
	//TODO
//public final class WebSocketEcho implements WebSocketListener {
//	private final ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
//
//	private void run() throws IOException {
//		OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
//		// wss://onli-quotation.btcdo.com/v1/market/?EIO=3&transport=websocket
//		Request request = new Request.Builder()
//				.url("wss://onli-quotation.btcdo.com/v1/market/?EIO=3&transport=websocket").build();
//		WebSocketCall.create(client, request).enqueue(this);

		// Trigger shutdown of the dispatcher's executor so this process can exit
		// cleanly.
		// client.dispatcher().executorService().shutdown();
//	}

//	@Override
//	public void onOpen(final WebSocket webSocket, Response response) {
//		writeExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					// ["subscribe",{"symbol":"BDB_ETH"}]
//					String[] reqContent = new String[2];
//					reqContent[0] = "subscribe";
//					reqContent[1] = JSONObject.toJSONString("{\"symbol\":\"BDB_ETH\"}");
////					System.out.println("~~~:"+JSONObject.toJSONStrin(reqContent));
//					webSocket.sendMessage(RequestBody.create(TEXT, "[\"subscribe\",{\"symbol\":\"BDB_ETH\"}]"));
//				} catch (IOException e) {
//					System.err.println("Unable to send messages: " + e.getMessage());
//				}
//			}
//		});
//	}

//	@Override
//	public void onMessage(ResponseBody message) throws IOException {
//		System.out.println("=====:" + message.contentType());
//		if (message.contentType() == TEXT) {
//			System.out.println("MESSAGE: " + message.string());
//		} else {
//			System.out.println("MESSAGE: " + message.source().readByteString().hex());
//		}
//		// message.close();
//	}
//
//	@Override
//	public void onPong(Buffer payload) {
//		System.out.println("PONG: " + payload.readUtf8());
//	}
//
//	@Override
//	public void onClose(int code, String reason) {
//		System.out.println("CLOSE: " + code + " " + reason);
//		writeExecutor.shutdown();
//	}
//
//	@Override
//	public void onFailure(IOException e, Response response) {
//		e.printStackTrace();
//		writeExecutor.shutdown();
//	}
//
//	public static void main(String... args) throws IOException {
//		new WebSocketEcho().run();
//	}

}
