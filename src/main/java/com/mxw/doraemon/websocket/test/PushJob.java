package com.mxw.doraemon.websocket.test;

import com.mxw.doraemon.utils.TimeUtils;
import com.mxw.doraemon.websocket.WsSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2021-01-05 17:11
 */
@Component
@Slf4j
public class PushJob {

	/**
	 *http://coolaf.com/tool/chattest
	 */
	// @Scheduled(initialDelay = 10_000,fixedDelay = 3 * 1000)
	public void pushMessage(){
		log.info("job execute~~~");
		try {
			WebSocketSession session= WsSessionManager.get("666666");
			session.sendMessage(new TextMessage("定时推送消息~"+ TimeUtils.getCurrentDateTimeStr()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
