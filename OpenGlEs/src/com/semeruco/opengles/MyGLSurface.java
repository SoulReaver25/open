package com.semeruco.opengles;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyGLSurface extends GLSurfaceView {

	private MyRenderer mRenderer;
	public static float mX = 0;
	public static float mY = 0;
	public static float mZ = 0;

	public MyGLSurface ( Context context )
	{
		super(context);
		setEGLContextClientVersion(2);
		mRenderer = new MyRenderer(context);
		final MyListener listener = new MyListener();
		final SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		setRenderer(mRenderer);
		setRenderMode(RENDERMODE_WHEN_DIRTY);
		getHolder().addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed ( SurfaceHolder holder ) {
				//
			//	sm.unregisterListener(listener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			}

			@Override
			public void surfaceCreated ( SurfaceHolder holder ) {
				//
				//sm.registerListener(listener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
			}

			@Override
			public void surfaceChanged ( SurfaceHolder holder , int format , int width , int height ) {
				//

			}
		});

	}

	private static class MyListener implements SensorEventListener {

		@Override
		public void onSensorChanged ( SensorEvent event ) {
			//
			if ( event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE )
				return;
			
			switch (event.sensor.getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					float[] values = event.values;
					mX = values[0];
					mY = values[1];
					mZ = values[2];
					//Log.d("SENSORS", "x " + mX + " y " + mY + " z " + mZ);
					break;

				default:
					break;
			}
		}

		@Override
		public void onAccuracyChanged ( Sensor sensor , int accuracy ) {
			//

		}

	}

}
