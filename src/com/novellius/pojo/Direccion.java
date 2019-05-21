package com.novellius.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Direccion {

	private String calle;
	private String cp;
	
	@Autowired
	public void setCalle(@Value("Elm Streeth")String calle) {
		this.calle = calle;
	}
	@Autowired
	public void setCp(@Value("1")String cp) {
		this.cp = cp;
	}

	public Direccion() {
		super();
	}

	public Direccion(String calle, String cp) {
		super();
		this.calle = calle;
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cp=" + cp + "]";
	}
	
	
}
