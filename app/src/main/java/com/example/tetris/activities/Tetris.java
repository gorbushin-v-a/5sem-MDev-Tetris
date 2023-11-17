package com.example.tetris.activities;

import android.content.Context;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.misc.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;
import com.example.tetris.shapes.Assets;
import com.example.tetris.shapes.MainMenuScreen;
import com.example.tetris.database.Settings;

public class Tetris extends GLGame {
	boolean firstTimeCreate = false;

	@Override
	public Context getApplicationContext() {
		return super.getApplicationContext();
	}

	@Override
	public Screen getStartScreen() {

		return new MainMenuScreen(this,super.getApplicationContext());
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate == false) {
			Assets.load(this);
			Settings.load(getFileIOIn());
			firstTimeCreate = true;
			Settings.getHighScore(getFileIOIn());
		} else {
			Settings.save(getFileIOIn());
			Assets.reload();
			Settings.getHighScore(getFileIOIn());
		}
	}
}