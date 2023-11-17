package com.example.tetris.shapes;

public abstract class Shape {
	int color;
	abstract Square[] getArray();
	abstract int getType();
	void update(float speedX, float speedY) {
		Square[] sq = getArray();
		for (int i = 0; i < sq.length; i++) {
			sq[i].position.add(speedX, speedY);
			sq[i].newFieldY += speedY;
			sq[i].newFieldX += speedX;

		}
	}
}