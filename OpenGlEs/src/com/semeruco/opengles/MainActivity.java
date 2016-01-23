package com.semeruco.opengles;

import com.semeruco.opengles.util.Logger;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

	private GLSurfaceView mSurfaceView;
	
	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		Logger.LOG = true;
		mSurfaceView = new MyGLSurface(this);
		setContentView(mSurfaceView);
	}

}
