package com.example.model;

public class UserRole 
{
	
	private int id;
	private String role;
	
	public UserRole() {
		// TODO Auto-generated constructor stub
	}

	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	
	
	public UserRole(String role) {
		super();
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + role + "]";
	}
	
	

}
