package com.revature.dao;

import java.util.Set;

public interface CrudRepository {
	
	public Object save(Object t);
	public Set<Object> findAll();
	public Object findById(int id);
	public boolean update(Object t);
	public boolean deleteById(int id);

}