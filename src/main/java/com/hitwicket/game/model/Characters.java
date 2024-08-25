package com.hitwicket.game.model;

import com.hitwicket.game.game.GameBoard;

public abstract class Characters {
    protected String name;
    protected int x, y;

    public Characters(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract boolean move(int newX, int newY, GameBoard board);
}

