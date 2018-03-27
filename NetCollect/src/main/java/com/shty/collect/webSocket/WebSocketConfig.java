package com.shty.collect.webSocket;

import java.net.URI;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class WebSocketConfig extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

		HttpSession httpSession = (HttpSession) request.getHttpSession();
		URI uri = request.getRequestURI();

		try {
			// sec.getUserProperties().put(HttpSession.class.getName(),
			// httpSession);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			sec.getUserProperties().put(URI.class.getName(), uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}