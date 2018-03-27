package com.shty.collect.webSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shty.collect.service.Linkedin_taskServiceI;

@Controller
@EnableWebSocket
@ServerEndpoint(value = "/socketTest", configurator = WebSocketConfig.class)
public class WebSocket {

	@Autowired
	Linkedin_taskServiceI linkedin_taskServiceI;

	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	public static final Set<WebSocket> connections = new CopyOnWriteArraySet<>();

	private final String nickname;
	public Session session;
	public EndpointConfig endpointConfig;

	public WebSocket() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) throws Exception {
		System.out.println("session.id : " + session.getId() + " : websocet start");
		this.session = session;
		this.endpointConfig = endpointConfig;
		connections.add(this);
		String message = String.format("* %s %s", nickname, "has joined.");
		// session.getBasicRemote().sendText(message);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		// 打印从客户端获取到的信息
		System.out.println(session.getId() + "从客户端接收到的信息: " + message);

		String mm = new ObjectMapper().writeValueAsString(linkedin_taskServiceI.getTask());

		// 向客户端第一次发送信息!
		session.getBasicRemote().sendText("你发来的信息是 ： " + mm);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws Exception {
		System.out.println(session.getId() + " websocet close");
		connections.remove(this);
		String message = String.format("* %s %s", nickname, "has disconnected.");
		System.err.println(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println(session.getId() + " websocet error : " + throwable.getMessage());
	}

	public static void broadcast(String msg) {
		for (WebSocket client : connections) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (Exception e) {
				System.out.println("Chat Error: Failed to send message to client");
				connections.remove(client);
				try {
					client.session.close();
				} catch (Exception e1) {
				}
				String message = String.format("* %s %s", client.nickname, "has been disconnected.");
				broadcast(message);
			}
		}
	}
}