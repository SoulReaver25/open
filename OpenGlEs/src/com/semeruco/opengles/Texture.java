package com.semeruco.opengles;

import static android.opengl.GLES20.*;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture {

	private final String TAG = getClass().getName();
	public int textureId;
	
	public Texture(Context context, int rsc)
	{
		this(context, rsc, GL_LINEAR);
	}

	public Texture(Context context, int rsc, int filter)
	{
		int[] textures = new int[1];
		glGenTextures(1, textures, 0);
		textureId = textures[0];
		textures = null;
		
		glBindTexture(GL_TEXTURE_2D, textureId);
		InputStream stream = context.getResources().openRawResource(rsc);
		Bitmap bitmap;
		try
		{
			bitmap = BitmapFactory.decodeStream(stream);
		}
		finally
		{
			try {
				stream.close();
			} catch (IOException e) {
				// 
				Log.e(TAG, "Unable to close stream");
			}
		}
		glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();
		
	}
	
}
