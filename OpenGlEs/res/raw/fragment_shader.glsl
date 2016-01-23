precision mediump float;

varying vec4 exColor;
varying vec2 exCoord;

uniform sampler2D sampler;


void main()
{
	
	gl_FragColor = texture2D(sampler, exCoord);
}