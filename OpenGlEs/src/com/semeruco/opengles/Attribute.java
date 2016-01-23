package com.semeruco.opengles;

public class Attribute {

	public enum Type {
		Attribute, Varying, Uniform
	}

	public enum Var {
		Int, bool, Float, vec2, vec3, vec4, ivec2, ivec3, ivec4, bvec2, bvec3, bvec4, mat2, mat3, mat4, sample2D
	}

	private String name;
	private Type type;
	private Var var;

	public Attribute ( int type , String name , String var )
	{
		setName(name);
		setType(type);
		setVariable(var);
	}

	private void setVariable ( String var ) {
		//
		switch (var) {
			case "int":
				this.var = Var.Int;
				break;
			case "bool":
				this.var = Var.Float;
				break;
			case "float":
				this.var = Var.bool;
				break;
			case "vec2":
				this.var = Var.vec2;
				break;
			case "vec3":
				this.var = Var.vec3;
				break;
			case "vec4":
				this.var = Var.vec4;
				break;
			case "ivec2":
				this.var = Var.ivec2;
				break;
			case "ivec3":
				this.var = Var.ivec3;
				break;
			case "ivec4":
				this.var = Var.ivec4;
				break;
			case "bvec2":
				this.var = Var.bvec2;
				break;
			case "bvec3":
				this.var = Var.bvec3;
				break;
			case "bvec4":
				this.var = Var.bvec4;
				break;
			case "mat2":
				this.var = Var.mat2;
				break;
			case "mat3":
				this.var = Var.mat3;
				break;
			case "mat4":
				this.var = Var.mat4;
				break;
			case "sample2D":
				this.var = Var.sample2D;
				break;

			default:
				break;
		}
	}

	private void setType ( int type ) {
		//
		switch (type) {
			case 1:
				this.type = Type.Attribute;
				break;
			case 2:
				this.type = Type.Varying;
				break;

			default:
				this.type = Type.Uniform;
				break;
		}
	}

	private void setName ( String name ) {
		//
		if ( name.charAt(name.length() - 1) == ';' )
			this.name = name.substring(0, name.length() - 1);
		else
			this.name = name;
	}

	public String getName () {
		return name;
	}

	public Type getType () {
		return type;
	}

	public Var getVar () {
		return var;
	}

}
