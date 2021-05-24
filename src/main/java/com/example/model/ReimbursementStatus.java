package com.example.model;

public class ReimbursementStatus 
{
	
	private int id;
	private String status;
	
	
	public ReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}


	public ReimbursementStatus() {
		//super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
	
	
	

}
