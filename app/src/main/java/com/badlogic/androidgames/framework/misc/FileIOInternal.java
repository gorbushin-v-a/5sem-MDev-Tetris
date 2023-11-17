package com.badlogic.androidgames.framework.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIOInternal {


public	InputStream readFile(String fileName) throws IOException;

public	OutputStream writeFile(String fileName) throws Exception;
}
