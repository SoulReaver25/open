package com.semeruco.opengles.program;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glShaderSource;

import java.util.ArrayList;

import com.semeruco.opengles.util.Logger;

import android.content.Context;

public class FragmentShader  extends GLShader{

	public FragmentShader ( Context context , int frag )
	{
		super(context, frag);
		id = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(id, source);
		glCompileShader(id);
		int[] in = new int[1];
		glGetShaderiv(id, GL_COMPILE_STATUS, in, 0);
		String info = glGetShaderInfoLog(id);
		Logger.w("FRAGMENTSHADER", "INFO LOG " + info + " " + in[0]);
	}

}
