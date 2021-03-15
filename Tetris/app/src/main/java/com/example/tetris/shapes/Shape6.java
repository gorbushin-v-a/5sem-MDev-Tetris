package com.example.tetris.shapes;

import com.badlogic.androidgames.framework.math.Vector2;

class Shape6 extends Shape {
    Square[] a = new Square[4];
    int color;
    public Shape6(int color) {
        a[0] = new Square(new Vector2(6.5f, 22.5f), color, 6+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
        a[1] = new Square(new Vector2(7.5f, 22.5f), color, 7+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
        a[2] = new Square(new Vector2(7.5f, 23.5f), color, 7+World.PLUS_WIDTH, 23-World.MINUS_HEIGHT);
        a[3] = new Square(new Vector2(8.5f, 22.5f), color, 8+World.PLUS_WIDTH, 22-World.MINUS_HEIGHT);
        this.color = color;
    }

    public Square[] getArray() {
        return a;
    }

    @Override
    int getType() {
        return 6;
    }
}
