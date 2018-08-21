package com.lidong.spring.pratice4;

public class City {
	private Integer id;
	private Integer pid;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public City() {
		super();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID:"+id+", PID:"+pid+", NAME:"+name;
	}
}
