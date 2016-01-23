package com.semeruco.opengles.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import static android.opengl.GLES20.*;

public abstract class GLShader {
	
	public static final int POSITION_OFFSET = 0;
	public static final int COLOR_OFFSET = 3;
	public static final int TEXTURE_OFFSET = 7;
	public static final int COLOR_BYTES_OFFSET = 3 * 4;
	public static final int TEXTURE_BYTES_OFFSET = 7 * 4;
	public static final int VERTEX_STRIDE = (3 + 4 + 2) * 4;
	
	private boolean lookingAttributes = true;
	private final String ATTRIB = "attribute";
	private final String UNIF = "uniform";
	private final String VARY = "varying";
	
	protected int id;
	protected ArrayList<Attribute> mAttributes;
	protected String source;
	
	public int getId(){return id;}
	public int getSize(){return mAttributes.size();}
	public Attribute get(int i){return mAttributes.get(i);}
	
	public GLShader (Context context, int res)
	{
		mAttributes = new ArrayList<Attribute>();
		 source = readFile(context, res);
	}
	
	protected String readFile( Context context, int res)
	{
		BufferedReader reader = new BufferedReader
				( new InputStreamReader
						(context.getResources().openRawResource(res)));

		
		String line;
		StringBuilder body= new StringBuilder();
		String[] tokens;
		
		try {
			while((line = reader.readLine()) != null)
			{
				body.append(line);
				body.append("\n");
				if(lookingAttributes)
				{
					tokens = line.trim().split(" ");
					searchAttributes(tokens);
				}
			}
		} catch (IOException e) {
			// 
			e.printStackTrace();
			return null;
		}finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}
		}
		
		return body.toString();
	}

	private void searchAttributes ( String[] tokens ) {
		// 
		switch (tokens[0]) {
			case ATTRIB:
				mAttributes.add(new Attribute(1, tokens[2], tokens[1]));
				break;
			case UNIF:
				mAttributes.add(new Attribute(3, tokens[2], tokens[1]));
				break;
			case VARY:
				mAttributes.add(new Attribute(2, tokens[2], tokens[1]));
				break;
			default:
				break;
		}
		if(tokens[0].equalsIgnoreCase("void"))
			lookingAttributes = false;
	}

}
