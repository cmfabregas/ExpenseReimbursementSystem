package com.example.model;

public class ReimbursementType 
{
	
	private int id;
	private String type;
	
	public ReimbursementType() 
	{
		// TODO Auto-generated constructor stub
	}

	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementType [id=" + id + ", type=" + type + "]";
	}
	
	
	
	

}
