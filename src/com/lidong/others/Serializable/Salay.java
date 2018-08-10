package com.lidong.others.Serializable;

import java.io.Serializable;

public class Salay implements Serializable{
	private static final long serialVersionUID = 24567L;

	private Double base;
	private Double bounse;

	// 编写构造器
	public Salay(Double base, Double bounse) {
		this.base = base;
		this.bounse = bounse;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double base) {
		this.base = base;
	}

	public Double getBounse() {
		return bounse;
	}

	public void setBounse(Double bounse) {
		this.bounse = bounse;
	}
}
