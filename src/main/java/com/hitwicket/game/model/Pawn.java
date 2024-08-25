package com.hitwicket.game.model;

import com.hitwicket.game.game.GameBoard;

public class Pawn extends Characters {

    public Pawn(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean move(int newX, int newY, GameBoard board) {
        if (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1 && !board.isPositionOccupied(newX, newY)) {
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }
}

