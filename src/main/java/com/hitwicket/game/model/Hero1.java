package com.hitwicket.game.model;

import com.hitwicket.game.game.GameBoard;

public class Hero1 extends Characters {

    public Hero1(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean move(int newX, int newY, GameBoard board) {
        if ((newX == x || newY == y) && Math.abs(newX - x) <= 2 && Math.abs(newY - y) <= 2 && !board.isPositionOccupied(newX, newY)) {
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }
}

