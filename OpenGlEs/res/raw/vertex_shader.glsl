attribute vec4 iPosition;
attribute vec4 iColor;
attribute vec2 iCoord;

varying vec4 exColor;
varying vec2 exCoord;

void main()
{
	gl_Position = iPosition;
	exColor = iColor;
	exCoord = iCoord;
}