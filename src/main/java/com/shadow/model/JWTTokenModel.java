package com.shadow.model;

public class JWTTokenModel {

	private String jwttoken;
	
	public JWTTokenModel(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
}
