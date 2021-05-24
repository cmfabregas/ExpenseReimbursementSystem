package com.example.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<E> 
{

	//method for inserting a new record into a table
	void insert(E object) throws SQLException;
	
	//method for getting all records from a table
	List<E> queryAll() throws SQLException; 
	
	//method for getting the record with it's ID
	E queryById(int id) throws SQLException;
	
	//gets the next id for a new record
	int getId() throws SQLException;
	

	
}
