package com.semeruco.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.content.Context;
import static android.opengl.GLES20.*;

import com.semeruco.opengles.program.GLProgram;
import com.semeruco.opengles.util.Logger;

public class Model {
	
	private GLProgram mProgram;
	private Texture mTexture;
	
	FloatBuffer vBuffer;
	ShortBuffer iBuffer;

	private int[] buffers;
	
	static float vertices[] = 
	{
		-0.750f, -0.750f, 0.0f, 1.0f, 0.20f, 0.0f, 01.0f, 0.0f, 1.0f,
		 0.750f, -0.750f, 0.0f, 1.0f, 0.20f, 0.0f, 01.0f, 1.0f, 1.0f,
		 0.75f,  0.750f, 0.0f, 1.0f, 0.20f, 0.0f, 01.0f, 1.0f, 0.0f,
		-0.750f,  0.750f, 0.0f, 1.0f, 0.20f, 0.0f, 0.50f, 0.0f, 0.0f
	};
	
	static short indices[] = 
		{
			0, 1, 2, 0, 2, 3
		};
	
	public Model(Context con)
	{
		mProgram = new GLProgram(con, R.raw.vertex_shader, R.raw.fragment_shader);
		mTexture = new Texture(con, R.raw.indice);
		buffers = new int[2];
		vBuffer = ByteBuffer.allocateDirect(vertices.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vBuffer.put(vertices);
		vBuffer.position(0);
		
		
		iBuffer = ByteBuffer.allocateDirect(indices.length * 2)
				.order(ByteOrder.nativeOrder())
				.asShortBuffer();
		iBuffer.put(indices);
		iBuffer.position(0);
		
		glGenBuffers(2, buffers, 0);
		glBindBuffer(GL_ARRAY_BUFFER, buffers[0]);
		glBufferData(GL_ARRAY_BUFFER, vertices.length * 4, vBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffers[1]);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.length * 2, iBuffer, GL_STATIC_DRAW);
		
		
	}
	
	public void draw()
	{
		mProgram.BindProgram();
		
		int positionId = mProgram.getLocation("iPosition");
		int colorId = mProgram.getLocation("iColor");
		
		int textAttribute = mProgram.getLocation("iCoord");
		int uSampleId = mProgram.getLocation("sampler");
		
		Logger.LOG = true;
		Logger.e("MODEL", "posId " + positionId + " colorId " + colorId + 
				" textAttribute "  + " Sampler " );
		
		if(mTexture != null)
		{
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, mTexture.textureId);
			glUniform1i(uSampleId, 0);
		}
		
		
		vBuffer.position(0);
		glBindBuffer(GL_ARRAY_BUFFER, buffers[0]);
		glEnableVertexAttribArray(positionId);
		glVertexAttribPointer(positionId, 3, GL_FLOAT, false, (3 + 4 + 2) * 4, 0);
		
		vBuffer.position(3);
		glEnableVertexAttribArray(colorId);
		glVertexAttribPointer(colorId, 4, GL_FLOAT, false, (3 + 4 + 2) * 4 , 0);
		
		vBuffer.position(7);
		glEnableVertexAttribArray(textAttribute);
		glVertexAttribPointer(textAttribute, 2, GL_FLOAT, false, (3 + 4 + 2) * 4 , 7*4);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffers[1]);
		glDrawElements(GL_TRIANGLES, 6 , GL_UNSIGNED_SHORT, 0);
		
		glDisableVertexAttribArray(positionId);
		glDisableVertexAttribArray(colorId);
		glDisableVertexAttribArray(textAttribute);
	}

}
