package com.semeruco.opengles.program;

import java.util.ArrayList;

import com.semeruco.opengles.util.Logger;

import android.content.Context;
import static android.opengl.GLES20.*;

public class VertexShader extends GLShader{

	public VertexShader ( Context context , int vertex )
	{
			super(context, vertex);
			id = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(id, source);
			glCompileShader(id);
			int[] in = new int[1];
			glGetShaderiv(id, GL_COMPILE_STATUS, in, 0);
			String info = glGetShaderInfoLog(id);
			Logger.w("VERTEXSHADER", "INFO LOG " + info + " " + in[0]  );
	}

}
