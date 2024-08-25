package com.hitwicket.game.game;

import java.util.HashMap;
import java.util.Map;

import com.hitwicket.game.model.Characters;

public class GameBoard {
    private Characters[][] grid;
    private Map<String, Characters> charactersCharacterss;
    private boolean playerOneTurn;

    public GameBoard() {
        grid = new Characters[5][5];
        charactersCharacterss = new HashMap<>();
        playerOneTurn = true;
    }

    public boolean addCharacters(Characters charactersCharacters) {
        if (isPositionOccupied(charactersCharacters.getX(), charactersCharacters.getY())) {
            return false;
        }
        grid[charactersCharacters.getX()][charactersCharacters.getY()] = charactersCharacters;
        charactersCharacterss.put(charactersCharacters.getName(), charactersCharacters);
        return true;
    }

    public boolean moveCharacters(String name, int newX, int newY) {
        Characters charactersCharacters = charactersCharacterss.get(name);
        if (charactersCharacters != null && charactersCharacters.move(newX, newY, this)) {
            // Clear the old position
            grid[charactersCharacters.getX()][charactersCharacters.getY()] = null;
            // Update character's position
            charactersCharacters.setPosition(newX, newY);
            // Set the new position
            grid[newX][newY] = charactersCharacters;
            toggleTurn();
            return true;
        }
        return false;
    }

    public boolean isPositionOccupied(int x, int y) {
        return grid[x][y] != null;
    }

    private void toggleTurn() {
        playerOneTurn = !playerOneTurn;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public String getGameState() {
        StringBuilder state = new StringBuilder();
        for (Characters[] row : grid) {
            for (Characters c : row) {
                state.append(c != null ? c.getName() + " " : ". ");
            }
            state.append("\n");
        }
        return state.toString();
    }
}
