package com.revature.dao;

import java.sql.Connection;
import java.util.Set;

import com.revature.config.ConnectionUtil;

public class UserRepository implements CrudRepository {

	@Override
	public Object save(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Object> findAll() {
		try(ConnectionUtil conn = ConnectionUtil.getInstance()){
			
		}
		return null;
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}