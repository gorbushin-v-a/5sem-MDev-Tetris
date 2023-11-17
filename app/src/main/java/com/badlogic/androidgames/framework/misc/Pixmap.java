package com.badlogic.androidgames.framework.misc;

import com.badlogic.androidgames.framework.misc.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
