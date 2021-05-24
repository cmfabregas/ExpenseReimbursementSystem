package com.example.model;

import java.sql.Timestamp;

public class Reimbursement 
{
	
	private int id;
	private double reimb_amount;
	private Timestamp reimb_submitted;
	private Timestamp reimb_resolved;
	private String reimb_description;
	private String reimb_receipt;
	private User reimbAuthor;
	private User reimbResolver;
	private ReimbursementStatus reimbStatus;
	private ReimbursementType reimbType;
	//private 
	
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int id, double reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, String reimb_receipt, User reimbAuthor, User reimbResolver,
			ReimbursementStatus reimbStatus, ReimbursementType reimbType) {
		super();
		this.id = id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}
	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}
	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}
	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}
	public String getReimb_receipt() {
		return reimb_receipt;
	}
	public void setReimb_receipt(String reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}
	public User getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public User getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public ReimbursementStatus getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(ReimbursementStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public ReimbursementType getReimbType() {
		return reimbType;
	}
	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reimb_amount=" + reimb_amount + ", reimb_submitted=" + reimb_submitted
				+ ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description + ", reimb_receipt="
				+ reimb_receipt + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatus="
				+ reimbStatus + ", reimbType=" + reimbType + "]";
	}
	
	
	
	
}


