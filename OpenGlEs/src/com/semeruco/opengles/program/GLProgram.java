package com.semeruco.opengles.program;

import java.util.HashMap;

import android.content.Context;
import static android.opengl.GLES20.*;

public class GLProgram {
	
	public int progamId;

	private HashMap<String, Integer> mLocations;
	
	public GLProgram(Context context, int vertex, int frag)
	{
		mLocations = new HashMap<String, Integer>();
		GLShader vShader = new VertexShader(context, vertex);
		GLShader fShader = new FragmentShader(context, frag);
		progamId = glCreateProgram();
		glAttachShader(progamId, vShader.getId());
		glAttachShader(progamId, fShader.getId());
		glLinkProgram(progamId);
		
		StringBuilder name = new StringBuilder();
		for ( int i = 0; i < vShader.getSize(); i++ ) {
			name.append(vShader.get(i).getName());
			mLocations.put(name.toString(), glGetAttribLocation(progamId, name.toString()) );
			name.setLength(0);
		}
		
		name.setLength(0);
//		for ( int i = 0; i < fShader.getSize(); i++ ) {
//			name.append(fShader.get(i).getName());
//			mLocations.put(name.toString(), glGetAttribLocation(progamId, name.toString()) );
//			name.setLength(0);
//		}
	}
	
	public void BindProgram()
	{
		glUseProgram(progamId);
	}
	
	public int getLocation(String name){
		return glGetAttribLocation(progamId, name);
	}
	

}
