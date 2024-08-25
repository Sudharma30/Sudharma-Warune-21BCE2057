package com.hitwicket.game.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.hitwicket.game.game.GameBoard;

import java.util.HashMap;
import java.util.Map;

public class GameWebSocketHandler extends TextWebSocketHandler {

    private GameBoard gameBoard = new GameBoard();
    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String[] parts = message.getPayload().split(":");
        String command = parts[0];

        if ("MOVE".equalsIgnoreCase(command)) {
            String characterName = parts[1];
            int newX = Integer.parseInt(parts[2]);
            int newY = Integer.parseInt(parts[3]);

            boolean success = gameBoard.moveCharacters(characterName, newX, newY);
            String response = success ? "MOVE_OK" : "INVALID_MOVE";
            session.sendMessage(new TextMessage(response));
            
            broadcastGameState();
        } else if ("JOIN".equalsIgnoreCase(command)) {
            sessions.put(session.getId(), session);
            session.sendMessage(new TextMessage("WELCOME"));
            broadcastGameState();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Add session to sessions map when a new connection is established
        sessions.put(session.getId(), session);
        session.sendMessage(new TextMessage("CONNECTED"));
        broadcastGameState();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove session from sessions map when a connection is closed
        sessions.remove(session.getId());
        broadcastGameState();
    }

    private void broadcastGameState() throws Exception {
        String gameState = gameBoard.getGameState();
        for (WebSocketSession session : sessions.values()) {
            session.sendMessage(new TextMessage(gameState));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Handle errors and close the session if necessary
        session.close(CloseStatus.SERVER_ERROR);
        sessions.remove(session.getId());
    }
}
