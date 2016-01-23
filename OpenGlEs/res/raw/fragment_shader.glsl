precision mediump float;

varying vec4 exColor;
varying vec2 exCoord;

uniform sampler2D sampler;


void main()
{
	vec4 temColor =  texture2D(sampler, exCoord);
	gl_FragColor = temColor;
}