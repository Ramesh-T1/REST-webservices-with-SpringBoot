package com.in28minutes.rest.webservices.versioning;

public class Name {
	private String firstName;
	private String lasName;
    
	public Name(String firstName, String lasName) {
		super();
		this.firstName = firstName;
		this.lasName = lasName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasName() {
		return lasName;
	}

	public void setLasName(String lasName) {
		this.lasName = lasName;
	}

	@Override
	public String toString() {
		return "Name [firstName=" + firstName + ", lasName=" + lasName + "]";
	}

}
