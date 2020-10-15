package com.revature.tier1;



public class NumberSumLength {
	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role;
	
	
	
	public NumberSumLength() {
		super();

	}

	public NumberSumLength(String firstName, String lastName, String username, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	

	public NumberSumLength(int id, String firstName, String lastName, String username, String password, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public static boolean checkNumberPowerLength(long num) {
		
		this.num = num;
		return false;
	}
}
