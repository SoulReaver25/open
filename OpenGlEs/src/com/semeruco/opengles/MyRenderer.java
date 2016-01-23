package com.semeruco.opengles;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {

	private Context mContext;
	private Model mModel;
	
	public MyRenderer ( Context context )
	{
		mContext = context;
		
	}

	@Override
	public void onSurfaceCreated ( GL10 gl , EGLConfig config ) {
		// 
		
		GLES20.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		mModel = new Model(mContext);
	}



	@Override
	public void onSurfaceChanged ( GL10 gl , int width , int height ) {
		// 
		GLES20.glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame ( GL10 gl ) {
		// 
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		mModel.draw();
	}

}
